package com.formationspringboot.gestionpatients.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.formationspringboot.gestionpatients.entites.RendezVous;

public interface IServiceRendezVous {
    
    public void addRendezVous(RendezVous r);
    public void deleteRendezVous(Long id);
    public List<RendezVous> getAllRendezVous();
    public RendezVous getRendezVousById(Long id);
    public List<RendezVous> getRendezVousByDate(Date date);
    public Page<RendezVous> findAllWithPatients(Date searchDate  ,Pageable pageable);

}
