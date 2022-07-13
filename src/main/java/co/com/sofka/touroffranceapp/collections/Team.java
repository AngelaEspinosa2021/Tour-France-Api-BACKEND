package co.com.sofka.touroffranceapp.collections;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

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

    @NotBlank
    private String teamName;

    @NotBlank
    private String associatedCountry;

}
