package org.sid.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Message;

import javax.mail.*;

import javax.mail.Transport;

import javax.mail.internet.*;

import org.junit.runner.RunWith;
import org.sid.dao.UserRepository;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;

import org.sid.form.EmailMessages;
import org.sid.form.ForgetPasswordForm;
import org.sid.form.ManagerForm;
import org.sid.form.RegisterForm;
import org.sid.form.UpdatePasswordForm;

import org.sid.form.ProfileForm;
import org.sid.service.AccountService;
import org.sid.service.CandidatService;
import org.sid.service.CreateDirectoryService;

import org.sid.service.SendmailService;
import org.sid.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
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

public class AccountRestController {
	@Value("${gmail.username}")
	private String username;
	@Value("${gmail.password}")
	private String password;
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
CreateDirectoryService createDirectoryService;
	

//----------------------------------sign-up------------------------------------------
	@PostMapping("/register")
public AppUser register(@RequestBody RegisterForm form) {

		if (!form.getPassword().equals(form.getRepassword()))
			throw new RuntimeException("vous devez confirmer votre mot de passe");
		long nbr = accountService.countuser();
		Date d = new Date();

		AppUser app = accountService.findUserByEmail(form.getEmail());
		if (app != null)
			throw new RuntimeException("email exist deja");
		if(accountService.findByCompanyName(form.getCompanyName())!=null)
			throw new RuntimeException("nom de la societé existe deja");
		

		AppUser appUser = new AppUser();
		appUser.setEmail(form.getEmail());
		appUser.setDateCreation(new Date());

		appUser.setCompanyName(form.getCompanyName());
		appUser.setFirstName(form.getFirstName());
		appUser.setLastName(form.getLastName());
		appUser.setPhoneUser(form.getTel());
		appUser.setPassword(form.getPassword());

		EmailMessages em = new EmailMessages();
		em.setBody("avec succée");
		em.setTo_address(appUser.getEmail());
		em.setSubject("avec succée");
		try {

			sendMail.sendmail(em,username,password);
			accountService.saveUser(appUser);

			if (nbr == 0) {
				accountService.saveRole(new AppRole(null, "ADMIN"));
				accountService.addRoleToUser(form.getEmail(), "ADMIN");
			} else if (nbr == 1) {
				accountService.saveRole(new AppRole(null, "MANAGER"));
				accountService.addRoleToUser(form.getEmail(), "MANAGER");
			} else {
				accountService.addRoleToUser(form.getEmail(), "MANAGER");
			}

		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(nbr!=0) {
			
		createDirectoryService.CreateDirectory(form.getCompanyName()+"/Rhs");
		createDirectoryService.CreateDirectory(form.getCompanyName()+"/Logos");
		createDirectoryService.CreateDirectory(form.getCompanyName()+"/Candidates");
		}
		else
			createDirectoryService.CreateDirectory(form.getCompanyName()+"/Rhs");
		
		return appUser;
	}
	//----------------------------------end-sign-up-------------------------------------

	

	//------------------------send link recovery password---------------------------------- 
	@PostMapping("/recoveryPassword")
	public void recoveryPassword(@RequestBody String email) {
		
		
		String digest="";
		UUID uuid = UUID.randomUUID();
		String DateExpiration = LocalDate.now().plusDays(1).toString();
		if(accountService.findUserByEmail(email)==null) {
			throw new RuntimeException("email n\'existe pas");
		}
		
		
		
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

		
		EmailMessages em = new EmailMessages();
		em.setBody("<a href='http://localhost:4200/updateAuth/"+digest
		+"'>lien web</a>       <a href='http://mycoolapp://updateAuth/"+digest + 
		"'>lien mobile</a>");
		em.setTo_address(email);
		em.setSubject("validation");
		

			try {
				sendMail.sendmail(em,username,password);
				AppUser appUser=accountService.findUserByEmail(email);
				
				
			accountService.recoveryPassword(digest, DateExpiration, appUser.getId());
				
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
		
	}

		 //----------------------send link recovery password---------------------------------------- 
		
	
	
	//-----------------------recovery password-------------------------------- 
	@PostMapping("/updateAuthentification")
		public void  updateAuthentification(@RequestBody ForgetPasswordForm fpf) {  
			  
			  if(!fpf.getPassword().equals(fpf.getRepassword())) 
				  throw new RuntimeException("vous devez valider mot de pass");
			  
			  AppUser appUser=accountService.findByTokenRecovery(fpf.getToken());
			   if(appUser==null)
				  throw new RuntimeException("token n\'existe pas");
			  if(checkDateExpiration(appUser.getDateExpiration()))
				  throw new RuntimeException("token expiré");
			  
			  appUser.setDateExpiration(null);
			  appUser.setTokenRecovery(null);
			  appUser.setPassword(fpf.getPassword());
			  accountService.saveUser(appUser);
			  
		
	}
	
	
	
	public boolean checkDateExpiration(String dateExperition) {
		 SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd"); 
		Date dateNow = null;
		Date dateExpiration = null;
		
			try {
				dateNow=dt.parse(LocalDate.now().toString());
				dateExpiration = dt.parse(dateExperition);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			 
			 if (dateExpiration.after(dateNow)) {
				return false; 
			 }
			 return true;
	}
	
	
	
	//--------------------end recovery password---------------------------
	
	
	
	
	
	@RequestMapping(value="/userProfile", method = RequestMethod.GET)

		public ProfileForm getProfile() {
			
			
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				String loggedUsername = auth.getName();
				AppUser user=userdao.findByEmail(loggedUsername);
				ProfileForm profile=new ProfileForm();
				profile.setEmail(user.getEmail());
				profile.setCompanyName(user.getCompanyName());
				profile.setPhoneUser(user.getPhoneUser());
				profile.setFirstName(user.getFirstName());
				profile.setLastName(user.getLastName());
				profile.setPhoto(user.getPhoto());
				profile.setId(user.getId());
				
				
			
			
			
			return profile;
		}
	
	//-------------------------update password-----------------------------------------
	@PostMapping("/updatePassword")
	public void updatePassword(@RequestBody UpdatePasswordForm udf) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		
		AppUser user=userdao.findByEmail(loggedUsername);
		
		
	
		 if(!BCrypt.checkpw(udf.getOldpassword(), user.getPassword()))
		
			 throw new RuntimeException("ancien mot de passe incorrect");
	 if(!udf.getPassword().equals(udf.getRepassword())) {
			throw new RuntimeException("vous devez confirmer mot de passe");
		}
		
		user.setPassword(udf.getPassword());
			
		
			accountService.saveUser(user);
		
		
		
	}
	   
	
	
	
	 //---------------------end update password-----------------------------------------
	
	
	
	//-------------------upload photo profil------------------------------------------

	@PostMapping("/uploadPhotoProfil")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String message = "";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		
		AppUser user=userdao.findByEmail(loggedUsername);
		createDirectoryService.CreateDirectory(user.getCompanyName()+"/Employees/"+user.getId());
		
		
		
		String path="upload-dir/"+user.getCompanyName()+"/Employees/"+user.getId();
		
		try {
			storageService.store(file,path);
           accountService.updatePhoto(user.getId(), file.getOriginalFilename());
 
			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			
			
			
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
		
	}
//---------------------end upload photo profil------------------------------------------
	
	//---------------------get photo profil---------------------------------------------
	@GetMapping("/getPhotoProfile/{filename}/{companyName}/{id}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable("filename") String filename,@PathVariable("companyName") String companyName,@PathVariable("id") String id) {
		String path="upload-dir/"+companyName+"/Employees/"+id;
		Resource file = storageService.loadFile(filename,path);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	//--------------------end photo profil---------------------------------------------
	//---------------get all manager------------------
		@GetMapping("getManagers")
		public List<ManagerForm> getManagers(){
			
			return accountService.getManagers();
		}
		@PostMapping("/refuseSignUp")
		public void refuseSignUp(@RequestBody  Long id)
		{
			accountService.refuseSignUp(id);
		}
		@PostMapping("/acceptSignUp")
		public void acceptSignUp(@RequestBody  Long id) {
			accountService.acceptSignUp(id);
		}
		
		
}
