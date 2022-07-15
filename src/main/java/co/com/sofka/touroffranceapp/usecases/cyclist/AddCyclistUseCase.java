package co.com.sofka.touroffranceapp.usecases.cyclist;

import co.com.sofka.touroffranceapp.mapper.MapperCyclist;
import co.com.sofka.touroffranceapp.model.CyclistDTO;
import co.com.sofka.touroffranceapp.model.TeamDTO;
import co.com.sofka.touroffranceapp.repositories.CyclistRepository;
import co.com.sofka.touroffranceapp.usecases.team.GetTeamUseCase;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * Clase que representa el caso de uso de agregar un Cyclist a un Team.
 */
@Service
@Validated
public class AddCyclistUseCase implements SaveCyclistInterface{

    private final CyclistRepository cyclistRepository;

    private final MapperCyclist mapperCyclist;

    private final GetTeamUseCase getTeamUseCase;

    public AddCyclistUseCase(CyclistRepository cyclistRepository,MapperCyclist mapperCyclis,GetTeamUseCase getTeamUseCase){
        this.cyclistRepository=cyclistRepository;
        this.mapperCyclist=mapperCyclis;
        this.getTeamUseCase=getTeamUseCase;
    }

    @Override
    public Mono<TeamDTO> saveCyclist(CyclistDTO cyclistDTO) {
        return getTeamUseCase.apply(cyclistDTO.getTeamId()).flatMap(team ->
                cyclistRepository.save(mapperCyclist.mapperACyclist(null).apply(cyclistDTO))
                        .map(cyclist -> {
                            team.getCyclists().add(cyclistDTO);
                            return team;
                        })
                );
    }
}
