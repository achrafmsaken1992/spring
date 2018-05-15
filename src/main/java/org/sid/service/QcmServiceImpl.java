package org.sid.service;

import java.util.List;

import javax.transaction.Transactional;

import org.sid.dao.OffreRepository;
import org.sid.dao.QcmRepository;
import org.sid.dao.QuestionRepository;
import org.sid.dao.SuggestionRepository;
import org.sid.entities.Offre;
import org.sid.entities.Qcm;
import org.sid.entities.Question;
import org.sid.entities.Suggestion;
import org.sid.form.QcmForm;
import org.sid.form.QuestionForm;
import org.sid.form.SuggestionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class QcmServiceImpl implements QcmService{
    @Autowired
	QcmRepository qcmRepository; 
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    OffreRepository offreRepository;
    @Autowired 
    SuggestionRepository suggestionRepository;
	public QcmRepository getQcmRepository() {
		return qcmRepository;
	}
	public void setQcmRepository(QcmRepository qcmRepository) {
		this.qcmRepository = qcmRepository;
	}
	public QuestionRepository getQuestionRepository() {
		return questionRepository;
	}
	public void setQuestionRepository(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}
	public OffreRepository getOffreRepository() {
		return offreRepository;
	}
	public void setOffreRepository(OffreRepository offreRepository) {
		this.offreRepository = offreRepository;
	}
	@Override
	public void addQcm(QcmForm qcmForm) {
	Qcm qcm=new Qcm();
	qcm.setDuree(qcmForm.getDuree());
	qcm.setTitre(qcmForm.getTitre());
	Offre offre=offreRepository.findOne(qcmForm.getOffre());
	qcm.setOffre(offre);
	qcmRepository.save(qcm);
	
		
	}
	@Override
	public List<Qcm> getQcmsByOffre(Long id) {
		// TODO Auto-generated method stub
		return qcmRepository.getQcmsByOffre(id);
	}
	@Override
	public void addQuestion(QuestionForm questionForm) {
		Question question=new Question();
		Qcm qcm=qcmRepository.findOne(questionForm.getQcm());
		question.setQcm(qcm);
		question.setQuestion(questionForm.getQuestion());
				questionRepository.save(question);
		
	}
	@Override
	public List<Question> getQuestionByQcm(Long id) {
		// TODO Auto-generated method stub
		return questionRepository.getQuestionByQcm(id);
	}
	@Override
	public void addSuggestion(SuggestionForm suggestionForm) {
		Suggestion suggestion=new Suggestion();
		Question question=questionRepository.findOne(suggestionForm.getQuestion());
		suggestion.setQuestion(question);
		suggestion.setReponse(suggestionForm.getReponse());
		suggestion.setSuggestion(suggestionForm.getSuggestion());
		suggestionRepository.save(suggestion);
		
	}
	@Override
	public List<Suggestion> getSuggestionByQuestion(Long id) {
		// TODO Auto-generated method stub
		return suggestionRepository.getSuggestionsByQuestion(id);
	}

}
