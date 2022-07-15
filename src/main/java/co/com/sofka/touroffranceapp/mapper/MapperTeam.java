package co.com.sofka.touroffranceapp.mapper;

import co.com.sofka.touroffranceapp.collections.Team;
import co.com.sofka.touroffranceapp.model.TeamDTO;
import co.com.sofka.touroffranceapp.repositories.CyclistRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * Clase que se encarga de mapear un Team de la base de datos a una respuesta de la clase
 * dto de la aplicacion o viceversa.
 */

@Component
public class MapperTeam {

    private final CyclistRepository cyclistRepository;

    private final MapperCyclist mapperCyclist;

    public MapperTeam(CyclistRepository cyclistRepository,MapperCyclist mapperCyclist){
        this.cyclistRepository=cyclistRepository;
        this.mapperCyclist=mapperCyclist;
    }

    /**
     * Metodo que se encarga de mapear un DTO de Team a la colección para que se guarde en la base de datos.
     * @param teamId
     * @return Objeto de Team.
     */

    public Function<TeamDTO, Team> mapperATeam(String teamId){
        return updateTeam -> {
            var team = new Team();
            team.setTeamId(teamId);
            team.setTeamName(updateTeam.getTeamName());
            team.setAssociatedCountry(updateTeam.getAssociatedCountry());
            return team;
        };
    }

    /**
     * Metodo que se encarga de mapear desde la colección Team y de la coleccion Cyclist para transferir el objeto
     * de tipo TeamDTO con cada uno de los Ciclistas asociados.
     * @return Objeto de TeamDTO completo.
     */
    public Function<TeamDTO, Mono<TeamDTO>> mapperATeamWithCyclist(){
        return teamDTO ->
                Mono.just(teamDTO).zipWith(
                        cyclistRepository.findAllByTeamId(teamDTO.getTeamId())
                                .map(mapperCyclist.mapperACyclistDTO())
                                .collectList(),
                        (team, cyclists) -> {
                            team.setCyclists(cyclists);
                            return team;
                        }
                );
    }

    /**
     * Metodo que se encarga de mapear desde la colección Team para transferir los datos al DTO..
     * @return Objeto TeamDTO.
     */
    public Function<Team, TeamDTO> mapperATeamDTO(){
        return entity -> new TeamDTO(
                entity.getTeamId(),
                entity.getTeamName(),
                entity.getAssociatedCountry()
        );
    }
}
