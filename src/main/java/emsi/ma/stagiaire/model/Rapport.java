package emsi.ma.stagiaire.model;

import jakarta.persistence.*;
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
public class Rapport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Veuillez saisir un sujet.")
    private String sujet;

    @NotNull(message = "Veuillez saisir la date de soumission de rapport.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateSoumission;

    @NotNull(message = "Veuillez séléctionner le stage.")
    @OneToOne
    private Stage stage;

}
