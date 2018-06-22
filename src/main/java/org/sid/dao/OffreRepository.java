package org.sid.dao;

import java.util.List;

import org.sid.entities.Appuser;
import org.sid.entities.Offre;
import org.sid.form.OffreStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface OffreRepository extends JpaRepository<Offre,Long>{
	@Query("select o from Offre o where o.manager.id=:id and active=:active and (o.titre like :mot or o.description like :mot) ORDER BY o.date DESC")
	public Page<Offre> findOffresByManager(@Param("id")Long id,@Param("mot")String mot,@Param("active")int active,Pageable pageable );
	
	
	
	@Query("select o from Offre o where active=1 and (o.titre like :mot or o.description like :mot) ORDER BY o.date DESC")
	public Page<Offre> findAllOffres(@Param("mot")String mot,Pageable pageable );
	
	@Query("select count(*) from Offre o where o.id=:id and o.manager.id=:u")
	public int isOffreManager(@Param("id")Long id,@Param("u")Long u );

	@Query("select count(*) from Offre o")
	public Long NbrOffres( );

	@Query("select count(*) from Offre o where o.manager.id=:id")
	public Long NbrOffresManager(@Param("id")Long id);
	
	@Query("select  new org.sid.form.OffreStat(count(o.id),o.entreprise) from Offre o group by o.entreprise order by count(o.id) desc")
	public List<OffreStat> statOffre();
	
}
