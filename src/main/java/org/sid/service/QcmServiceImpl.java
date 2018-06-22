package org.sid.service;

import java.util.List;

import javax.transaction.Transactional;

import org.sid.dao.NoteRepository;
import org.sid.dao.OffreRepository;
import org.sid.dao.QcmRepository;
import org.sid.dao.QuestionRepository;
import org.sid.dao.ReponseRepository;
import org.sid.dao.SuggestionRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.Appuser;
import org.sid.entities.Note;
import org.sid.entities.Offre;
import org.sid.entities.Qcm;
import org.sid.entities.Question;
import org.sid.entities.Reponse;
import org.sid.entities.Suggestion;
import org.sid.form.NoteForm;
import org.sid.form.QcmForm;
import org.sid.form.QuestionForm;
import org.sid.form.ReponseForm;
import org.sid.form.SuggestionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    ReponseRepository reponseRepository;
    @Autowired
	private UserRepository userRepository;
    @Autowired
    private NoteRepository noteRpository;
    

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
	public void deleteQcm(Long id) {
		qcmRepository.delete(id);
		
	}
	@Override
	public void updateQcm(QcmForm qcmForm) {
		Qcm qcm=qcmRepository.findOne(qcmForm.getId());
		qcm.setDuree(qcmForm.getDuree());
		qcm.setTitre(qcmForm.getTitre());
		
		
		qcmRepository.save(qcm);
		
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
	public void deleteQuestion(Long id) {
		questionRepository.delete(id);
		
	}
	@Override
	public void updateQuestion(QuestionForm questionForm) {
		Question question=questionRepository.findOne(questionForm.getId());
	
		
		question.setQuestion(questionForm.getQuestion());
				questionRepository.save(question);
		
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
	@Override
	public void updateSuggestion(SuggestionForm suggestionForm) {
		Suggestion suggestion=suggestionRepository.findOne(suggestionForm.getId());
	
		
		suggestion.setReponse(suggestionForm.getReponse());
		suggestion.setSuggestion(suggestionForm.getSuggestion());
		suggestionRepository.save(suggestion);
		
	}

	@Override
	public void deleteSuggestion(Long id) {
		suggestionRepository.delete(id);
		
	}
	@Override
	public void addReponse(ReponseForm reponseForm) {
		Reponse reponse=new Reponse();
		Appuser etudiant=userRepository.findOne(reponseForm.getEtudiant());
		Question question=questionRepository.findOne(reponseForm.getQuestion());
		reponse.setEtudiant(etudiant);
		reponse.setQuestion(question);
		reponse.setResultat(reponseForm.getResultat());
		reponseRepository.save(reponse);
		
		
	}
	/*@Override
	public void getReponses(Long etudiant, Long question) {
		return 
		
	}*/
	@Override
	public Note findNoteByqcmByetudiant(Long etudiant, Long qcm) {
		// TODO Auto-generated method stub
		return noteRpository.findNoteByqcmByetudiant(etudiant, qcm);
	}
	@Override
	public Page<Question> getQuestions(Long qcm, Pageable pageable) {
		// TODO Auto-generated method stub
		return questionRepository.getQuestions(qcm, pageable);
	}
	@Override
	public Long addNote(NoteForm noteForm) {
Note note =new Note();
Appuser etudiant=userRepository.findOne(noteForm.getEtudiantId());
Qcm qcm=qcmRepository.findOne(noteForm.getQcmId());
note.setEtudiant(etudiant);
note.setQcm(qcm);
note.setId(noteForm.getId());
note.setReponseCorrect(noteForm.getReponseC());
note.setReponseFausse(noteForm.getReponseF());
return noteRpository.save(note).getId();
	}
	@Override
	public Page<Note> findNoteByqcm(Long qcm,Pageable pageable) {
		// TODO Auto-generated method stub
		return noteRpository.findNoteByqcm(qcm,pageable);
	}
	@Override
	public Long nbrParticipants(Long qcm) {
		
		return noteRpository.nbrParticipants(qcm);
	}
	@Override
	public int moyenne(Long qcm) {
		// TODO Auto-generated method stub
		return (int)noteRpository.moyenne(qcm);
	}
	@Override
	public int meuilleurNote(Long qcm) {
		// TODO Auto-generated method stub
		return (int)noteRpository.meuilleurNote(qcm);
	}
	@Override
	public int plusMauvaisNote(Long qcm) {
		// TODO Auto-generated method stub
		return (int)noteRpository.plusMauvaisNote(qcm);
	}
	@Override
	public Qcm findQcmById(Long qcm) {
	
		return qcmRepository.findOne(qcm);
	}
	@Override
	public Long nbrParticipantsReussis(Long qcm) {
		// TODO Auto-generated method stub
		return noteRpository.nbrParticipantsReussis(qcm);
	}
	@Override
	public List<Qcm> getQcmsByOffreManager(Long id) {
		// TODO Auto-generated method stub
		return qcmRepository.getQcmsByOffresManager(id);
	}
	
	@Override
	public int isQcmManager(Long id,Long manager) {
		int res=0;
		// TODO Auto-generated method stub
		if(qcmRepository.qcmManager(id,manager)>0)
			res=1;
		return res;
	}
	@Override
	public int isQuestionManager(Long id, Long manager) {
		int res=0;
		// TODO Auto-generated method stub
		if(questionRepository.questionManager(id,manager)>0)
			res=1;
		return res;
	}
	@Override
	public Long nbrQuestionsByQcm(Long id) {
		// TODO Auto-generated method stub
		return questionRepository.nbrQuestions(id);
	}
	@Override
	public Long nbrQuizs() {
		// TODO Auto-generated method stub
		return qcmRepository.nbrQuizs();
	}
	@Override
	public Long nbrQuizsrepondus(Long id) {
		// TODO Auto-generated method stub
		return noteRpository.nbrQuizsEtudiant(id);
	}
	@Override
	public Long nbrQuizsManager(Long id) {
		// TODO Auto-generated method stub
		return qcmRepository.nbrQuizsManager(id);
	}
	@Override
	public Long nbrReps(Long id) {
		// TODO Auto-generated method stub
		return noteRpository.nbrRepQuizsManager(id);
	}
	@Override
	public Long nbrSuggestionsByQuestion(Long id) {
		// TODO Auto-generated method stub
		return suggestionRepository.nbrSuggestions(id);
	}
	@Override
	public Long nbrQuizOffre(Long id) {
		// TODO Auto-generated method stub
		return qcmRepository.nbrQuizsOffre(id);
	}
	@Override
	public Long nbrRepCorrect() {
		// TODO Auto-generated method stub
		return noteRpository.nbrRepCorrect();
	}
	@Override
	public Long nbrRepFausse() {
		// TODO Auto-generated method stub
		return noteRpository.nbrRepFausse();
	}
	@Override
	public Long nbrMoyenne() {
		// TODO Auto-generated method stub
		return noteRpository.nbrMoyenne();
	}
	@Override
	public Long nbrRed() {
		// TODO Auto-generated method stub
		return noteRpository.nbrRed();
	}
	
}
