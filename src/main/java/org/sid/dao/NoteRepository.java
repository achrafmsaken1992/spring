package org.sid.dao;

import java.util.List;

import org.sid.entities.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoteRepository extends JpaRepository<Note, Long>{

	
@Query("select r from Note r where r.qcm.id=:qcm and r.etudiant.id=:etudiant")
public Note findNoteByqcmByetudiant(@Param("etudiant")Long etudiant,@Param("qcm")Long qcm);

@Query("select n from Note n where n.qcm.id=:qcm order By (n.reponseCorrect/(n.reponseFausse+n.reponseCorrect)) desc ")
public Page<Note> findNoteByqcm(@Param("qcm")Long qcm,Pageable pageable);

@Query("select count(*) from Note n where n.qcm.id=:qcm  ")
public Long nbrParticipants(@Param("qcm")Long qcm);


@Query("select AVG((n.reponseCorrect/(n.reponseFausse+n.reponseCorrect))*100) from Note n where n.qcm.id=:qcm")
public double moyenne(@Param("qcm") Long qcm);

@Query("select MAX((n.reponseCorrect/(n.reponseFausse+n.reponseCorrect))*100) from Note n where n.qcm.id=:qcm")
public double meuilleurNote(@Param("qcm") Long qcm);


@Query("select MIN((n.reponseCorrect/(n.reponseFausse+n.reponseCorrect))*100) from Note n where n.qcm.id=:qcm")
public double plusMauvaisNote(@Param("qcm") Long qcm);

@Query("select count(*) from Note n where n.qcm.id=:qcm and (n.reponseCorrect/(n.reponseFausse+n.reponseCorrect))>=0.5  ")
public Long nbrParticipantsReussis(@Param("qcm")Long qcm);

@Query("select count(*) from Note n where n.etudiant.id=:id   ")
public Long nbrQuizsEtudiant(@Param("id")Long id);

@Query("select count(distinct id) from Note n where n.qcm.offre.manager.id=:id")
public Long nbrRepQuizsManager(@Param("id")Long id);

@Query("select sum(n.reponseCorrect) from Note n")
public Long nbrRepCorrect();

@Query("select sum(n.reponseFausse) from Note n")
public Long nbrRepFausse();


@Query("select count(n.id) from Note n where n.reponseCorrect>=n.reponseFausse")
public Long nbrMoyenne();

@Query("select count(n.id) from Note n where n.reponseCorrect<n.reponseFausse")
public Long nbrRed();

}







