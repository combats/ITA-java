package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.controller.entity.ImageFile;
import com.softserveinc.ita.exception.JcrException;
import com.softserveinc.ita.imageprocessing.ImageProcessor;
import com.softserveinc.ita.jcr.JcrDataAccess;
import com.softserveinc.ita.service.ImageService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageProcessor imageProcessor;

    @Autowired
    private JcrDataAccess jcrDataAccess;

    /**
     * Executes POST and PUT
     * @param imageFile - image to save
     * @return - String with operation status
     * @throws JcrException
     */
    @Override
    public String postImage(ImageFile imageFile) throws JcrException {
        return jcrDataAccess.post(imageFile);
    }

    /**
     * Returns Image in original size
     * @param nodeName - name of image (Node name - applicantID)
     * @return - ImageFile with original size pic
     * @throws JcrException
     */
    @Override
    public ImageFile getImage(String nodeName) throws JcrException {
        ImageFile respFile = jcrDataAccess.get(nodeName);
        return respFile;
    }

    /**
     * Processing Image resize function
     * @param nodeName - name of image (Node name - applicantID)
     * @param height - height of requested pic
     * @param width - width of requested pic
     * @return - ImageFile with image-processed pic
     * @throws JcrException
     * @throws IOException
     */
    @Override
    public ImageFile getImage(String nodeName, int width, int height) throws JcrException, IOException {
        ImageFile tempImFile = jcrDataAccess.get(nodeName);
        ImageFile respFile = imageProcessor.doScalr(tempImFile, tempImFile.getMimeType(), width, height);
        return respFile;
    }


    @Override
    public String deleteImage(String nodeName) throws JcrException {
        return jcrDataAccess.delete(nodeName);
    }

    /**
     * Decoding Image and saves it in repository
     * @param nodeName - name of image (Node name - applicantID)
     * @param source - Base64 String with image
     * @param mime - real mime-type of image
     * @return - String with operation status
     * @throws JcrException
     */
    @Override
    public String postImage64(String nodeName, String source, String mime) throws JcrException {
        byte[] fromSource = Base64.decodeBase64(source);
        ImageFile tempFile = new ImageFile(nodeName, nodeName, mime, fromSource);

        return postImage(tempFile);
    }

    /**
     * Returns encoded image with original size
     * @param nodeName - name of image (Node name - applicantID)
     * @return - Base64 String with image
     * @throws JcrException
     */
    @Override
    public String getImage64(String nodeName) throws JcrException {
        ImageFile tempFile = getImage(nodeName);

        return Base64.encodeBase64String(tempFile.getContent());
    }

    /**
     * Encodes Image into Base64 String
     * @param nodeName - name of image (Node name - applicantID)
     * @param height - height of requested pic
     * @param width - width of requested pic
     * @return - Base64 String with Image
     * @throws JcrException
     * @throws IOException
     */
    @Override
    public String getImage64(String nodeName, int width, int height) throws JcrException, IOException {
        ImageFile tempFile = getImage(nodeName, width, height);

        return Base64.encodeBase64String(tempFile.getContent());
    }
}
