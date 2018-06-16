package org.sid.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
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
import org.sid.form.ContactForm;
import org.sid.form.EmailMessages;
import org.sid.form.ForgetPasswordForm;
import org.sid.form.MessagerieForm;
import org.sid.form.RegisterForm;
import org.sid.form.UpdatePasswordForm;
import org.sid.service.AccountService;
import org.sid.service.CreateDirectoryService;
import org.sid.service.FcmPushTest;
import org.sid.service.OffreService;
import org.sid.service.QcmService;
import org.sid.service.SendmailService;
import org.sid.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	OffreService offreService;
	@Autowired
	QcmService qcmService;
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
    			EmailMessages em = new EmailMessages();
    			em.setBody("<a href='http://localhost:4200/activation/"+uuid+"'>validation mail</a>");
    			em.setTo_address(appUser.getEmail());
    			em.setSubject("validation email");
    			try {

    				sendMail.sendmail(em,username,password);
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
    					createDirectoryService.CreateDirectory("entreprise/"+form.getNomEntreprise()+"/offres");
    	    			createDirectoryService.CreateDirectory("entreprise/"+form.getNomEntreprise()+"/profile");
    	    			//createDirectoryService.CreateDirectory(form.getCompanyName()+"/Candidates");
    				}

    			} catch (MessagingException e) {
    				e.printStackTrace();
    			} catch (IOException e) {
    				
    				e.printStackTrace();
    			}
    			if(nbr!=0) {
    				//createDirectoryService.CreateDirectory("entreprise/"+form.getNomEntreprise()+"/offres");
	    			//createDirectoryService.CreateDirectory("entreprise/"+form.getNomEntreprise()+"/profile");
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
    		
    		try {
                String response= FcmPushTest.pushFCMNotificationToOneUser(userdao.getOne(messagerie.getUser2()).getTokenNotification(),messagerie.getUser1(),messagerie.getImage(),messagerie.getRole(),messagerie.getBody());
                System.out.println("firebase response server :: "+response);
            }
    		
            catch (Exception ex)
            {
                System.out.println("Exeption firebase response server  : "+ex.getMessage());
            }
    	
    	
    		accountService.addMessagerie(messagerie);
    	}
    	
    	@PostMapping("/updateTokenNotification")
    	public void updateToken(
    			@RequestParam(name="notification")	String notification ,
    			@RequestParam(name="id")	Long id ) {
    		
    		userdao.updateTokenNotification(notification, id);
    		
    	}

