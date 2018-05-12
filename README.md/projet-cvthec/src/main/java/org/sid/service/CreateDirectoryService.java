package org.sid.service;




import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
@Transactional


public class CreateDirectoryService {
	private  final String path="C:/Users/ASUS/Documents/erp-api/projet-cvthec/upload-dir/";
	public void CreateDirectory(String link) {
		 Path dirsPath = Paths.get(this.path+link);
		
		 
		
		 try {
	            
	            Files.createDirectories(dirsPath);
	            
	        } catch (IOException e) {
	        	throw new RuntimeException("reperoire n\'exsite pas");
	        }
	}
	
	
}
