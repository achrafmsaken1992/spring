package org.sid.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;

import org.sid.dao.UserRepository;
import org.sid.entities.AppRole;
import org.sid.entities.Appuser;
import org.sid.entities.Messagerie;
import org.sid.entities.Offre;
import org.sid.form.EmailMessages;
import org.sid.form.EtudiantForm;
import org.sid.form.RegisterForm;
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
public class EtudiantRestController {
	@Autowired
	private BCryptPasswordEncoder  bCryptPasswordEncoder ;
	@Autowired
	AccountService accountService;
	@Autowired
	EtudiantService etudiantService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	UserRepository userdao;
	@Autowired
	StorageService storageService;
	@Autowired
	SendmailService sendMail;
	@Autowired
	OffreService offreService;
@Autowired
CreateDirectoryService createDirectoryService;
@Value("${gmail.username}")
private String username;
@Value("${gmail.password}")
private String password;



	
	@PostMapping("admin/ajoutEtudiant")
	public Appuser ajoutEtudiant(@RequestBody EtudiantForm form) {

			
		   
			Appuser app = accountService.findUserByEmail(form.getEmail());
			if (app != null)
				throw new RuntimeException("email exist deja");
			
			

			Appuser appUser = new Appuser();
			appUser.setEmail(form.getEmail());
			//DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			//String requiredDate = df.format(new Date()).toString();
			appUser.setDateCreation(new Date());
appUser.setDateNaissance(form.getDateNaissance());
			appUser.setDateNaissance(form.getDateNaissance());
			appUser.setPrenom(form.getPrenom());
			appUser.setNom(form.getNom());
			appUser.setTel(form.getTel());
			appUser.setPassword("1234");
		
String uuid=this.getUuid();
appUser.setToken(uuid);
appUser.setValide(1);

accountService.saveUser(appUser);


	accountService.saveRole(new AppRole(null, "ETUDIANT"));
	accountService.addRoleToUser(form.getEmail(), "ETUDIANT");
			EmailMessages em = new EmailMessages();
			em.setBody("<a href='http://localhost:4200/sessions/activation/"+uuid+"'>validation mail</a>");
			em.setTo_address(appUser.getEmail());
			em.setSubject("validation email");
			try {

				sendMail.sendmail(em,username,password);
				
					
				
					createDirectoryService.CreateDirectory("/etudiant/"+appUser.getId()+"/image");
				

			} catch (MessagingException e) {
				//e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			return appUser;
			
		
		}	
	
	
	public String getUuid() {
		String digest="";
		UUID uuid = UUID.randomUUID();
		
		
		
		try {
		try {
			
		
				MessageDigest salt;
				salt = MessageDigest.getInstance("SHA-256");
				salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
				StringBuilder builder = new StringBuilder();
			    for (byte b: salt.digest()) {
			      builder.append(String.format("%02x", b));}
			    
				 digest =  builder.toString();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		   
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return digest;
	}
	@RequestMapping(value="/admin/getEtudiants",method=RequestMethod.GET)
	public Page<Appuser> getEtudiants(
			
			@RequestParam(name="nom",defaultValue="")	String nom,
			@RequestParam(name="prenom",defaultValue="")	String prenom,
			@RequestParam(name="active",defaultValue="1")	int active,
			@RequestParam(name="valide",defaultValue="1")	int valide,
			
			
			@RequestParam(name="page",defaultValue="0")	int page,
			@RequestParam(name="size",defaultValue="5")	int size)
   {
		
	
		return etudiantService.getEtudiants("%"+nom+"%", "%"+prenom+"%",active, valide, new PageRequest(page, size));
		
		
   }
	
	
	@PostMapping("/etudiant/uploadImage")
	public ResponseEntity<String> imageOffre(@RequestParam("file") MultipartFile file) {
		String message = "";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		
		Appuser user=userdao.findUserByEmail(loggedUsername);
		
		String path="upload-dir/etudiant/"+user.getId()+"/image/";
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
	
	@GetMapping("/getPhotoEtudiant/{filename}/{id}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable("filename") String filename,@PathVariable("id") String id) {
		
		
		String path="upload-dir/etudiant/"+id+"/image/";
		Resource file = storageService.loadFile(filename,path);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@RequestMapping(value="/etudiant/getManagers",method=RequestMethod.GET)
	public List<Appuser> getManagers(
			@RequestParam(name="mot",defaultValue="")	String mot
			)
			
   {
		
	
		return accountService.getManagersMessagerie("%"+mot+"%");
   }
	@RequestMapping(value="/etudiant/getAllOffres",method=RequestMethod.GET)
	public Page<Offre> getManagers(
			
			@RequestParam(name="mot",defaultValue="")	String mot,
			
			
			
			
			@RequestParam(name="page",defaultValue="0")	int page,
			@RequestParam(name="size",defaultValue="4")	int size)
   {Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String loggedUsername = auth.getName();
	
	Appuser user=userdao.findUserByEmail(loggedUsername);
		
	return offreService.getAllOffres("%"+mot+"%", new PageRequest(page, size));
		
		
		
   }
	@RequestMapping(value="getEtudiantById",method=RequestMethod.GET)
	public Appuser getEtudiantById(
			@RequestParam(name="id")Long id
			)
			
   {
		
	
		return accountService.findUserById(id);
   }
	
}
