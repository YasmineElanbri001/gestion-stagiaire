package emsi.ma.stagiaire.service;

import emsi.ma.stagiaire.model.EncadrantStage;
import emsi.ma.stagiaire.repository.EncadrantStageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EncadrantStageService {

        @Autowired
        private EncadrantStageRepo encadrantStageRepo;

        public List<EncadrantStage> findAll() {
            return encadrantStageRepo.findAll();
        }

        public Optional<EncadrantStage> findById(Long id) {
            return encadrantStageRepo.findById(id);
        }

        public EncadrantStage save(EncadrantStage encadrantStage) {
            return encadrantStageRepo.save(encadrantStage);
        }

        public void deleteById(Long id) {
            encadrantStageRepo.deleteById(id);
        }

}
