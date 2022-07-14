package co.com.sofka.touroffranceapp.mapper;

import co.com.sofka.touroffranceapp.collections.Cyclist;
import co.com.sofka.touroffranceapp.model.CyclistDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Clase que se encarga de mapear un Cyclist de la base de datos a una respuesta de la clase
 * dto de la aplicacion o viceversa.
 */

@Component
public class MapperCyclist {

    /**
     * Metodo que se encarga de mapear un DTO de Cyclist a la colección para que se guarde en la base de datos.
     * @param cyclistId
     * @return Objeto de Cyclist.
     */

    public Function<CyclistDTO, Cyclist> mapperACyclist(String cyclistId){
        return updateCyclist -> {
          var cyclist = new Cyclist();
          cyclist.setCyclistId(cyclistId);
          cyclist.setFullName(updateCyclist.getFullName());
          cyclist.setTeamId(updateCyclist.getTeamId());
          cyclist.setNationality(updateCyclist.getNationality());
          return cyclist;
        };
    }

    /**
     * Metodo que se encarga de mapear desde la colección Cyclist para transferir los datos al DTO.
     * @return Objeto de CyclistDTO
     */
    public Function<Cyclist, CyclistDTO> mapperACyclistDTO(){
        return entity -> new CyclistDTO(
                entity.getCyclistId(),
                entity.getFullName(),
                entity.getTeamId(),
                entity.getNationality()
        );
    }
}
