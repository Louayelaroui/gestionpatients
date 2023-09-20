package com.formationspringboot.gestionpatients.dao;

import com.formationspringboot.gestionpatients.entites.Consultation;
import com.formationspringboot.gestionpatients.entites.RendezVous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    @Query("SELECT c FROM Consultation c WHERE (:searchDate IS NULL OR c.dateconsultation = :searchDate)")
    public Page<Consultation> findAllWithConsultation(@Param("searchDate") Date searchDate, Pageable pageable);

}
