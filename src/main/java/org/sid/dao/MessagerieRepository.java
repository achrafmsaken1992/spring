package org.sid.dao;

import java.util.List;

import org.sid.entities.Appuser;
import org.sid.entities.Messagerie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface MessagerieRepository extends JpaRepository<Messagerie,Long>{
	@Query("select m from Messagerie m where (m.user1.id=:u1 and m.user2.id=:u2) or (m.user2.id=:u1 and m.user1.id=:u2) order by date desc "
			)
	public List<Messagerie> getMessagerie(@Param("u1")Long user1,@Param("u2")Long user2);
	
	@Query("select m from Messagerie m where m.user2.id=:u order by date desc")
	public Page<Messagerie> getMessageriesCall(@Param("u")Long user,Pageable pageable);
	
	@Query("select count(*) from Messagerie m where m.user2.id=:u")
	public Long nbrMessageriesRecu(@Param("u")Long user);
	
	@Query("select count(*) from Messagerie m where m.user1.id=:u")
	public Long nbrMessageriesEnvoye(@Param("u")Long user);
	
	
	@Query("select count(DISTINCT m.id) from Messagerie m "
			+ "where m.user1.id in "
			+ "(select c.id from  Appuser c,AppRole r where "
			+ " c.active=1 and c.valide=1 and r.roleName='MANAGER' and r  MEMBER OF  c.roles)")
	public Long nbrMessageriesManager();
	
	
	@Query("select count(DISTINCT m.id) from Messagerie m "
			+ "where m.user1.id in "
			+ "(select c.id from  Appuser c,AppRole r where "
			+ " c.active=1 and c.valide=1 and r.roleName='ETUDIANT' and r  MEMBER OF  c.roles)")
	public Long nbrMessageriesEtudiants();
	
	
	
//,AppRole r where  c.active=1 and c.valide=1 and r.roleName='ETUDIANT' and r  MEMBER OF  c.roles
}
