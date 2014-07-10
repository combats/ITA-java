package com.softserveinc.ita.controller;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.tika.metadata.HttpHeaders;
import org.imgscalr.Scalr;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        String endpoint = "/repo/imgfile";
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
        String endpoint = "/repo/imgfile";

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
        String endpointPost = "/repo/imgfile";
        String endpointGet = "/repo/imgfile/height=200&width=200";
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


        mockMvc.perform(fileUpload(endpointPost)
                .file(multipartFile)
                .param("name", name))
                .andExpect(status().isOk());

        String name1 = "124";

        mockMvc.perform(get(endpointGet)
                .param("name", name1))
                .andDo(print())
                .andExpect(content().bytes(imageInByte))
                .andExpect(content().contentType("image/jpeg"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGETImageAndExpectedIsError() throws Exception {
        String endpointGet = "/repo/imgfile/height=200&width=200";
        String name2 = "125";
        mockMvc.perform(get(endpointGet)
                .param("name", name2))
                .andDo(print())
                .andExpect(content().string("Some jcr trouble in JcrJackrabbitDataAccessImpl: method get - such node doesn`t exist"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testDELETENodeAndExpectedIsOk() throws Exception {
        String endpoint = "/repo/imgfile";
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
        String endpoint = "/repo/imgfile";
        String name5 = "127";
        mockMvc.perform(delete(endpoint)
                .param("name", name5))
                .andDo(print())
                .andExpect(content().string("Some jcr trouble in JcrJackrabbitDataAccessImpl: method delete - such node doesn`t exist"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGETImageOriginalsizeAndExpectedIsOk() throws Exception {
        String endpointPost = "/repo/imgfile";
        String endpointGet = "/repo/imgfile";
        String fileNameFromResources = "input-1024x768.jpg";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "input-1024x768.jpg", "image/jpeg", is);
        String name = "128";

        byte[] imageInByte = IOUtils.toByteArray(this.getClass()
                .getResourceAsStream("/" + fileNameFromResources));

        mockMvc.perform(fileUpload(endpointPost)
                .file(multipartFile)
                .param("name", name))
                .andExpect(status().isOk());

        String name1 = "128";

        mockMvc.perform(get(endpointGet)
                .param("name", name1))
                .andDo(print())
                .andExpect(content().bytes(imageInByte))
                .andExpect(content().contentType("image/jpeg"))
                .andExpect(status().isOk());
    }

    @Test
    public void TestPostImage64AndExpectedIsOk() throws Exception {
        String fileNameFromResources = "input-1024x768.jpg";
        String endpointPost = "/repo/img/applicant/129";

        byte[] imageInByte = IOUtils.toByteArray(this.getClass()
                .getResourceAsStream("/" + fileNameFromResources));

        String imageInString = Base64.encodeBase64String(imageInByte);

        mockMvc.perform(post(endpointPost)
                .param("string64image", imageInString)
                .header("Content-Type", "image/jpeg"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetImage64AndExpectedIsOk() throws Exception {
        String fileNameFromResources = "input-1024x768.jpg";
        String endpointPost = "/repo/img/applicant/100";

        byte[] imageInByte = IOUtils.toByteArray(this.getClass()
                .getResourceAsStream("/" + fileNameFromResources));

        String imageInString = Base64.encodeBase64String(imageInByte);

        mockMvc.perform(post(endpointPost)
                .param("string64image", imageInString)
                .header("Content-Type", "image/jpeg"))
                .andDo(print())
                .andExpect(status().isOk());

        String endpointGet = "/repo/img/applicant/100/height=200&width=200";

        BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream("/" + fileNameFromResources));
        BufferedImage scaledImg = Scalr.resize(img, Scalr.Mode.AUTOMATIC, 200, 200);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(scaledImg, "jpg", baos);
        byte[] imageAfter = baos.toByteArray();
        baos.flush();
        baos.close();
        String image64After = Base64.encodeBase64String(imageAfter);


        mockMvc.perform(get(endpointGet)
                .param("string64image", imageInString)
                .header("Content-Type", "image/jpeg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(image64After));
    }

    @Test
    public void testGetImage64OriginalSizeAndExpectedIsOk() throws Exception {
        String fileNameFromResources = "input-imgscalr-Auto-200x200.jpg";
        String endpointPost = "/repo/img/applicant/101";

        byte[] imageInByte = IOUtils.toByteArray(this.getClass()
                .getResourceAsStream("/" + fileNameFromResources));

        String imageInString = Base64.encodeBase64String(imageInByte);

        mockMvc.perform(post(endpointPost)
                .param("string64image", imageInString)
                .header("Content-Type", "image/jpeg"))
                .andDo(print())
                .andExpect(status().isOk());

        String endpointGet = "/repo/img/applicant/101";

        mockMvc.perform(get(endpointGet)
                .param("string64image", imageInString)
                .header("Content-Type", "image/jpeg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(imageInString));
    }

    @Test
    public void testGetImage64AndExpectedIsJcrException() throws Exception {
        String endpointGet = "/repo/img/applicant/nonexistent/height=200&width=200";

        mockMvc.perform(get(endpointGet))
                .andDo(print())
                .andExpect(content().string("Some jcr trouble in JcrJackrabbitDataAccessImpl: method get - such node doesn`t exist"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGetImage64OriginalSizeAndExpectedIsJcrException() throws Exception {
        String endpointGet = "/repo/img/applicant/nonexistent2";

        mockMvc.perform(get(endpointGet))
                .andDo(print())
                .andExpect(content().string("Some jcr trouble in JcrJackrabbitDataAccessImpl: method get - such node doesn`t exist"))
                .andExpect(status().isInternalServerError());
    }
}
