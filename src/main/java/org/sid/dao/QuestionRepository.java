package org.sid.dao;

import java.util.List;

import org.sid.entities.Qcm;
import org.sid.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long>{
	@Query("select q from Question q where q.qcm.id=:id")
	public List<Question> getQuestionByQcm(@Param("id") Long id);
}
