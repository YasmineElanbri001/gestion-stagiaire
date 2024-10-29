package emsi.ma.stagiaire.controller;

import emsi.ma.stagiaire.model.Stagiaire;
import emsi.ma.stagiaire.service.StagiaireService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Correct import
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class StagiaireController {

    @Autowired
    private StagiaireService stagiaireService;

    @ModelAttribute("uri")
    public String getCurrentUrl(HttpServletRequest request) {
        return "stagiaire";
    }

    @GetMapping("/stagiaires")
    public String listStagiaires(Model model) {
        model.addAttribute("stagiaires", stagiaireService.getAllStagiaires());
        return "Stagiaire/list_stagiaires";
    }

    @GetMapping("/stagiaires/new")
    public String createStagiaireForm(Model model) {
        Stagiaire stagiaire = new Stagiaire();
        model.addAttribute("stagiaire", stagiaire);
        return "Stagiaire/create_stagiaires";
    }


    @PostMapping("/stagiaires")
    public String saveStagiaire(Model stagiaireModel, @Valid Stagiaire stagiaire, BindingResult valResultat) {
        if (valResultat.hasErrors()) {
            stagiaireModel.addAttribute("stagiaire", stagiaire);
            return "Stagiaire/create_stagiaires";
        }
        stagiaireService.saveStagiaire(stagiaire);
        stagiaireModel.addAttribute("created", "ok");
        stagiaireModel.addAttribute("stagiaire", new Stagiaire());
        return "Stagiaire/create_stagiaires";
    }

    @GetMapping("/stagiaires/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Optional<Stagiaire> stagiaire = stagiaireService.getStagiaireById(id);
        if (stagiaire.isPresent()) {
            model.addAttribute("stagiaire", stagiaire.get());
            return "Stagiaire/edit_stagiaires";
        }
        return "redirect:/stagiaires";
    }

    @PostMapping("/stagiaires/edit/{id}")
    public String updateStagiaire(Model stagiaireModel, @Valid Stagiaire stagiaire, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "edit_stagiaires";
        stagiaireService.saveStagiaire(stagiaire);
        stagiaireModel.addAttribute("stagiaire", stagiaire);
        stagiaireModel.addAttribute("updated", "ok");
        return "Stagiaire/edit_stagiaires";
    }

    @GetMapping("/stagiaires/delete/{id}")
    public String deleteStagiaire(@PathVariable Long id) {
        stagiaireService.deleteStagiaire(id);
        return "redirect:/stagiaires";
    }

}
