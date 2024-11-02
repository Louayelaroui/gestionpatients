package com.formationspringboot.gestionpatients.restapi;


import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import com.formationspringboot.gestionpatients.entites.Patient;
import com.formationspringboot.gestionpatients.service.IServicePatient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController

@RequestMapping("api/")
public class PatientControleur2 {
	
	IServicePatient sp;

	
	
	public PatientControleur2(IServicePatient sp) {
		super();
		this.sp = sp;
	}



	@GetMapping("/index")
	public List<Patient> getAllPatients() {
		return sp.getAllPatients();
		
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	public void deletePatient(@PathVariable Long id) {
	    sp.deletePatient(id);
	   
	}

	
    @GetMapping(value = "/image/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable Long id) throws IOException {
        return sp.getMedicalImage(id);
    }

    @PostMapping("/save")
    public void addProduct(@RequestParam String p, @RequestParam MultipartFile mf) throws IOException {
        Patient patient=new ObjectMapper().readValue(p,Patient.class);
        sp.addPatient(patient,mf);
    }
    
	
	@PutMapping("/update/{id}")
	public void update(@RequestBody Patient p  ) {
		sp.addPatient(p);
	}
	
}
