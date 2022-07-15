package co.com.sofka.touroffranceapp.usecases.cyclist;


import co.com.sofka.touroffranceapp.exceptions.CustomExceptionInternalServerError;
import co.com.sofka.touroffranceapp.exceptions.CustomExceptionNotFound;
import co.com.sofka.touroffranceapp.mapper.MapperCyclist;
import co.com.sofka.touroffranceapp.model.CyclistDTO;
import co.com.sofka.touroffranceapp.repositories.CyclistRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * Clase que representa el caso de uso para listar todos los Cyclist asociados a un pais.
 */

@Service
@Validated
public class GetAllCyclistByNationalityUseCase implements Function<String, Flux<CyclistDTO>> {

    private final CyclistRepository cyclistRepository;

    private final MapperCyclist mapperCyclist;

    public GetAllCyclistByNationalityUseCase(CyclistRepository cyclistRepository,MapperCyclist mapperCyclist){
        this.cyclistRepository=cyclistRepository;
        this.mapperCyclist=mapperCyclist;
    }

    @Override
    public Flux<CyclistDTO> apply(String nationality) {
        return cyclistRepository.findAllByNationality(nationality)
                .map(mapperCyclist.mapperACyclistDTO())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .onErrorResume(error -> {
                    if (error.getMessage().equals("404 NOT_FOUND")) {
                        return Mono.error(new CustomExceptionNotFound("No tenemos Ciclistas de esa nacionalidad."));
                    }
                    return Mono.error(new CustomExceptionInternalServerError("Campos vacios"));
                });
    }
}
