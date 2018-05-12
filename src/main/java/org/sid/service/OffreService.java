package org.sid.service;

import java.util.List;

import org.sid.entities.Offre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OffreService {
public Long addOffre(Offre offre);
public Page<Offre> getOffreByManager(Long id,String mot,int active, Pageable pageable);

}
