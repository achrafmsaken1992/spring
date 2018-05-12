package org.sid.service;

import org.springframework.web.multipart.MultipartFile;

public class UploadPhotoCandidatForm {
	MultipartFile file;
	Long id;
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
