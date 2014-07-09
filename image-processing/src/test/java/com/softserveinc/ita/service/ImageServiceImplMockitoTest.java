package com.softserveinc.ita.service;

import com.softserveinc.ita.controller.entity.ImageFile;
import com.softserveinc.ita.exception.JcrException;
import com.softserveinc.ita.imageprocessing.ImageProcessor;
import com.softserveinc.ita.jcr.JcrDataAccess;
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
        ImageFile imageFile = new ImageFile(nodeName, "input-1024x768.jpg","image/jpeg", input);

        when(jcrDataAccess.post(imageFile)).thenReturn("File added successfully ");
        String responce = imageService.postImage(imageFile);
        verify(jcrDataAccess, times(1)).post(imageFile);
        assertEquals("File added successfully ", responce);

        IOUtils.closeQuietly(is);
    }

    @Test
    public void testPutImageAndExpectedIsOk() throws JcrException, IOException {
        String nodeName = "131";

        String fileNameFromResources = "input-1024x768.jpg";
        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        byte[] input = IOUtils.toByteArray(is);
        ImageFile imageFile = new ImageFile(nodeName, "input-1024x768.jpg","image/jpeg", input);

        when(jcrDataAccess.post(imageFile)).thenReturn("File added successfully ");
        String responce = imageService.putImage(imageFile);
        verify(jcrDataAccess, times(1)).post(imageFile);
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
        ImageFile imageFile = new ImageFile(nodeName, "input-1024x768.jpg","image/jpeg", input);

            BufferedImage img = ImageIO.read(new ByteArrayInputStream(input));
            BufferedImage scaledImg = Scalr.resize(img, Scalr.Mode.AUTOMATIC, height, width);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(scaledImg, "jpg", baos);
            byte[] imageInByte = baos.toByteArray();
            baos.flush();
            baos.close();

        ImageFile scaledPic = new ImageFile(nodeName, "input-1024x768.jpg","image/jpeg", imageInByte);

        when(jcrDataAccess.get(nodeName)).thenReturn(imageFile);
        when(imageProcessor.doScalr(imageFile, imageFile.getMimeType(), height, width)).thenReturn(scaledPic);

        ImageFile responceFile = imageService.getImage(nodeName, height, width);

        verify(jcrDataAccess, times(1)).get(nodeName);
        verify(imageProcessor, times(1)).doScalr(imageFile, imageFile.getMimeType(), height, width);
        assertEquals(scaledPic.getContent().length, responceFile.getContent().length);

        IOUtils.closeQuietly(is);
    }

    @Test(expected = JcrException.class)
    public void testGetNonExistentImageAndExpectedJcrException() throws JcrException, IOException {
        String nodeName = "Non-Existent-nodeName";
        int height = 200;
        int width = 200;

        when(jcrDataAccess.get(nodeName)).thenThrow(new JcrException());
        ImageFile responceFile = imageService.getImage(nodeName, height, width);
        verify(jcrDataAccess, times(1)).get(nodeName);
        assertNull(responceFile);
    }

    @Test
    public void testDeleteImageAndExpectedIsOk() throws JcrException, IOException {
        String nodeName = "132";

        String fileNameFromResources = "input-1024x768.jpg";
        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        byte[] input = IOUtils.toByteArray(is);
        ImageFile imageFile = new ImageFile(nodeName, "input-1024x768.jpg","image/jpeg", input);

        when(jcrDataAccess.post(imageFile)).thenReturn("File added successfully ");
        String responce = imageService.postImage(imageFile);
        verify(jcrDataAccess, times(1)).post(imageFile);
        assertEquals("File added successfully ", responce);

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
}
