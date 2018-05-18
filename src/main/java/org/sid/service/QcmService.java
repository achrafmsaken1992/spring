package org.sid.service;

import java.util.List;

import org.sid.entities.Appuser;
import org.sid.entities.Note;
import org.sid.entities.Qcm;
import org.sid.entities.Question;
import org.sid.entities.Reponse;
import org.sid.entities.Suggestion;
import org.sid.form.QcmForm;
import org.sid.form.QuestionForm;
import org.sid.form.ReponseForm;
import org.sid.form.SuggestionForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QcmService {
public void addQcm(QcmForm qcmForm);
public List<Qcm> getQcmsByOffre(Long id);
public void deleteQcm(Long id);
public void updateQcm(QcmForm qcmForm);

public void addQuestion(QuestionForm questionForm);
public List<Question> getQuestionByQcm(Long id);
public void deleteQuestion(Long id);
public void updateQuestion(QuestionForm questionForm);

public void addSuggestion(SuggestionForm suggestionForm);
public List<Suggestion> getSuggestionByQuestion(Long id);
public void deleteSuggestion(Long id);
public void updateSuggestion(SuggestionForm suggestionForm);
public void addReponse(ReponseForm reponseForm);
//public List<Reponse> getReponses(Long etudiant,Long question);
public Note findNoteByqcmByetudiant(Long etudiant,Long qcm);
public Page<Question> getQuestions(Long qcm,Pageable pageable);
}
