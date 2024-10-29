package emsi.ma.stagiaire.config.service;

import emsi.ma.stagiaire.config.model.User;
import emsi.ma.stagiaire.enumeration.Role;

public interface UserService {
        User saveNewUser(String username, String password, String repassword, Role role);
        User updateUser(Long id, String username, String password, String repassword, Role role);

}
