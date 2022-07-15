package co.com.sofka.touroffranceapp.usecases.cyclist;

import co.com.sofka.touroffranceapp.exceptions.CustomExceptionInternalServerError;
import co.com.sofka.touroffranceapp.exceptions.CustomExceptionNotFound;
import co.com.sofka.touroffranceapp.mapper.MapperCyclist;
import co.com.sofka.touroffranceapp.repositories.CyclistRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 * Clase que representa el caso de uso de eliminar un Cyclist.
 */
@Service
@Validated
public class DeleteCyclistUseCase implements DeleteCyclistInterface{

    private final CyclistRepository cyclistRepository;

    private final MapperCyclist mapperCyclist;

    public DeleteCyclistUseCase(CyclistRepository cyclistRepository,MapperCyclist mapperCyclist){
        this.cyclistRepository=cyclistRepository;
        this.mapperCyclist=mapperCyclist;
    }

    @Override
    public Mono<Void> deleteCyclist(String cyclistId) {
        return cyclistRepository.deleteById(cyclistId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .onErrorResume(error -> {
                    if (error.getMessage().equals("404 NOT_FOUND")) {
                        return Mono.error(new CustomExceptionNotFound("El Ciclista no se encuentra registrado."));
                    }
                    return Mono.error(new CustomExceptionInternalServerError("Campos vacios"));
                });
    }
}
