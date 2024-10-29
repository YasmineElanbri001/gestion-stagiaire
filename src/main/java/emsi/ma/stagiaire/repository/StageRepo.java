package emsi.ma.stagiaire.repository;


import emsi.ma.stagiaire.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StageRepo extends JpaRepository<Stage, Long> {
    List<Stage> findByEncadrantStage_Id(Long encadrantId);

    List<Stage> findByStagiaire_Id(Long stagaireId);

    @Query("SELECT s.service, COUNT(s) FROM Stage s GROUP BY s.service")
    List<Object[]> countStagesByService();

    @Query("SELECT s.id FROM Stage s WHERE s.id NOT IN (SELECT r.stage.id FROM Rapport r)")
    List<Long> findStagesWithoutRapport();
}


