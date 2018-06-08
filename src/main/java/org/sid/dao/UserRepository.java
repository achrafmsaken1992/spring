package org.sid.dao;

import java.util.List;


import org.sid.entities.Appuser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;


public interface UserRepository extends JpaRepository<Appuser, Long>{
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Appuser a SET a.valide =1   WHERE a.id =:id")

	int valideCManager( @Param("id")Long id  );
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Appuser a SET a.resume =:x  WHERE a.id =:id")
	public void updateResume(@Param("x") String resume, @Param("id")Long id );
	
	
	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Appuser a SET a.tokenRecovery =:x , a.dateExpiration =:y  WHERE a.id =:id")
	public void recoveryPassword(@Param("x") String tokenRecovery, @Param("y")String dateExpiration, @Param("id")Long id );

	
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
	
	@Query("select c from Appuser c where  (ANY(select e.motCle from c.experience e ) like :exp)"
			+ "and (ANY(select co.name from c.competance co ) like :comp)")
	public List<Appuser> getProfile(@Param("exp")String exp,@Param("comp") String comp);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Appuser a SET a.tokenNotification =:x  WHERE a.id =:id")
	public void updateTokenNotification(@Param("x") String notification, @Param("id")Long id );
	
	
	public Appuser findByTokenRecovery(String token);
	

	@Query("select distinct c from Appuser c,AppRole r,Language l,Competance comp,Experience exp,Formation for where c.nom like :nom and c.prenom like :prenom "
	+" and c.active=:active and c.valide=:valide "
	+" and ((l.nom like :la and l  MEMBER OF  c.langue) or (c.langue IS EMPTY and :la='%%'))"
	+" and ((comp.name like :co and comp  MEMBER OF  c.competance) or (c.competance IS EMPTY and :co='%%')) "
	+" and ((((exp.description like :ex) or (exp.motCle like :ex)) and exp  MEMBER OF  c.experience) or (c.experience  IS EMPTY and :ex='%%')) "
	+" and (((for.section like :fo  or for.specialite like :fo) and for  MEMBER OF  c.formation) or (c.formation IS EMPTY and :fo='%%')) "
	+" and r.roleName='ETUDIANT' and r  MEMBER OF  c.roles and active =:active and valide =:valide")
	 public Page<Appuser> RechEtudiant(@Param("nom")String nom,@Param("prenom")String prenom,
			@Param("la")String langue,
			@Param("co")String competance,
			@Param("ex")String experience,
			@Param("fo")String formation,
	@Param("active")int active,@Param("valide")int valide,Pageable pageable);
			
	


}
