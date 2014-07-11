package com.softserveinc.ita.controller;

import com.softserveinc.ita.controller.entity.DataTransferFile;
import com.softserveinc.ita.exception.JcrException;
import com.softserveinc.ita.service.ImageService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping(value="/repo")
public class ImageController {

    @Autowired
    private ImageService imageService;

    /**
     * Needed only for the tests with Tomcat
     * @return jsp page with multipart file upload form
     */
    @RequestMapping(method=RequestMethod.GET)
    public  String provideUploadInfo() {
        return "imageprocessing";
    }

    /**
     * Receives an image and saves it in JCR repository
     * @param ID - ID of applicant
     * @param file - photo of applicant
     * @return - (JSON) String with operation status
     * @throws IOException
     * @throws JcrException
     */

    @RequestMapping(value = "/imgfile/{ID}", method= {RequestMethod.POST}, consumes = "multipart/form-data")
    public ResponseEntity<String> postImage(@PathVariable("ID") String ID,
                                            @RequestParam("file") MultipartFile file) throws IOException, JcrException {
        if (file.isEmpty()) {
            return new ResponseEntity<>("You failed to upload " +
                    ID + " because the file was empty.", HttpStatus.BAD_REQUEST);
        }

        String contentType = file.getContentType();
        if(!isSupportedFormat(contentType)) {
            return new ResponseEntity<>("You failed to upload " +
            ID + "-image" + " because the file has incorrect type: " + file.getContentType(), HttpStatus.BAD_REQUEST);
        }

        String applicantID = ID + "-image";
        DataTransferFile dataTransferFile = new DataTransferFile(applicantID, file.getOriginalFilename(),file.getContentType(), file.getBytes());
        String response = imageService.postImage(dataTransferFile);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    /**
//     * Receives an image and saves it in JCR repository
//     * @param ID - ID of applicant
//     * @param file - photo of applicant
//     * @return - (JSON) String with operation status
//     * @throws IOException
//     * @throws JcrException
//     */
//    @RequestMapping(value = "/imgfile", method = RequestMethod.PUT, consumes = "multipart/form-data")
//    public ResponseEntity<String> putImage(@RequestParam("name") String ID,
//                                                 @RequestParam("file") MultipartFile file) throws IOException, JcrException {
//        if (file.isEmpty()) {
//            return new ResponseEntity<>("You failed to upload " +
//                    ID + " because the file was empty.", HttpStatus.BAD_REQUEST);
//        }
//
//        String contentType = file.getContentType();
//        if(!isSupportedFormat(contentType)) {
//            return new ResponseEntity<>("You failed to upload " +
//            ID + "-image" + " because the file has incorrect type: " + file.getContentType(), HttpStatus.BAD_REQUEST);
//        }
//
//        String applicantID = ID + "-image";
//        ImageFile imageFile = new ImageFile(applicantID, file.getOriginalFilename(),file.getContentType(), file.getBytes());
//        String response = imageService.postImage(imageFile);
//        return new ResponseEntity<>(response + applicantID, HttpStatus.OK);
//    }

    private boolean isSupportedFormat(String format){
        return  format.equals("image/gif") ||
                format.equals("image/jpeg") ||
                format.equals("image/png");
    }

    /**
     * Returns binary representation of Image
     * @param ID - ID of applicant
     * @param height - height of requested image
     * @param width - width of requested image
     * @return - byte array image
     * @throws JcrException
     * @throws IOException
     */
    @RequestMapping(value = "/imgfile/{ID}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@RequestParam(value = "height", required = false) Integer height,
                                           @RequestParam(value = "width", required = false) Integer width,
                                           @PathVariable("ID") String ID) throws JcrException, IOException {

        DataTransferFile imageResponse;
        if (height != null && width != null) {
            imageResponse = imageService.getImage(ID + "-image", width, height);
        } else {
            imageResponse = imageService.getImage(ID + "-image");
        }
        final HttpHeaders headers = new HttpHeaders();
        String responseMediaType = imageResponse.getMimeType();
        if(isSupportedFormat(responseMediaType)){
            headers.setContentType(MediaType.parseMediaType(responseMediaType));
        }
        headers.setContentLength(imageResponse.getContent().length);
        return new ResponseEntity<>(imageResponse.getContent(), headers, HttpStatus.OK) ;
    }

    /**
     * Deletes image from repository
     * @param ID - ID of applicant
     * @return - (JSON) String with operation status
     * @throws JcrException
     */
    @RequestMapping(value = "/imgfile/{ID}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteImage(@PathVariable("ID") String ID) throws JcrException {
        String response = imageService.deleteImage(ID + "-image");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Adds Image encoded in Base64 String into repository
     * @param ID - ID of applicant
     * @param contentType - image type
     * @param string64image - encoded image
     * @return (JSON) String with operation status
     * @throws JcrException
     */
    @RequestMapping(value = "/img/applicant/{ID}", method = RequestMethod.POST)
    public ResponseEntity<String> postImage64(@PathVariable("ID") String ID,
                                              @RequestHeader("Content-Type") String contentType,
                                              @RequestParam("string64image") String string64image) throws JcrException {
        if(string64image == null) {
            return new ResponseEntity<>("You failed to upload " +
                    ID + " because the file was empty.", HttpStatus.BAD_REQUEST);
        }
        if(!isValidBase64String(string64image)) {
            return new ResponseEntity<>("You failed to upload " +
                    ID + " because the image is not valid Base64 string.", HttpStatus.BAD_REQUEST);
        }
        String applicantID = ID + "-image";
        String response = imageService.postImage64(applicantID, string64image, contentType);
        return new ResponseEntity<>(response + applicantID, HttpStatus.OK);
    }

    private boolean isValidBase64String(String image) {
        return Base64.isBase64(image);
    }

    /**
     * Returns Base64 String representation of Image in requested size
     * @param ID - ID of applicant
     * @param height - required height
     * @param width - width of requested image
     * @return
     * @throws JcrException
     * @throws IOException
     * TODO: check utf-8 in Base64 Strings!
     */
    @RequestMapping(value = "/img/applicant/{ID}", method = RequestMethod.GET)
    public ResponseEntity<String> getImage64(@PathVariable("ID") String ID,
                                             @RequestParam(value = "height", required = false) Integer height,
                                             @RequestParam(value = "width", required = false) Integer width) throws JcrException, IOException {
        String applicantID = ID + "-image";
        String responseInBase64;
        if(height != null && width != null) {
            responseInBase64 = imageService.getImage64(applicantID, width, height);
        } else {
            responseInBase64 = imageService.getImage64(applicantID);
        }
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN); // Not sure about this
        headers.setContentLength(responseInBase64.length());
        return new ResponseEntity<>(responseInBase64, headers, HttpStatus.OK);
    }

    /**
     * Custom exceptions handler
     * @param ex - exception
     * @return - (JSON) String with exception info
     */
    @ExceptionHandler(JcrException.class)
    public ResponseEntity<String> handleException(JcrException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exceptions handler in case of IOExceptions (witch could never be)
     * @param ex - exception
     * @return - (JSON) String with exception info
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleException(IOException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exceptions handler in case of unexpected Exceptions
     * @param ex - exception
     * @return - (JSON) String with exception info
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("Something really bad happened [" + ex.getMessage() + "]", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