//getMessageriesCall
    	@RequestMapping(value="/getNotifications",method=RequestMethod.GET)
    	public Page<Messagerie> getMessageriesCall(
    			
    			@RequestParam(name="user")	Long user,
    			
    			
    			
    			
    			@RequestParam(name="size",defaultValue="5")	int size)
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
    	
    	@RequestMapping(value="isActiveAndValide",method = RequestMethod.GET)
    	public Appuser isActiveAndValide(
    		@RequestParam(name="email")	String email) {
    	if(userdao.findUserByEmail(email).getActive()==0)throw new RuntimeException("erreur1");
    	if(userdao.findUserByEmail(email).getValide()==0)throw new RuntimeException("erreur2");
				return userdao.findUserByEmail(email);
    	
				
    		
    		
    	}
    	
    	
    	
    	//-------------------------update password-----------------------------------------
    	@PostMapping("/updatePassword")
    	public void updatePassword(@RequestBody UpdatePasswordForm udf) {
    		
    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    		String loggedUsername = auth.getName();
    		
    		Appuser user=userdao.findUserByEmail(loggedUsername);
    		
    		
    	
    		 if(!BCrypt.checkpw(udf.getOldpassword(), user.getPassword()))
    		
    			 throw new RuntimeException("ancien mot de passe incorrect");
    	 if(!udf.getPassword().equals(udf.getRepassword())) {
    			throw new RuntimeException("vous devez confirmer mot de passe");
    		}
    		
    		user.setPassword(udf.getPassword());
    			
    		
    			accountService.saveUser(user);
    		
    		
    		
    	}
    	
    	
    	
    	
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
    		+"'>lien web</a>");
    		em.setTo_address(email);
    		em.setSubject("validation");
    		

    			try {
    				sendMail.sendmail(em,username,password);
    				Appuser appUser=accountService.findUserByEmail(email);
    				
    				
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
    				  throw new RuntimeException("vous devez valider mot de passe");
    			  
    			  Appuser appUser=accountService.findByTokenRecovery(fpf.getToken());
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
    	
    	@RequestMapping(value="/admin/valideDemande", method = RequestMethod.GET)
    	
		public int valideDemande(@RequestParam("id") Long id) {
    		
    		Appuser manager=userdao.findOne(id);
    			createDirectoryService.CreateDirectory("entreprise/"+manager.getNomEntreprise()+"/offres");
			createDirectoryService.CreateDirectory("entreprise/"+manager.getNomEntreprise()+"/profile");
    		
		return accountService.valideCompteManager(id);	
		}
    	
    	
    	
    	
@RequestMapping(value="admin/refuseDemande", method = RequestMethod.GET)
    	
		public void refuseDemande(@RequestParam("id") Long id) {
		 userdao.delete(id);	
		}
@PostMapping("/envoyerContact")
public void envoyerContact(@RequestBody ContactForm contactForm) {
	EmailMessages em = new EmailMessages();
	em.setBody("commentaire cvthec Iset Sousse<br> "+"Nom et Prenom:"+contactForm.getNp()+"<br>email: "+contactForm.getEmail()+"<br>"+
	"commentaire:"+contactForm.getCommentaire());
	em.setTo_address("achraf.ben.abdeljalil@gmail.com");
	em.setSubject("commentaire cvthec Iset Sousse");
	

		try {
			sendMail.sendmail(em,username,password);
			

			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}

@PostMapping("/admin/supprimerEtudiant")
public void supprimerEtudiant(@RequestBody Long id) {
	String email=userdao.findOne(id).getEmail();
	userdao.delete(id);
	EmailMessages em = new EmailMessages();
	em.setBody("Votre compte chez cvthec Iset Sousse a été supprimé");
	em.setTo_address(email);
	em.setSubject("Suppression Compte cvthec Iset Sousse");
	

		try {
			sendMail.sendmail(em,username,password);
			

			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}


@PostMapping("/supprimerMessage")
public void supprimerOffre(@RequestBody Long id) {
	accountService.deleteMessage(id);
	
}
@RequestMapping(value="/nbrMessageRecu",method=RequestMethod.GET)
public Long nbrMessageRecu(@RequestParam(name="id")	Long id) 
{
return accountService.nbrMessagesRecu(id);	
}
@RequestMapping(value="/nbrMessageEnvoye",method=RequestMethod.GET)
public Long nbrMessageEnvoye(@RequestParam(name="id")	Long id) 
{
return accountService.nbrMessagesEnvoye(id);	
}   	
@RequestMapping(value="/nbrOffres",method=RequestMethod.GET)
public Long nbrOffres(@RequestParam(name="id")	Long id) 
{
return offreService.nbrOffres(id);	
} 
@RequestMapping(value="/nbrQuizs",method=RequestMethod.GET)
public Long nbrQuizs(@RequestParam(name="id")	Long id) 
{
return qcmService.nbrQuizs(id);
} 
@RequestMapping(value="/nbrQuizs",method=RequestMethod.GET)
public Long nbrQuizsRepondus(@RequestParam(name="id")	Long id) 
{
return qcmService.nbrQuizs(id);
} 
@RequestMapping(value="/nbrEntreprises",method=RequestMethod.GET)
public Long nbrEntreprises() 
{
return userdao.nbrManager();
}    	
}
