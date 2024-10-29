package emsi.ma.stagiaire.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncadrantStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Veuillez saisir votre nom.")
    @Size(min=2,max=100, message= "la taille doit être entre 2 et 100 caractère.")
    private String nom;

    @NotBlank(message = "Veuillez saisir votre prénom.")
    @Size(min=2,max=100, message= "la taille doit être entre 2 et 100 caractère.")
    private String prenom;

    @NotBlank(message= "Veuillez saisir un email.")
    @Email(message= "Veuillez saisir un email valide.")
    private String email;

    @NotBlank(message= "Veuillez saisir votre adresse.")
    @Size(min=4,max=100, message= "Adresse invalide.")
    private String adresse;

    @NotBlank(message= "Veuillez saisir votre numéro de téléphone.")
    @Pattern(regexp = "^[0-9]{10}$", message = "Veuillez saisir un numéro de téléphone valide.")
    private String telephone;

    @OneToMany(mappedBy = "encadrantStage", fetch = FetchType.EAGER)
    private List<Stage> stages;

}
