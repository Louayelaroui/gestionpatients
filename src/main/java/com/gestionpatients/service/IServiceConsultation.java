package com.formationspringboot.gestionpatients.service;

import com.formationspringboot.gestionpatients.entites.Consultation;
import com.formationspringboot.gestionpatients.entites.RendezVous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface IServiceConsultation {
    public void addConsultation(Consultation c);
    public void deleteConsultation(Long id);
    public List<Consultation> getAllConsultation();
    public Consultation getConsultationByid(Long id);
    public List<Consultation> getConsulatationByDate(Date date);
    public Page<Consultation> findAllWithConsultation(Date searchDate  , Pageable pageable);
}
