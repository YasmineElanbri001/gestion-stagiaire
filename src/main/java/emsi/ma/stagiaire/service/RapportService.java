package emsi.ma.stagiaire.service;

import emsi.ma.stagiaire.model.Rapport;
import emsi.ma.stagiaire.repository.RapportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RapportService {


        @Autowired
        private RapportRepo rapportRepo;

        public List<Rapport> getAllRapports() {
            return rapportRepo.findAll();
        }

        public Rapport getRapportById(Long id) {
            return rapportRepo.findById(id).orElse(null);
        }

        public Rapport saveRapport(Rapport rapport) {
            return rapportRepo.save(rapport);
        }

        public void deleteRapport(Long id) {
            rapportRepo.deleteById(id);
        }
}
