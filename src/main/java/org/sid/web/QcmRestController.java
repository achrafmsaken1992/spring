package org.sid.web;

import java.util.List;

import org.sid.entities.Appuser;
import org.sid.entities.Qcm;
import org.sid.entities.Question;
import org.sid.entities.Suggestion;
import org.sid.form.QcmForm;
import org.sid.form.QuestionForm;
import org.sid.form.SuggestionForm;
import org.sid.service.QcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@PostMapping("/manager/addQuestion")
	public void addQuestion(@RequestBody QuestionForm questionForm) {
		qcmService.addQuestion(questionForm);
	}
	@RequestMapping(value="/getQuestionsByQcm",method=RequestMethod.GET)
	public List<Question> getQuestions(
			
			@RequestParam(name="id")	Long id){
				return qcmService.getQuestionByQcm(id);
		
		
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
	
	
	
	
	
}
