package org.sid.dao;

import org.sid.entities.Appuser;
import org.sid.entities.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long>{

}
