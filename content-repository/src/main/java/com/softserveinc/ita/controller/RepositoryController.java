package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Base64DataTransferFile;
import com.softserveinc.ita.entity.DataTransferFile;
import com.softserveinc.ita.exception.*;
import com.softserveinc.ita.service.DocumentService;
import com.softserveinc.ita.service.ImageService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping(value="/")
public class RepositoryController {
    String nodeName;
    private static final String APPLICANT_SUFFIX = "-applicant";
    private static final String USER_SUFFIX = "-user";

    @Autowired
    private ImageService imageService;

    @Autowired
    private DocumentService documentService;

    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(ModelMap modelMap) {
        modelMap.addAttribute("message", "Hello, hello!");
        return "index";
    }

    /**
     * Receives an image and saves it in JCR repository
     * @param ID - ID of applicant
     * @param file - photo of applicant
     * @return - (JSON) String with operation status
     * @throws java.io.IOException
     * @throws com.softserveinc.ita.exception.JcrException
     */

    @RequestMapping(value = "imgfile/{client}/{ID}", method= {RequestMethod.POST, RequestMethod.PUT},
                                                      consumes = "multipart/form-data")
    public ResponseEntity<String> postImage(@PathVariable("client") String client,
                                            @PathVariable("ID") String ID,
                                            @RequestParam("file") MultipartFile file) throws Exception {

        checkImageFile(file, ID);
        String requestedNODE = detectClient(ID, client);
        DataTransferFile dataTransferFile = new DataTransferFile(requestedNODE, file.getOriginalFilename(),
                                                                 file.getContentType(), file.getBytes());

        String response = imageService.postImage(dataTransferFile);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String detectClient(String ID, String client) throws RoutingException {
        if(!(client.equals("applicant") || client.equals("user"))) {
            throw new RoutingException("Caused by: " + client);
        }
        if(client.equals("applicant")) {
            return ID + APPLICANT_SUFFIX;
        }
        return ID + USER_SUFFIX;
    }

    private void checkImageFile(MultipartFile file, String ID) throws EmptyFileException,
                                                               UnsupportedFileContentTypeException {
        if (file.isEmpty()) {
            throw new EmptyFileException(ID);
        }
        String contentType = file.getContentType();
        if(!isSupportedFormatForImage(contentType)) {
            throw new UnsupportedFileContentTypeException(ID +". " + "Caused by: " + contentType);
        }
    }

    /**
     * Returns binary representation of Image
     * @param ID - ID of applicant
     * @param height - height of requested image
     * @param width - width of requested image
     * @return - byte array image
     * @throws com.softserveinc.ita.exception.JcrException
     * @throws java.io.IOException
     */
    @RequestMapping(value = "imgfile/{client}/{ID}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@RequestParam(value = "height", required = false) Integer height,
                                           @RequestParam(value = "width", required = false) Integer width,
                                           @PathVariable("ID") String ID,
                                           @PathVariable("client") String client) throws Exception {

        String requestedNODE = detectClient(ID, client);
        DataTransferFile imageResponse;
        if (height != null && width != null) {
            imageResponse = imageService.getImage(requestedNODE, width, height);
        } else {
            imageResponse = imageService.getImage(requestedNODE);
        }
        final HttpHeaders headers = new HttpHeaders();
        String responseMediaType = imageResponse.getMimeType();
        if(isSupportedFormatForImage(responseMediaType)) {
            headers.setContentType(MediaType.parseMediaType(responseMediaType));
        }
        headers.setContentLength(imageResponse.getContent().length);
        return new ResponseEntity<>(imageResponse.getContent(), headers, HttpStatus.OK) ;
    }

    private boolean isSupportedFormatForImage(String contentType) {
        return contentType.equals("image/gif") || contentType.equals("image/jpeg") || contentType.equals("image/png");
    }

    /**
     * Deletes image from repository
     * @param ID - ID of applicant
     * @return - (JSON) String with operation status
     * @throws com.softserveinc.ita.exception.JcrException
     */
    @RequestMapping(value = "imgfile/{client}/{ID}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteImage(@PathVariable("client") String client,
                                              @PathVariable("ID") String ID) throws Exception {

        String requestedNODE = detectClient(ID, client);
        String response = imageService.deleteImage(requestedNODE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Adds Image encoded in Base64 String into repository
     * @param ID - ID of applicant
     * @param contentType - image type
     * @param string64image - encoded image
     * @return (JSON) String with operation status
     * @throws com.softserveinc.ita.exception.JcrException
     */
    @RequestMapping(value = "img/{client}/{ID}", method = RequestMethod.POST)
    public ResponseEntity<String> postImage64(@PathVariable("ID") String ID,
                                              @PathVariable("client") String client,
                                              @RequestHeader("Content-Type") String contentType,
                                              @RequestParam("string64image") String string64image) throws Exception {

        checkBase64ImageFile(string64image, ID, contentType);
        String requestedNODE = detectClient(ID, client);
        String response = imageService.postImage64(requestedNODE, string64image, contentType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void checkBase64ImageFile(String string64image, String ID, String contentType) throws EmptyFileException,
            UnsupportedFileContentTypeException, Base64ValidationException {
        if (string64image == null) {
            throw new EmptyFileException(ID);
        }
        if(!isValidBase64String(string64image)) {
            throw new Base64ValidationException(ID);
        }
        if(!isSupportedFormatForImage(contentType)) {
            throw new UnsupportedFileContentTypeException(ID +". " + "Caused by: " + contentType);
        }
    }

    private boolean isValidBase64String(String image) {
        return Base64.isBase64(image);
    }

    /**
     * Returns Base64 String representation of Image in requested size
     * @param ID - ID of applicant
     * @param height - required height
     * @param width - width of requested image
     * @return - Image encoded in Base64 String
     * @throws com.softserveinc.ita.exception.JcrException
     * @throws java.io.IOException
     */
    @RequestMapping(value = "img/{client}/{ID}", method = RequestMethod.GET)
    public ResponseEntity<String> getImage64(@PathVariable("client") String client,
                                             @PathVariable("ID") String ID,
                                             @RequestParam(value = "height", required = false) Integer height,
                                             @RequestParam(value = "width", required = false) Integer width)
                                             throws Exception {

        String requestedNODE = detectClient(ID, client);
        Base64DataTransferFile responseInBase64;
        if(height != null && width != null) {
            responseInBase64 = imageService.getImage64(requestedNODE, width, height);
        } else {
            responseInBase64 = imageService.getImage64(requestedNODE);
        }
        final HttpHeaders headers = new HttpHeaders();

        String responseMediaType = responseInBase64.getContentType();
        if(isSupportedFormatForImage(responseMediaType)) {
            headers.setContentType(MediaType.parseMediaType(responseMediaType));
        }
        headers.setContentLength(responseInBase64.getContent().length());
        headers.set("Content-Transfer-Encoding", "base64");
        return new ResponseEntity<>(responseInBase64.getContent(), headers, HttpStatus.OK);
    }

    /**
     *
     * @param ID - ID of applicant
     * @param file - document with applicants CV
     * @return - (JSON) String with operation status
     * @throws java.io.IOException
     * @throws com.softserveinc.ita.exception.JcrException
     */
    @RequestMapping(value = "doc/{ID}", method= {RequestMethod.POST}, consumes = "multipart/form-data")
    public ResponseEntity<String> postDocument(@PathVariable("ID") String ID,
                                               @RequestParam("file") MultipartFile file) throws Exception {

        checkDocumentFile(file, ID);
        nodeName = ID + APPLICANT_SUFFIX;
        DataTransferFile dataTransferFile = new DataTransferFile(nodeName, file.getOriginalFilename(),
                                                                 file.getContentType(), file.getBytes());
        String response = documentService.postDocument(dataTransferFile);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void checkDocumentFile(MultipartFile file, String ID) throws EmptyFileException,
                                                                         UnsupportedFileContentTypeException {
        if (file.isEmpty()) {
            throw new EmptyFileException(ID);
        }
        String contentType = file.getContentType();
        if(!isSupportedFormatForDocument(contentType)) {
            throw new UnsupportedFileContentTypeException(ID +". " + "Caused by: " + contentType);
        }
    }

    private boolean isSupportedFormatForDocument(String contentType) {
        return contentType.equals("text/plain") ||
               contentType.equals("application/pdf") ||
               contentType.equals("application/vnd.oasis.opendocument.text") || // OpenDocument
               contentType.equals("application/msword") || // Microsoft Word files
               contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") || // MS Word 2007
               contentType.equals("application/rtf");
    }

    @RequestMapping(value = "doc/{ID}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getDocument(@PathVariable("ID") String ID) throws JcrException, IOException {

        DataTransferFile documentResponse;
        nodeName = ID + APPLICANT_SUFFIX;
        documentResponse = documentService.getDocument(nodeName);
        final HttpHeaders headers = new HttpHeaders();
        String responseMediaType = documentResponse.getMimeType();
        if(isSupportedFormatForDocument(responseMediaType)){
            headers.setContentType(MediaType.parseMediaType(responseMediaType));
        }
        headers.setContentLength(documentResponse.getContent().length);
        return new ResponseEntity<>(documentResponse.getContent(), headers, HttpStatus.OK) ;
    }

    @RequestMapping(value = "doc/{ID}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteDocument(@PathVariable("ID") String ID) throws JcrException {
        String response = documentService.deleteDocument(ID + APPLICANT_SUFFIX);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Custom exceptions handler
     * @param ex - exception
     * @return - (JSON) String with exception info
     */
    @ExceptionHandler(NotExistingNodeJcrException.class)
    public ResponseEntity<String> handleException(NotExistingNodeJcrException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JcrException.class)
    public ResponseEntity<String> handleException(JcrException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RoutingException.class)
    public ResponseEntity<String> handleException(RoutingException ex) {
        return new ResponseEntity<>("You failed to upload because of invalid path: must contain %applicant% " +
                                    "or %user% in it." + " " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyFileException.class)
    public ResponseEntity<String> handleException(EmptyFileException ex) {
        return new ResponseEntity<>("You failed to upload " +ex.getMessage() + " because the file was empty.",
                                    HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnsupportedFileContentTypeException.class)
    public ResponseEntity<String> handleException(UnsupportedFileContentTypeException ex) {
        return new ResponseEntity<>("You failed to upload because the file has incorrect type: " + ex.getMessage(),
                                    HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Base64ValidationException.class)
    public ResponseEntity<String> handleException(Base64ValidationException ex) {
        return new ResponseEntity<>("You failed to upload because the file was empty." + ex.getMessage(),
                                    HttpStatus.BAD_REQUEST);
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
        return new ResponseEntity<>("Something really bad happened [" + ex.getMessage() + "]",
                                    HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
