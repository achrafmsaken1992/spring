package org.sid.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.sid.dao.RoleRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.sid.form.EmailMessages;
import org.sid.form.RhForm;
import org.sid.form.ProfileForm;
import org.sid.service.AccountService;
import org.sid.service.CreateDirectoryService;
import org.sid.service.SendmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin

public class RhRestController {
	@Value("${gmail.username}")
	private String username;
	@Value("${gmail.password}")
	private String password;
	@Autowired
	AccountService accountService;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userdao;
	@Autowired 
	CreateDirectoryService createDirectory;
	@Autowired
	SendmailService sendmailService;
	
@PostMapping("registerRh")
	public void registerRh(@RequestBody RhForm rhForm) {
	AppUser app=accountService.findUserByEmail(rhForm.getEmail());
	if(app!=null)throw new RuntimeException("email exist deja");
	
	
	AppUser rhUser=new AppUser();
	rhUser.setEmail(rhForm.getEmail());
	rhUser.setPassword(rhForm.getPassword());
	rhUser.setDateCreation(new Date());
	rhUser.setFirstName(rhForm.getFirstName());
	rhUser.setLastName(rhForm.getLastName());
	rhUser.setPhoneUser(rhForm.getPhone());
	rhUser.setActive(1);
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String loggedUsername = auth.getName();
	org.sid.entities.AppUser use2r=userdao.findByEmail(loggedUsername);
	
	
	rhUser.setCompanyName(use2r.getCompanyName());
	rhUser.setAppUser(accountService.findUserByEmail(loggedUsername));
	AppUser rhUser2=	accountService.saveUser(rhUser);
	
	
	if(roleRepository.findRoleByRoleName("URH")==null) {
		accountService.saveRole(new AppRole(null,"URH"));
		
	}


	accountService.addRoleToUser(rhForm.getEmail(),"URH");
	
	EmailMessages em=new EmailMessages();
	em.setSubject("rh de la societe "+use2r.getCompanyName());
	em.setTo_address(rhForm.getEmail());
	em.setBody("login:"+rhForm.getEmail()+"\n"+"mot de passe:"+rhForm.getPassword());
	try {
		sendmailService.sendmail(em, username, password);
		
		} catch (MessagingException e) {
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	createDirectory.CreateDirectory(use2r.getCompanyName()+"/Employees/"+rhUser2.getId());
	
}






//get List Rh

@RequestMapping(value="/getListRh",method=RequestMethod.GET)

public List<ProfileForm> getListRh(){
	
	
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String managerEmail = auth.getName();
	

	
	
	return accountService.getUsersByRole(managerEmail, "URH");
	
	
	
	
}











	
}
