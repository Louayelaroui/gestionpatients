package com.formationspringboot.gestionpatients.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.formationspringboot.gestionpatients.entites.Medecin;
import com.formationspringboot.gestionpatients.entites.RendezVous;
import com.formationspringboot.gestionpatients.service.IServiceRendezVous;
import com.formationspringboot.gestionpatients.service.IServiceMedecin;
import com.formationspringboot.gestionpatients.service.IServicePatient;

import jakarta.validation.Valid;

@Controller
public class RendezVousControleur {

    private final IServiceRendezVous sr;
    private final IServicePatient sp;
    private final IServiceMedecin md;

    public RendezVousControleur(IServiceRendezVous sr,IServiceMedecin md, IServicePatient sp) {
        this.sr = sr;
        this.sp = sp;
        this.md = md;
    }
//
//    @GetMapping("/rendezvous")
//    public String getRendezVous(Model m,
//            @RequestParam(name = "searchQuery", defaultValue = "") String searchQuery,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "5") int size) {
//        Page<RendezVous> pageRendezVous = sr.getRendezVousByDate(searchQuery, PageRequest.of(page, size));
//
//        m.addAttribute("data", pageRendezVous.getContent());
//        m.addAttribute("searchQuery", searchQuery);
//        m.addAttribute("pages", new int[pageRendezVous.getTotalPages()]);
//        m.addAttribute("current", page);
//        return "vuerendezvous";
//    }
    
   
    @GetMapping("/rendezvous")
    public String getRendezVous(Model m,
            @RequestParam(name = "searchQuery", defaultValue = "") String searchQuery,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Date searchDate = null;
        if (!searchQuery.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                searchDate = dateFormat.parse(searchQuery);
            } catch (ParseException e) {
                // Handle parsing exception if necessary
            }
        }

        Page<RendezVous> pageRendezVous = sr.findAllWithPatients(searchDate, PageRequest.of(page, size));

        m.addAttribute("data", pageRendezVous.getContent());
        m.addAttribute("searchQuery",searchQuery);
        m.addAttribute("pages", new int[pageRendezVous.getTotalPages()]);
        m.addAttribute("current", page);
        return "vuerendezvous";
    }



    @GetMapping("/rendezvous/delete/{id}")
    public String deleteRendezVous(@PathVariable Long id) {
        sr.deleteRendezVous(id);
        return "redirect:/rendezvous";
    }

    @GetMapping("/rendezvous/add")
    public String addRendezVous(Model m) {
        m.addAttribute("rendezVous", new RendezVous());
        // You can also retrieve a list of patients to associate with the rendezvous
        List<Patient> patients = sp.getAllPatients();
        m.addAttribute("patients", patients);
        List<Medecin> medecins = md.getAllMedecins();
        m.addAttribute("medecins", medecins);
        return "addrendezvous";
    }

    @PostMapping("/rendezvous/save")
    public String saveRendezVous(@Valid @ModelAttribute RendezVous r, BindingResult br) {
        if (br.hasErrors())
            return "addrendezvous";
        sr.addRendezVous(r);
        return "redirect:/rendezvous";
    }

    @GetMapping("/rendezvous/update/{id}")
    public String updateRendezVous(@PathVariable Long id, Model m) {
        RendezVous r = sr.getRendezVousById(id);
        m.addAttribute("rendezVous", r);
        // You can also retrieve a list of patients to associate with the rendezvous
        List<Patient> patients = sp.getAllPatients();
        m.addAttribute("patients", patients);
        List<Medecin> medecins = md.getAllMedecins();
        m.addAttribute("medecins", medecins);
        return "addrendezvous";
    }
}
