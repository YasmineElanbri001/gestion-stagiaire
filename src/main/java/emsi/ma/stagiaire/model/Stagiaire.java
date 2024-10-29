package emsi.ma.stagiaire.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stagiaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=2,max=100, message= "la taille doit être entre 2 et 100 caractère")
    private String nom;

    @NotNull(message = "Veuillez saisir le prénom")
    @Size(min=2,max=100, message= "la taille doit être entre 2 et 100 caractère")
    private String prenom;

    @NotNull
    @NotBlank(message= "Veuillez saisir un email")
    @Email(message= "Veuillez saisir un email.")
    private String email;

    @Size(min=4,max=100, message= "Adresse invalide.")
    private String adresse;

    @Pattern(regexp = "^[0-9]{10}$", message = "Veuillez saisir un numéro de téléphone valide")
    private String telephone;

    @OneToMany(mappedBy = "stagiaire", fetch = FetchType.EAGER)
    private List<Stage> stage = new ArrayList<>();

}
