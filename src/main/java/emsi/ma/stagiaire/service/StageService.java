package emsi.ma.stagiaire.service;

import emsi.ma.stagiaire.enumeration.Services;
import emsi.ma.stagiaire.model.Stage;
import emsi.ma.stagiaire.repository.StageRepo;
import emsi.ma.stagiaire.repository.StagiaireRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StageService {

    @Autowired
    private StageRepo stageRepo;

    @Autowired
    private StagiaireRepo stagiaireRepo;

    public List<Stage> findAll() {
        return stageRepo.findAll();
    }

    public Optional<Stage> findById(Long id) {
        return stageRepo.findById(id);
    }

    @Transactional
    public void save(Stage stage) {
        stageRepo.save(stage);
    }

    public void deleteById(Long id) {
        stageRepo.deleteById(id);
    }

    public List<Stage> getStagesByEncadrant(Long encadrantId) {
        return stageRepo.findByEncadrantStage_Id(encadrantId);
    }

    public List<Stage> getStagesByStagiaire(Long stagiaireId) {
        return stageRepo.findByStagiaire_Id(stagiaireId);
    }

    public Map<String, Long> getStageCountByService() {
        List<Object[]> results = stageRepo.countStagesByService();
        Map<String, Long> stageCountMap = new HashMap<>();

        for (Services service : Services.values()) {
            stageCountMap.put(service.name(), 0L);
        }

        for (Object[] result : results) {
            String service = ((Services) result[0]).name();
            Long count = (Long) result[1];
            stageCountMap.put(service, count);
        }
        return stageCountMap;
    }

    public List<Long> getStagesWithoutRapport() {
        return stageRepo.findStagesWithoutRapport();
    }
}
