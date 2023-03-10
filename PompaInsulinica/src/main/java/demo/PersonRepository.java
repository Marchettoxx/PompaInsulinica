package demo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

    /**
     * @param username username persona
     * @return Person con username uguale a quello passato
     */
    Optional<Person> findByUsername(String username);
}
