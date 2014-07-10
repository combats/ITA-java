package com.softserveinc.ita.controller;

import org.imgscalr.Scalr;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class ImgControllerTest extends BaseMVCTest {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;
    @Before
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void testPOSTImageAndExpectedIsOk() throws Exception {
        String endpoint = "/upload/image";
        String fileNameFromResources = "input-1024x768.jpg";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "input-1024x768.jpg", "image/jpeg", is);
        String name = "123";

        mockMvc.perform(fileUpload(endpoint)
                .file(multipartFile)
                .param("name", name))
                .andDo(print())
                .andExpect(content().string("File added successfully " + name + "-image"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPOSTImageAndExpectedIsNotOk() throws Exception {
        String endpoint = "/upload/image";

        MockMultipartFile multipartFile = new MockMultipartFile("file", "input.txt", "text/plain", "Hello World".getBytes());
        String name = "123";

        mockMvc.perform(fileUpload(endpoint)
                .file(multipartFile)
                .param("name", name))
                .andExpect(content().string("You failed to upload " + name + "-image" + " because the file has incorrect type: " + multipartFile.getContentType()))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void testGETImageAndExpectedIsOk() throws Exception {
        String endpoint = "/upload/image";
        String fileNameFromResources = "input-1024x768.jpg";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "input-1024x768.jpg", "image/jpeg", is);
        String name = "124";


        BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream("/" + fileNameFromResources));
        BufferedImage scaledImg = Scalr.resize(img, Scalr.Mode.AUTOMATIC, 200, 200);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(scaledImg, "jpg", baos);
        byte[] imageInByte = baos.toByteArray();
        baos.flush();
        baos.close();


        mockMvc.perform(fileUpload(endpoint)
                .file(multipartFile)
                .param("name", name))
                .andExpect(status().isOk());

        String name1 = "124";

        mockMvc.perform(get(endpoint)
                .param("name", name1)
                .param("height", "200")
                .param("width", "200"))
                .andDo(print())
                .andExpect(content().bytes(imageInByte))
                .andExpect(content().contentType("image/jpeg"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGETImageAndExpectedIsError() throws Exception {
        String endpoint = "/upload/image";
        String name2 = "125";
        mockMvc.perform(get(endpoint)
                .param("name", name2)
                .param("height", "200")
                .param("width", "200"))
                .andDo(print())
                .andExpect(content().string("Some jcr trouble in JcrJackrabbitDataAccessImpl: method get - such node doesn`t exist"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testDELETENodeAndExpectedIsOk() throws Exception {
        String endpoint = "/upload/image";
        String fileNameFromResources = "input-1024x768.jpg";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "input-1024x768.jpg", "image/jpeg", is);
        String name = "126";

        mockMvc.perform(fileUpload(endpoint)
                .file(multipartFile)
                .param("name", name))
                .andExpect(status().isOk());

        String name3 = "126";

        mockMvc.perform(delete(endpoint)
                .param("name", name3))
                .andDo(print())
                .andExpect(content().string("You successfully deleted " + name3 + "-image"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDELETENodeAndExpectedIsError() throws Exception {
        String endpoint = "/upload/image";
        String name5 = "127";
        mockMvc.perform(delete(endpoint)
                .param("name", name5))
                .andDo(print())
                .andExpect(content().string("Some jcr trouble in JcrJackrabbitDataAccessImpl: method delete - such node doesn`t exist"))
                .andExpect(status().isInternalServerError());
    }
}
