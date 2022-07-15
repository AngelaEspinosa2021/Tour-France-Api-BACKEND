package co.com.sofka.touroffranceapp.usecases.team;

import co.com.sofka.touroffranceapp.exceptions.CustomExceptionBadRequest;
import co.com.sofka.touroffranceapp.exceptions.CustomExceptionInternalServerError;
import co.com.sofka.touroffranceapp.mapper.MapperTeam;
import co.com.sofka.touroffranceapp.model.TeamDTO;
import co.com.sofka.touroffranceapp.repositories.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.net.http.HttpConnectTimeoutException;

/**
 * Clase que representa el caso de uso de crear un Team.
 */

@Service
@Validated
public class CreateTeamUseCase implements SaveTeamInterface {

    private final TeamRepository teamRepository;

    private final MapperTeam mapperTeam;

    public CreateTeamUseCase(TeamRepository teamRepository, MapperTeam mapperTeam) {
        this.teamRepository = teamRepository;
        this.mapperTeam = mapperTeam;
    }

    /**
     * Metodo que permite crear un nuevo Team
     *
     * @param teamDTO
     * @return Objeto de TeamDTO
     */
    @Override
    public Mono<TeamDTO> saveTeam(TeamDTO teamDTO) {
        return teamRepository.findByTeamName(teamDTO.getTeamName())
                .map(element -> {
                    if (element.getTeamName().contentEquals(teamDTO.getTeamName())) {
                        throw new CustomExceptionInternalServerError("Nombre de Equipo ya registrado.");
                    }
                    return element;
                })
                .switchIfEmpty(teamRepository.save(mapperTeam.mapperATeamWithId(null).apply(teamDTO))
                        .map(mapperTeam.mapperATeamDTO()));

    }
}




