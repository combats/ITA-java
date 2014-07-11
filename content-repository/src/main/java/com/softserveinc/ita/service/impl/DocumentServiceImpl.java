package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.controller.entity.DataTransferFile;
import com.softserveinc.ita.exception.JcrException;
import com.softserveinc.ita.jcr.JcrDataAccess;
import com.softserveinc.ita.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;

public class DocumentServiceImpl implements DocumentService{

    @Autowired
    private JcrDataAccess jcrDataAccess;

    /**
     * Executes POST and PUT
     * @param dataTransferFile - document to save
     * @return - String with operation status
     * @throws JcrException
     */
    @Override
    public String postDocument(DataTransferFile dataTransferFile) throws JcrException {
        return jcrDataAccess.post(dataTransferFile);
    }

    /**
     * Returns document
     * @param nodeName - name of document (Node name - applicantID)
     * @return - document of applicant
     * @throws JcrException
     */
    @Override
    public DataTransferFile getDocument(String nodeName) throws JcrException {
        return jcrDataAccess.get(nodeName);
    }

    @Override
    public String deleteDocument(String nodeName) throws JcrException {
        return jcrDataAccess.delete(nodeName);
    }
}
