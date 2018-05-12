package org.sid.web;

import org.sid.dao.FormationRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.Appuser;
import org.sid.entities.Competance;
import org.sid.entities.Experience;
import org.sid.entities.Formation;
import org.sid.entities.Language;
import org.sid.form.CompetanceForm;
import org.sid.form.ExperienceForm;
import org.sid.form.FormationForm;
import org.sid.form.LangueForm;
import org.sid.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProfileRestController {
	@Autowired
	AccountService accountService;
	@Autowired
	EtudiantService etudiantService;
	@Autowired
	UserRepository userdao;
	@PostMapping("etudiant/addExperience")
	public void addExperience(@RequestBody ExperienceForm experienceForm) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		
		Appuser etudiant=userdao.findUserByEmail(loggedUsername);
		
		
		
		Experience experience=new Experience();
		experience.setEtudiant(etudiant);
		experience.setDate1(experienceForm.getDate1());
		experience.setDate2(experienceForm.getDate2());
	
		experience.setEntreprise(experienceForm.getEntreprise());
		experience.setLieu(experienceForm.getLieu());
		experience.setDescription(experienceForm.getDescription());
		experience.setTitre(experienceForm.getTitre());
		etudiantService.addExperience(experience);
		
	}
	@PostMapping("/etudiant/deleteExperience")
	public void deleteExperience(@RequestBody Long id) {
		etudiantService.deleteExperience(id);
	}
	@PostMapping("/etudiant/updateExperience")
	public void updateExperience(@RequestBody ExperienceForm experience) {
		etudiantService.updateExperience(experience);
	}
	
	
	
	@PostMapping("etudiant/addCompetance")
	public void addCompetance(@RequestBody CompetanceForm competanceForm) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		
		Appuser etudiant=userdao.findUserByEmail(loggedUsername);
		
		Competance competance=new Competance();
		
		competance.setCertificat(competanceForm.getCertificat());
		competance.setId(competanceForm.getId());
		competance.setName(competanceForm.getName());
		competance.setNote(competanceForm.getNote());
		competance.setEtudiant(etudiant);
		
		
		
		
		etudiantService.addCompetance(competance);
		
	}
	@PostMapping("/etudiant/deleteCompetance")
	public void deleteCompetance(@RequestBody Long id) {
		etudiantService.deleteCompetance(id);
	}
	@PostMapping("/etudiant/updateCompetance")
	public void updateCompetance(@RequestBody CompetanceForm competance) {
		etudiantService.updateCompetance(competance);
	}
	
	
	
	@PostMapping("etudiant/addLangue")
	public void addLangue(@RequestBody LangueForm langueForm) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		
		Appuser etudiant=userdao.findUserByEmail(loggedUsername);
		
		Language langue=new Language();
		
		
		langue.setNiveau(langueForm.getNiveau());
		langue.setNom(langueForm.getNom());
		langue.setEtudiant(etudiant);
		
		
		
		
		
		etudiantService.addLangue(langue);
		
	}
	@PostMapping("/etudiant/deleteLangue")
	public void deleteLangue(@RequestBody Long id) {
		etudiantService.deleteLangue(id);
	}
	@PostMapping("/etudiant/updateLangue")
	public void updateLangue(@RequestBody LangueForm langue) {
		etudiantService.updateLangue(langue);
	}
	
	
	
	@PostMapping("etudiant/addFormation")
	public void addFormation(@RequestBody FormationForm formationForm) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		
		Appuser etudiant=userdao.findUserByEmail(loggedUsername);
		
		Formation formation=new Formation();
		
		formation.setDate1(formationForm.getDate1());
		formation.setDate2(formationForm.getDate2());
		formation.setEtudiant(etudiant);
		formation.setSection(formationForm.getSection());
		formation.setSpecialite(formationForm.getSpecialite());
		formation.setUniversite(formationForm.getUniversite());
		formation.setResult(formationForm.getResult());
		
		etudiantService.addFormation(formation);
	}
	@PostMapping("etudiant/deleteFormation")
	public void deleteFormation(@RequestBody Long id) {
		etudiantService.deleteFormation(id);
	}
	@PostMapping("/etudiant/updateFormation")
	public void updateFormation(@RequestBody FormationForm formationForm) {
		etudiantService.updateFormation(formationForm);
	}
	
	
}
