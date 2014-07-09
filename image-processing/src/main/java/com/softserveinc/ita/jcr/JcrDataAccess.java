package com.softserveinc.ita.jcr;

import com.softserveinc.ita.controller.entity.ImageFile;
import com.softserveinc.ita.exception.JcrException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public interface JcrDataAccess {
    String post(ImageFile imageFile) throws JcrException;
    ImageFile get(String nodeName) throws JcrException;
    String delete(String nodeName) throws JcrException;
}
