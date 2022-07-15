package co.com.sofka.touroffranceapp.usecases.cyclist;

import co.com.sofka.touroffranceapp.model.CyclistDTO;
import co.com.sofka.touroffranceapp.model.TeamDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface UpdateCyclistInterface {

    Mono<CyclistDTO> updateCyclist (@Valid CyclistDTO cyclistDTO);
}
