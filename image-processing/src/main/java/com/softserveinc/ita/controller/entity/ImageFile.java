package com.softserveinc.ita.controller.entity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Just simple entity to handle some data
 */
public class ImageFile implements Serializable{
    private String nodeName;
    private String originalFileName;
    private String mimeType;
    private byte[] content;

    public ImageFile() {
    }

    public ImageFile(String nodeName, String originalFileName, String mimeType, byte[] content) {
        this.nodeName = nodeName;
        this.originalFileName = originalFileName;
        this.mimeType = mimeType;
        this.content = content;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageFile imageFile = (ImageFile) o;

        if (nodeName != null ? !nodeName.equals(imageFile.nodeName) : imageFile.nodeName != null)
            return false;
        if (!Arrays.equals(content, imageFile.content)) return false;
        if (mimeType != null ? !mimeType.equals(imageFile.mimeType) : imageFile.mimeType != null) return false;
        if (originalFileName != null ? !originalFileName.equals(imageFile.originalFileName) : imageFile.originalFileName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nodeName != null ? nodeName.hashCode() : 0;
        result = 31 * result + (originalFileName != null ? originalFileName.hashCode() : 0);
        result = 31 * result + (mimeType != null ? mimeType.hashCode() : 0);
        result = 31 * result + (content != null ? Arrays.hashCode(content) : 0);
        return result;
    }


}
