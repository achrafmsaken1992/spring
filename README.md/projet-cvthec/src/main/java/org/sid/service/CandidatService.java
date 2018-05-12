package org.sid.service;

import java.util.List;


import org.sid.entities.Candidate;
import org.sid.entities.Skill;
import org.sid.entities.Experience;
import org.sid.entities.Certificate;
import org.sid.entities.Training;
import org.sid.entities.Language;
import org.sid.entities.Project;
import org.sid.entities.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CandidatService {
	public Candidate savecandidat(Candidate candidat);
	public Candidate findCandidatByEmail(String email1,String email2);
	
	public Page<Candidate> getCandidats(String firstname,String lastname,String title,String email,Pageable pageable);
public void addCompetance(Skill skill);	
public void addFormation(Training training);
public void addLangue(Language language);
public void addExperience(Experience experience);
public void addResume(Resume resume);
public void addProject(Project project);
public void addCertificate(Certificate certificate);
	public Candidate getCandidatById(Long id);
	
	public Candidate findByCandidat_id(Long id);
	public void deleteCompetance(Long id);	
	public void deleteFormation(Long id);
	public void deleteLangue(Long id);
	public void deleteExperience(Long id);
	public void deleteResume(Long id);
	public void deleteProject(Long id);
	public void deleteCertificate(Long id);
	
	
	public void updateCompetance(Skill skill);
	public void updateFormation(Training training);
	public void updateLangue(Language language);
	public void updateExperience(Experience experience);
	public void updateResume(Resume resume);
	public void updateProject(Project project);
	public void updateCertificate(Certificate certificate);
	
	public List <String> findTitles(String email);
	public void updatePhotoCandidate(String photo,Long id); 
}
