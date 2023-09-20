package com.formationspringboot.gestionpatients.web;

import com.formationspringboot.gestionpatients.entites.Consultation;
import com.formationspringboot.gestionpatients.service.IServiceConsultation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
    public class ConsultationControleur {

        IServiceConsultation cons;


        public ConsultationControleur(IServiceConsultation cons) {
            this.cons = cons;
        }

    @GetMapping("/consultation")
    public String getConsultation(Model m,
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
        Page<Consultation> pageconsultaion = cons.findAllWithConsultation(searchDate, PageRequest.of(page, size));

        if (pageconsultaion != null) {
            m.addAttribute("data", pageconsultaion.getContent());
            m.addAttribute("pages", new int[pageconsultaion.getTotalPages()]);
        } else {
            // You can choose to handle this however you want.
            // Maybe add an attribute that suggests no data was found.
            m.addAttribute("data", List.of());
            m.addAttribute("pages", new int[0]);
        }

        m.addAttribute("searchQuery", searchQuery);
        m.addAttribute("current", page);
        return "consultation";
    }



        @GetMapping("/consultation/delete/{id}")
        public String deleteConsultation(@PathVariable Long id) {
            cons.deleteConsultation(id);
            return "redirect:/consultation";
        }

        @GetMapping("/consultation/add")
        public String addConsultation(Model m) {
            m.addAttribute("consultation", new Consultation());
            return "addconsultation";
        }

        @PostMapping("/consultation/save")
        public String saveconsultation(@Valid @ModelAttribute Consultation c, BindingResult br) {
            if (br.hasErrors())
                return "consultation";
            cons.addConsultation(c);
            return "redirect:/consultation";
        }

        @GetMapping("/consultation/update/{id}")
        public String updateconsultation(@PathVariable Long id, Model m) {
            Consultation c = cons.getConsultationByid(id);
            m.addAttribute("consultation", c);
            return "addconsultation";
        }
    }
