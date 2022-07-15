package co.com.sofka.touroffranceapp.usecases.team;

import co.com.sofka.touroffranceapp.exceptions.CustomExceptionInternalServerError;
import co.com.sofka.touroffranceapp.exceptions.CustomExceptionNotFound;
import co.com.sofka.touroffranceapp.mapper.MapperCyclist;
import co.com.sofka.touroffranceapp.mapper.MapperTeam;
import co.com.sofka.touroffranceapp.model.TeamDTO;
import co.com.sofka.touroffranceapp.repositories.CyclistRepository;
import co.com.sofka.touroffranceapp.repositories.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class GetAllTeamByCountryUseCase implements Function<String, Flux<TeamDTO>> {

    private final TeamRepository teamRepository;

    private final MapperTeam mapperTeam;

    private final CyclistRepository cyclistRepository;

    private final MapperCyclist mapperCyclist;

    public GetAllTeamByCountryUseCase(TeamRepository teamRepository,MapperTeam mapperTeam,CyclistRepository cyclistRepository,MapperCyclist mapperCyclist){
        this.teamRepository=teamRepository;
        this.mapperTeam=mapperTeam;
        this.cyclistRepository=cyclistRepository;
        this.mapperCyclist=mapperCyclist;
    }


    @Override
    public Flux<TeamDTO> apply(String country) {
        return teamRepository.findAllByAssociatedCountry(country)
                .map(mapperTeam.mapperATeamDTO())
                .flatMap(mapperTeam.mapperATeamWithCyclist())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .onErrorResume(error -> {
                    if (error.getMessage().equals("404 NOT_FOUND")) {
                        return Mono.error(new CustomExceptionNotFound("No tenemos Equipos representando a ese pais."));
                    }
                    return Mono.error(new CustomExceptionInternalServerError("Campos vacios"));
                });
    }
}
