package demo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitTest {

    private Person person;
    private Measurement measurement;

    @Before
    public void setUp() {
        person = new Person("Luca", "Piccolo", "piccolo@gmail.com", "piccololu", "luca123.");
        measurement = new Measurement(1L, 200, 3, "pizza e coca");
    }

    @Test
    public void AATestPersonName() {
        person.setName("Pietro");
        assertEquals("Pietro", person.getName());
    }

    @Test
    public void ABTestPersonSurname() {
        person.setSurname("DiLuca");
        assertEquals("DiLuca", person.getSurname());
    }

    @Test
    public void ACTestPersonEmail() {
        person.setEmail("pietrodiluca@gmail.com");
        assertEquals("pietrodiluca@gmail.com", person.getEmail());
    }

    @Test
    public void ADTestPersonUsername() {
        person.setUsername("pietro");
        assertEquals("pietro", person.getUsername());
    }

    @Test
    public void AETestPersonPassword() {
        person.setPassword("pietro123");
        assertEquals("pietro123", person.getPassword());
    }

    @Test
    public void AFTTestPersonBlankConstructor() {
        Person person1 = new Person();
        assertNull(person1.getId());
    }

    @Test
    public void AGTestPersonId() {
        Long id = person.getId();
        assertNotEquals(Long.valueOf(0), id);
    }

    /**
     * Test AH:
     * Verifico che il metodo toString della classe Person mi ritorni la stringa da me desiderata.
     */
    @Test
    public void AHTestPersonToString() {
        assertEquals("Person[id=null, name= 'Luca', surname='Piccolo', email='piccolo@gmail.com', username='piccololu', password='luca123.']", person.toString());
    }

    /**
     * Test BA:
     * Verifico se il cambio d'ID di un utente funziona.
     */
    @Test
    public void BATestMeasurementChangeIdUPerson() {
        measurement.setIdPerson(2L);
        assertEquals(Long.valueOf(2), measurement.getIdPerson());
    }

    @Test
    public void BBTestMeasurementBlankConstructor() {
        Measurement measurement1 = new Measurement();
        assertNull(measurement1.getId());
    }

    @Test
    public void BCTestMeasurementId() {
        Long id = measurement.getId();
        assertNotEquals(Long.valueOf(0), id);
    }

    @Test
    public void BDTestMeasurementGlycemia() {
        assertEquals(Integer.valueOf(200), measurement.getGlycemia());
    }

    @Test
    public void BETestMeasurementInsulin() {
        assertEquals(Double.parseDouble("3"), measurement.getInsulin(), Double.parseDouble("3"));
    }

    @Test
    public void BFTestMeasurementComment() {
        assertEquals("pizza e coca", measurement.getComment());
    }

    /**
     * Test BG:
     * Vorrei anche verificare lo stesso metodo della classe PompaInsulinica
     * ma essendo che abbiamo come variabile il tempo reale in cui viene effettuata un'iniezione non riesco a fare ciò.
     */
    @Test
    public void BGTestMeasurementToString() {
        assertEquals("Measurement[id=null, idPerson='1', glycemia= '200', insulin='3', comment='pizza e coca', time='00:00']", measurement.toString());
    }

    @Test
    public void BHTestMeasurementTime() {
        String time = measurement.getTime();
        assertFalse(time.isEmpty());
    }
}
