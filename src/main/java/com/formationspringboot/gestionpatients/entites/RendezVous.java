package com.formationspringboot.gestionpatients.entites;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateRdv;

    @Override
	public String toString() {
		return "RendezVous [id=" + id + ", dateRdv=" + dateRdv + ", heurRdv=" + heurRdv + ", patient=" + patient + "]";
	}

	private String heurRdv;

    @ManyToOne // Many RendezVous can be associated with one Patient
    @JoinColumn(name = "patient_id") // Define the foreign key column
    private Patient patient;
    @ManyToOne 
    @JoinColumn(name = "medecin_id") 
    private Medecin medecin;
    @OneToOne
    private Consultation consultation ;
    public RendezVous() {
        // Default constructor
    }

    public RendezVous(Date dateRdv, String heurRdv) {
        this.dateRdv = dateRdv;
        this.heurRdv = heurRdv;
    }

    // Getter and Setter methods for id, dateRdv, heurRdv, and patient

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(Date dateRdv) {
        this.dateRdv = dateRdv;
    }

    public String getHeurRdv() {
        return heurRdv;
    }

    public void setHeurRdv(String heurRdv) {
        this.heurRdv = heurRdv;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
  
    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }
}
