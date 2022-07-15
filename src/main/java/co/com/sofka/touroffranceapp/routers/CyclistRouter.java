package co.com.sofka.touroffranceapp.routers;

import co.com.sofka.touroffranceapp.collections.Cyclist;
import co.com.sofka.touroffranceapp.model.CyclistDTO;
import co.com.sofka.touroffranceapp.usecases.cyclist.AddCyclistUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CyclistRouter {

    @Bean
    public RouterFunction<ServerResponse> addCyclist(AddCyclistUseCase addCyclistUseCase){
        Function<CyclistDTO, Mono<ServerResponse>> addCyclist = CyclistDTO -> addCyclistUseCase.saveCyclist(CyclistDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result)
                );

        return route(POST("/cyclist/add").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CyclistDTO.class)
                        .flatMap(addCyclist));

    }
}
