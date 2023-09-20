package com.formationspringboot.gestionpatients.dao;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.formationspringboot.gestionpatients.entites.RendezVous;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    //  custom query method to find RendezVous by a specific attribute (e.g., date)
    List<RendezVous> findByDateRdv(Date date);

    //  custom query method with JPQL query
    @Query("SELECT r FROM RendezVous r WHERE r.heurRdv = :heure")
    List<RendezVous> findByHeureRdv(@Param("heure") String heure);
  
   
    @Query("SELECT rv FROM RendezVous rv JOIN FETCH rv.patient WHERE (:searchDate IS NULL OR rv.dateRdv = :searchDate)")
    public Page<RendezVous> findAllWithPatients(@Param("searchDate") Date searchDate, Pageable pageable);

    
   
}


    

