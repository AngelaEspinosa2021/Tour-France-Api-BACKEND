package co.com.sofka.touroffranceapp.usecases.cyclist;

import co.com.sofka.touroffranceapp.mapper.MapperCyclist;
import co.com.sofka.touroffranceapp.model.CyclistDTO;
import co.com.sofka.touroffranceapp.model.TeamDTO;
import co.com.sofka.touroffranceapp.repositories.CyclistRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class AddCyclistUseCase implements SaveCyclistInterface{

    // private final CyclistRepository cyclistRepository;

    //private final MapperCyclist mapperCyclist;
    



    @Override
    public Mono<TeamDTO> saveCyclist(CyclistDTO cyclistDTO) {
        return null;
    }
}
