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
import org.sid.form.ActiveAccount;
import org.sid.form.EmailMessages;
import org.sid.form.ForgetPasswordForm;
import org.sid.form.MessagerieForm;
import org.sid.form.RegisterForm;
import org.sid.service.AccountService;
import org.sid.service.CreateDirectoryService;
import org.sid.service.SendmailService;
import org.sid.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AccountRestController {
	
	
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
	
	@Value("${gmail.username}")
	private String username;
	@Value("${gmail.password}")
	private String password;
 
	
	
	
    	@PostMapping("/register")
    	public Long register(@RequestBody RegisterForm form) {

    			
    			long nbr = accountService.countuser();
    			

    			Appuser app = accountService.findUserByEmail(form.getEmail());
    			if (app != null)
    				throw new RuntimeException("email exist deja");
    			if(accountService.findUserByNomEntreprise(form.getNomEntreprise())!=null)
    				throw new RuntimeException("nom de la societé existe deja");
    			

    			Appuser appUser = new Appuser();
    			appUser.setEmail(form.getEmail());
    			
    			appUser.setDateCreation(new Date());
appUser.setDateNaissance(form.getDateNaissance());
    			appUser.setNomEntreprise(form.getNomEntreprise());
    			appUser.setPrenom(form.getPrenom());
    			appUser.setNom(form.getNom());
    			appUser.setTel(form.getTel());
    			appUser.setPassword("1234");
    		
String uuid=this.getUuid();
appUser.setToken(uuid);
    			//EmailMessages em = new EmailMessages();
    			//em.setBody("<a href='http://localhost:4200/activation/"+uuid+"'>validation mail</a>");
    			//em.setTo_address(appUser.getEmail());
    			//em.setSubject("validation email");
    			//try {

    				//sendMail.sendmail(em,username,password);
    				if(nbr ==0) {
    					appUser.setValide(1);
    				}
    				accountService.saveUser(appUser);

    				if (nbr == 0) {
    					accountService.saveRole(new AppRole(null, "ADMIN"));
    					accountService.addRoleToUser(form.getEmail(), "ADMIN");
    				} else {
    					accountService.saveRole(new AppRole(null, "MANAGER"));
    					accountService.addRoleToUser(form.getEmail(), "MANAGER");
    				
    				}

    			/*} catch (MessagingException e) {
    				//e.printStackTrace();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				//e.printStackTrace();
    			}*/
    			if(nbr!=0) {
    				
    			//createDirectoryService.CreateDirectory(form.getCompanyName()+"/Rhs");
    			//createDirectoryService.CreateDirectory(form.getCompanyName()+"/Logos");
    			//createDirectoryService.CreateDirectory(form.getCompanyName()+"/Candidates");
    			}
    			else
    				createDirectoryService.CreateDirectory("/Admin");
    			return appUser.getId();
    		}	
    	
    	@PostMapping("activationCompte")
    	public void activeCompte(@RequestBody ActiveAccount fpf) {  
  			  
  			  if(!fpf.getPassword().equals(fpf.getRepassword())) 
  				  throw new RuntimeException("vous devez valider mot de pass");
  			  
  			  Appuser appUser=accountService.findUserByToken(fpf.getToken());
  			   if(appUser==null)
  				  throw new RuntimeException("token n\'existe pas");
  		
  			  
  			  
  			  appUser.setToken(null);
  			  appUser.setActive(1);
  			  appUser.setPassword(fpf.getPassword());
  			  accountService.saveUser(appUser);
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
    	
    	
    	@RequestMapping(value="/getMessageries",method=RequestMethod.GET)
    	public List<Messagerie> getMessagerie(
    			
    			@RequestParam(name="user1")	Long user1,
    			@RequestParam(name="user2")	Long user2)
    			
    	{
    		return accountService.messageries(user1,user2);
    		
    	}

    	@PostMapping("/addMessage")
    	public void addMessage(@RequestBody MessagerieForm messagerie) {
    		accountService.addMessagerie(messagerie);
    	}

//getMessageriesCall
    	@RequestMapping(value="/getNotifications",method=RequestMethod.GET)
    	public Page<Messagerie> getMessageriesCall(
    			
    			@RequestParam(name="user")	Long user,
    			
    			
    			
    			
    			@RequestParam(name="size",defaultValue="10")	int size)
       {
    		
    	
    		return accountService.getMessageriesCall(user, new PageRequest(0, size));
    				//new PageRequest(page, size)
    		
    		
       }
    	@RequestMapping(value="/userProfile", method = RequestMethod.GET)

		public Appuser getManagerProfile() {
			
			
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				String loggedUsername = auth.getName();
				Appuser user=userdao.findUserByEmail(loggedUsername);
				
				return user;
				
				
			
			
			
			
		}
}
