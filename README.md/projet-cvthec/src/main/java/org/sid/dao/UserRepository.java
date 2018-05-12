package org.sid.dao;

import java.util.List;

import org.sid.entities.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<AppUser, Long>{
	
	
	public AppUser findByEmail(String Username);
	public AppUser findByTokenRecovery(String token);
	
	
	@Query("select a from AppUser a where a.appUser.email=:email")
	public List<AppUser>findUserByManagerEmail(@Param("email")String email);
	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE AppUser a SET a.tokenRecovery =:x , a.dateExpiration =:y  WHERE a.id =:id")
	public void recoveryPassword(@Param("x") String tokenRecovery, @Param("y")String dateExpiration, @Param("id")Long id );
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE AppUser a SET a.photo =:x   WHERE a.id =:id")
	public void updatePhoto(@Param("x") String photo, @Param("id")Long id );
	
	
	public AppUser findByCompanyName(String companyName);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE AppUser a SET a.active =1   WHERE a.id =:id")
	public void ActiveSociaty( @Param("id")Long id );




}
