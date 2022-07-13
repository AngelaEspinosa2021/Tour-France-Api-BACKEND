package co.com.sofka.touroffranceapp.collections;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Team {

    @Id
    private String teamId;

    private String teamName;

    private String associatedCountry;

}
