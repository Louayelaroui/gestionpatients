package com.formationspringboot.gestionpatients.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateconsultation;
    @Override
    public String toString() {
        return "consultation [id=" + id + ", dateconsultation=" + dateconsultation + ", Raportconsultation=" + Raportconsultation + ", prixcconsultation=" + prixcconsultation + "rendez vous :" +rendezVous   + "]";
    }
    private String Raportconsultation;
    private double prixcconsultation;
    @OneToOne (mappedBy = "consultation")
    private RendezVous rendezVous;
}
