package org.sid.dao;

import java.util.List;

import org.sid.entities.Question;
import org.sid.entities.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long>{
	@Query("select s from Suggestion s where s.question.id=:id")
	public List<Suggestion> getSuggestionsByQuestion(@Param("id") Long id);
}
