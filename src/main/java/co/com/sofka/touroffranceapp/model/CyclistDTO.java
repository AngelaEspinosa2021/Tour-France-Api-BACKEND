package co.com.sofka.touroffranceapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * CyclistDTO class.
 * DTO para la colección Cyclist
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CyclistDTO {

    private String cyclistId;

    @NotBlank(message = "Debe tener un nombre completo asociado para este objeto.")
    private String fullName;

    @NotBlank(message = "Debe tener un teamId asociado para este objeto.")
    private String teamId;

    @NotBlank(message = "Debe tener una nacionalidad asociada para este objeto.")
    private String nationality;

    public CyclistDTO(@NotBlank String fullName,@NotBlank String teamId,@NotBlank String nationality) {
        this.cyclistId=UUID.randomUUID().toString().toUpperCase().substring(0,3);
        this.fullName = fullName;
        this.teamId = teamId;
        this.nationality = nationality;
    }
}
