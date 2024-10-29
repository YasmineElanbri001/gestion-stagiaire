package emsi.ma.stagiaire.controller;

import emsi.ma.stagiaire.service.AttestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
public class AttestationController {

    @Autowired
    private AttestationService attestationService;

    @GetMapping("/genererattestation")
    public ResponseEntity<byte[]> generateAttestation(
            @RequestParam String nomStagiaire,
            @RequestParam String service,
            @RequestParam String dateDebut,
            @RequestParam String dateFin) {

        ByteArrayInputStream bis = attestationService.generateAttestationPdf(nomStagiaire, service, dateDebut, dateFin);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=attestation_stage.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }
}

