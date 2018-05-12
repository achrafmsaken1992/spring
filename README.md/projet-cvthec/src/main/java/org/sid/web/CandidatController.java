package org.sid.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.Resource;
import org.sid.dao.CandidateRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.AppUser;
import org.sid.entities.Candidate;

import org.sid.form.CandidateForm;
import org.sid.form.UpdatePhotoCandidatForm;
import org.sid.service.CandidatService;
import org.sid.service.CreateDirectoryService;
import org.sid.service.StorageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@CrossOrigin
public class CandidatController {
	@Autowired
	CandidateRepository rep;
	@Autowired
	CandidatService candidatService;
	@Autowired
	UserRepository userdao;
	@Autowired
	StorageService storageService;
	@Autowired
	CreateDirectoryService createDirectoryService;
	
String idCandidat=null;
	
	@PostMapping("/registercandidat")

	public Long registercandidat(@RequestBody CandidateForm candidatForm) {
//,@RequestParam("cv") MultipartFile file
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		AppUser userRh=userdao.findByEmail(loggedUsername);
		
		Candidate can=candidatService.findCandidatByEmail( candidatForm.getEmail(),userRh.getAppUser().getEmail());
		
		
		
		
		AppUser userCompany=userRh.getAppUser();
		
		Candidate candidat=new Candidate();
		
		candidat.setAdresse(candidatForm.getAdresse());
		candidat.setEmail(candidatForm.getAdresse());
		candidat.setCv(candidatForm.getCv());
		candidat.setEmail(candidatForm.getEmail());
		candidat.setFirstName(candidatForm.getFirstname());
		candidat.setLastName(candidatForm.getLastname());
		candidat.setTel(candidatForm.getTel());
		candidat.setTitle(candidatForm.getTitle());
		candidat.setAppUser(userCompany);
	
Candidate candi=		 candidatService.savecandidat(candidat);
this.idCandidat=candi.getCandidateId().toString();


String path=userRh.getCompanyName()+"/Candidates/"+this.idCandidat;
createDirectoryService.CreateDirectory(path);
return candi.getCandidateId();
		}

	@PostMapping("/registerCvcandidat")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,Long id) {
		String message = "";
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String loggedUsername = auth.getName();
			AppUser userRh=userdao.findByEmail(loggedUsername);
			String path="upload-dir/"+userRh.getCompanyName()+"/Candidates/"+id;
			this.idCandidat=null;
			storageService.store(file,path);
			
			
	

 
			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
		
			
				
				
	}	
		
	
	
	
	
	
	
	
	@PostMapping("/updateCv")
	public ResponseEntity<String> handleFileUploadMobile(@RequestParam("file") MultipartFile file,Long id) {
		String message = "";
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String loggedUsername = auth.getName();
			AppUser userRh=userdao.findByEmail(loggedUsername);
			String path="upload-dir/"+userRh.getCompanyName()+"/Candidates/"+id;
			
			storageService.store(file,path);
			
			
		
			Candidate can=candidatService.getCandidatById(id);
			can.setCv(file.getOriginalFilename());
			candidatService.savecandidat(can);

 
			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
		
			
				
				
	}	
	
	
	
	
		
	
		@RequestMapping(value="/getCandidats",method=RequestMethod.GET)
	public Page<Candidate> getCandidats(
			
			@RequestParam(name="firstname",defaultValue="")	String firstname,
			@RequestParam(name="lastname",defaultValue="")	String lastname,
			@RequestParam(name="title",defaultValue="")	String title,
			
			
			@RequestParam(name="page",defaultValue="0")	int page,
			@RequestParam(name="size",defaultValue="5")	int size)
   {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String loggedUsername = auth.getName();
			
			AppUser userRh=userdao.findByEmail(loggedUsername);
		
			return 	candidatService.getCandidats("%"+firstname+"%","%"+lastname+"%","%"+title+"%","%"+userRh.getAppUser().getEmail()+"%", new PageRequest(page, size));
			
	}
		
		
		
		@RequestMapping(value="/getCandidatByid",method=RequestMethod.GET)
		public Candidate getCandidatByid(@RequestParam("id")Long id) {
			Candidate candidat=candidatService.getCandidatById(id);
			if(candidat==null)throw new RuntimeException("not existe");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String loggedUsername = auth.getName();
			AppUser userRh=userdao.findByEmail(loggedUsername);
			if(!userRh.getAppUser().equals(candidat.getAppUser()))throw new RuntimeException("permission denied");
			
		return candidat;
		}
		
		@GetMapping("/files/{filename:.+}/{idCandidat:.+}/{companyName}")
		@ResponseBody
		public ResponseEntity<Resource> getFile(@PathVariable String filename,@PathVariable String idCandidat,@PathVariable String companyName) {
			String path="upload-dir/"+companyName+"/Candidates/"+idCandidat+"/";
			//Resource file = storageService.loadFile(filename,path);
			
			Resource file = storageService.loadFile(filename,path);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.body(file);
		}
		
	
@PostMapping("deleteCandidate")
public void delete(@RequestBody Long id) {

	rep.delete(id);
}
	



@PostMapping("/uploadPhotoCandidat")
public ResponseEntity<String> uploadPhotoCandidat(Long id,@RequestParam("file") MultipartFile file) {
	String message = "";
	try {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		
		AppUser userRh=userdao.findByEmail(loggedUsername);
		createDirectoryService.CreateDirectory(userRh.getCompanyName()+"/Candidates/"+id+"/photos");
		candidatService.updatePhotoCandidate(file.getOriginalFilename(),id);
		
		
				String path="upload-dir/"+userRh.getCompanyName()+"/Candidates/"+id+"/photos";
		storageService.store(file,path);
		
		



		message = "You successfully uploaded " + file.getOriginalFilename() + "!";
		return ResponseEntity.status(HttpStatus.OK).body(message);
	} catch (Exception e) {
		message = "FAIL to upload " + file.getOriginalFilename() + "!";
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	}

}

@GetMapping("/getPhotoCandidate/{filename}/{companyName}/{id}")
@ResponseBody
public ResponseEntity<Resource> getPhotoCandidate(@PathVariable("filename") String filename,@PathVariable("companyName") String companyName,@PathVariable("id") String id) {
	String path="upload-dir/"+companyName+"/Candidates/"+id+"/photos";
	Resource file = storageService.loadFile(filename,path);
	return ResponseEntity.ok()
			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
			.body(file);
}

}