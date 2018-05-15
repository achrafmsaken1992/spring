package org.sid.dao;

import java.util.List;

import org.sid.entities.Appuser;
import org.sid.entities.Qcm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QcmRepository extends JpaRepository<Qcm, Long>{
	@Query("select q from Qcm q where q.offre.id=:id")
	public List<Qcm> getQcmsByOffre(@Param("id") Long id);
}
