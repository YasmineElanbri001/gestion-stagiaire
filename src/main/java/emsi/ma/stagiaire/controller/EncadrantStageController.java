package emsi.ma.stagiaire.controller;

import emsi.ma.stagiaire.model.EncadrantStage;
import emsi.ma.stagiaire.model.Stagiaire;
import emsi.ma.stagiaire.service.EncadrantStageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class EncadrantStageController {

    @Autowired
    private EncadrantStageService encadrantStageService;

    @ModelAttribute("uri")
    public String getCurrentUrl(HttpServletRequest request) {
        return "encadrant";
    }

    @GetMapping("/encstages")
    public String listEncadrants(Model model) {
        model.addAttribute("encstages", encadrantStageService.findAll());
        return "/EncadStage/list_encstages";
    }

    @GetMapping("/encstages/create")
    public String createEncadrantForm(Model model) {
        model.addAttribute("encadrantStage", new EncadrantStage());
        return "/EncadStage/create_encstages";
    }

    @PostMapping("/encstages")
    public String save(Model encadrantModel, @Valid EncadrantStage encadrantStage, BindingResult valResultat) {
        if(valResultat.hasErrors()){
            encadrantModel.addAttribute("encadrantStage", encadrantStage);
            return "EncadStage/create_encstages";
        }
        encadrantStageService.save(encadrantStage);
        encadrantModel.addAttribute("created","ok");
        encadrantModel.addAttribute("stagiaire", new Stagiaire());
        return "EncadStage/create_encstages";
    }

    @GetMapping("/encstages/edit/{id}")
    public String editEncadrantForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("encadrantStage", encadrantStageService.findById(id).orElseThrow());
        return "EncadStage/edit_encstages";
    }

    @PostMapping("/encstages/edit/{id}")
    public String editEncadrant(Model encadrantModel, @Valid EncadrantStage encadrantStage, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "EncadStage/edit_encstages";
        encadrantStageService.save(encadrantStage);
        encadrantModel.addAttribute("encadrantStage", encadrantStage);
        encadrantModel.addAttribute("updated", "ok");
        return "EncadStage/edit_encstages";
    }

    @GetMapping("/encstages/delete/{id}")
    public String deleteEncadrant(@PathVariable("id") Long id) {
        encadrantStageService.deleteById(id);
        return "redirect:/encstages";
    }
}
