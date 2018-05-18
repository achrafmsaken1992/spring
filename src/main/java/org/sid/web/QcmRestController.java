package org.sid.web;

import java.util.List;

import org.sid.dao.SuggestionRepository;
import org.sid.entities.Appuser;
import org.sid.entities.Note;
import org.sid.entities.Qcm;
import org.sid.entities.Question;
import org.sid.entities.Suggestion;
import org.sid.form.EtudiantForm;
import org.sid.form.QcmForm;
import org.sid.form.QuestionForm;
import org.sid.form.ReponseForm;
import org.sid.form.SuggestionForm;
import org.sid.service.QcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
@RestController
@CrossOrigin
public class QcmRestController {
	@Autowired
	QcmService qcmService;
	@PostMapping("/manager/addQcm")
	public void addQcm(@RequestBody QcmForm qcmForm) {
		qcmService.addQcm(qcmForm);
	}
	
	@RequestMapping(value="/getQcmsByOffre",method=RequestMethod.GET)
	public List<Qcm> getQcms(
			
			@RequestParam(name="id")	Long id){
				return qcmService.getQcmsByOffre(id);
		
		
	}
	@PostMapping("/manager/deleteQuiz")
	public void deleteQcms(@RequestBody Long id) {
		qcmService.deleteQcm(id);
		
	}
	@PostMapping("/manager/updateQuiz")
	public void updateQcms(@RequestBody QcmForm QcmForm) {
		qcmService.updateQcm(QcmForm);
	}
	
	
	
	
	@PostMapping("/manager/addQuestion")
	public void addQuestion(@RequestBody QuestionForm questionForm) {
		qcmService.addQuestion(questionForm);
	}
	@RequestMapping(value="/getQuestionsByQcm",method=RequestMethod.GET)
	public List<Question> getQuestions(
			
			@RequestParam(name="id")	Long id){
				return qcmService.getQuestionByQcm(id);
		
		
	}

	/*
	@RequestMapping(value="/etudiant/getQcms",method=RequestMethod.GET)
	public Page<Appuser> getManagers(
			@RequestParam(name="nom",defaultValue="")	String nom,
			@RequestParam(name="prenom",defaultValue="")	String prenom,
			@RequestParam(name="nomEntreprise",defaultValue="")	String nomEntreprise,
			@RequestParam(name="active",defaultValue="1")	int active,
			@RequestParam(name="valide",defaultValue="1")	int valide,
			
			
			@RequestParam(name="page",defaultValue="0")	int page,
			@RequestParam(name="size",defaultValue="5")	int size)
   {
		
	
		return accountService.getManagers("%"+nom+"%","%"+prenom+"%","%"+nomEntreprise+"%", active, valide, new PageRequest(page, size));
   }
	*/
	
	
	@PostMapping("/manager/deleteQuestion")
	public void deleteQuestion(@RequestBody Long id) {
		qcmService.deleteQuestion(id);
		
	}
	@PostMapping("/manager/updateQuestion")
	public void updateQuestion(@RequestBody QuestionForm questionForm) {
		qcmService.updateQuestion(questionForm);
	}
	
	@PostMapping("/manager/addSuggestion")
	public void addSuggestion(@RequestBody SuggestionForm suggestionForm) {
		qcmService.addSuggestion(suggestionForm);
	}
	@RequestMapping(value="/getSuggestionsByQuestion",method=RequestMethod.GET)
	public List<Suggestion> getSuggestions(
			
			@RequestParam(name="id")	Long id){
				return qcmService.getSuggestionByQuestion(id);
		
		
	}
	@PostMapping("/manager/deleteSuggestion")
	public void deleteSuggestion(@RequestBody Long id) {
		qcmService.deleteSuggestion(id);
		
	}
	@PostMapping("/manager/updateSuggestion")
	public void updateSuggestion(@RequestBody SuggestionForm suggestionForm) {
		qcmService.updateSuggestion(suggestionForm);
	}
	@PostMapping("/etudiant/addReponse")
	public void addReponse(@RequestBody ReponseForm reponseForm) {
		qcmService.addReponse(reponseForm);
	}
	@RequestMapping(value="/findNoteByqcmByetudiant",method=RequestMethod.GET)
	public Note findNoteByqcmByetudiant(
			@RequestParam(name="etudiant")	Long etudiant,
			@RequestParam(name="qcm")	Long qcm)
			
			{
				return qcmService.findNoteByqcmByetudiant(etudiant,qcm);
		
		
	}
	@RequestMapping(value="/getQuestions",method=RequestMethod.GET)
	public Page<Question> getEtudiants(
			@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="qcm")	Long qcm)
   {
		
	
		return qcmService.getQuestions(qcm, new PageRequest(page, 1));
		
		
   }
	@RequestMapping(value="/getSuggestionReponse",method=RequestMethod.GET)
	public int getSuggestionReponse(
			@RequestParam(name="reponse") 	String reponse,
			@RequestParam(name="question") 	Long id)
   {
		int a=0;
		String resultat="";
	List<Suggestion> suggestions=qcmService.getSuggestionByQuestion(id);
		for(Suggestion sug:suggestions) {
			resultat+=sug.getReponse();
		}
		if(resultat.equals(reponse)) {
			a=1;
		}
	
		return a;
		
		
   }
	
	
}
