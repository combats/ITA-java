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
import java.util.HashMap;

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
        String endpoint = "/repo/imgfile/123";
        String fileNameFromResources = "input-1024x768.jpg";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "input-1024x768.jpg", "image/jpeg", is);

        mockMvc.perform(fileUpload(endpoint)
                .file(multipartFile))
                .andDo(print())
                .andExpect(content().string("File added successfully " + "123" + "-image"))
                .andExpect(status().isOk());
    }

//    @Test //TODO: always failing
//    public void testPUTImageAndExpectedIsOk() throws Exception {
//        String endpoint = "/repo/imgfile/163";
//        String fileNameFromResources = "input-1024x768.jpg";
//
//        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
//        MockMultipartFile multipartFile = new MockMultipartFile("file", "input-1024x768.jpg", "image/jpeg", is);
//        HashMap<String, String> contentTypeParams = new HashMap<>();
//        contentTypeParams.put("boundary", "265001916915724");
//        MediaType mediaType = new MediaType("multipart", "form-data", contentTypeParams);
//
//        mockMvc.perform(put(endpoint)
//                .content(multipartFile.getBytes())
//                .contentType(mediaType))
//                .andDo(print())
//                .andExpect(content().string("File added successfully " + "163" + "-image"))
//                .andExpect(status().isOk());
//    }

    @Test
    public void testPOSTImageAndExpectedIsNotOk() throws Exception {
        String endpoint = "/repo/imgfile/160";

        MockMultipartFile multipartFile = new MockMultipartFile("file", "input.txt", "text/plain", "Hello World".getBytes());
        String name = "160";

        mockMvc.perform(fileUpload(endpoint)
                .file(multipartFile))
                .andExpect(content().string("You failed to upload " + name + "-image" + " because the file has incorrect type: " + multipartFile.getContentType()))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void testGETImageAndExpectedIsOk() throws Exception {
        String endpointPost = "/repo/imgfile/124";
        String endpointGet = "/repo/imgfile/124?height=200&width=200";
        String fileNameFromResources = "input-1024x768.jpg";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "input-1024x768.jpg", "image/jpeg", is);

        BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream("/" + fileNameFromResources));
        BufferedImage scaledImg = Scalr.resize(img, Scalr.Mode.AUTOMATIC, 200, 200);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(scaledImg, "jpg", baos);
        byte[] imageInByte = baos.toByteArray();
        baos.flush();
        baos.close();


        mockMvc.perform(fileUpload(endpointPost)
                .file(multipartFile))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get(endpointGet))
                .andDo(print())
                .andExpect(content().bytes(imageInByte))
                .andExpect(content().contentType("image/jpeg"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGETImageAndExpectedIsError() throws Exception {
        String endpointGet = "/repo/imgfile/125?height=200&width=200";

        mockMvc.perform(get(endpointGet))
                .andDo(print())
                .andExpect(content().string("Some jcr trouble in JcrJackrabbitDataAccessImpl: method get - such node doesn`t exist"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testDELETENodeAndExpectedIsOk() throws Exception {
        String endpoint = "/repo/imgfile/126";
        String fileNameFromResources = "input-1024x768.jpg";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "input-1024x768.jpg", "image/jpeg", is);

        mockMvc.perform(fileUpload(endpoint)
                .file(multipartFile))
                .andExpect(status().isOk());

        mockMvc.perform(delete(endpoint))
                .andDo(print())
                .andExpect(content().string("File successfully deleted " + "126" + "-image"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDELETENodeAndExpectedIsError() throws Exception {
        String endpoint = "/repo/imgfile/127";

        mockMvc.perform(delete(endpoint))
                .andDo(print())
                .andExpect(content().string("Some jcr trouble in JcrJackrabbitDataAccessImpl: method delete - such node doesn`t exist"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGETImageOriginalsizeAndExpectedIsOk() throws Exception {
        String endpointPost = "/repo/imgfile/128";
        String endpointGet = "/repo/imgfile/128";
        String fileNameFromResources = "input-1024x768.jpg";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "input-1024x768.jpg", "image/jpeg", is);

        byte[] imageInByte = IOUtils.toByteArray(this.getClass()
                .getResourceAsStream("/" + fileNameFromResources));

        mockMvc.perform(fileUpload(endpointPost)
                .file(multipartFile))
                .andExpect(status().isOk());

        mockMvc.perform(get(endpointGet))
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

        String endpointGet = "/repo/img/applicant/100?height=200&width=200";

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
        String fileNameFromResources = "time-square-imgscarl-resize-QUALITY-AUTO-10x10.png";
        String endpointPost = "/repo/img/applicant/101";

        byte[] imageInByte = IOUtils.toByteArray(this.getClass()
                .getResourceAsStream("/" + fileNameFromResources));

        String imageInString = Base64.encodeBase64String(imageInByte);

        mockMvc.perform(post(endpointPost)
                .param("string64image", imageInString)
                .header("Content-Type", "image/png"))
                .andDo(print())
                .andExpect(status().isOk());

        String endpointGet = "/repo/img/applicant/101";

        mockMvc.perform(get(endpointGet))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(imageInString));
    }

    @Test
    public void testGetImage64AndExpectedIsJcrException() throws Exception {
        String endpointGet = "/repo/img/applicant/nonexistent?height=200&width=200";

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
