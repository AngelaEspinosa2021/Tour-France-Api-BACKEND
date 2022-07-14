package co.com.sofka.touroffranceapp.usecases.team;

import co.com.sofka.touroffranceapp.model.TeamDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * Interfaz funcional de crear un Team.
 */
@FunctionalInterface
public interface SaveTeamInterface {
    /**
     * Metodo que permite crear un Team.
     * @param teamDTO
     */
    Mono<TeamDTO> saveTeam(@Valid TeamDTO teamDTO);
}
