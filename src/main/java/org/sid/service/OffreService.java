package org.sid.service;

import java.util.List;

import org.sid.entities.Offre;
import org.sid.form.OffreForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OffreService {
public Long addOffre(Offre offre);
public void supprimerOffre(Long id);
public void modifierOffre(OffreForm offreForm);
public Page<Offre> getOffreByManager(Long id,String mot,int active, Pageable pageable);
public Page<Offre> getAllOffres(String mot, Pageable pageable);
public int manegerOffre(Long id,Long manager);
public Long nbrOffres(Long id);
}
