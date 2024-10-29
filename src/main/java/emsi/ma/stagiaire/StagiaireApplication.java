package emsi.ma.stagiaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StagiaireApplication {

    public static void main(String[] args) {
        SpringApplication.run(StagiaireApplication.class, args);
    }

    /*@Bean
    CommandLineRunner saveUsers(UserService securityService) {
        return args -> {
            try {
                // Créer des utilisateurs s'ils n'existent pas déjà
                if (securityService.loadUserByUsername("user1") == null) {
                    securityService.saveNewUser("user1", "123", "123");
                }

                if (securityService.loadUserByUsername("yasmina") == null) {
                    securityService.saveNewUser("yasmina", "123", "123");
                }

                if (securityService.loadUserByUsername("mohamed") == null) {
                    securityService.saveNewUser("mohamed", "123", "123");
                }

                // Créer les rôles s'ils n'existent pas déjà
                if (securityService.loadRoleByName("Admin") == null) {
                    securityService.saveNewRole("Admin", "Admin role");
                }

                if (securityService.loadRoleByName("User") == null) {
                    securityService.saveNewRole("User", "User role");
                }

                // Ajouter des rôles aux utilisateurs
                securityService.addRoleToUser("mohamed", "User");
                securityService.addRoleToUser("mohamed", "Admin");

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }*/
}
