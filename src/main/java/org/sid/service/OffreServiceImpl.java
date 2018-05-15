package org.sid.service;

import java.util.List;

import javax.transaction.Transactional;

import org.sid.dao.OffreRepository;
import org.sid.entities.Offre;
import org.sid.form.OffreForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
@Transactional
public class OffreServiceImpl implements OffreService{
	@Autowired
	OffreRepository offreRepository;
	@Override
	public Long addOffre(Offre offre) {
	Offre o=	offreRepository.save(offre);
	return o.getId();
		
	}
	@Override
	public Page<Offre> getOffreByManager(Long id,String mot,int active, Pageable pageable) {
		// TODO Auto-generated method stub
		return offreRepository.findOffresByManager(id,mot,active,pageable);
	}
	@Override
	public Page<Offre> getAllOffres(String mot,  Pageable pageable) {
		// TODO Auto-generated method stub
		return offreRepository.findAllOffres(mot, pageable);
	}
	

}
