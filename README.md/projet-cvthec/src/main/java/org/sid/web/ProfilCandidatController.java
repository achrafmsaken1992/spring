package org.sid.web;

import java.util.List;

import org.sid.dao.UserRepository;
import org.sid.entities.AppUser;
import org.sid.entities.Candidate;
import org.sid.entities.Certificate;
import org.sid.entities.Skill;
import org.sid.entities.Experience;
import org.sid.entities.Training;
import org.sid.entities.Language;
import org.sid.entities.Project;
import org.sid.entities.Resume;
import org.sid.form.SkillForm;
import org.sid.form.CertificateForm;
import org.sid.form.ExperienceForm;
import org.sid.form.TrainingForm;
import org.sid.form.LangueForm;
import org.sid.form.ProjectForm;
import org.sid.form.ResumeForm;
import org.sid.service.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProfilCandidatController {
	@Autowired
	CandidatService candidatService;
	@Autowired
	UserRepository userdao;

	@PostMapping("/addExperience")
	public void addExperience(@RequestBody ExperienceForm experienceForm) {
		Candidate candidate=candidatService.findByCandidat_id(experienceForm.getCandidateId());
		Experience experience=new Experience();
		experience.setCandidate(candidate);
		experience.setDate1(experienceForm.getDate1());
		experience.setDate2(experienceForm.getDate2());
	
		experience.setCompany(experienceForm.getCompany());
		experience.setPlace(experienceForm.getPlace());
		experience.setDescription(experienceForm.getDescription());
		experience.setTitle(experienceForm.getTitle());
		candidatService.addExperience(experience);
		
	}
	@PostMapping("/deleteExperience")
	public void deleteExperience(@RequestBody Long id) {
		candidatService.deleteExperience(id);
	}
	@PostMapping("/deleteCompetance")
	public void deleteCompetance(@RequestBody Long id) {
		candidatService.deleteCompetance(id);
	}
	@PostMapping("/deleteLangue")
	public void deleteLangue(@RequestBody Long id) {
		candidatService.deleteLangue(id);
	}
	@PostMapping("/deleteFormation")
	public void deleteFormation(@RequestBody Long id) {
		candidatService.deleteFormation(id);
	}
	@PostMapping("/deleteResume")
	public void deleteResume(@RequestBody Long id) {
		candidatService.deleteResume(id);
	}
	@PostMapping("/deleteProject")
	public void deleteProject(@RequestBody Long id) {
		candidatService.deleteResume(id);
	}
	
	@PostMapping("/deleteCertificate")
	public void deleteCertificate(@RequestBody Long id) {
		candidatService.deleteCertificate(id);
	}
	
	@PostMapping("/addCompetance")
	public void addCompetance(@RequestBody SkillForm SkillForm) {
		Candidate candidat=candidatService.findByCandidat_id(SkillForm.getCandidateId());
		Skill competance=new Skill();
		
		competance.setCertificat(SkillForm.getCertificat());
		competance.setName(SkillForm.getName());
		competance.setNote(SkillForm.getNote());
		competance.setCandidate(candidat);
		
		
		candidatService.addCompetance(competance);
		
	}
	@PostMapping("/addResume")
	public void addResume(@RequestBody ResumeForm resumeForm) {
		Candidate candidate=candidatService.findByCandidat_id(resumeForm.getCandidateId());
		Resume resume=new Resume();
		resume.setResume(resumeForm.getResume());
		resume.setCandidate(candidate);
		
		
		
		candidatService.addResume(resume);
		
	}
	
	@PostMapping("/addCertificate")
	public void addCertificate(@RequestBody CertificateForm certicateForm) {
		Candidate candidate=candidatService.findByCandidat_id(certicateForm.getCandidat_id());
		Certificate certificate=new Certificate();
		certificate.setTitle(certicateForm.getTitle());
		certificate.setLink(certicateForm.getLink());
		
		certificate.setCandidate(candidate);
		
		
		
		candidatService.addCertificate(certificate);
		
	}
	
	
	@PostMapping("/addProject")
	public void addProject(@RequestBody ProjectForm projectForm) {
		Candidate candidate=candidatService.findByCandidat_id(projectForm.getCandidateId());
		Project project=new Project();
	
		
		project.setCandidate(candidate);
project.setDescription(projectForm.getDescription());
project.setLink(projectForm.getLink());
		candidatService.addProject(project);
		
	}
	
	
	
	@PostMapping("/addLangue")
	public void addLangue(@RequestBody LangueForm langueForm) {
		Candidate candidate=candidatService.findByCandidat_id(langueForm. getCandidat_id());
		Language language=new Language();
		language.setName(langueForm.getLangue());
		language.setCandidate(candidate);
		language.setLevel(langueForm.getNiveau());
		language.setCandidate(candidate);
		
		
		candidatService.addLangue(language);
		
	}
	
	@PostMapping("/addFormation")
	public void addFormation(@RequestBody TrainingForm formationForm) {
		Candidate candidat=candidatService.findByCandidat_id(formationForm.getCandidateId());
		Training formation=new Training();
		formation.setCandidat(candidat);
		formation.setDate1(formationForm.getDate1());
		
		formation.setDate2(formationForm.getDate2());
		formation.setGraduate(formationForm.getGraduate());
		formation.setFieldStudy(formationForm.getFieldStudy());
		formation.setResult(formationForm.getResult());
		formation.setUniversity(formationForm.getUniversity());
		
		
		candidatService.addFormation(formation);
		
	}
	
	
	
	@RequestMapping(value="/getTitles",method=RequestMethod.GET)
	public List<String> getTitles(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		
		AppUser userRh=userdao.findByEmail(loggedUsername);
		
		
		return candidatService.findTitles("%"+userRh.getAppUser().getEmail()+"%");
	}
	
	
	
	@PostMapping("/updateExperience")
	public void updateExperience(@RequestBody Experience experience) {
		candidatService.updateExperience(experience);
	}
	@PostMapping("/updateCompetance")
	public void updateCompetance(@RequestBody Skill skill) {
		candidatService.updateCompetance(skill);
	}
	@PostMapping("/updateLangue")
	public void updateLangue(@RequestBody Language lang) {
		candidatService.updateLangue(lang);
	}
	@PostMapping("/updateFormation")
	public void updateFormation(@RequestBody Training training) {
		candidatService.updateFormation(training);
	}
	@PostMapping("/updateResume")
	public void updateResume(@RequestBody Resume resume) {
		candidatService.updateResume(resume);
	}
	@PostMapping("/updateProject")
	public void updateProject(@RequestBody Project project) {
		candidatService.updateProject(project);
	}
	@PostMapping("/updateCertificate")
	public void updateCertificate(@RequestBody Certificate certificate) {
		candidatService.updateCertificate(certificate);
	}
	
}
