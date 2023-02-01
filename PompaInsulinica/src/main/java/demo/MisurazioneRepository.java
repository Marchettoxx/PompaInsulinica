package demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MisurazioneRepository extends CrudRepository<Misurazione, Long> {

    /**
     * @param idUtente id utente
     * @return Misurazione con id utente uguale a quello passato
     */
    List<Misurazione> findByIdUtente(Long idUtente);
}
