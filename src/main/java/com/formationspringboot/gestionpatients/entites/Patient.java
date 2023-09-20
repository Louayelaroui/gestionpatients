package com.formationspringboot.gestionpatients.entites;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


@Entity
public class Patient {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Size(min=2,message="le nom doit avoir au minimum 2 caracters")
	private String nom;
	@NotEmpty
	private String prenom;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat (pattern="yyyy-MM-dd")
	
	private Date dateDeNaissance;
	
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<RendezVous> rendezVousList;

	
	public Patient(Long id, @Size(min = 2, message = "le nom doit avoir au minimum 2 caracters") String nom,
			@NotEmpty String prenom, Date dateDeNaissance, List<RendezVous> rendezVousList, int numTel) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.rendezVousList = rendezVousList;
		this.numTel = numTel;
	}
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Patient(Long id, String nom, String prenom, Date dateDeNaissance, int numTel) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		
		this.dateDeNaissance = dateDeNaissance;
		this.numTel = numTel;
	}
	@Override
	public String toString() {
		return "Patient [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateDeNaissance=" + dateDeNaissance
				+ ", numTel=" + numTel + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}
	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}
	public int getNumTel() {
		return numTel;
	}
	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}
	private int numTel;


}
