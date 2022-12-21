package demo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

        Optional<Person> findByUsername(String username);

        Person findById(long id);
    }
