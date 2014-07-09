package com.softserveinc.ita.image;

import com.softserveinc.ita.controller.entity.ImageFile;
import com.softserveinc.ita.imageprocessing.imgscalr.ImgscalrImpl;
import junit.framework.Assert;
import org.apache.commons.io.IOUtils;
import org.junit.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Taken from: https://github.com/thebuzzmedia/imgscalr/tree/master/src/test/java/org/imgscalr
 */
public class ImgscalrImplTest extends BaseImageScalTest{
    private ImgscalrImpl imgscalrImpl = new ImgscalrImpl();
    private  BufferedImage src; // was static
    private  byte[] bytesrc;
    private  ImageFile source;

    private int height = 200;
    private int width = 200;

    @Before
    public void setup() throws IOException {
        src = load("time-square.png");
        bytesrc = loadByte("time-square.png");
        source = new ImageFile("nodeName", "originalName", "image/png", bytesrc);
    }

    @After
    public void tearDown() {
        src.flush();
    }

    protected BufferedImage load(String name) {
        BufferedImage i = null;

        try {
            i = ImageIO.read(this.getClass().getResourceAsStream("/" + name));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

    protected byte[] loadByte(String name) {
        byte[] input = new byte[0];

        try {
            input = IOUtils.toByteArray(this.getClass().getResourceAsStream("/" + name));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return input;
    }

    protected void assertEquals(BufferedImage orig, BufferedImage tmp) throws AssertionError {
        // Ensure neither image is null.
        Assert.assertNotNull(orig);
        Assert.assertNotNull(tmp);

        // Ensure dimensions are equal.
        Assert.assertEquals(orig.getWidth(), tmp.getWidth());
        Assert.assertEquals(orig.getHeight(), tmp.getHeight());
    }

    @Test
    public void testResizeWHExact() throws IOException {
        ImageFile tmpFile = imgscalrImpl.doScalr(source, source.getMimeType(), width, height);
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(tmpFile.getContent()));
        assertEquals(load("time-square-imgscarl-resize-QUALITY-AUTO-200x200.png"), img);
    }
}