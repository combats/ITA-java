package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.controller.entity.ImageFile;
import com.softserveinc.ita.exception.JcrException;
import com.softserveinc.ita.imageprocessing.ImageProcessor;
import com.softserveinc.ita.jcr.JcrDataAccess;
import com.softserveinc.ita.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ImageServiceImpl implements ImageService {
    private ImageFile tempImFile;
    private ImageFile respFile;

    @Autowired
    private ImageProcessor imageProcessor;

    @Autowired
    private JcrDataAccess jcrDataAccess;

    @Override
    public String postImage(ImageFile imageFile) throws JcrException {
        return jcrDataAccess.post(imageFile);
    }

    /**
     * We are using jcrDataAccess.post() because JCR combines PUT & POST into one POST operation,
     * overriding data if necessary
     * @param imageFile - Image file
     * @return - String with operation status
     * @throws JcrException
     */
    @Override
    public String putImage(ImageFile imageFile) throws JcrException {
        return jcrDataAccess.post(imageFile);
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
    public ImageFile getImage(String nodeName, int height, int width) throws JcrException, IOException {
        tempImFile = jcrDataAccess.get(nodeName);
        respFile = imageProcessor.doScalr(tempImFile, tempImFile.getMimeType(), height,width);
        return respFile;
    }

    @Override
    public String deleteImage(String nodeName) throws JcrException {
        return jcrDataAccess.delete(nodeName);
    }
}
