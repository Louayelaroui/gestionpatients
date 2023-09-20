package com.formationspringboot.gestionpatients.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.formationspringboot.gestionpatients.entites.Medecin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface MedecinRepository extends JpaRepository<Medecin, Long>{

		public Page <Medecin> findByNomContains(String mc,Pageable p);
		@Query("select p from Patient p where p.nom like %:x%")
		public List<Medecin> rechercheParCle(@Param("x")String mc);


}
