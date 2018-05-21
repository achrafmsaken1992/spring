package org.sid.service;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.sid.dao.MessagerieRepository;
import org.sid.dao.RoleRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.Appuser;
import org.sid.entities.Messagerie;
import org.sid.form.MessagerieForm;
import org.sid.entities.AppRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@Service
@Transactional

public  class AccountServiceImpl implements AccountService{

	@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private MessagerieRepository messagerieRepository;
		@Override
		public Appuser saveUser(Appuser user) {
		
			String hash=bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(hash);
			
			// TODO Auto-generated method stub
			return userRepository.save(user);
		}

		@Override
		public AppRole saveRole(AppRole role) {
			AppRole appRole=roleRepository.findRoleByRoleName(role.getRoleName());
			if(appRole!=null) {
				return appRole;
			}
			else {
			return roleRepository.save(role);
			}
		}
		

		@Override
		public void addRoleToUser(String username, String roleName) {
			
	AppRole appRole=roleRepository.findRoleByRoleName(roleName);

Appuser appUser=userRepository.findUserByEmail(username);


	appUser.getRoles().add(appRole);
		}

		@Override
		public Long countuser() {
			// TODO Auto-generated method stub
			return roleRepository.count();
		}

		
	
			@Override
			public Appuser findUserByEmail(String email) {
				// TODO Auto-generated method stub
			return	userRepository.findUserByEmail(email);
			}

			@Override
			public Appuser findUserByNomEntreprise(String nom) {
				return userRepository.findUserByNomEntreprise(nom);
			}

			@Override
			public Appuser findUserByToken(String token) {
				// TODO Auto-generated method stub
				return userRepository.findUserByToken(token);
			}

			@Override
			public Page<Appuser> getManagers(String nom,String prenom,String nomEntreprise, int active, int valide, Pageable pageable) {
				// TODO Auto-generated method stub
				return userRepository.getManager(nom,prenom,nomEntreprise, active, valide, pageable);
			}

			@Override
			public Appuser findUserById(Long id) {
				// TODO Auto-generated method stub
				return userRepository.findOne(id);
			}

			@Override
			public void updateImage(String image, Long id) {
				userRepository.updateImage(image, id);
				
			}

			@Override
			public List<Appuser> getManagersMessagerie(String mot) {
				
				return userRepository.getManagersMessagerie(mot);
			}

			@Override
			public List<Messagerie> messageries(Long user1, Long user2) {
				// TODO Auto-generated method stub
				return messagerieRepository.getMessagerie(user1,user2);
			}

			@Override
			public void addMessagerie(MessagerieForm messagerieForm) {
				Appuser user1=userRepository.findOne(messagerieForm.getUser1());
				Appuser user2=userRepository.findOne(messagerieForm.getUser2());
				Messagerie messagerie=new Messagerie();
				messagerie.setDate(new Date());
				messagerie.setUser1(user1);
				messagerie.setUser2(user2);
				messagerie.setMessage(messagerieForm.getMessage());
				messagerieRepository.save(messagerie);
				
			}

			@Override
			public Page<Messagerie> getMessageriesCall(Long user, Pageable pageable) {
				// TODO Auto-generated method stub
				return messagerieRepository.getMessageriesCall(user, pageable);
			}
		

		


	
}



	
	
	
	



