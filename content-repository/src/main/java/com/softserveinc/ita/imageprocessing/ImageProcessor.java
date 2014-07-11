package com.softserveinc.ita.imageprocessing;

import com.softserveinc.ita.controller.entity.DataTransferFile;

import java.io.IOException;

public interface ImageProcessor {
    DataTransferFile doScalr(DataTransferFile source,String mimeType, int width, int height) throws IOException;
}
