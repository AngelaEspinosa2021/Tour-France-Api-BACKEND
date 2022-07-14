package co.com.sofka.touroffranceapp.routers;

import co.com.sofka.touroffranceapp.model.TeamDTO;
import co.com.sofka.touroffranceapp.usecases.team.CreateTeamUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.awt.image.PackedColorModel;
import java.util.function.Function;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TeamRouter {

    @Bean
    public RouterFunction<ServerResponse> create(CreateTeamUseCase createTeamUseCase){
        Function<TeamDTO, Mono<ServerResponse>> executor = teamDTO -> createTeamUseCase.saveTeam(teamDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
               POST("/team/create").and(accept(APPLICATION_JSON)),
                request -> request.bodyToMono(TeamDTO.class).flatMap(executor)
        );
    }
}
