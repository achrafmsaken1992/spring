package org.sid.service;

import java.util.*;

import org.sid.entities.Appuser;
import org.sid.entities.Messagerie;
import org.sid.entities.AppRole;

import org.sid.form.ManagerForm;
import org.sid.form.MessagerieForm;
import org.sid.form.ProfileForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface AccountService {

	AppRole saveRole(AppRole role);

	void addRoleToUser(String username, String roleName);

	Appuser saveUser(Appuser user);
Long countuser();
public Appuser findUserByEmail(String email);
public Appuser findUserByNomEntreprise(String nom);
public Appuser findUserByToken(String token);
public Page<Appuser> getManagers(String nom,String prenom,String nomEntreprise,int active,int valide,Pageable pageable);
public List<Appuser> getManagersMessagerie(String mot);
public Appuser findUserById(Long id);
public void updateImage(String image,Long id);
public List<Messagerie> messageries(Long user1,Long user2);
public void addMessagerie(MessagerieForm messagerieForm);
public Page<Messagerie> getMessageriesCall(Long user,Pageable pageable);
void recoveryPassword(String tokenRecovery, String dateExpiration, Long id);
public Appuser findByTokenRecovery(String token);
public int valideCompteManager(Long id);
public void updateResume(Long id,String resume);
public Page<Appuser>RechEtudiant(String nom,String prenom,String langue,
		String competance,String experience,String formation,
		int active,int valide,Pageable pageable);
public void deleteMessage(Long id);
public Long nbrMessagesRecu(Long id);
public Long nbrMessagesEnvoye(Long id);
public Long nbrEntreprises(Long id);
}
