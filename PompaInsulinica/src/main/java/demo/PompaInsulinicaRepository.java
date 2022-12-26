package demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PompaInsulinicaRepository extends CrudRepository<PompaInsulinica, Long> {

        List<PompaInsulinica> findByIdUtente(Long idUtente);

        PompaInsulinica findById(long id);
    }
