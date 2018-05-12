package org.sid.dao;



import org.sid.entities.AppRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
@Repository
public interface RoleRepository extends JpaRepository<AppRole, Long>{
	public AppRole findRoleByRoleName(String roleName);
	

	
	public long count();

}
