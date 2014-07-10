package com.softserveinc.ita.controller;

import com.softserveinc.ita.controller.entity.ImageFile;
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
    private String responce;
    private ImageFile imageFile;
    private String applicantID;
    private ImageFile imageResponce;

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

    @RequestMapping(value = "/imgfile", method= RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<String> postImage(@RequestParam("name") String ID,
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

        applicantID = ID + "-image";
        imageFile = new ImageFile(applicantID, file.getOriginalFilename(),file.getContentType(), file.getBytes());
        responce = imageService.postImage(imageFile);
        return new ResponseEntity<>(responce + applicantID, HttpStatus.OK);
    }

    /**
     * Receives an image and saves it in JCR repository
     * @param ID - ID of applicant
     * @param file - photo of applicant
     * @return - (JSON) String with operation status
     * @throws IOException
     * @throws JcrException
     */
    @RequestMapping(value = "/imgfile", method = RequestMethod.PUT, consumes = "multipart/form-data")
    public ResponseEntity<String> putImage(@RequestParam("name") String ID,
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

        applicantID = ID + "-image";
        imageFile = new ImageFile(applicantID, file.getOriginalFilename(),file.getContentType(), file.getBytes());
        responce = imageService.putImage(imageFile);
        return new ResponseEntity<>(responce + applicantID, HttpStatus.OK);
    }

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
    @RequestMapping(value = "/imgfile/height={value1}&width={value2}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable("value1") int height,
                                           @PathVariable("value2") int width,
                                           @RequestParam("name") String ID) throws JcrException, IOException {

        imageResponce = imageService.getImage(ID + "-image", width, height);
        final HttpHeaders headers = new HttpHeaders();
        String responseMediaType = imageResponce.getMimeType();
        if(isSupportedFormat(responseMediaType)){
            headers.setContentType(MediaType.parseMediaType(responseMediaType));
        }
        headers.setContentLength(imageResponce.getContent().length);
        return new ResponseEntity<>(imageResponce.getContent(), headers, HttpStatus.OK) ;
    }

    /**
     * Returns binary representation of Image in original size
     * @param ID - ID of applicant
     * @return - byte array image
     * @throws JcrException
     * @throws IOException
     */
    @RequestMapping(value = "/imgfile", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageOriginalSize(@RequestParam("name") String ID) throws JcrException, IOException {

        imageResponce = imageService.getImage(ID + "-image");
        final HttpHeaders headers = new HttpHeaders();
        String responseMediaType = imageResponce.getMimeType();
        if(isSupportedFormat(responseMediaType)){
            headers.setContentType(MediaType.parseMediaType(responseMediaType));
        }
        headers.setContentLength(imageResponce.getContent().length);
        return new ResponseEntity<>(imageResponce.getContent(), headers, HttpStatus.OK) ;
    }

    /**
     * Deletes image from repository
     * @param ID - ID of applicant
     * @return - (JSON) String with operation status
     * @throws JcrException
     */
    @RequestMapping(value = "/imgfile", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteImage(@RequestParam("name") String ID) throws JcrException {
        responce = imageService.deleteImage(ID + "-image");
        return new ResponseEntity<>("You successfully deleted " + ID + "-image", HttpStatus.OK);
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
        applicantID = ID + "-image";
        responce = imageService.postImage64(applicantID, string64image, contentType);
        return new ResponseEntity<>(responce + applicantID, HttpStatus.OK);
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
     */
    @RequestMapping(value = "/img/applicant/{ID}/height={value1}&width={value2}", method = RequestMethod.GET)
    public ResponseEntity<String> getImage64(@PathVariable("ID") String ID,
                                             @PathVariable("value1") int height,
                                             @PathVariable("value2") int width) throws JcrException, IOException {
        applicantID = ID + "-image";
        String responseInBase64 = imageService.getImage64(applicantID, width, height);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN); // Not sure about this
        headers.setContentLength(responseInBase64.length());
        return new ResponseEntity<>(responseInBase64, headers, HttpStatus.OK);
    }

    /**
     * Returns Base64 String representation of Image in original size
     * @param ID - ID of applicant
     * @return - Base64 encoded String
     * @throws JcrException
     * @throws IOException
     */
    @RequestMapping(value = "/img/applicant/{ID}", method = RequestMethod.GET)
    public ResponseEntity<String> getImage64OriginalSize(@PathVariable("ID") String ID) throws JcrException, IOException {
        applicantID = ID + "-image";
        String responseInBase64 = imageService.getImage64(applicantID);
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
