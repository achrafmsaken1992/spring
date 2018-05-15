package org.sid.service;

import java.util.List;

import org.sid.entities.Qcm;
import org.sid.entities.Question;
import org.sid.entities.Suggestion;
import org.sid.form.QcmForm;
import org.sid.form.QuestionForm;
import org.sid.form.SuggestionForm;

public interface QcmService {
public void addQcm(QcmForm qcmForm);
public List<Qcm> getQcmsByOffre(Long id);
public void addQuestion(QuestionForm questionForm);
public List<Question> getQuestionByQcm(Long id);
public void addSuggestion(SuggestionForm suggestionForm);
public List<Suggestion> getSuggestionByQuestion(Long id);
}
