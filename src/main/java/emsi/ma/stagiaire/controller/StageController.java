package emsi.ma.stagiaire.controller;

import emsi.ma.stagiaire.model.Stage;
import emsi.ma.stagiaire.service.EncadrantStageService;
import emsi.ma.stagiaire.service.StageService;
import emsi.ma.stagiaire.service.StagiaireService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class StageController {

    @Autowired
    private StageService stageService;

    @Autowired
    private StagiaireService stagiaireService;

    @Autowired
    private EncadrantStageService encadrantStageService;

    @ModelAttribute("uri")
    public String getCurrentUrl(HttpServletRequest request) {
        return "stage";
    }

    @GetMapping("/stages")
    public String listStages(Model stageModel) {
        List<Stage> stages = stageService.findAll();
        for(Stage s : stages){
            if(LocalDate.now().isAfter(s.getDateFin())){
                s.setEtat("Terminé");
            }else{
                s.setEtat("En cours");
            }
        }
        stageModel.addAttribute("stages", stages);
        stageModel.addAttribute("encadrant", "");
        stageModel.addAttribute("option", "tous");
        return "Stage/list_stage";
    }

    @GetMapping("/stages/encadrant/{id}")
    public String listEncaStages(Model stageModel, @PathVariable(name = "id") Long id) {
        List<Stage> stages = stageService.getStagesByEncadrant(id);
        for(Stage s : stages){
            if(LocalDate.now().isAfter(s.getDateFin())){
                s.setEtat("Terminé");
            }else{
                s.setEtat("En cours");
            }
        }
        stageModel.addAttribute("stages", stages);
        stageModel.addAttribute("encadrant", "ok");
        stageModel.addAttribute("option", "tous");
        return "Stage/list_stage";
    }

    @GetMapping("/stages/stagiaire/{id}")
    public String listStagesStagaire(Model stageModel, @PathVariable(name = "id") Long id) {
        List<Stage> stages = stageService.getStagesByStagiaire(id);
        for(Stage s : stages){
            if(LocalDate.now().isAfter(s.getDateFin())){
                s.setEtat("Terminé");
            }else{
                s.setEtat("En cours");
            }
        }
        stageModel.addAttribute("stages", stages);
        stageModel.addAttribute("stagiaire", "ok");
        stageModel.addAttribute("option", "tous");
        return "Stage/list_stage";
    }

    @PostMapping("/filterStages")
    public String filterStages(@RequestParam("afficher") String option, Model stageModel){
        List<Stage> stages = stageService.findAll();
        for(Stage s : stages){
            if(LocalDate.now().isAfter(s.getDateFin())){
                s.setEtat("Terminé");
            }else{
                s.setEtat("En cours");
            }
        }
        switch (option){
            case "tous" :
                stageModel.addAttribute("stages", stages);
                stageModel.addAttribute("option", "tous");
                break;
            case "encours" :
                stages = stages.stream()
                        .filter(s -> "En cours".equals(s.getEtat()))
                        .collect(Collectors.toList());
                stageModel.addAttribute("stages", stages);
                stageModel.addAttribute("option", "encours");
                break;
            case "terminer" :
                stages = stages.stream()
                        .filter(s -> "Terminé".equals(s.getEtat()))
                        .collect(Collectors.toList());
                stageModel.addAttribute("stages", stages);
                stageModel.addAttribute("option", "terminer");
        }
        return "Stage/list_stage";
    }

    @GetMapping("/stages/create")
    public String createStageForm(Model stageModel) {
        Stage stage = new Stage();
        stageModel.addAttribute("stage", stage);
        stageModel.addAttribute("stagiaires", stagiaireService.getAllStagiaires());
        stageModel.addAttribute("encadrantStages", encadrantStageService.findAll());
        return "Stage/add_stages";
    }

    @PostMapping("/stages")
    public String saveStage(Model stageModel, @Valid Stage stage, BindingResult valResultat) {
        if(valResultat.hasErrors()){
            stageModel.addAttribute("stage", stage);
            stageModel.addAttribute("stagiaires", stagiaireService.getAllStagiaires());
            stageModel.addAttribute("encadrantStages", encadrantStageService.findAll()); // Consistent naming
            return "Stage/add_stages";
        }
        stageService.save(stage);
        stageModel.addAttribute("created","ok");
        stageModel.addAttribute("stage", new Stage());
        return "Stage/add_stages";
    }

    @GetMapping("/stages/edit/{id}")
    public String editStageForm(@PathVariable Long id, Model model) {
        Optional<Stage> stage = stageService.findById(id);
        if (stage.isPresent()) {
            model.addAttribute("stage", stage.get());
            model.addAttribute("stagiaires", stagiaireService.getAllStagiaires());
            model.addAttribute("encadrantStages", encadrantStageService.findAll());
            return "Stage/edit_stages";
        } else {
            return "redirect:/stages";
        }
    }

    @PostMapping("/stages/edit/{id}")
    public String updateStage(Model stageModel, @Valid Stage stage, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            stageModel.addAttribute("stage", stage);
            stageModel.addAttribute("stagiaires", stagiaireService.getAllStagiaires());
            stageModel.addAttribute("encadrantStages", encadrantStageService.findAll());
        }else{
            stageService.save(stage);
            stageModel.addAttribute("stage", stage);
            stageModel.addAttribute("stagiaires", stagiaireService.getAllStagiaires());
            stageModel.addAttribute("encadrantStages", encadrantStageService.findAll());
            stageModel.addAttribute("updated", "ok");
        }
        return "Stage/edit_stages";
    }


    @GetMapping("/stages/delete/{id}")
    public String deleteStage(@PathVariable Long id) {
        stageService.deleteById(id);
        return "redirect:/stages";
    }
}
