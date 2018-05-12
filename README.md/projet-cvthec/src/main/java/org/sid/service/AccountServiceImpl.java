package org.sid.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.sid.dao.RoleRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.sid.form.ManagerForm;
import org.sid.form.ProfileForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Service
@Transactional
public  class AccountServiceImpl implements AccountService{

	@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;

@Autowired
private UserRepository userRepository;
@Autowired
private RoleRepository roleRepository;

	@Override
	public AppUser saveUser(AppUser user) {
	
		String hash=bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hash);
		
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}
	

	@Override
	public void addRoleToUser(String username, String roleName) {
		
AppRole appRole=roleRepository.findRoleByRoleName(roleName);

AppUser appUser=userRepository.findByEmail(username);


appUser.getRoles().add(appRole);
	}

	
	
	@Override
	public AppUser findUserByEmail(String email) {
		// TODO Auto-generated method stub
	return	userRepository.findByEmail(email);
	}

	@Override
	public long countuser() {
		return roleRepository.count();
	}

	@Override
	public AppUser findByTokenRecovery(String token) {
		// TODO Auto-generated method stub
		return userRepository.findByTokenRecovery(token);
	}

	@Override
	public List<ProfileForm> getUsersByRole(String managerEmail, String role) {
	AppRole ro;
		Boolean isRh;
	
			List<AppUser>allUsers=new ArrayList<AppUser>();
			allUsers=userRepository.findUserByManagerEmail(managerEmail);
			
			
			List<ProfileForm>usersByrole=new ArrayList<ProfileForm>();
			
			for(int i=0;i<allUsers.size();i++) {
			isRh=false;
			for( AppRole r :allUsers.get(i).getRoles()) {
				if(r.getRoleName().equals("URH"))
					isRh=true;
			}
		if(isRh==true)
		{
					
					ProfileForm profile=new ProfileForm();
					profile.setCompanyName(allUsers.get(i).getCompanyName());
					profile.setEmail(allUsers.get(i).getEmail());
					profile.setFirstName(allUsers.get(i).getFirstName());
					profile.setLastName(allUsers.get(i).getLastName());
					profile.setPhoneUser(allUsers.get(i).getPhoneUser());
					profile.setPhoto(allUsers.get(i).getPhoto());
					profile.setId(allUsers.get(i).getId());
					
					
					usersByrole.add(profile);
				
			}
		}
			
			return usersByrole;
	
	
	}

	
	@Override
	public void recoveryPassword(String tokenRecovery, String dateExpiration, Long id) {
		userRepository.recoveryPassword(tokenRecovery, dateExpiration, id);
	}

	@Override
	public void updatePhoto(Long id,String photo) {
		userRepository.updatePhoto(photo, id);
		
	}

	@Override
	public AppUser findByCompanyName(String companyName) {
		// TODO Auto-generated method stub
		return userRepository.findByCompanyName(companyName);
	}

	@Override
	public List<ManagerForm> getManagers() {
		Boolean isManager;
		List<ManagerForm>managerProfiles=new ArrayList<ManagerForm>();
		List<AppUser>allUsers=new ArrayList<AppUser>();
		allUsers=userRepository.findAll();
		
		
		List<ProfileForm>usersByrole=new ArrayList<ProfileForm>();
		
		for(int i=0;i<allUsers.size();i++) {
		isManager=false;
		for( AppRole r :allUsers.get(i).getRoles()) {
			if(r.getRoleName().equals("MANAGER"))
				isManager=true;
		}
	if(isManager==true)
	{
				
				ManagerForm profile=new ManagerForm();
				profile.setCompanyName(allUsers.get(i).getCompanyName());
				profile.setEmail(allUsers.get(i).getEmail());
				profile.setFirstName(allUsers.get(i).getFirstName());
				profile.setLastName(allUsers.get(i).getLastName());
				profile.setPhoneUser(allUsers.get(i).getPhoneUser());
				profile.setPhoto(allUsers.get(i).getPhoto());
				profile.setId(allUsers.get(i).getId());
				profile.setActive(allUsers.get(i).getActive());
				
				
				managerProfiles.add(profile);
			
		}
	}
		
		return managerProfiles;

	}

	@Override
	public void refuseSignUp(Long id) {
	
		userRepository.delete(id);
	}

	@Override
	public void acceptSignUp(Long id) {
		userRepository.ActiveSociaty(id);
		
	}
	
	


	

	
}



	
	
	
	



