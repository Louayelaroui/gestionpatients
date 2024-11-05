package com.formationspringboot.gestionpatients.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.formationspringboot.gestionpatients.dao.MedecinRepository;
import com.formationspringboot.gestionpatients.dao.PatientRepository;
import com.formationspringboot.gestionpatients.entites.Medecin;
@Service
public class ServiceMedecin implements IServiceMedecin{
	
	MedecinRepository md;
	public ServiceMedecin(MedecinRepository md) {
		super();
		this.md = md;
	}
	@Override
	public void addMedecin(Medecin p) {
		md.save(p);
		
	}

	@Override
	public void deleteMedecin(Long id) {
		md.deleteById(id);
		
	}

	@Override
	public List<Medecin> getAllMedecins() {
		return md.findAll();
	}

	@Override
	public List<Medecin> getMedecinsByName(String mc) {
		return md.rechercheParCle(mc);
	}

	@Override
	public Medecin getMedecinById(Long id) {
		return md.findById(id).get();
	}

	@Override
	public Page<Medecin> getMedecinsByName(String mc, Pageable p) {
		return md.findByNomContains(mc, p);
	}

}
