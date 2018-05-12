package org.sid.service;

import java.util.List;

import javax.transaction.Transactional;

import org.sid.dao.CandidateRepository;
import org.sid.dao.CertificateRepository;
import org.sid.dao.SkillRepository;
import org.sid.dao.ExperienceRepository;
import org.sid.dao.TrainingRepository;
import org.sid.dao.LanguageRepository;
import org.sid.dao.ProjectRepository;
import org.sid.dao.ResumeRepository;
import org.sid.entities.AppUser;
import org.sid.entities.Candidate;
import org.sid.entities.Certificate;
import org.sid.entities.Skill;
import org.sid.entities.Experience;
import org.sid.entities.Training;
import org.sid.entities.Language;
import org.sid.entities.Project;
import org.sid.entities.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CandidatServiceImpl implements CandidatService{
@Autowired
CandidateRepository	candidatRepository;
@Autowired
TrainingRepository formationRepository;
@Autowired
ExperienceRepository experienceRepository;
@Autowired
LanguageRepository langueRepository;
@Autowired
SkillRepository competanceRepository;
@Autowired
ResumeRepository resumeRepository;
@Autowired 
ProjectRepository projectRepository;
@Autowired 
CertificateRepository certificateRepository;
Long idCandidate=null;
	
	@Override
	public Candidate savecandidat(Candidate candidat) {
		return candidatRepository.save(candidat);
	}

	@Override
	public Candidate findCandidatByEmail(String email1, String email2) {
		
		return candidatRepository.getCandidatByemail(email1, email2);
	}

	




	@Override
	public Candidate getCandidatById(Long id) {
		// TODO Auto-generated method stub
		return candidatRepository.findOne(id);
	}

	@Override
	public void addCompetance(Skill skill) {
		competanceRepository.save(skill);
		
	}

	@Override
	public void addFormation(Training training) {
		formationRepository.save(training);
		
	}

	@Override
	public void addLangue(Language language) {
		 langueRepository.save(language);
		
	}

	@Override
	public void addExperience(Experience experience) {
		experienceRepository.save(experience);
		
	}

	@Override
	public Candidate findByCandidat_id(Long id) {
		// TODO Auto-generated method stub
		return candidatRepository.findBycandidateId(id);
	}

	@Override
	public void deleteCompetance(Long id) {
		// TODO Auto-generated method stub
		competanceRepository.delete(id);
	}

	@Override
	public void deleteFormation(Long id) {
		// TODO Auto-generated method stub
		formationRepository.delete(id);
	}

	@Override
	public void deleteLangue(Long id) {
	langueRepository.delete(id);
		
	}

	@Override
	public void deleteExperience(Long id) {
		experienceRepository.delete(id);
		
	}

	@Override
	public List<String> findTitles(String y) {
		// TODO Auto-generated method stub
		
		return candidatRepository.findTitles(y);
	}

	@Override
	public Page<Candidate> getCandidats(String firstname, String lastname, String title, String email,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return candidatRepository.getCandidats(firstname,lastname,title,email, pageable);
	}

	@Override
	public void updateCompetance(Skill skill) {
		Skill s=competanceRepository.findOne(skill.getId());
		skill.setCandidate(s.getCandidate());
		// TODO Auto-generated method stub
		competanceRepository.save(skill);
	}

	@Override
	public void updateFormation(Training training) {
	Training train=formationRepository.findOne(training.getId());
		training.setCandidat(train.getCandidate());
		
		
		formationRepository.save(training);
		
	}

	@Override
	public void updateLangue(Language language) {
		Language lang= langueRepository.findOne(language.getId());
		language.setCandidate(lang.getCandidate());
		
		langueRepository.save(language);
		
	}

	@Override
	public void updateExperience(Experience experience) {
		 Experience exp;
		 exp=experienceRepository.findOne(experience.getId());
		
		 experience.setCandidate(exp.getCandidate());
		
	experienceRepository.save(experience);
		
	}

	@Override
	public void updatePhotoCandidate(String photo, Long id) {
		candidatRepository.updatePhotoCandidate(photo, id);
		
	}

	@Override
	public void addResume(Resume resume) {
		resumeRepository.save(resume);
		
	}

	@Override
	public void deleteResume(Long id) {
		resumeRepository.delete(id);
		
	}

	@Override
	public void updateResume(Resume resume) {
		Resume auxResume=resumeRepository.findOne(resume.getId());
		auxResume.setResume(resume.getResume());
		
		
		
		resumeRepository.save(auxResume);
	}

	@Override
	public void addProject(Project project) {
		projectRepository.save(project);
		
	}

	@Override
	public void deleteProject(Long id) {
		projectRepository.delete(id);
		
	}

	@Override
	public void updateProject(Project project) {
		Project auxProject=projectRepository.findOne(project.getId());
		auxProject.setLink(project.getLink());
		auxProject.setTitle(project.getTitle());
		
		
		
		projectRepository.save(auxProject);
		
	}

	@Override
	public void addCertificate(Certificate certificate) {
		certificateRepository.save(certificate);
		
	}

	@Override
	public void deleteCertificate(Long id) {
		certificateRepository.delete(id);
		
	}

	@Override
	public void updateCertificate(Certificate certificate) {
		Certificate auxCertificate=certificateRepository.findOne(certificate.getId());
	
		
		auxCertificate.setLink(certificate.getLink());
		auxCertificate.setTitle(certificate.getTitle());
		
		
	
		certificateRepository.save(auxCertificate);
		
	}

	

	

	

	

}
