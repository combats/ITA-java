package com.softserveinc.ita.imageprocessing;

import com.softserveinc.ita.controller.entity.ImageFile;

import java.io.IOException;

public interface ImageProcessor {
    ImageFile doScalr(ImageFile source,String mimeType, int height, int width) throws IOException;
}
