package emsi.ma.stagiaire.service;

import emsi.ma.stagiaire.model.Stagiaire;
import emsi.ma.stagiaire.repository.StagiaireRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StagiaireService {

    @Autowired
    private StagiaireRepo stagiaireRepo;


    public List<Stagiaire> getAllStagiaires() {
        return stagiaireRepo.findAll();
    }

    public void saveStagiaire(Stagiaire stagiaire) {
        stagiaireRepo.save(stagiaire);
    }


    public Optional<Stagiaire> getStagiaireById(Long id) {
        return stagiaireRepo.findById(id);
    }

    public void deleteStagiaire(Long id) {
        stagiaireRepo.deleteById(id);
    }

}
