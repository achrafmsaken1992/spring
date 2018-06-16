package org.sid.dao;

import java.util.List;

import org.sid.entities.Experience;
import org.sid.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LangueRepository extends JpaRepository<Language, Long>{
	@Query("select DISTINCT  nom from Language")
	public List<String> getLangues();
}
