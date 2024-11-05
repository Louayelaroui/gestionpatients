package com.gestionpatients.restapi;

import com.gestionpatients.entites.Medecin;
import com.gestionpatients.service.IServiceMedecin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/medecins")
public class MedecinControleur2 {

    private final IServiceMedecin md;



    @GetMapping
    public ResponseEntity<Page<Medecin>> getMedecins(
            @RequestParam(name = "searchQuery", defaultValue = "") String searchQuery,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<Medecin> pageMedecins = md.getMedecinsByName(searchQuery, PageRequest.of(page, size));
        return ResponseEntity.ok(pageMedecins);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedecin(@PathVariable Long id) {
        md.deleteMedecin(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<String> saveMedecin(@Valid @RequestBody Medecin medecin) {
        md.addMedecin(medecin);
        return ResponseEntity.status(HttpStatus.CREATED).body("Medecin added successfully");
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateMedecin(@PathVariable Long id, @Valid @RequestBody Medecin updatedMedecin) {
        Medecin existingMedecin = md.getMedecinById(id);
        if (existingMedecin == null) {
            return ResponseEntity.notFound().build();
        }
        updatedMedecin.setId(id);
         md.addMedecin(updatedMedecin);
         return ResponseEntity.status(HttpStatus.CREATED).body("Medecin updated successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medecin> getMedecinById(@PathVariable Long id) {
        Medecin medecin = md.getMedecinById(id);
        if (medecin == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medecin);
    }
}
