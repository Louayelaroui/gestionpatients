package com.formationspringboot.gestionpatients.web;

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

import com.formationspringboot.gestionpatients.entites.Medecin;
import com.formationspringboot.gestionpatients.service.IServiceMedecin;

import jakarta.validation.Valid;

@Controller
public class MedecinControleur {
	IServiceMedecin md;
	public MedecinControleur(IServiceMedecin md) {
		super();
		this.md = md;
	}
	@GetMapping("/medecin/index")
	public String getMedecins(Model m,
			@RequestParam(name = "searchQuery" ,defaultValue="") String searchQuery,
			@RequestParam(defaultValue = "0")int page,
			@RequestParam(defaultValue = "5")int size
			) {
		Page<Medecin>pageMedecins=md.getMedecinsByName(searchQuery, PageRequest.of(page, size));
		
		    
		m.addAttribute("data",pageMedecins.getContent());
		m.addAttribute("searchQuery",searchQuery);
		m.addAttribute("pages",new int[pageMedecins.getTotalPages()]);
		m.addAttribute("current",page);
		return "vuemedecin";
		
	}
	
	
	
	@GetMapping("/medecin/delete/{id}")
	public String deleteMedecin(@PathVariable Long id) {
	    md.deleteMedecin(id);
	    return "redirect:/medecin/index";
	}
	
	@GetMapping("/medecin/formmedecin")
	public String addMedecin(Model m) {
	    m.addAttribute("medecin", new Medecin());
	    return "add_medecin";
	}

	
	@PostMapping("/medecin/save")
	public String saveMedecin(@Valid @ModelAttribute Medecin p ,BindingResult br) {
		if (br.hasErrors())
			return "add_medecin";
		md.addMedecin(p);
		return "redirect:/medecin/index";
	}
	
	@GetMapping("/medecin/update/{id}")
	public String update(@PathVariable Long id, Model m) {
		Medecin p=md.getMedecinById(id);
		m.addAttribute("medecin",p);
		return "add_medecin";
	}
}
