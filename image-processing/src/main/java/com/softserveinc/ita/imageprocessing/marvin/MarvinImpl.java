package com.softserveinc.ita.imageprocessing.marvin;

import com.softserveinc.ita.controller.entity.ImageFile;
import com.softserveinc.ita.imageprocessing.ImageProcessor;

import java.io.IOException;

public class MarvinImpl implements ImageProcessor {
    @Override
    public ImageFile doScalr(ImageFile source,String mimeType, int height, int width) throws IOException {
        return null;
    }
}
