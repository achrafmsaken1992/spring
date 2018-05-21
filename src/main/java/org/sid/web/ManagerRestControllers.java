package org.sid.web;

import java.util.Date;
import java.util.List;

import org.sid.dao.UserRepository;

import org.sid.entities.Appuser;
import org.sid.entities.Offre;
import org.sid.form.ManagerForm;
import org.sid.form.OffreForm;
import org.sid.service.AccountService;
import org.sid.service.CreateDirectoryService;
import org.sid.service.EtudiantService;
import org.sid.service.OffreService;
import org.sid.service.SendmailService;
import org.sid.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class ManagerRestControllers {
	@Autowired
	private BCryptPasswordEncoder  bCryptPasswordEncoder ;
	@Autowired
	AccountService accountService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	UserRepository userdao;
	@Autowired
	StorageService storageService;
	@Autowired
	SendmailService sendMail;
	@Autowired
	EtudiantService etudiantService;
@Autowired
CreateDirectoryService createDirectoryService;
@Autowired 
OffreService offreService;
	@Autowired
	
	@Value("${gmail.username}")
	private String username;
	@Value("${gmail.password}")
	private String password;
	
	@RequestMapping(value="/admin/getManagers",method=RequestMethod.GET)
	public Page<Appuser> getManagers(
			@RequestParam(name="nom",defaultValue="")	String nom,
			@RequestParam(name="prenom",defaultValue="")	String prenom,
			@RequestParam(name="nomEntreprise",defaultValue="")	String nomEntreprise,
			@RequestParam(name="active",defaultValue="1")	int active,
			@RequestParam(name="valide",defaultValue="1")	int valide,
			
			
			@RequestParam(name="page",defaultValue="0")	int page,
			@RequestParam(name="size",defaultValue="5")	int size)
   {
		
	
		return accountService.getManagers("%"+nom+"%","%"+prenom+"%","%"+nomEntreprise+"%", active, valide, new PageRequest(page, size));
   }
	
	
	
	
	
	
	
	
		@PostMapping("/manager/addOffre")
		public Long addOffre(@RequestBody OffreForm offreForm) {
			Offre offre=new Offre();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String loggedUsername = auth.getName();
			Appuser appUser=userdao.findUserByEmail(loggedUsername);	
		
	offre.setDescription(offreForm.getDescription());
	offre.setTitre(offreForm.getTitre());
	offre.setImage(offreForm.getImage());
	offre.setEntreprise(appUser.getNomEntreprise());

	offre.setManager(appUser);
	offre.setDate(new Date());
	offre.setActive(1);
			Long id=offreService.addOffre(offre);
	createDirectoryService.CreateDirectory("/entreprise/"+appUser.getNomEntreprise()+"/offres/"+id.toString());
	
			return id;
			
			
		}
		@PostMapping("/manager/imageOffre")
		public ResponseEntity<String> imageOffre(Long id,@RequestParam("file") MultipartFile file) {
			String message = "";
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String loggedUsername = auth.getName();
			
			Appuser user=userdao.findUserByEmail(loggedUsername);
			
			String path="upload-dir/entreprise/"+user.getNomEntreprise()+"/offres/"+id.toString();
			try {
			   storageService.store(file,path);
	           
	  
				message = "You successfully uploaded " + file.getOriginalFilename() + "!";
				
				
				
				return ResponseEntity.status(HttpStatus.OK).body(message);
			} catch (Exception e) {
				message = "FAIL to upload " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
			}
			
		}
		
		@RequestMapping(value="/manager/getManagerOffres",method=RequestMethod.GET)
		public Page<Offre> getManagers(
				
				@RequestParam(name="mot",defaultValue="")	String mot,
				
				@RequestParam(name="active",defaultValue="1")	int active,
				
				
				
				@RequestParam(name="page",defaultValue="0")	int page,
				@RequestParam(name="size",defaultValue="4")	int size)
	   {Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		
		Appuser user=userdao.findUserByEmail(loggedUsername);
			
		return offreService.getOffreByManager(user.getId(),"%"+mot+"%", active, new PageRequest(page, size));
			
			
			
	   }
		@GetMapping("/getPhotoOffreByManager/{filename}/{id}/{nomEntreprise}")
		@ResponseBody
		public ResponseEntity<Resource> getFile(@PathVariable("filename") String filename,@PathVariable("id") String id,@PathVariable("nomEntreprise") String nomEntreprise) {
			
			
			String path="upload-dir/entreprise/"+nomEntreprise+"/offres/"+id;
			Resource file = storageService.loadFile(filename,path);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.body(file);
		}
		
		@GetMapping("/getPhotoManager/{filename}/{nomEntreprise}")
		@ResponseBody
		public ResponseEntity<Resource> getProfileManager(@PathVariable("filename") String filename,@PathVariable("nomEntreprise") String nomEntreprise) {
			
			
			String path="upload-dir/entreprise/"+nomEntreprise+"/profile";
			Resource file = storageService.loadFile(filename,path);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.body(file);
		}
		
		
		@PostMapping("/manager/uploadImage")
		public ResponseEntity<String> imageOffre(@RequestParam("file") MultipartFile file) {
			String message = "";
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String loggedUsername = auth.getName();
			
			Appuser user=userdao.findUserByEmail(loggedUsername);
			
			String path="upload-dir/entreprise/"+user.getNomEntreprise()+"/profile/";
			try {
			   storageService.store(file,path);
	           
			   accountService.updateImage(file.getOriginalFilename(), user.getId());
				message = "You successfully uploaded " + file.getOriginalFilename() + "!";
				
				
				
				return ResponseEntity.status(HttpStatus.OK).body(message);
			} catch (Exception e) {
				message = "FAIL to upload " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
			}
			
		}
		
		
		
		
		
		
		@RequestMapping(value="/manager/getEtudiantsMessagerie",method=RequestMethod.GET)
		public List<Appuser> getEtudiants(
				
				@RequestParam(name="mot",defaultValue="")	String mot
				
			
				
				
			)
	   {
			
		
			return etudiantService.getEtudiantMessagerie("%"+mot+"%");
			
			
	   }
		
   }
	
	
 

