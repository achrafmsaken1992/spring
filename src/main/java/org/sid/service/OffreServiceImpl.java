package org.sid.service;

import java.util.List;

import javax.transaction.Transactional;

import org.sid.dao.OffreRepository;
import org.sid.dao.QcmRepository;
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
	@Autowired
	QcmRepository qcmRepository;
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
	@Override
	public int manegerOffre(Long id,Long manager) {
		int res=0;
		// TODO Auto-generated method stub
		if(offreRepository.isOffreManager(id,manager)>0)
			res=1;
		return res;
	}
	@Override
	public void supprimerOffre(Long id) {
		offreRepository.delete(id);
		
	}
	@Override
	public void modifierOffre(OffreForm offreForm) {
		Offre offre=offreRepository.findOne(offreForm.getId());
		offre.setTitre(offreForm.getTitre());
		offre.setDescription(offreForm.getDescription());
		offreRepository.save(offre);
		
	}

	

}
