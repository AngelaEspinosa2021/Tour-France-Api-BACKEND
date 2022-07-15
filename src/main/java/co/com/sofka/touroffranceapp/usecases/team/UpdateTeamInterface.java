package co.com.sofka.touroffranceapp.usecases.team;

import co.com.sofka.touroffranceapp.model.CyclistDTO;
import co.com.sofka.touroffranceapp.model.TeamDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * Interfaz funcional de modificar un Team.
 */

@FunctionalInterface
public interface UpdateTeamInterface {

    Mono<TeamDTO> updateTeam (@Valid TeamDTO teamDTO);
}
