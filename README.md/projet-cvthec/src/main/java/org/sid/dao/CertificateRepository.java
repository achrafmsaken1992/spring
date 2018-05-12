package org.sid.dao;

import org.sid.entities.Certificate;
import org.sid.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long>{

}
