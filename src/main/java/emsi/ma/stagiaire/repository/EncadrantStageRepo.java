package emsi.ma.stagiaire.repository;


import emsi.ma.stagiaire.model.EncadrantStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncadrantStageRepo extends JpaRepository<EncadrantStage, Long> {
}
