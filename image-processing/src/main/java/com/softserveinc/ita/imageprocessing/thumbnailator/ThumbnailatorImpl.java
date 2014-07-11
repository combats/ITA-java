package com.softserveinc.ita.imageprocessing.thumbnailator;

import com.softserveinc.ita.controller.entity.DataTransferFile;
import com.softserveinc.ita.imageprocessing.ImageProcessor;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Uses Thumbnailator library
 * https://code.google.com/p/thumbnailator/
 */
public class ThumbnailatorImpl implements ImageProcessor {
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
    public DataTransferFile doScalr(DataTransferFile source,String mimeType, int width, int height) throws IOException {
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(source.getContent()));
        BufferedImage thumbnail = Thumbnails.of(img)
                .size(height, width)
                .asBufferedImage();
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
        ImageIO.write(thumbnail, imageExtension, baos);
        byte[] imageInByte = baos.toByteArray();
        baos.flush();
        baos.close();

        return new DataTransferFile(source.getNodeName(), source.getOriginalFileName(), source.getMimeType(), imageInByte);
    }
}
