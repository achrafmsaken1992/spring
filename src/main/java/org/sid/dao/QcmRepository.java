package org.sid.dao;

import java.util.List;

import org.sid.entities.Appuser;
import org.sid.entities.Qcm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QcmRepository extends JpaRepository<Qcm, Long>{
	@Query("select q from Qcm q where q.offre.id=:id and ((select count(*) from Question qu "
			+ "where qu MEMBER OF q.questions  and ((select count(*) from Suggestion s where s MEMBER OF qu.suggestions)>=1))>=3) ")
	public List<Qcm> getQcmsByOffre(@Param("id") Long id);
	
	@Query("select q from Qcm q where q.offre.id=:id")
	public List<Qcm> getQcmsByOffresManager(@Param("id") Long id);
	
	@Query("select count(*) from Qcm q where q.id=:id and q.offre.manager.id=:u")
	public int qcmManager(@Param("id")Long id,@Param("u")Long u );
	
}
