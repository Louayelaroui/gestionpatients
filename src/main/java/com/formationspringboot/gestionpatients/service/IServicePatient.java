package com.formationspringboot.gestionpatients.service;


import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import com.formationspringboot.gestionpatients.entites.Patient;

public interface IServicePatient {
	
	public void addPatient(Patient p,MultipartFile mf) throws IOException;
	public void addPatient(Patient p);
	public void deletePatient(Long id);
	public List<Patient> getAllPatients();
	public List<Patient> getPatientsByName(String mc);
	public Patient getPatientById(Long id);
	public Page<Patient> getPatientsByName(String mc,Pageable p);
	public byte[] getMedicalImage(Long id) throws IOException;

}
