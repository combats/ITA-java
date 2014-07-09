package com.softserveinc.ita.jcr;

import com.softserveinc.ita.controller.entity.ImageFile;
import com.softserveinc.ita.exception.JcrException;
import org.apache.commons.io.IOUtils;
import org.apache.jackrabbit.api.JackrabbitRepository;
import org.apache.jackrabbit.api.JackrabbitRepositoryFactory;
import org.apache.jackrabbit.core.RepositoryFactoryImpl;
import org.junit.Test;

import javax.jcr.*;
import java.io.*;
import java.util.Properties;

import static org.junit.Assert.assertEquals;


public class JcrJackrabbitImplREPOSITORYTest extends BaseJcrTest{
    public static final String REPOSYTORY_HOME_DIR_CHARACTER = "D";

    JackrabbitRepositoryFactory rf = new RepositoryFactoryImpl();
    Properties prop = new Properties();
    Session session;

    @Test
    public void testCreateANodeAndExpectedIsSuccess() {
        prop.setProperty("org.apache.jackrabbit.repository.home", REPOSYTORY_HOME_DIR_CHARACTER + ":\\pkruttc_property\\repo");
        prop.setProperty("org.apache.jackrabbit.repository.conf", REPOSYTORY_HOME_DIR_CHARACTER + ":\\pkruttc_property\\repo" + "\\repository.xml");
        try {
            String fileNameFromResources = "input-1024x768.jpg";

            InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
            byte[] input = IOUtils.toByteArray(is);
            String nodeName = "repositoryInitTest";
            ImageFile imageFile = new ImageFile(nodeName, "input-1024x768.jpg","image/jpeg", input);

            JackrabbitRepository repository = (JackrabbitRepository) rf.getRepository(prop);

            session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
            Node root = session.getRootNode();
            Node file = null;

            if (root.hasNode(imageFile.getNodeName())) {
                file = root.getNode(imageFile.getNodeName());
            } else {
                ByteArrayInputStream bis = new ByteArrayInputStream(imageFile.getContent());

                file = root.addNode(imageFile.getNodeName(), "nt:file");
                Node content = file.addNode("jcr:content", "nt:resource");
                Binary binary = session.getValueFactory().createBinary(bis);
                content.setProperty("jcr:data", binary);
                content.setProperty("jcr:mimeType", imageFile.getMimeType());
                session.save();
            }

        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        } finally {
                session.logout();
        }
    }

    @Test
    public void testGetANodeAndExpectedIsSuccess() throws JcrException, IOException {
        prop.setProperty("org.apache.jackrabbit.repository.home", REPOSYTORY_HOME_DIR_CHARACTER + ":\\pkruttc_property\\repo");
        prop.setProperty("org.apache.jackrabbit.repository.conf", REPOSYTORY_HOME_DIR_CHARACTER + ":\\pkruttc_property\\repo" + "\\repository.xml");
            ImageFile responseImageFile = null;
            String nodeName = "repositoryInitTest";
            String fileNameFromResources = "input-1024x768.jpg";

            InputStream is = this.getClass().getResourceAsStream("/" + fileNameFromResources);
            byte[] input = IOUtils.toByteArray(is);
            ImageFile imageFile = new ImageFile(nodeName, "input-1024x768.jpg","image/jpeg", input);
        try {

            JackrabbitRepository repository = (JackrabbitRepository) rf.getRepository(prop);

            session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
            Node root = session.getRootNode();
            Node file = null;
            InputStream stream = null;
            String originalFileName = null;
            String mimeType = null;
            if (root.hasNode(nodeName)) {
                file=root.getNode(nodeName);
                Node content=file.getNode("jcr:content");
                String path = content.getPath();
                originalFileName = nodeName;
                mimeType = session.getNode(path).getProperty("jcr:mimeType").getString();
                Binary bin = session.getNode(path).getProperty("jcr:data").getBinary();
                stream = bin.getStream();

                responseImageFile = new ImageFile(nodeName, originalFileName, mimeType, IOUtils.toByteArray(stream));
            } else {
                throw new JcrException("Some jcr trouble in JcrJackrabbitDataAccessImpl: method get - such node doesn`t exist");
            }

        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        } finally {
            session.logout();
        }
        assertEquals(imageFile.getNodeName(), responseImageFile.getNodeName());
    }
}
