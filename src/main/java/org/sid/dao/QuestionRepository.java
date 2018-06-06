package org.sid.dao;

import java.util.List;

import org.sid.entities.Qcm;
import org.sid.entities.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface QuestionRepository extends JpaRepository<Question, Long>{
	@Query("select q from Question q where q.qcm.id=:id")
	public List<Question> getQuestionByQcm(@Param("id") Long id);
	
	@Query("select q from Question q where q.qcm.id=:qcm")
	public Page<Question> getQuestions(@Param("qcm") Long qcm,Pageable pageable);
	
	
	@Query("select count(q) from Question q where q.qcm.id=:qcm  and (count(q.suggestions)>=1)")
	public Long nbrQuestions(@Param("qcm") Long qcm);
	@Query("select count(*) from Question q where q.id=:id and q.qcm.offre.manager.id=:u")
	public int questionManager(@Param("id")Long id,@Param("u")Long u );
}
