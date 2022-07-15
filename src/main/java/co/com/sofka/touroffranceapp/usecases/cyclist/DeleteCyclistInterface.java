package co.com.sofka.touroffranceapp.usecases.cyclist;

import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * Interfaz funcional para eliminar un Cyclist.
 */

@FunctionalInterface
public interface DeleteCyclistInterface {

    Mono<Void> deleteCyclist (@Valid String cyclistId);
}
