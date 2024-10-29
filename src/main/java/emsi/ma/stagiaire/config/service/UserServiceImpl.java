package emsi.ma.stagiaire.config.service;

import emsi.ma.stagiaire.config.model.User;
import emsi.ma.stagiaire.config.repo.UserRepo;
import emsi.ma.stagiaire.enumeration.Role;
import emsi.ma.stagiaire.model.Stage;
import emsi.ma.stagiaire.model.Stagiaire;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j

public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveNewUser(String username, String password, String repassword, Role role) {

        User existingUser = userRepo.findByUsername(username);
        if (existingUser != null) throw new RuntimeException("Utilisateur " + username + " existe déjà !");

        String hashedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setRepassword(hashedPassword);
        user.setRole(role);
        user.setActive(true);
        return userRepo.save(user);
    }

    @Override
    public User updateUser(Long id, String username, String password, String repassword, Role role) {
        Optional<User> optionalUser = userRepo.findById(id);
        User user = optionalUser.orElseThrow(() -> new RuntimeException("Utilisateur avec ID " + id + " n'existe pas !"));
        String hashedPassword = passwordEncoder.encode(password);
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setRepassword(hashedPassword);
        user.setRole(role);
        user.setActive(true);
        return userRepo.save(user);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }
}


