package co.com.sofka.touroffranceapp.usecases.cyclist;

import co.com.sofka.touroffranceapp.mapper.MapperCyclist;
import co.com.sofka.touroffranceapp.model.CyclistDTO;
import co.com.sofka.touroffranceapp.repositories.CyclistRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UpdateCyclistUseCase implements UpdateCyclistInterface{

    private final CyclistRepository cyclistRepository;

    private final MapperCyclist mapperCyclist;

    public UpdateCyclistUseCase(CyclistRepository cyclistRepository, MapperCyclist mapperCyclist){
        this.cyclistRepository=cyclistRepository;
        this.mapperCyclist=mapperCyclist;
    }

    @Override
    public Mono<CyclistDTO> updateCyclist(CyclistDTO cyclistDTO) {
        return cyclistRepository.findById(cyclistDTO.getCyclistId()).flatMap(cyclist ->
                cyclistRepository.save(mapperCyclist.mapperACyclist(cyclistDTO.getCyclistId()).apply(cyclistDTO))
                        .map(mapperCyclist.mapperACyclistDTO()));
    }
}
