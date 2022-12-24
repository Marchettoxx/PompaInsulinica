package demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PompaInsulinicaRepository extends CrudRepository<Person, Long> {

    Optional<Person> findByIdUtente(Long idUtente);

    }
