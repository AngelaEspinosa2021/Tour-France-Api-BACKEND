package co.com.sofka.touroffranceapp.usecases.team;

import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * Interfaz funcional de eliminar un Team con todos los miembros del equipo.
 */

@FunctionalInterface
public interface DeleteTeamInterface {

    Mono<Void> deleteTeam (@Valid String teamId);
}
