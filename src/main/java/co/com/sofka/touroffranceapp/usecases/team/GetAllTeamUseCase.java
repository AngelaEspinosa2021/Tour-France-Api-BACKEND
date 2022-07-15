package co.com.sofka.touroffranceapp.usecases.team;

import co.com.sofka.touroffranceapp.mapper.MapperCyclist;
import co.com.sofka.touroffranceapp.mapper.MapperTeam;
import co.com.sofka.touroffranceapp.model.TeamDTO;
import co.com.sofka.touroffranceapp.repositories.CyclistRepository;
import co.com.sofka.touroffranceapp.repositories.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class GetAllTeamUseCase implements Supplier<Flux<TeamDTO>> {

    private final TeamRepository teamRepository;

    private final MapperTeam mapperTeam;

    private final CyclistRepository cyclistRepository;

    private final MapperCyclist mapperCyclist;

    public GetAllTeamUseCase(TeamRepository teamRepository,MapperTeam mapperTeam,CyclistRepository cyclistRepository,MapperCyclist mapperCyclist){
        this.teamRepository=teamRepository;
        this.mapperTeam=mapperTeam;
        this.cyclistRepository=cyclistRepository;
        this.mapperCyclist=mapperCyclist;
    }

    @Override
    public Flux<TeamDTO> get() {
        return teamRepository.findAll()
                .map(mapperTeam.mapperATeamDTO())
                .flatMap(mapperTeam.mapperATeamWithCyclist());
    }
}
