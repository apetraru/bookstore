package com.bk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author Andrei Petraru 
 * 11.09.2014
 */
@Entity
@Table(name = "IMAGE")
public class Image extends AbstractEntity {
	private static final long serialVersionUID = 6819358211220341707L;

	private String name;
	@Column(name = "file_size")
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
