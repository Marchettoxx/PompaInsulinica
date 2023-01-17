package demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MisurazioneRepository extends CrudRepository<Misurazione, Long> {

        List<Misurazione> findByIdUtente(Long idUtente);

        Misurazione findById(long id);
    }
