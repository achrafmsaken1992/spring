package org.sid.dao;

import java.util.List;


import org.sid.entities.Competance;
import org.sid.form.CompetanceStat;
import org.sid.form.OffreStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompetanceRepository extends JpaRepository<Competance, Long>{
	
@Query("select DISTINCT  name from Competance")
public List<String> getCompetances();

@Query("select  new org.sid.form.CompetanceStat(count(c.id),c.name) from Competance c group by c.name order by count(c.id) desc")
public List<CompetanceStat> competanceStat();
	
}
