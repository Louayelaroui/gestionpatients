package com.formationspringboot.gestionpatients.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.formationspringboot.gestionpatients.entites.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	public Page <Patient> findByNomContains(String mc,Pageable p);
//	or
//	public List<Patient> rechercherParMotCle
	@Query("select p from Patient p where p.nom like %:x%")
	public List<Patient> rechercheParCle(@Param("x")String mc);

}
