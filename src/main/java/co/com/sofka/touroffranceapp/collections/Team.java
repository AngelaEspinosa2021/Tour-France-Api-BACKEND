package co.com.sofka.touroffranceapp.collections;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * Team class.
 * Clase que representa a la colección Team que guardará los equipos creados en la base de datos.
 */
@Document(collection = "team")
@Getter
@Setter
public class Team {

    @Id
    private String teamId;

    @NotBlank(message = "Debe tener un nombre de equipo asociado para este objeto.")
    private String teamName;

    @NotBlank(message = "Debe tener un pais asociado para este objeto.")
    private String associatedCountry;


}
