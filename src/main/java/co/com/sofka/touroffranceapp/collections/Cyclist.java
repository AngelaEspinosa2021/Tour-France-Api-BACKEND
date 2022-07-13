package co.com.sofka.touroffranceapp.collections;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Cyclist {

    @Id
    private String cyclistId;

    private String fullName;

    private String teamId;

    private String nationality;


}
