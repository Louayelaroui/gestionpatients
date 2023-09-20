package com.formationspringboot.gestionpatients.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.formationspringboot.gestionpatients.dao.RendezVousRepository;
import com.formationspringboot.gestionpatients.entites.Patient;
import com.formationspringboot.gestionpatients.entites.RendezVous;

@Service
public class ServiceRendezVous implements IServiceRendezVous {
    private final RendezVousRepository rendezVousRepository;

    public ServiceRendezVous(RendezVousRepository rendezVousRepository) {
        this.rendezVousRepository = rendezVousRepository;
    }

    @Override
    public void addRendezVous(RendezVous r) {
        rendezVousRepository.save(r);
    }

    @Override
    public void deleteRendezVous(Long id) {
        rendezVousRepository.deleteById(id);
    }

    @Override
    public List<RendezVous> getAllRendezVous() {
        return rendezVousRepository.findAll();
    }

    @Override
    public RendezVous getRendezVousById(Long id) {
        return rendezVousRepository.findById(id).orElse(null);
    }

	@Override
	public List<RendezVous> getRendezVousByDate(Date date) {
		return rendezVousRepository.findByDateRdv(date);
	}

	
	
	@Override
	public Page<RendezVous> findAllWithPatients(Date searchDate,Pageable pageable) {
		// TODO Auto-generated method stub
		return rendezVousRepository.findAllWithPatients(searchDate, pageable);
	}

    
}
