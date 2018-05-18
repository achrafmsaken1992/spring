package org.sid.dao;

import java.util.List;

import org.sid.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoteRepository extends JpaRepository<Note, Long>{

	
@Query("select r from Note r where r.qcm.id=:qcm and r.etudiant.id=:etudiant")
public Note findNoteByqcmByetudiant(@Param("etudiant")Long etudiant,@Param("qcm")Long qcm);
}
