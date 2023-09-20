package com.formationspringboot.gestionpatients.web;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formationspringboot.gestionpatients.entites.Patient;
import com.formationspringboot.gestionpatients.service.IServicePatient;

import jakarta.validation.Valid;

@Controller
public class PatientControleur {
	
	IServicePatient sp;

	public PatientControleur(IServicePatient sp) {
		super();
		this.sp = sp;
	}
	
	@GetMapping("/index")
	public String getPatients(Model m,
			@RequestParam(name = "searchQuery" ,defaultValue="") String searchQuery,
			@RequestParam(defaultValue = "0")int page,
			@RequestParam(defaultValue = "5")int size
			) {
		Page<Patient>pagePatients=sp.getPatientsByName(searchQuery, PageRequest.of(page, size));
		
		    
		m.addAttribute("data",pagePatients.getContent());
		m.addAttribute("searchQuery",searchQuery);
		m.addAttribute("pages",new int[pagePatients.getTotalPages()]);
		m.addAttribute("current",page);
		return "vuepatient";
		
	}
	
	
	
	@GetMapping("/delete/{id}")
	public String deletePatient(@PathVariable Long id) {
	    sp.deletePatient(id);
	    return "redirect:/index";
	}
	
//	@GetMapping("/search")
//	public String searchPatients(@RequestParam(name = "searchQuery", required = false) String searchQuery, Model model) {
////	    List<Patient> resultList;
////
////	    if (searchQuery != null && !searchQuery.isEmpty()) {
////	        resultList = sp.getPatientsByName(searchQuery);
////	    } else {
////	        resultList = sp.getAllPatients();
////	    }
////
////	    model.addAttribute("data", resultList);
////	    return "vuepatient";
//		
//		model.addAttribute("data",sp.getPatientsByName(searchQuery));
//		model.addAttribute("searchQuery",searchQuery);
//		 return "vuepatient";
//	}

	@GetMapping("/formpatient")
	public String addPatient(Model m) {
		m.addAttribute("patient",new Patient());
		return "add";
	}
	
	@PostMapping("/save")
	public String savePatient(@Valid @ModelAttribute Patient p ,BindingResult br) {
		if (br.hasErrors())
			return "add";
		sp.addPatient(p);
		return "redirect:/index";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model m) {
		Patient p=sp.getPatientById(id);
		m.addAttribute("patient",p);
		return "add";
	}
	
}
