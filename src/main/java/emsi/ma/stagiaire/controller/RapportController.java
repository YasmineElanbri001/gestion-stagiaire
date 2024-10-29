package emsi.ma.stagiaire.controller;

import emsi.ma.stagiaire.model.Rapport;
import emsi.ma.stagiaire.model.Stagiaire;
import emsi.ma.stagiaire.service.*;
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
public class RapportController {
    @Autowired
    private RapportService rapportService;
    @Autowired
    private StageService stageService;

    @ModelAttribute("uri")
    public String getCurrentUrl(HttpServletRequest request) {
        return "rapport";
    }

    @GetMapping("/rapports")
    public String listRapports(Model model) {
        model.addAttribute("rapports", rapportService.getAllRapports());
        return "Rapp/list_rapports";
    }

    @GetMapping("/rapports/create")
    public String createRapportForm(Model model) {
        model.addAttribute("rapport", new Rapport());
        model.addAttribute("stages", stageService.getStagesWithoutRapport());

        return "Rapp/create_rapports";
    }

    @PostMapping("/rapports")
    public String saveRapport(Model rapportModel, @Valid Rapport rapport, BindingResult valResultat) {
        if (valResultat.hasErrors()) {
            rapportModel.addAttribute("rapport", rapport);
            rapportModel.addAttribute("stages", stageService.getStagesWithoutRapport());
            return "Rapp/create_rapports";
        }
        rapportService.saveRapport(rapport);
        rapportModel.addAttribute("created", "ok");
        rapportModel.addAttribute("rapport", new Rapport());
        rapportModel.addAttribute("stages", stageService.getStagesWithoutRapport());
        return "Rapp/create_rapports";
    }

    @GetMapping("/rapports/edit/{id}")
    public String editRapportForm(@PathVariable Long id, Model model) {
        Rapport rapport = rapportService.getRapportById(id);
        model.addAttribute("rapport", rapport);
        model.addAttribute("stages", stageService.getStagesWithoutRapport());
        return "Rapp/edit_rapports";
    }

    @PostMapping("/rapports/edit/{id}")
    public String updateRapport(Model rapportModel, @Valid Rapport rapport, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            rapportModel.addAttribute("rapport", rapport);
            rapportModel.addAttribute("stages", stageService.getStagesWithoutRapport());
        }else{
            rapportService.saveRapport(rapport);
            rapportModel.addAttribute("rapport", rapport);
            rapportModel.addAttribute("stages", stageService.getStagesWithoutRapport());
            rapportModel.addAttribute("updated", "ok");
        }
        return "Rapp/edit_rapports";
    }

    @GetMapping("/rapports/delete/{id}")
    public String deleteRapport(@PathVariable Long id) {
        rapportService.deleteRapport(id);
        return "redirect:/rapports";
    }
}
