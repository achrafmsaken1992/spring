package org.sid.dao;

import java.util.List;

import org.sid.entities.Appuser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<Appuser, Long>{


	
	@Query("select a from Appuser a where a.email=:email")
	public Appuser findUserByEmail(@Param("email")String email);
	
	@Query("select a from Appuser a where a.nomEntreprise=:nom")
	public Appuser findUserByNomEntreprise(@Param("nom")String nom);
	
	@Query("select a from Appuser a where a.token=:token")
	public Appuser findUserByToken(@Param("token")String token);
	
	@Query("select c from Appuser c,AppRole r where c.nom like :nom and c.prenom like :prenom and c.nomEntreprise like :n and c.active=:active and c.valide=:valide and r.roleName='MANAGER' and r  MEMBER OF  c.roles")
	public Page<Appuser> getManager(@Param("nom")String nom,@Param("prenom")String prenom,@Param("n")String nomEntreprise,@Param("active")int active,
			@Param("valide")int valide,Pageable pageable);
	
	@Query("select c from Appuser c,AppRole r where c.nom like :n and  c.prenom like :p and c.active=:active and c.valide=:valide and r.roleName='Etudiant' and r  MEMBER OF  c.roles")
	public Page<Appuser> getEtudiant(@Param("n")String nom,@Param("p")String prenom,@Param("active")int active,
			@Param("valide")int valide,Pageable pageable);
	@Query("select c from Appuser c,AppRole r where (c.nom like :mot or  c.prenom like :mot) and c.active=1 and c.valide=1 and r.roleName='Etudiant' and r  MEMBER OF  c.roles")
	public List<Appuser> getEtudiantMessagerie(@Param("mot") String mot);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Appuser a SET a.image =:x  WHERE a.id =:id")
	public void updateImage(@Param("x") String image, @Param("id")Long id );	
	
	@Query("select c from Appuser c,AppRole r where (c.nom like :mot or c.prenom like :mot or c.nomEntreprise like :mot) and c.active=1 and c.valide=1 and r.roleName='MANAGER' and r  MEMBER OF  c.roles")
	public List<Appuser> getManagersMessagerie(@Param("mot")String mot);
}
