package com.softserveinc.ita.service;

import com.softserveinc.ita.controller.entity.ImageFile;
import com.softserveinc.ita.exception.JcrException;

import java.io.IOException;

public interface ImageService {
    String postImage(ImageFile imageFile) throws JcrException;
    String putImage(ImageFile imageFile) throws JcrException;
    ImageFile getImage(String nodeName) throws JcrException;
    ImageFile getImage(String nodeName, int height, int width) throws JcrException, IOException;
    String deleteImage(String nodeName) throws JcrException;
    String postImage64(String nodeName, String source, String mime) throws JcrException;
    String getImage64(String nodeName) throws JcrException;
    String getImage64(String nodeName, int height, int width) throws JcrException, IOException;
}
