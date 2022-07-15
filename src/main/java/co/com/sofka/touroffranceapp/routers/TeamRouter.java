package co.com.sofka.touroffranceapp.routers;

import co.com.sofka.touroffranceapp.model.TeamDTO;
import co.com.sofka.touroffranceapp.usecases.team.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TeamRouter {

    @Bean
    public RouterFunction<ServerResponse> create(CreateTeamUseCase createTeamUseCase){
        Function<TeamDTO, Mono<ServerResponse>> createTeam = teamDTO -> createTeamUseCase.saveTeam(teamDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
               POST("/team/create").and(accept(APPLICATION_JSON)),
                request -> request.bodyToMono(TeamDTO.class)
                        .flatMap(createTeam));
    }

    @Bean
    public RouterFunction<ServerResponse> get(GetTeamUseCase getTeamUseCase){
        return route(
                GET("/team/get/{id}").and(accept(APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getTeamUseCase.apply(
                                request.pathVariable("id")),
                                TeamDTO.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> deleteTeam(DeleteTeamUseCase deleteTeamUseCase){
        return route(
                DELETE("/team/delete/{id}").and(accept(APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteTeamUseCase.deleteTeam(request.pathVariable("id")),
                                Void.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> updateTeam(UpdateTeamUseCase updateTeamUseCase){
        Function<TeamDTO, Mono<ServerResponse>> updateTeam = TeamDTO -> updateTeamUseCase.updateTeam(TeamDTO)
                .flatMap(result -> ServerResponse.ok()
                                .contentType(APPLICATION_JSON)
                                .bodyValue(result));
        return route(
                PUT("/team/update").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TeamDTO.class)
                        .flatMap(updateTeam)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getAllTeam(GetAllTeamUseCase getAllTeamUseCase){
        return route(
                GET("/team/getAll").and(accept(APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllTeamUseCase.get(), TeamDTO.class))
        );
    }
}
