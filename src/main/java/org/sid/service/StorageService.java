package org.sid.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.FileSystemUtils;
@Service

public class StorageService {
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	
	public void store(MultipartFile file,String path) {
		//,String name
		 Path rootLocation = Paths.get(path+"/");
		try {
			Files.copy(file.getInputStream(), rootLocation.resolve(file.getOriginalFilename()));
		//name+file.getOriginalFilename().split("\\.")[1])
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}
	
	public Resource loadFile(String filename,String path) {
		 Path rootLocation = Paths.get(path+"/");
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	
	}
	
}
