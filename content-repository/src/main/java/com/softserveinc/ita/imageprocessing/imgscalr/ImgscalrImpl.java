package com.softserveinc.ita.imageprocessing.imgscalr;

import com.softserveinc.ita.entity.DataTransferFile;
import com.softserveinc.ita.imageprocessing.AbstractImageProcessor;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Uses Imgscalr library
 * http://www.thebuzzmedia.com/software/imgscalr-java-image-scaling-library/
 */
public class ImgscalrImpl extends AbstractImageProcessor {

    /**
     * Makes resize of image
     * @param source - ImageFile that need to be resized
     * @param mimeType - mime-type of image
     * @param height - required height
     * @param width - required width
     * @return - ImageFile with resized image (as byte array)
     * @throws java.io.IOException
     */
    @Override
    public DataTransferFile resize(DataTransferFile source, String mimeType, int width, int height) throws IOException {
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(source.getContent()));
        BufferedImage scaledImg = Scalr.resize(img, Scalr.Method.QUALITY, Scalr.Mode.AUTOMATIC, width, height);

        byte[] imageInByte = writeImage(scaledImg, mimeType);

        return new DataTransferFile(source.getNodeName(), source.getOriginalFileName(), source.getMimeType(), imageInByte);
    }
}
