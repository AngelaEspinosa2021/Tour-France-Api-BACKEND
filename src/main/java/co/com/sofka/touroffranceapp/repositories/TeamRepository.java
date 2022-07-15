package co.com.sofka.touroffranceapp.repositories;

import co.com.sofka.touroffranceapp.collections.Team;
import co.com.sofka.touroffranceapp.model.TeamDTO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Representa a una interfaz que hereda de la interfaz ReactiveMongoRepository que tiene SpringBoot, todos sus metodos.
 */
@Repository
public interface TeamRepository extends ReactiveMongoRepository<Team, String> {
    Mono<TeamDTO> findByTeamName(String teamName);

    Flux<Team> findAllByAssociatedCountry(String country);


}
