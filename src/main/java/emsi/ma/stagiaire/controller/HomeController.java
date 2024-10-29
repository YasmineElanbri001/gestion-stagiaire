package emsi.ma.stagiaire.controller;

import emsi.ma.stagiaire.enumeration.Services;
import emsi.ma.stagiaire.service.StageService;
import emsi.ma.stagiaire.service.StagiaireService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;

@Controller
public class HomeController {

    @ModelAttribute("uri")
    public String getCurrentUrl(HttpServletRequest request) {
        return "home";
    }

    @Autowired
    private StagiaireService stagiaireService;

    @Autowired
    private StageService stageService;

    @GetMapping("/")
    public String home(Model homeModel) {
        Map<String, Long> stageCountMap = stageService.getStageCountByService();
        homeModel.addAttribute("stageCountMap", stageCountMap);
        homeModel.addAttribute("nbStagiaires", stagiaireService.getAllStagiaires().size());
        homeModel.addAttribute("services", Services.values());
        return "home";
    }
}