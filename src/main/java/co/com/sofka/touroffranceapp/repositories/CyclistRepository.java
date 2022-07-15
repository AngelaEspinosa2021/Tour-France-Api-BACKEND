package co.com.sofka.touroffranceapp.repositories;

import co.com.sofka.touroffranceapp.collections.Cyclist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Representa a una interfaz que hereda de la interfaz ReactiveMongoRepository que tiene SpringBoot, todos sus metodos.
 */
@Repository
public interface CyclistRepository extends ReactiveMongoRepository<Cyclist, String> {

    Flux<Cyclist> findAllByTeamId(String Id);

    Flux<Cyclist> findAllByNationality(String Id);

    Mono<Void> deleteByTeamId(String teamId);
}
