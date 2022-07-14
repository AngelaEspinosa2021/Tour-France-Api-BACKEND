package co.com.sofka.touroffranceapp.repositories;

import co.com.sofka.touroffranceapp.collections.Cyclist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Representa a una interfaz que hereda de la interfaz ReactiveMongoRepository que tiene SpringBoot, todos sus metodos.
 */
@Repository
public interface CyclistRepository extends ReactiveMongoRepository<Cyclist, String> {
}
