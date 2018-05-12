package org.sid.dao;

import java.util.List;

import org.sid.entities.Appuser;
import org.sid.entities.Messagerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessagerieRepository extends JpaRepository<Messagerie,Long>{
	@Query("select m from Messagerie m where (m.user1=:u1 and m.user2=:u2) or (m.user2=:u1 and m.user1=:u2) order by date desc "
			)
	public List<Messagerie> getMessagerie(@Param("u1")Long user1,@Param("u2")Long user2);
}
