package com.softserveinc.ita.service;

import com.softserveinc.ita.controller.entity.DataTransferFile;
import com.softserveinc.ita.exception.JcrException;
import com.softserveinc.ita.jcr.JcrDataAccess;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;
import java.io.InputStream;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class DocumentServiceImplMockitoTest extends BaseServiceTest {

    @Autowired
    @Mock
    private JcrDataAccess jcrDataAccess;

    @Autowired
    @InjectMocks
    private DocumentService documentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPostDocumentAndExpectedIsOk() throws IOException, JcrException {
        String nodeName = "testDocumentService100";

        String fileNameFromResources = "plain_text.txt";
        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        byte[] input = IOUtils.toByteArray(is);
        DataTransferFile dataTransferFile = new DataTransferFile(nodeName, "plain_text.txt", "text/plain", input);

        when(jcrDataAccess.post(dataTransferFile)).thenReturn("File added successfully ");
        String response = documentService.postDocument(dataTransferFile);
        verify(jcrDataAccess, times(1)).post(dataTransferFile);
        assertEquals("File added successfully ", response);

        IOUtils.closeQuietly(is);
    }

    @Test
    public void testGetDocumentAndExpectedIsOk() throws JcrException, IOException {
        String nodeName = "testDocumentService100";

        String fileNameFromResources = "plain_text.txt";
        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        byte[] input = IOUtils.toByteArray(is);
        DataTransferFile dataTransferFile = new DataTransferFile(nodeName, "plain_text.txt","text/plain", input);

        when(jcrDataAccess.get(nodeName)).thenReturn(dataTransferFile);

        DataTransferFile responseFile = documentService.getDocument(nodeName);

        verify(jcrDataAccess, times(1)).get(nodeName);
        assertEquals(dataTransferFile.getContent().length, responseFile.getContent().length);
        IOUtils.closeQuietly(is);
    }

    @Test(expected = JcrException.class)
    public void testGetNonExistentDocumentAndExpectedIsJcrException() throws JcrException, IOException {
        String nodeName = "testDocumentServiceNonExistent";

        when(jcrDataAccess.get(nodeName)).thenThrow(new JcrException());

        DataTransferFile responseFile = documentService.getDocument(nodeName);

        verify(jcrDataAccess, times(1)).get(nodeName);
        assertNull(responseFile);
    }

    @Test
    public void testDeleteDocumentAndExpectedIsOk() throws JcrException, IOException {
        String nodeName = "testDocumentService101";

        String fileNameFromResources = "plain_text.txt";
        InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
        byte[] input = IOUtils.toByteArray(is);
        DataTransferFile dataTransferFile = new DataTransferFile(nodeName, "plain_text.txt","text/plain", input);

        when(jcrDataAccess.post(dataTransferFile)).thenReturn("File added successfully ");
        String response = documentService.postDocument(dataTransferFile);
        verify(jcrDataAccess, times(1)).post(dataTransferFile);
        assertEquals("File added successfully ", response);

        IOUtils.closeQuietly(is);

        when(jcrDataAccess.delete(nodeName)).thenReturn("File successfully deleted");
        String delStatus = documentService.deleteDocument(nodeName);
        verify(jcrDataAccess, times(1)).delete(nodeName);
        assertEquals("File successfully deleted", delStatus);
    }

    @Test(expected = JcrException.class)
    public void TestDeleteNonExistentDocumentAndExpectedJcrException() throws JcrException {
        String nodeName = "testDocumentServiceNonExistent";

        when(jcrDataAccess.delete(nodeName)).thenThrow(new JcrException());
        String delStatus = documentService.deleteDocument(nodeName);
        verify(jcrDataAccess, times(1)).delete(nodeName);
        assertNull(delStatus);
    }
}
