package org.sid.dao;

import java.util.List;


import org.sid.entities.Competance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompetanceRepository extends JpaRepository<Competance, Long>{
	
@Query("select DISTINCT  name from Competance")
public List<String> getCompetances();


	
}
