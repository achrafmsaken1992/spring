package org.sid.service;

import java.util.List;

import javax.transaction.Transactional;

import org.sid.dao.CompetanceRepository;
import org.sid.dao.ExperienceRepository;
import org.sid.dao.FormationRepository;
import org.sid.dao.LangueRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.Appuser;
import org.sid.entities.Competance;
import org.sid.entities.Experience;
import org.sid.entities.Formation;
import org.sid.entities.Language;
import org.sid.form.CompetanceForm;
import org.sid.form.EtudiantForm;
import org.sid.form.ExperienceForm;
import org.sid.form.FormationForm;
import org.sid.form.LangueForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
@Transactional
public  class EtudiantServiceImpl implements EtudiantService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ExperienceRepository experienceRepository;
	@Autowired 
	private FormationRepository formationRepository;
	@Autowired
	private LangueRepository langueRepository;
	@Autowired
	private CompetanceRepository competanceRepository;
	
	
	
	@Override
	public Page<Appuser> getEtudiants(String nom, String prenom, int active, int valide, Pageable pageable) {
		// TODO Auto-generated method stub
		return userRepository.getEtudiant(nom, prenom, active, valide, pageable);
	}
	@Override
	public void addExperience(Experience experience) {
		experienceRepository.save(experience);
		
	}
	@Override
	public void deleteExperience(Long id) {
		experienceRepository.delete(id);
		
	}
	@Override
	public void updateExperience(ExperienceForm experienceForm) {
		Experience experience=experienceRepository.findOne(experienceForm.getId());
		experience.setDate1(experienceForm.getDate1());
		experience.setDate2(experienceForm.getDate2());
		experience.setDescription(experienceForm.getDescription());
		experience.setEntreprise(experienceForm.getEntreprise());
		experience.setMotCle(experienceForm.getMotCle());
	
		experience.setTitre(experienceForm.getTitre());
		
		experienceRepository.save(experience);
		
	}
	@Override
	public void addCompetance(Competance competance) {
		competanceRepository.save(competance);
		
	}
	@Override
	public void deleteCompetance(Long id) {
		competanceRepository.delete(id);
		
		
	}
	@Override
	public void updateCompetance(CompetanceForm competanceForm) {
		Competance competance=competanceRepository.findOne(competanceForm.getId());
		competance.setCertificat(competanceForm.getCertificat());
		competance.setName(competanceForm.getName());
		competance.setNote(competanceForm.getNote());
		
		competanceRepository.save(competance);
	}
	@Override
	public void addLangue(Language langue) {
		langueRepository.save(langue);
		
	}
	@Override
	public void deleteLangue(Long id) {
		langueRepository.delete(id);
		
	}
	@Override
	public void updateLangue(LangueForm langueForm) {
		Language langue=langueRepository.findOne(langueForm.getId());
		
		langue.setNiveau(langueForm.getNiveau());
		langue.setNom(langueForm.getNom());
		langueRepository.save(langue);
	}
	@Override
	public void addFormation(Formation formation) {
		formationRepository.save(formation);
		
	}
	@Override
	public void deleteFormation(Long id) {
		formationRepository.delete(id);
		
	}
	@Override
	public void updateFormation(FormationForm formationForm) {
		Formation formation=formationRepository.findOne(formationForm.getId());
		
		formation.setDate1(formationForm.getDate1());
		formation.setDate2(formationForm.getDate2());
		formation.setResult(formationForm.getResult());
		formation.setSection(formationForm.getSection());
		formation.setSpecialite(formationForm.getSpecialite());
		formation.setUniversite(formationForm.getUniversite());
		
		formationRepository.save(formation);
		
	}
	@Override
	public List<Appuser> getEtudiantMessagerie(String mot) {
		// TODO Auto-generated method stub
		return userRepository.getEtudiantMessagerie(mot);
	}
	@Override
	public List<String> getTitreCompetances() {
		// TODO Auto-generated method stub
		return competanceRepository.getCompetances();
	}
	@Override
	public void updateResume(Long id, String resume) {
		this.userRepository.updateResume(resume, id);
		
	}
	@Override
	public List<String> getLangues() {
		// TODO Auto-generated method stub
		return langueRepository.getLangues();
	}
	@Override
	public void updateCv(String cv, Long id) {
		userRepository.updateCv(cv, id);
		
	}
	

}
