package emsi.ma.stagiaire.repository;


import emsi.ma.stagiaire.model.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StagiaireRepo extends JpaRepository<Stagiaire, Long> {
}
