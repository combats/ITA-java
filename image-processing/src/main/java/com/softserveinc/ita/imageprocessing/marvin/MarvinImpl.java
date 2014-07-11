package com.softserveinc.ita.imageprocessing.marvin;

import com.softserveinc.ita.controller.entity.DataTransferFile;
import com.softserveinc.ita.imageprocessing.ImageProcessor;

import java.io.IOException;

public class MarvinImpl implements ImageProcessor {
    @Override
    public DataTransferFile doScalr(DataTransferFile source,String mimeType, int height, int width) throws IOException {
        return null;
    }
}
