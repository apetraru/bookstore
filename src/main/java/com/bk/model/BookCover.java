package com.bk.model;

import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * User: ph
 * Date: 1/31/13
 */

@Entity
public class BookCover extends AbstractEntity{
    private String name;
    private Long size;
    private String contentType;

    @Lob
    private byte[] fileContent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}
