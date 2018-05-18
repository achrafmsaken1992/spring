package org.sid.dao;

import java.util.List;

import org.sid.entities.Appuser;
import org.sid.entities.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReponseRepository extends JpaRepository<Reponse, Long>{

	//@Query("select r from Reponse r where r.question.id=:question and r.etudiant.id=:etudiant")
	//public List<Reponse> findReponseByquestionByetudiant(@Param("etudiant")Long etudiant,@Param("question")Long question);
	
}
