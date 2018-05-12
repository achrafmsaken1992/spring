package org.sid.dao;




import org.sid.entities.Candidate;
import org.sid.entities.Experience;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long>{

	
}
