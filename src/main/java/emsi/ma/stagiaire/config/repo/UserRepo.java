package emsi.ma.stagiaire.config.repo;

import emsi.ma.stagiaire.config.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
