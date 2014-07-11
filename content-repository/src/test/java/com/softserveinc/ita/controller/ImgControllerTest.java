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
    private static final String IMAGE_SUFFIX = "-image";
    private static final String DOCUMENT_SUFFIX = "-document";
    private static final String APPLICANT_SUFFIX = "-applicant";
    private static final String USER_SUFFIX = "-user";

    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;
    @Before
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void testPOSTUserImageAndExpectedIsOk() throws Exception {
        String name = "testController100";
        String endpoint = "/repo/imgfile/user/" + name;
        String fileNameFromResources = "input-1024x768.jpg";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "input-1024x768.jpg", "image/jpeg", is);

        mockMvc.perform(fileUpload(endpoint)
                .file(multipartFile))
                .andExpect(content().string("File added successfully " + name + USER_SUFFIX + IMAGE_SUFFIX))
                .andExpect(status().isOk());
    }

    @Test
    public void testPOSTApplicantImageAndExpectedIsOk() throws Exception {
        String name = "testController100";
        String endpoint = "/repo/imgfile/applicant/" + name;
        String fileNameFromResources = "input-1024x768.jpg";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "input-1024x768.jpg", "image/jpeg", is);

        mockMvc.perform(fileUpload(endpoint)
                .file(multipartFile))
                .andExpect(content().string("File added successfully " + name + APPLICANT_SUFFIX + IMAGE_SUFFIX))
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
    public void testPOSTUserImageAndExpectedIsNotOk() throws Exception {
        String name = "testController101";
        String endpoint = "/repo/imgfile/user/" + name;

        MockMultipartFile multipartFile = new MockMultipartFile("file", "input.txt", "text/plain", "Hello World".getBytes());

        mockMvc.perform(fileUpload(endpoint)
                .file(multipartFile))
                .andExpect(content().string("You failed to upload " + name + IMAGE_SUFFIX + " because the file has incorrect type: " + multipartFile.getContentType()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testPOSTApplicantImageAndExpectedIsNotOk() throws Exception {
        String name = "testController101";
        String endpoint = "/repo/imgfile/applicant/" + name;

        MockMultipartFile multipartFile = new MockMultipartFile("file", "input.txt", "text/plain", "Hello World".getBytes());

        mockMvc.perform(fileUpload(endpoint)
                .file(multipartFile))
                .andExpect(content().string("You failed to upload " + name + IMAGE_SUFFIX + " because the file has incorrect type: " + multipartFile.getContentType()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testPOSTUFOImageAndExpectedIsNotOk() throws Exception {
        String name = "testController101";
        String endpoint = "/repo/imgfile/ufo/" + name;

        MockMultipartFile multipartFile = new MockMultipartFile("file", "iWantToBelieve.png", "image/png", "Hello World".getBytes());

        mockMvc.perform(fileUpload(endpoint)
                .file(multipartFile))
                .andExpect(content().string("You failed to upload because of invalid path: must contain %applicant% or %user% in it."))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGETUserImageAndExpectedIsOk() throws Exception {
        String name = "testController102";
        String endpointPost = "/repo/imgfile/user/" + name;
        String endpointGet = "/repo/imgfile/user/" + name + "?height=200&width=200";
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
                .andExpect(status().isOk());

        mockMvc.perform(get(endpointGet))
                .andExpect(content().bytes(imageInByte))
                .andExpect(content().contentType("image/jpeg"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGETApplicantImageAndExpectedIsOk() throws Exception {
        String name = "testController102";
        String endpointPost = "/repo/imgfile/applicant/" + name;
        String endpointGet = "/repo/imgfile/applicant/" + name + "?height=200&width=200";
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
                .andExpect(status().isOk());

        mockMvc.perform(get(endpointGet))
                .andExpect(content().bytes(imageInByte))
                .andExpect(content().contentType("image/jpeg"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGETUFOImageAndExpectedIsOk() throws Exception {
        String name = "testController102";
        String endpointGet = "/repo/imgfile/ufo/" + name + "?height=200&width=200";

        mockMvc.perform(get(endpointGet))
                .andDo(print())
                .andExpect(content().string("You failed to upload because of invalid path: must contain %applicant% or %user% in it."))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGETApplicantImageAndExpectedIsError() throws Exception {
        String name = "testControllerNonExistent";
        String endpointGet = "/repo/imgfile/applicant/" + name + "?height=200&width=200";

        mockMvc.perform(get(endpointGet))
                .andExpect(content().string("Some jcr trouble in JcrJackrabbitDataAccessImpl: method get - such node doesn`t exist"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testDeleteApplicantImageAndExpectedIsOk() throws Exception {
        String name = "testController103";
        String endpoint = "/repo/imgfile/applicant/" + name;
        String fileNameFromResources = "input-1024x768.jpg";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "input-1024x768.jpg", "image/jpeg", is);

        mockMvc.perform(fileUpload(endpoint)
                .file(multipartFile))
                .andExpect(status().isOk());

        mockMvc.perform(delete(endpoint))
                .andExpect(content().string("File successfully deleted " + name + APPLICANT_SUFFIX + IMAGE_SUFFIX))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUserImageAndExpectedIsOk() throws Exception {
        String name = "testController103";
        String endpoint = "/repo/imgfile/user/" + name;
        String fileNameFromResources = "input-1024x768.jpg";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "input-1024x768.jpg", "image/jpeg", is);

        mockMvc.perform(fileUpload(endpoint)
                .file(multipartFile))
                .andExpect(status().isOk());

        mockMvc.perform(delete(endpoint))
                .andExpect(content().string("File successfully deleted " + name + USER_SUFFIX + IMAGE_SUFFIX))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUFOImageAndExpectedIsOk() throws Exception {
        String name = "testController103";
        String endpoint = "/repo/imgfile/ufo/" + name;

        mockMvc.perform(delete(endpoint))
                .andExpect(content().string("You failed to upload because of invalid path: must contain %applicant% or %user% in it."))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteApplicantImageAndExpectedIsError() throws Exception {
        String name = "testController104";
        String endpoint = "/repo/imgfile/applicant/" + name;

        mockMvc.perform(delete(endpoint))
                .andExpect(content().string("Some jcr trouble in JcrJackrabbitDataAccessImpl: method delete - such node doesn`t exist"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGETApplicantImageOriginalsizeAndExpectedIsOk() throws Exception {
        String name = "testController105";
        String endpointPost = "/repo/imgfile/applicant/" + name;
        String endpointGet = "/repo/imgfile/applicant/" + name;
        String fileNameFromResources = "input-1024x768.jpg";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "input-1024x768.jpg", "image/jpeg", is);

        byte[] imageInByte = IOUtils.toByteArray(this.getClass()
                .getResourceAsStream("/" + fileNameFromResources));

        mockMvc.perform(fileUpload(endpointPost)
                .file(multipartFile))
                .andExpect(status().isOk());

        mockMvc.perform(get(endpointGet))
                .andExpect(content().bytes(imageInByte))
                .andExpect(content().contentType("image/jpeg"))
                .andExpect(status().isOk());
    }

    @Test
    public void TestPostUserImage64AndExpectedIsOk() throws Exception {
        String name = "testController105";
        String fileNameFromResources = "input-1024x768.jpg";
        String endpointPost = "/repo/img/user/" + name;

        byte[] imageInByte = IOUtils.toByteArray(this.getClass()
                .getResourceAsStream("/" + fileNameFromResources));

        String imageInString = Base64.encodeBase64String(imageInByte);

        mockMvc.perform(post(endpointPost)
                .param("string64image", imageInString)
                .header("Content-Type", "image/jpeg"))
                .andExpect(status().isOk());
    }

    @Test
    public void TestPostApplicantImage64AndExpectedIsOk() throws Exception {
        String name = "testController105";
        String fileNameFromResources = "input-1024x768.jpg";
        String endpointPost = "/repo/img/applicant/" + name;

        byte[] imageInByte = IOUtils.toByteArray(this.getClass()
                .getResourceAsStream("/" + fileNameFromResources));

        String imageInString = Base64.encodeBase64String(imageInByte);

        mockMvc.perform(post(endpointPost)
                .param("string64image", imageInString)
                .header("Content-Type", "image/jpeg"))
                .andExpect(status().isOk());
    }

    @Test
    public void TestPostUfoImage64AndExpectedIsOk() throws Exception {
        String name = "testController105";
        String fileNameFromResources = "input-1024x768.jpg";
        String endpointPost = "/repo/img/ufo/" + name;

        byte[] imageInByte = IOUtils.toByteArray(this.getClass()
                .getResourceAsStream("/" + fileNameFromResources));

        String imageInString = Base64.encodeBase64String(imageInByte);

        mockMvc.perform(post(endpointPost)
                .param("string64image", imageInString)
                .header("Content-Type", "image/jpeg"))
                .andExpect(content().string("You failed to upload because of invalid path: must contain %applicant% or %user% in it."))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetApplicantImage64AndExpectedIsOk() throws Exception {
        String name = "testController106";
        String fileNameFromResources = "input-1024x768.jpg";
        String endpointPost = "/repo/img/applicant/" + name;
        String endpointGet = "/repo/img/applicant/" + name + "?height=200&width=200";

        byte[] imageInByte = IOUtils.toByteArray(this.getClass()
                .getResourceAsStream("/" + fileNameFromResources));

        String imageInString = Base64.encodeBase64String(imageInByte);

        mockMvc.perform(post(endpointPost)
                .param("string64image", imageInString)
                .header("Content-Type", "image/jpeg"))
                .andExpect(status().isOk());


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
                .andExpect(status().isOk())
                .andExpect(content().string(image64After));
    }

    @Test
    public void testGetUserImage64OriginalSizeAndExpectedIsOk() throws Exception {
        String name = "testController107";
        String fileNameFromResources = "time-square-imgscarl-resize-QUALITY-AUTO-10x10.png";
        String endpointPost = "/repo/img/user/" + name;
        String endpointGet = "/repo/img/user/" + name;

        byte[] imageInByte = IOUtils.toByteArray(this.getClass()
                .getResourceAsStream("/" + fileNameFromResources));

        String imageInString = Base64.encodeBase64String(imageInByte);

        mockMvc.perform(post(endpointPost)
                .param("string64image", imageInString)
                .header("Content-Type", "image/png"))
                .andExpect(status().isOk());


        mockMvc.perform(get(endpointGet))
                .andExpect(status().isOk())
                .andExpect(content().string(imageInString));
    }

    @Test
    public void testGetUfoImage64OriginalSizeAndExpectedIsOk() throws Exception {
        String name = "testController107";
        String fileNameFromResources = "time-square-imgscarl-resize-QUALITY-AUTO-10x10.png";
        String endpointPost = "/repo/img/user/" + name;
        String endpointGet = "/repo/img/ufo/" + name;

        byte[] imageInByte = IOUtils.toByteArray(this.getClass()
                .getResourceAsStream("/" + fileNameFromResources));

        String imageInString = Base64.encodeBase64String(imageInByte);

        mockMvc.perform(post(endpointPost)
                .param("string64image", imageInString)
                .header("Content-Type", "image/png"))
                .andExpect(status().isOk());


        mockMvc.perform(get(endpointGet))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("You failed to upload because of invalid path: must contain %applicant% or %user% in it."));
    }

    @Test
    public void testGetUserImage64AndExpectedIsJcrException() throws Exception {
        String name = "testControllerNonExistent";
        String endpointGet = "/repo/img/user/" + name + "?height=200&width=200";

        mockMvc.perform(get(endpointGet))
                .andExpect(content().string("Some jcr trouble in JcrJackrabbitDataAccessImpl: method get - such node doesn`t exist"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGetApplicantImage64OriginalSizeAndExpectedIsJcrException() throws Exception {
        String name = "testControllerNonExistent";
        String endpointGet = "/repo/img/applicant/" + name;

        mockMvc.perform(get(endpointGet))
                .andExpect(content().string("Some jcr trouble in JcrJackrabbitDataAccessImpl: method get - such node doesn`t exist"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testPOSTDocumentAndExpectedIsOk() throws Exception {
        String name = "testController108";
        String endpoint = "/repo/doc/" + name;
        String fileNameFromResources = "plain_text.txt";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        MockMultipartFile multipartFile = new MockMultipartFile("file", fileNameFromResources, "text/plain", is);

        mockMvc.perform(fileUpload(endpoint)
                .file(multipartFile))
                .andExpect(content().string("File added successfully " + name + APPLICANT_SUFFIX + DOCUMENT_SUFFIX))
                .andExpect(status().isOk());
    }

    @Test
    public void testPOSTDocumentAndExpectedIsError() throws Exception {
        String name = "testController109";
        String endpoint = "/repo/doc/" + name;
        String fileNameFromResources = "plain_text.txt";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        MockMultipartFile multipartFile = new MockMultipartFile("file", fileNameFromResources, "image/png", is);

        mockMvc.perform(fileUpload(endpoint)
                .file(multipartFile))
                .andExpect(content().string("You failed to upload " +
                        name + APPLICANT_SUFFIX + DOCUMENT_SUFFIX + " because the file has incorrect type: " + "image/png"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGETDocumentAndExpectedIsOk() throws Exception {
        String name = "testController110";
        String endpoint= "/repo/doc/" + name;
        String fileNameFromResources = "plain_text.txt";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        MockMultipartFile multipartFile = new MockMultipartFile("file", fileNameFromResources, "text/plain", is);

        byte[] docInByte = IOUtils.toByteArray(this.getClass().getResourceAsStream("/" + fileNameFromResources));

        mockMvc.perform(fileUpload(endpoint)
                .file(multipartFile))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get(endpoint))
                .andExpect(content().bytes(docInByte))
                .andExpect(content().contentType("text/plain"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetIDocumentAndExpectedIsJcrException() throws Exception {
        String name = "testControllerNonExistent";
        String endpointGet = "/repo/doc/" + name;

        mockMvc.perform(get(endpointGet))
                .andExpect(content().string("Some jcr trouble in JcrJackrabbitDataAccessImpl: method get - such node doesn`t exist"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testDeleteDocumentNodeAndExpectedIsOk() throws Exception {
        String name = "testController111";
        String endpoint = "/repo/doc/" + name;
        String fileNameFromResources = "plain_text.txt";

        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        MockMultipartFile multipartFile = new MockMultipartFile("file", fileNameFromResources, "text/plain", is);

        mockMvc.perform(fileUpload(endpoint)
                .file(multipartFile))
                .andExpect(status().isOk());

        mockMvc.perform(delete(endpoint))
                .andExpect(content().string("File successfully deleted " + name  + APPLICANT_SUFFIX + DOCUMENT_SUFFIX))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteDocumentAndExpectedIsError() throws Exception {
        String name = "testControllerNonExistent";
        String endpoint = "/repo/doc/" + name;

        mockMvc.perform(delete(endpoint))
                .andExpect(content().string("Some jcr trouble in JcrJackrabbitDataAccessImpl: method delete - such node doesn`t exist"))
                .andExpect(status().isInternalServerError());
    }
}
