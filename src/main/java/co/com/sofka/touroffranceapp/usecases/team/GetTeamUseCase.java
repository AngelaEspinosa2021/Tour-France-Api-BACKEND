package co.com.sofka.touroffranceapp.usecases.team;

import co.com.sofka.touroffranceapp.mapper.MapperCyclist;
import co.com.sofka.touroffranceapp.mapper.MapperTeam;
import co.com.sofka.touroffranceapp.model.CyclistDTO;
import co.com.sofka.touroffranceapp.model.TeamDTO;
import co.com.sofka.touroffranceapp.repositories.CyclistRepository;
import co.com.sofka.touroffranceapp.repositories.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetTeamUseCase implements Function<String, Mono<TeamDTO>> {

    private final TeamRepository teamRepository;

    private final CyclistRepository cyclistRepository;

    private final MapperTeam mapperTeam;

    private final MapperCyclist mapperCyclist;

    public GetTeamUseCase(TeamRepository teamRepository,CyclistRepository cyclistRepository,MapperTeam mapperTeam,MapperCyclist mapperCyclist){
        this.teamRepository=teamRepository;
        this.cyclistRepository=cyclistRepository;
        this.mapperTeam=mapperTeam;
        this.mapperCyclist=mapperCyclist;
    }

    @Override
    public Mono<TeamDTO> apply(String teamId){
        Objects.requireNonNull(teamId, "TeamId es obligatorio.");
        return teamRepository.findById(teamId)
                .map(mapperTeam.mapperATeamDTO())
                .flatMap(mapCyclistAgregate());
    }

    private Function<TeamDTO, Mono<TeamDTO>> mapCyclistAgregate(){
        return teamDTO ->
                Mono.just(teamDTO).zipWith(
                        cyclistRepository.findAllByTeamId(teamDTO.getTeamId())
                                .map(mapperCyclist.mapperACyclistDTO())
                                .collectList(),
                        (team, cyclists) -> {
                            team.setCyclists(cyclists);
                            return team;
                        }
                );
    }
}
