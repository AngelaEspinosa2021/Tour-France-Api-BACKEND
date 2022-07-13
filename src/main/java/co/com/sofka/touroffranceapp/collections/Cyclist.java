package co.com.sofka.touroffranceapp.collections;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

/**
 * Cyclist class.
 * Clase que representa a la colección Cyclist que guardará los ciclistas creados en la base de datos.
 */
@Document(collection = "cyclist")
@Getter
@Setter
public class Cyclist {

    @Id
    private String cyclistId;

    @NotBlank
    private String fullName;

    @NotBlank
    private String teamId;

    @NotBlank
    private String nationality;


}
