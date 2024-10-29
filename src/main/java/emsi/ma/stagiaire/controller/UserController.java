package emsi.ma.stagiaire.controller;

import emsi.ma.stagiaire.config.model.User;
import emsi.ma.stagiaire.config.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @ModelAttribute("uri")
    public String getCurrentUrl(HttpServletRequest request) {
        return "utilisateur";
    }

    @GetMapping("/utilisateurs")
    public String listUtilisateurs(Model model) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        model.addAttribute("utilisateurs", userService.findAll());
        model.addAttribute("connectedUser", username);
        return "Utilisateur/list_utilisateurs";
    }

    @GetMapping("/utilisateur/create")
    public String createUtilisateurForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "Utilisateur/create_utilisateur";
    }


    @PostMapping("/utilisateur/create")
    public String saveStagiaire(Model userModel, @Valid User user, BindingResult valResultat) {
        boolean hasErrors = valResultat.hasErrors();
        boolean passwordsMatch = user.getPassword().equals(user.getRepassword());

        userModel.addAttribute("user", user);

        if(user.getRepassword() == ""){
            userModel.addAttribute("rpwd", "vide");
        }

        if (!passwordsMatch) {
            userModel.addAttribute("confirmer", "non");
            return "Utilisateur/create_utilisateur";
        } else if (hasErrors) {
            userModel.addAttribute("confirmer", "ok");
            return "Utilisateur/create_utilisateur";
        } else {
            try{
                userService.saveNewUser(user.getUsername(),user.getPassword(), user.getRepassword(),user.getRole());
                userModel.addAttribute("created", "ok");
                userModel.addAttribute("user", new User());
                return "Utilisateur/create_utilisateur";
            }catch (RuntimeException e){
                userModel.addAttribute("userexist", e.getMessage());
                return "Utilisateur/create_utilisateur";
            }
        }
    }

    @GetMapping("/utilisateur/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "Utilisateur/edit_utilisateur";
        }
        return "redirect:/utilisateurs";
    }

    @PostMapping("/utilisateur/edit/{id}")
    public String updateUser(Model userModel, @Valid User user, BindingResult valResultat) {
        boolean hasErrors = valResultat.hasErrors();
        boolean passwordsMatch = user.getPassword().equals(user.getRepassword());

        userModel.addAttribute("user", user);

        if(user.getRepassword() == ""){
            userModel.addAttribute("rpwd", "vide");
        }

        if (!passwordsMatch) {
            userModel.addAttribute("confirmer", "non");
            return "Utilisateur/edit_utilisateur";
        } else if (hasErrors) {
            userModel.addAttribute("confirmer", "ok");
            return "Utilisateur/edit_utilisateur";
        } else {
            userService.updateUser(user.getId(), user.getUsername(),user.getPassword(), user.getRepassword(),user.getRole());
            userModel.addAttribute("updated", "ok");
            userModel.addAttribute("user", new User());
            return "Utilisateur/edit_utilisateur";
        }
    }

    @GetMapping("/utilisateur/delete/{id}")
    public String deleteUtilisateur(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String connectedUsername = auth.getName();
        Optional<User> user = userService.getUserById(id);
        User userToDelete = user.get();
        if (userToDelete.getUsername().equals(connectedUsername)) {
            userService.deleteById(id);
            new SecurityContextLogoutHandler().logout(request, response, auth);
            return "redirect:/login?logout";
        } else {
            // Supprimez l'utilisateur sans déconnexion si ce n'est pas l'utilisateur connecté
            userService.deleteById(id);
            return "redirect:/utilisateurs";
        }
    }

}

