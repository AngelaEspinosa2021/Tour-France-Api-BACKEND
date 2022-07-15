package co.com.sofka.touroffranceapp.usecases.team;

import co.com.sofka.touroffranceapp.exceptions.CustomExceptionInternalServerError;
import co.com.sofka.touroffranceapp.exceptions.CustomExceptionNotFound;
import co.com.sofka.touroffranceapp.mapper.MapperTeam;
import co.com.sofka.touroffranceapp.model.TeamDTO;
import co.com.sofka.touroffranceapp.repositories.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 * Clase que representa el caso de uso de modificar un Equipo.
 */
@Service
@Validated
public class UpdateTeamUseCase implements UpdateTeamInterface{

    private final TeamRepository teamRepository;

    private final MapperTeam mapperTeam;

    public UpdateTeamUseCase(TeamRepository teamRepository,MapperTeam mapperTeam){
        this.teamRepository=teamRepository;
        this.mapperTeam=mapperTeam;
    }

    @Override
    public Mono<TeamDTO> updateTeam(TeamDTO teamDTO) {
        return teamRepository.findById(teamDTO.getTeamId()).flatMap(team ->
                        teamRepository.save(mapperTeam.mapperATeam(teamDTO.getTeamId()).apply(teamDTO))
                                .map(mapperTeam.mapperATeamDTO())
                                .flatMap(mapperTeam.mapperATeamWithCyclist()))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .onErrorResume(error -> {
                    if (error.getMessage().equals("404 NOT_FOUND")) {
                        return Mono.error(new CustomExceptionNotFound("El Equipo no se encuentra registrado."));
                    }
                    return Mono.error(new CustomExceptionInternalServerError("Campos vacios"));
                });
    }
}
