package co.com.sofka.touroffranceapp.collections;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

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

    private String fullName;

    private String teamId;

    private String nationality;

    public void setCyclistId(String cyclistId) {
        if(cyclistId==null){
            this.cyclistId=UUID.randomUUID().toString().toUpperCase().substring(0,3);
        }
        else {
            this.cyclistId = cyclistId;
        }
    }
}
