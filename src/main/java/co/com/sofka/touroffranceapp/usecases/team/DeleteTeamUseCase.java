package co.com.sofka.touroffranceapp.usecases.team;

import co.com.sofka.touroffranceapp.exceptions.CustomExceptionInternalServerError;
import co.com.sofka.touroffranceapp.exceptions.CustomExceptionNotFound;
import co.com.sofka.touroffranceapp.repositories.CyclistRepository;
import co.com.sofka.touroffranceapp.repositories.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

/**
 * Clase que representa el caso de uso de eliminar un Team con todos sus Cyclist asociados.
 */

@Service
@Validated
public class DeleteTeamUseCase implements DeleteTeamInterface{

    private final TeamRepository teamRepository;

    private final CyclistRepository cyclistRepository;

    public DeleteTeamUseCase(TeamRepository teamRepository,CyclistRepository cyclistRepository){
        this.teamRepository=teamRepository;
        this.cyclistRepository=cyclistRepository;
    }

    @Override
    public Mono<Void> deleteTeam(String teamId) {
        return teamRepository.deleteById(teamId)
                .switchIfEmpty(Mono.defer(() -> cyclistRepository.deleteByTeamId(teamId)))
                .onErrorResume(error -> {
                    if (error.getMessage().equals("404 NOT_FOUND")) {
                        return Mono.error(new CustomExceptionNotFound("El Equipo no se encuentra registrado"));
                    }
                   return Mono.error(new CustomExceptionInternalServerError("Campos vacios"));
                });
    }
}
