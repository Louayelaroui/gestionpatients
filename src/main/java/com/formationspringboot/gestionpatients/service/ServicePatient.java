package com.formationspringboot.gestionpatients.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.formationspringboot.gestionpatients.dao.PatientRepository;
import com.formationspringboot.gestionpatients.entites.Patient;

@Service
public class ServicePatient  implements IServicePatient{
	

	PatientRepository pr;
	
	
	public ServicePatient(PatientRepository pr) {
		super();
		this.pr = pr;
	}

	@Override
	public void addPatient(Patient p) {
		
		pr.save(p);
	
	}

	@Override
	public void deletePatient(Long id) {
		
		pr.deleteById(id);
		
	}

	@Override
	public List<Patient> getAllPatients() {
		
		return pr.findAll();
	}

	@Override
	public List<Patient> getPatientsByName(String mc) {
		
		return pr.rechercheParCle(mc);
	}

	@Override
	public Patient getPatientById(Long id) {
		
		return pr.findById(id).get();
	}

	@Override
	public Page<Patient> getPatientsByName(String mc, Pageable p) {
		// TODO Auto-generated method stub
		return pr.findByNomContains(mc, p);
	}

}
