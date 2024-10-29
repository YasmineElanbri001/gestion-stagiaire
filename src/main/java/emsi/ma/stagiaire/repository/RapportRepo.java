package emsi.ma.stagiaire.repository;


import emsi.ma.stagiaire.model.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RapportRepo extends JpaRepository<Rapport, Long> {
}
