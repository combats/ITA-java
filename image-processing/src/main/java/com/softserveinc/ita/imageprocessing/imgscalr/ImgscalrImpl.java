package com.softserveinc.ita.imageprocessing.imgscalr;

import com.softserveinc.ita.controller.entity.ImageFile;
import com.softserveinc.ita.imageprocessing.ImageProcessor;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Uses Imgscalr library
 * http://www.thebuzzmedia.com/software/imgscalr-java-image-scaling-library/
 */
public class ImgscalrImpl implements ImageProcessor {
    private String imageExtension = "jpg";

    /**
     * Makes resize of image
     * @param source - ImageFile that need to be resized
     * @param mimeType - mime-type of image
     * @param height - required height
     * @param width - required width
     * @return - ImageFile with resized image (as byte array)
     * @throws IOException
     */
    @Override
    public ImageFile doScalr(ImageFile source,String mimeType, int height, int width) throws IOException {
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(source.getContent()));
        BufferedImage scaledImg = Scalr.resize(img, Scalr.Method.QUALITY, Scalr.Mode.AUTOMATIC, width, height);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(mimeType.matches("(?i)(gif)")){
            imageExtension = "gif";
        }
        if(mimeType.matches("(?i)(png)")){
            imageExtension = "png";
        }
        if(mimeType.matches("(?i)(bmp)")){
            imageExtension = "bmp";
        }
        if(mimeType.matches("(?i)(jpeg)")){
            imageExtension = "jpeg";
        }
        ImageIO.write(scaledImg, imageExtension, baos);
        byte[] imageInByte = baos.toByteArray();
        baos.flush();
        baos.close();

        return new ImageFile(source.getNodeName(), source.getOriginalFileName(), source.getMimeType(), imageInByte);
    }
}
