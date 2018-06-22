package org.sid.web;

import java.util.List;

import org.sid.dao.SuggestionRepository;
import org.sid.entities.Appuser;
import org.sid.entities.Note;
import org.sid.entities.Qcm;
import org.sid.entities.Question;
import org.sid.entities.Suggestion;
import org.sid.form.EtudiantForm;
import org.sid.form.NoteForm;
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
	public List<Qcm> getQcms(		@RequestParam(name="id")	Long id){
				return qcmService.getQcmsByOffre(id);
		
		
	}
	
	@RequestMapping(value="/getQcmsByOffreManager",method=RequestMethod.GET)
	public List<Qcm> getQcmsManager(@RequestParam(name="id")	Long id){
				return qcmService.getQcmsByOffreManager(id);
		
		
	}
	//getQcmsByOffre
	
	
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
			)
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
	@PostMapping("etudiant/addNote")
	public Long addNote(@RequestBody NoteForm noteForm) {
		return qcmService.addNote(noteForm);
		
	}
	@RequestMapping(value="/findNoteByqcm",method=RequestMethod.GET)
	public Page<Note> findNoteByqcm(
		
			@RequestParam(name="qcm")	Long qcm,
	@RequestParam(name="page",defaultValue="0")	int page)
			
			{
				return qcmService.findNoteByqcm(qcm,new PageRequest(page,5));
		
		
	}
	@RequestMapping(value="/nbrParticipantsQcm",method=RequestMethod.GET)
	public Long nbrParticipantsQcm(
		
			@RequestParam(name="qcm")	Long qcm)
			
			{
				return qcmService.nbrParticipants(qcm);
		
		
	}
	@RequestMapping(value="/nbrParticipantsReussisQcm",method=RequestMethod.GET)
	public Long nbrParticipantsReussisQcm(
		
			@RequestParam(name="qcm")	Long qcm)
			
			{
				return qcmService.nbrParticipantsReussis(qcm);
		
		
	}
	
	
	
	@RequestMapping(value="/moyenneNoteQcm",method=RequestMethod.GET)
	public int moyenneNoteQcm(
		
			@RequestParam(name="qcm")	Long qcm)
			
			{
				return qcmService.moyenne(qcm);
		
		
	}
	@RequestMapping(value="/meuilleurNoteQcm",method=RequestMethod.GET)
	public int meuilleurNoteQcm(
		
			@RequestParam(name="qcm")	Long qcm)
			
			{
				return qcmService.meuilleurNote(qcm);
		
		
	}
	@RequestMapping(value="/plusMauvaisNoteQcm",method=RequestMethod.GET)
	public int plusMauvaisNote(
		
			@RequestParam(name="qcm")	Long qcm)
			
			{
				return qcmService.plusMauvaisNote(qcm);
		
		
	}
	@RequestMapping(value="/findQuizById",method=RequestMethod.GET)
	public Qcm findQuizById(
		
			@RequestParam(name="qcm")	Long qcm
	)
			
			{
				return qcmService.findQcmById(qcm);
		
		
	}
	
	@RequestMapping(value="/nbrQuestionsByQcm",method=RequestMethod.GET)
	public Long nbrQuestionsByOffre(@RequestParam(name="id")	Long id) 
	{
	return qcmService.nbrQuestionsByQcm(id);	
	}
	@RequestMapping(value="/nbrSuggestionByQuestion",method=RequestMethod.GET)
	public Long nbrSuggestionByQuestion(@RequestParam(name="id")	Long id) 
	{
	return qcmService.nbrSuggestionsByQuestion(id);
	}
	
	
	@RequestMapping(value="/nbrQuizs",method=RequestMethod.GET)
	public Long nbrQuizs() 
	{
	return qcmService.nbrQuizs();
	} 
	@RequestMapping(value="/nbrQuizsRepondus",method=RequestMethod.GET)
	public Long nbrQuizsRepondus(@RequestParam(name="id")	Long id) 
	{
	return qcmService.nbrQuizsrepondus(id);
	} 
	@RequestMapping(value="/nbrQuizsManager",method=RequestMethod.GET)
	public Long nbrQuizsManager(@RequestParam(name="id")	Long id) 
	{
	return qcmService.nbrQuizsManager(id);
	} 
	@RequestMapping(value="/nbrRepQuizsManager",method=RequestMethod.GET)
	public Long nbrRepQuizsManager(@RequestParam(name="id")	Long id) 
	{
	return qcmService.nbrReps(id);
	}
	@RequestMapping(value="/nbrQuizsOffre",method=RequestMethod.GET)
	public Long nbrQuizsOffre(@RequestParam(name="id")	Long id) 
	{
	return qcmService.nbrQuizOffre(id);
	} 
	@RequestMapping(value="/admin/nbrRepCorrect",method=RequestMethod.GET)
	public Long nbrRepCorrect() 
	{
	return qcmService.nbrRepCorrect();
	} 
	@RequestMapping(value="/admin/nbrRepFausse",method=RequestMethod.GET)
	public Long nbrRepFausse() 
	{
	return qcmService.nbrRepFausse();
	} 
	@RequestMapping(value="/admin/nbrMoyenne",method=RequestMethod.GET)
	public Long nbrMoyenne() 
	{
	return qcmService.nbrMoyenne();
	} 
	@RequestMapping(value="/admin/nbrRed",method=RequestMethod.GET)
	public Long nbrRed() 
	{
	return qcmService.nbrRed();
	} 
	
}
