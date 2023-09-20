package com.formationspringboot.gestionpatients;

import java.util.Date;
import java.util.List;

import com.formationspringboot.gestionpatients.dao.ConsultationRepository;
import com.formationspringboot.gestionpatients.entites.Consultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.formationspringboot.gestionpatients.dao.MedecinRepository;
import com.formationspringboot.gestionpatients.dao.PatientRepository;
import com.formationspringboot.gestionpatients.dao.RendezVousRepository;
import com.formationspringboot.gestionpatients.entites.Patient;
import com.formationspringboot.gestionpatients.entites.RendezVous;
import com.formationspringboot.gestionpatients.entites.Medecin;

@SpringBootApplication
public class GestionpatientsApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedecinRepository medecinRepository; // Utilisez le repository Medecin

    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Autowired
    private ConsultationRepository consultationRepository;

    public static void main(String[] args) {
        SpringApplication.run(GestionpatientsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Create and save a Patient
        Patient patient = new Patient(null, "ilhem", "amiri", new Date(), 122323);
        patientRepository.save(patient);
       // create and save Patint :

        // Create and save a Medecin
        Medecin medecin = new Medecin();
        medecin.setNom("Dr. Smith");
        medecin.setPrenom("John");
        medecin.setEmail("dr.smith@example.com");
        medecin.setNumTel("555555555");
        medecin.setSpecialite("Cardiologist");
        medecinRepository.save(medecin); 

        // Create and save a RendezVous associated with the Patient
        RendezVous rendezVous = new RendezVous(new Date(), "10:00 AM");
        rendezVous.setPatient(patient);
        rendezVous.setMedecin(medecin);
        rendezVousRepository.save(rendezVous);
        Consultation consultation = new Consultation();
        consultation.setDateconsultation(new Date());
        consultation.setRaportconsultation("Sample report");
        consultation.setPrixcconsultation(100.0);
        consultation.setRendezVous(rendezVous);  // if the Consultation is associated with a RendezVous
        consultationRepository.save(consultation);
        // Retrieve and print the Patients, Medecins et RendezVous
        List<Patient> patients = patientRepository.findAll();
        List<Medecin> medecins = medecinRepository.findAll(); // Utilisez le repository Medecin
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        System.out.println("Patients: " + patients);
        System.out.println("Medecins: " + medecins);
        System.out.println("RendezVous: " + rendezVousList);
    }
}
