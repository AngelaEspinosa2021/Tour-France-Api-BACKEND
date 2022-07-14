package co.com.sofka.touroffranceapp.usecases.cyclist;

import co.com.sofka.touroffranceapp.model.CyclistDTO;
import co.com.sofka.touroffranceapp.model.TeamDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * Interfaz funcional de agregar un Cyclist a un Team.
 */
@FunctionalInterface
public interface SaveCyclistInterface {
    Mono<TeamDTO> saveCyclist(@Valid CyclistDTO cyclistDTO);
}
