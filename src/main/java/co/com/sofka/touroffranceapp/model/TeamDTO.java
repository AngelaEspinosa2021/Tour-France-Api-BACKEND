package co.com.sofka.touroffranceapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * TeamDTO class.
 * DTO para la colección Team
 */



@Getter
@Setter
@NoArgsConstructor
public class TeamDTO {

    private String teamId;

    @NotBlank(message = "Debe tener un nombre de equipo asociado para este objeto.")
    private String teamName;

    @NotBlank(message = "Debe tener un pais asociado para este objeto.")
    private String associatedCountry;

    private List<CyclistDTO> cyclists;

    public TeamDTO(String teamId, String teamName, String associatedCountry) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.associatedCountry = associatedCountry;
    }

    public TeamDTO(String teamName, String associatedCountry) {
        this.teamName = teamName;
        this.associatedCountry = associatedCountry;
    }

    public List<CyclistDTO> getCyclists(){
        this.cyclists= Optional.ofNullable(cyclists).orElse(new ArrayList<>());
        return cyclists;
    }

    public void setCyclists(List<CyclistDTO> cyclists) {
        this.cyclists = cyclists;
    }
}
