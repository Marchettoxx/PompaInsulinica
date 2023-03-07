package demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeasuramentRepository extends CrudRepository<Measurement, Long> {

    /**
     * @param idPerson id utente
     * @return Lista di Measurement con id utente uguale a quello passato
     */
    List<Measurement> findByIdPerson(Long idPerson);
}
