package com.formationspringboot.gestionpatients.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.formationspringboot.gestionpatients.entites.Medecin;


public interface IServiceMedecin {
	public void addMedecin(Medecin p);
	public void deleteMedecin(Long id);
	public List<Medecin> getAllMedecins();
	public List<Medecin> getMedecinsByName(String mc);
	public Medecin getMedecinById(Long id);
	public Page<Medecin> getMedecinsByName(String mc,Pageable p);
}
