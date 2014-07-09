package com.softserveinc.ita.jcr;

import com.softserveinc.ita.controller.entity.ImageFile;
import com.softserveinc.ita.exception.JcrException;
import com.softserveinc.ita.jcr.jackrabbit.JcrSessionCreator;
import org.apache.commons.io.IOUtils;
import org.apache.jackrabbit.JcrConstants;
import org.apache.jackrabbit.api.JackrabbitRepository;
import org.apache.jackrabbit.api.JackrabbitRepositoryFactory;
import org.apache.jackrabbit.core.RepositoryFactoryImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jcr.*;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;


public class JcrDataAccessTest extends BaseJcrTest{
    Session session;

    @Autowired
    private JcrSessionCreator jcrSession;

    @Autowired
    private JcrDataAccess jcrDataAccess;

    @Test
    public void testCreateANodeAndExpectedIsNoExceptions() throws JcrException, IOException {
        String response = null;
        String fileNameFromResources = "input-1024x768.jpg";
        String nodeName = "123";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        byte[] input = IOUtils.toByteArray(is);
        ImageFile imageFile = new ImageFile(nodeName, "input-1024x768.jpg","image/jpeg", input);

        response = jcrDataAccess.post(imageFile);
        assertEquals("File added successfully ", response);
        IOUtils.closeQuietly(is);
    }

    @Test
    public void testGetPostedImageAndExpectedIsOk() throws IOException, JcrException {
        String response = null;
        String fileNameFromResources = "input-1024x768.jpg";
        String nodeName = "124";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        byte[] input = IOUtils.toByteArray(is);
        ImageFile imageFile = new ImageFile(nodeName, "input-1024x768.jpg","image/jpeg", input);

        response = jcrDataAccess.post(imageFile);
        assertEquals("File added successfully ", response);

        ImageFile responseImage = jcrDataAccess.get(nodeName);

        assertEquals(imageFile.getNodeName(), responseImage.getNodeName());
        assertNotSame(imageFile.getOriginalFileName(), responseImage.getOriginalFileName());
        assertEquals(imageFile.getContent().length, responseImage.getContent().length);
        assertEquals(imageFile.getMimeType(), responseImage.getMimeType());
        IOUtils.closeQuietly(is);
    }

    @Test
    public void testPostImageAndExpectedDataOverridingInASameNode() throws JcrException, IOException {
        String response1 = null;
        String response2 = null;
        String fileNameFromResources1 = "input-1024x768.jpg";
        String fileNameFromResources2 = "time-square.png";
        String nodeName = "125";

        InputStream is1 = this.getClass().getResourceAsStream("/" + fileNameFromResources1);
        byte[] input1 = IOUtils.toByteArray(is1);
        ImageFile imageFile1 = new ImageFile(nodeName, "input-1024x768.jpg","image/jpeg", input1);

        response1 = jcrDataAccess.post(imageFile1);
        assertEquals("File added successfully ", response1);

        ImageFile responseImage1 = jcrDataAccess.get(nodeName);

        assertEquals(imageFile1.getNodeName(), responseImage1.getNodeName());
        assertNotSame(imageFile1.getOriginalFileName(), responseImage1.getOriginalFileName());
        assertEquals(imageFile1.getContent().length, responseImage1.getContent().length);
        assertEquals(imageFile1.getMimeType(), responseImage1.getMimeType());
        IOUtils.closeQuietly(is1);

        InputStream is2 = this.getClass().getResourceAsStream("/" + fileNameFromResources2);
        byte[] input2 = IOUtils.toByteArray(is2);
        ImageFile imageFile2 = new ImageFile(nodeName, "input-1024x768.jpg","image/png", input2);

        response2 = jcrDataAccess.post(imageFile2);
        assertEquals("File added successfully ", response2);

        ImageFile responseImage2 = jcrDataAccess.get(nodeName);

        assertEquals(imageFile2.getNodeName(), responseImage2.getNodeName());
        assertNotSame(imageFile2.getOriginalFileName(), responseImage2.getOriginalFileName());
        assertEquals(imageFile2.getContent().length, responseImage2.getContent().length);
        assertEquals(imageFile2.getMimeType(), responseImage2.getMimeType());
        IOUtils.closeQuietly(is2);
    }

    @Test(expected = JcrException.class)
    public void testGetNonExistentImageAndExpectedJcrException() throws JcrException {
        String nodeName = "126";

        ImageFile responseImage = jcrDataAccess.get(nodeName);
    }

    @Test
    public void testDeletePostedImageAndExpectedIsOk() throws JcrException, IOException {
        String response = null;
        String deleteResponse = null;
        String fileNameFromResources = "input-1024x768.jpg";
        String nodeName = "127";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        byte[] input = IOUtils.toByteArray(is);
        ImageFile imageFile = new ImageFile(nodeName, "input-1024x768.jpg","image/jpeg", input);

        response = jcrDataAccess.post(imageFile);
        assertEquals("File added successfully ", response);

        deleteResponse = jcrDataAccess.delete(nodeName);

        assertEquals("File successfully deleted", deleteResponse);
        IOUtils.closeQuietly(is);
    }

    @Test(expected = JcrException.class)
    public void testDeleteNonExistentImageAndExpectedJcrException() throws JcrException {
        String nodeName = "128";
        String deleteResponse = null;

        deleteResponse = jcrDataAccess.delete(nodeName);
    }
}
