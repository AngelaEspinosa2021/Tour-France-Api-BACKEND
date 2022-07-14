package co.com.sofka.touroffranceapp.usecases.team;

import co.com.sofka.touroffranceapp.mapper.MapperTeam;
import co.com.sofka.touroffranceapp.model.TeamDTO;
import co.com.sofka.touroffranceapp.repositories.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

/**
 * Clase que representa el caso de uso de crear un Team.
 */

@Service
@Validated
public class CreateTeamUseCase implements SaveTeamInterface {

    private final TeamRepository teamRepository;

    private final MapperTeam mapperTeam;

    public CreateTeamUseCase(TeamRepository teamRepository, MapperTeam mapperTeam){
        this.teamRepository=teamRepository;
        this.mapperTeam=mapperTeam;
    }

    /**
     * Metodo que permite crear un nuevo Team
     * @param teamDTO
     * @return Objeto de TeamDTO
     */
    @Override
    public Mono<TeamDTO> saveTeam(TeamDTO teamDTO) {
        return teamRepository
                .save(mapperTeam.mapperATeam(null).apply(teamDTO))
                .map(mapperTeam.mapperATeamDTO());
    }
}
