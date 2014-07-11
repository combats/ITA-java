package com.softserveinc.ita.service;

import com.softserveinc.ita.controller.entity.DataTransferFile;
import com.softserveinc.ita.exception.JcrException;
import com.softserveinc.ita.imageprocessing.ImageProcessor;
import com.softserveinc.ita.jcr.JcrDataAccess;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ImageServiceImplMockitoTest extends BaseServiceTest {
    @Autowired
    @Mock
    private JcrDataAccess jcrDataAccess;

    @Autowired
    @Mock
    private ImageProcessor imageProcessor;

    @Autowired
    @InjectMocks
    private ImageService imageService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPostImageAndExpectedIsOk() throws IOException, JcrException {
        String nodeName = "130";

        String fileNameFromResources = "input-1024x768.jpg";
        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        byte[] input = IOUtils.toByteArray(is);
        DataTransferFile dataTransferFile = new DataTransferFile(nodeName, "input-1024x768.jpg","image/jpeg", input);

        when(jcrDataAccess.post(dataTransferFile)).thenReturn("File added successfully ");
        String responce = imageService.postImage(dataTransferFile);
        verify(jcrDataAccess, times(1)).post(dataTransferFile);
        assertEquals("File added successfully ", responce);

        IOUtils.closeQuietly(is);
    }

    @Test
    public void testGetImageAndExpectedIsOk() throws JcrException, IOException {
        String nodeName = "131";
        int height = 200;
        int width = 200;

        String fileNameFromResources = "input-1024x768.jpg";
        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        byte[] input = IOUtils.toByteArray(is);
        DataTransferFile dataTransferFile = new DataTransferFile(nodeName, "input-1024x768.jpg","image/jpeg", input);

            BufferedImage img = ImageIO.read(new ByteArrayInputStream(input));
            BufferedImage scaledImg = Scalr.resize(img, Scalr.Mode.AUTOMATIC, height, width);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(scaledImg, "jpg", baos);
            byte[] imageInByte = baos.toByteArray();
            baos.flush();
            baos.close();

        DataTransferFile scaledPic = new DataTransferFile(nodeName, "input-1024x768.jpg","image/jpeg", imageInByte);

        when(jcrDataAccess.get(nodeName)).thenReturn(dataTransferFile);
        when(imageProcessor.doScalr(dataTransferFile, dataTransferFile.getMimeType(), height, width)).thenReturn(scaledPic);

        DataTransferFile responseFile = imageService.getImage(nodeName, height, width);

        verify(jcrDataAccess, times(1)).get(nodeName);
        verify(imageProcessor, times(1)).doScalr(dataTransferFile, dataTransferFile.getMimeType(), height, width);
        assertEquals(scaledPic.getContent().length, responseFile.getContent().length);

        IOUtils.closeQuietly(is);
    }

    @Test
    public void testGetImageInOriginalSizeAndExpectedIsOk() throws JcrException, IOException {
        String nodeName = "131";

        String fileNameFromResources = "input-1024x768.jpg";
        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        byte[] input = IOUtils.toByteArray(is);
        DataTransferFile dataTransferFile = new DataTransferFile(nodeName, "input-1024x768.jpg","image/jpeg", input);

        when(jcrDataAccess.get(nodeName)).thenReturn(dataTransferFile);

        DataTransferFile responseFile = imageService.getImage(nodeName);

        verify(jcrDataAccess, times(1)).get(nodeName);
        assertEquals(dataTransferFile.getContent().length, responseFile.getContent().length);
        IOUtils.closeQuietly(is);
    }

    @Test(expected = JcrException.class)
    public void testGetNonExistentImageAndExpectedJcrException() throws JcrException, IOException {
        String nodeName = "Non-Existent-nodeName";
        int height = 200;
        int width = 200;

        when(jcrDataAccess.get(nodeName)).thenThrow(new JcrException());
        DataTransferFile responseFile = imageService.getImage(nodeName, height, width);
        verify(jcrDataAccess, times(1)).get(nodeName);
        assertNull(responseFile);
    }

    @Test
    public void testDeleteImageAndExpectedIsOk() throws JcrException, IOException {
        String nodeName = "132";

        String fileNameFromResources = "input-1024x768.jpg";
        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        byte[] input = IOUtils.toByteArray(is);
        DataTransferFile dataTransferFile = new DataTransferFile(nodeName, "input-1024x768.jpg","image/jpeg", input);

        when(jcrDataAccess.post(dataTransferFile)).thenReturn("File added successfully ");
        String response = imageService.postImage(dataTransferFile);
        verify(jcrDataAccess, times(1)).post(dataTransferFile);
        assertEquals("File added successfully ", response);

        IOUtils.closeQuietly(is);

        when(jcrDataAccess.delete(nodeName)).thenReturn("File successfully deleted");
        String delStatus = imageService.deleteImage(nodeName);
        verify(jcrDataAccess, times(1)).delete(nodeName);
        assertEquals("File successfully deleted", delStatus);
    }

    @Test(expected = JcrException.class)
    public void TestDeleteNonExistentImageAndExpectedJcrException() throws JcrException {
        String nodeName = "new-Non-Existent-nodeName";

        when(jcrDataAccess.delete(nodeName)).thenThrow(new JcrException());
        String delStatus = imageService.deleteImage(nodeName);
        verify(jcrDataAccess, times(1)).delete(nodeName);
        assertNull(delStatus);
    }

    @Test
    public void testPostImage64AndExpectedIsOk() throws IOException, JcrException {
        String nodeName = "133";

        String fileNameFromResources = "input-1024x768.jpg";
        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        byte[] input = IOUtils.toByteArray(is);
        String image64 = Base64.encodeBase64String(input);
        DataTransferFile dataTransferFile = new DataTransferFile(nodeName, nodeName,"image/jpeg", input);

        when(jcrDataAccess.post(dataTransferFile)).thenReturn("File added successfully ");

        String response = imageService.postImage64(nodeName, image64, "image/jpeg");

        verify(jcrDataAccess, times(1)).post(dataTransferFile);
        assertEquals("File added successfully ", response);

        IOUtils.closeQuietly(is);
    }

    @Test
    public void testGetImage64AndExpectedIsOk() throws IOException, JcrException {
        String nodeName = "133";
        int height = 200;
        int width = 200;

        String fileNameFromResources = "input-1024x768.jpg";
        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        byte[] input = IOUtils.toByteArray(is);
        DataTransferFile dataTransferFile = new DataTransferFile(nodeName, nodeName,"image/jpeg", input);

            BufferedImage img = ImageIO.read(new ByteArrayInputStream(input));
            BufferedImage scaledImg = Scalr.resize(img, Scalr.Mode.AUTOMATIC, height, width);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(scaledImg, "jpg", baos);
            byte[] imageInByte = baos.toByteArray();
            String image64 = Base64.encodeBase64String(imageInByte);
            baos.flush();
            baos.close();

        DataTransferFile scaledPic = new DataTransferFile(nodeName, nodeName,"image/jpeg", imageInByte);

        when(jcrDataAccess.get(nodeName)).thenReturn(dataTransferFile);
        when(imageProcessor.doScalr(dataTransferFile, dataTransferFile.getMimeType(), height, width)).thenReturn(scaledPic);

        String response64 = imageService.getImage64(nodeName, height, width);

        verify(jcrDataAccess, times(1)).get(nodeName);
        verify(imageProcessor, times(1)).doScalr(dataTransferFile, dataTransferFile.getMimeType(), height, width);
        assertEquals(image64, response64);

        IOUtils.closeQuietly(is);
    }

    @Test
    public void testGetImage64InOriginalSizeAndExpectedIsOk() throws IOException, JcrException {
        String nodeName = "133";

        String fileNameFromResources = "input-1024x768.jpg";
        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        byte[] input = IOUtils.toByteArray(is);
        String image64 = Base64.encodeBase64String(input);
        DataTransferFile dataTransferFile = new DataTransferFile(nodeName, nodeName,"image/jpeg", input);

        when(jcrDataAccess.get(nodeName)).thenReturn(dataTransferFile);

        String response64 = imageService.getImage64(nodeName);

        verify(jcrDataAccess, times(1)).get(nodeName);
        assertEquals(image64, response64);

        IOUtils.closeQuietly(is);
    }

    @Test(expected = JcrException.class)
    public void testGetImage64WithNonExistentNodeNameAndExpectedIsJcrException() throws JcrException, IOException {
        String nodeName = "Non-Existent-nodeName3";
        int height = 200;
        int width = 200;

        when(jcrDataAccess.get(nodeName)).thenThrow(new JcrException());
        String response64 = imageService.getImage64(nodeName, height, width);
        verify(jcrDataAccess, times(1)).get(nodeName);
        assertNull(response64);
    }
}
