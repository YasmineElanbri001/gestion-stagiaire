//package emsi.ma.stagiaire.service;
//
//import emsi.ma.stagiaire.model.User;
//import emsi.ma.stagiaire.repository.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepo userRepo;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public void save(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepo.save(user);
//    }َ
//
//    public User findByUsername(String username) {
//        return userRepo.findByUsername(username);
//    }
//}
