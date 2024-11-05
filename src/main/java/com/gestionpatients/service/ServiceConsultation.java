package com.formationspringboot.gestionpatients.service;

import com.formationspringboot.gestionpatients.dao.ConsultationRepository;
import com.formationspringboot.gestionpatients.entites.Consultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceConsultation implements IServiceConsultation {
    @Autowired
    private ConsultationRepository consultationRepository;

    @Override
    public void addConsultation(Consultation c) {
        consultationRepository.save(c);
    }

    @Override
    public void deleteConsultation(Long id) {
        consultationRepository.deleteById(id);
    }

    @Override
    public List<Consultation> getAllConsultation() {
        return consultationRepository.findAll();
    }

    @Override
    public Consultation getConsultationByid(Long id) {
        return consultationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Consultation> getConsulatationByDate(Date date) {
        // Implement this if you need it, using a custom query in the repository
        return null;
    }

    @Override
    public Page<Consultation> findAllWithConsultation(Date searchDate, Pageable pageable) {

        return consultationRepository.findAllWithConsultation(searchDate,pageable);
    }
}