package emsi.ma.stagiaire.model;

import emsi.ma.stagiaire.enumeration.Services;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Veuillez saisir la date de début de stage")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @NotNull(message = "Veuillez saisir la date de fin de stage")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_fin")
    private LocalDate dateFin;

    @NotNull(message = "Veuillez séléctionner le stagiaire.")
    @ManyToOne(fetch = FetchType.LAZY)
    private Stagiaire stagiaire;

    @NotNull(message = "Veuillez séléctionner l'encadrant de stage.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encadrantStage_id")
    private EncadrantStage encadrantStage;

    @NotNull(message = "Veuillez séléctionner le service.")
    @Enumerated(EnumType.STRING)
    private Services service;

    @Transient
    private String etat;
}
