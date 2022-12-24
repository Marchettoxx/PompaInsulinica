package demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PompaInsulinicaRepository extends CrudRepository<PompaInsulinica, Long> {

    Optional<Person> findByIdUtente(Long idUtente);

    PompaInsulinica findById(long id);
}
