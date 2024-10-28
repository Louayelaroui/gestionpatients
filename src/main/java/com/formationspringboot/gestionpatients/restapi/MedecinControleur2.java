package com.formationspringboot.gestionpatients.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.formationspringboot.gestionpatients.entites.Medecin;
import com.formationspringboot.gestionpatients.service.IServiceMedecin;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/medecins")
public class MedecinControleur2 {

    private final IServiceMedecin md;


    // Get paginated list of medecins with optional search query
    @GetMapping
    public ResponseEntity<Page<Medecin>> getMedecins(
            @RequestParam(name = "searchQuery", defaultValue = "") String searchQuery,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<Medecin> pageMedecins = md.getMedecinsByName(searchQuery, PageRequest.of(page, size));
        return ResponseEntity.ok(pageMedecins);
    }

    // Delete a medecin by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedecin(@PathVariable Long id) {
        md.deleteMedecin(id);
        return ResponseEntity.noContent().build();
    }

    // Add a new medecin
    @PostMapping
    public ResponseEntity<String> saveMedecin(@Valid @RequestBody Medecin medecin) {
        md.addMedecin(medecin);
        return ResponseEntity.status(HttpStatus.CREATED).body("Medecin added successfully");
    }

    // Update an existing medecin by ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMedecin(@PathVariable Long id, @Valid @RequestBody Medecin updatedMedecin) {
        Medecin existingMedecin = md.getMedecinById(id);
        if (existingMedecin == null) {
            return ResponseEntity.notFound().build();
        }
        updatedMedecin.setId(id); // Ensure the ID remains the same
         md.addMedecin(updatedMedecin); // save or update method
         return ResponseEntity.status(HttpStatus.CREATED).body("Medecin updated successfully");
    }

    // Get a medecin by ID
    @GetMapping("/{id}")
    public ResponseEntity<Medecin> getMedecinById(@PathVariable Long id) {
        Medecin medecin = md.getMedecinById(id);
        if (medecin == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medecin);
    }
}
