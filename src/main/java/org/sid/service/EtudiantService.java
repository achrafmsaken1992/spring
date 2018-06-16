package org.sid.service;

import java.util.List;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EtudiantService {
	public Page<Appuser> getEtudiants(String nom,String prenom,int active,int valide,Pageable pageable);
	public void addExperience(Experience experience);
	public void deleteExperience(Long id);
	public void updateExperience(ExperienceForm experience);
	
	public void addCompetance(Competance competance);
	public void deleteCompetance(Long id);
	public void updateCompetance(CompetanceForm competance);
	
	public void addLangue(Language langue);
	public void deleteLangue(Long id);
	public void updateLangue(LangueForm langue);
	
	public void addFormation(Formation formation);
	public void deleteFormation(Long id);
	public void updateFormation(FormationForm formationForm);
	public List<Appuser>getEtudiantMessagerie(String mot);
	public List<String>getTitreCompetances();
	public void updateResume(Long id, String resume);
	public List<String>getLangues();
	public void updateCv(String cv,Long id);
}
