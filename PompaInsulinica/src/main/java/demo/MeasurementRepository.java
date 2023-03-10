package demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeasurementRepository extends CrudRepository<Measurement, Long> {

    /**
     * @param idPerson id persona
     * @return Lista di Measurement con id persona uguale a quello passato
     */
    List<Measurement> findByIdPerson(Long idPerson);
}
