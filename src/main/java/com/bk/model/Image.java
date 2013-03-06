package com.bk.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * User: ph
 * Date: 1/31/13
 */

@Entity
public class Image extends AbstractEntity implements Serializable{
    private String name;
    private String contentType;
	
	@Column(name="FILE_SIZE")
    private Long size;

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
