package com.formationspringboot.gestionpatients.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.formationspringboot.gestionpatients.entites.Patient;

public interface IServicePatient {
	
	public void addPatient(Patient p);
	public void deletePatient(Long id);
	public List<Patient> getAllPatients();
	public List<Patient> getPatientsByName(String mc);
	public Patient getPatientById(Long id);
	public Page<Patient> getPatientsByName(String mc,Pageable p);

}
