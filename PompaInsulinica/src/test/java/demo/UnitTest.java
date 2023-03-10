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

    // TEST PERSON

    /**
     * Test AA:
     * Utilizzo il metodo setName per inserire un nome,
     * verifico con il metodo getName ciò che ho appena inserito
     */
    @Test
    public void AATestPersonName() {
        person.setName("Pietro");
        assertEquals("Pietro", person.getName());
    }

    /**
     * Test AB:
     * Utilizzo il metodo setSurname per inserire un cognome,
     * verifico con il metodo getSurname ciò che ho appena inserito.
     */
    @Test
    public void ABTestPersonSurname() {
        person.setSurname("DiLuca");
        assertEquals("DiLuca", person.getSurname());
    }

    /**
     * Test AC:
     * Utilizzo il metodo setEmail per inserire una mail,
     * verifico con il metodo getEmail ciò che ho appena inserito.
     */
    @Test
    public void ACTestPersonEmail() {
        person.setEmail("pietrodiluca@gmail.com");
        assertEquals("pietrodiluca@gmail.com", person.getEmail());
    }

    /**
     * Test AD:
     * Utilizzo il metodo setUsername per inserire un username,
     * verifico con il metodo getUsername ciò che ho appena inserito.
     */
    @Test
    public void ADTestPersonUsername() {
        person.setUsername("pietro");
        assertEquals("pietro", person.getUsername());
    }

    /**
     * Test AE:
     * Utilizzo il metodo setPassword per inserire una password,
     * verifico con il metodo getPassword ciò che ho appena inserito.
     */
    @Test
    public void AETestPersonPassword() {
        person.setPassword("pietro123");
        assertEquals("pietro123", person.getPassword());
    }

    /**
     * Test AF:
     * Utilizzo il costruttore Person() per creare un oggetto senza parametri,
     * verifico che l'id dell'utente sia vuoto.
     */
    @Test
    public void AFTestPersonBlankConstructor() {
        Person person1 = new Person();
        assertNull(person1.getId());
    }

    /**
     * Test AG:
     * Utilizzo il metodo getId() per prendere l'id della persona,
     * controllo che sia nullo.
     */
    @Test
    public void AGTestPersonId() {
        Long id = person.getId();
        assertNull(id);
    }

    /**
     * Test AH:
     * Verifico che il metodo toString della classe Person
     * mi ritorni la stringa da me desiderata.
     */
    @Test
    public void AHTestPersonToString() {
        assertEquals("Person[id=null, name= 'Luca', surname='Piccolo', email='piccolo@gmail.com', username='piccololu', password='luca123.']", person.toString());
    }

    // TEST MEASUREMENT

    /**
     * Test BA:
     * Verifico se il cambio d'id di un utente funziona.
     */
    @Test
    public void BATestMeasurementChangeIdUPerson() {
        measurement.setIdPerson(2L);
        assertEquals(Long.valueOf(2), measurement.getIdPerson());
    }

    /**
     * Test BB:
     * Utilizzo il costruttore Measurement() per creare un oggetto senza
     * parametri, verifico che l'id della misurazione sia nullo.
     */
    @Test
    public void BBTestMeasurementBlankConstructor() {
        Measurement measurement1 = new Measurement();
        assertNull(measurement1.getId());
    }

    /**
     * Test BC:
     * Utilizzo il metodo getId()
     * per prendere l'id della misurazione.
     * Verifico che l'id della misurazione sia nullo.
     */
    @Test
    public void BCTestMeasurementId() {
        Long id = measurement.getId();
        assertNull(id);
    }

    /**
     * Test BD:
     * Verifico con il metodo getGlycemia il valore di glicemia da me inserito.
     */
    @Test
    public void BDTestMeasurementGlycemia() {
        assertEquals(Integer.valueOf(200), measurement.getGlycemia());
    }

    /**
     * Test BE:
     * Verifico con il metodo getInsulin il valore d'insulina da me inserito.
     */
    @Test
    public void BETestMeasurementInsulin() {
        assertEquals(Double.parseDouble("3"), measurement.getInsulin(), Double.parseDouble("3"));
    }

    /**
     * Test BF:
     * Verifico con il metodo getComment il commento da me inserito.
     */
    @Test
    public void BFTestMeasurementComment() {
        assertEquals("pizza e coca", measurement.getComment());
    }

    /**
     * Test BG:
     * Verifico che il metodo toString della classe Measurement
     * mi ritorni la stringa da me desiderata.
     */
    @Test
    public void BGTestMeasurementToString() {
        assertEquals("Measurement[id=null, idPerson='1', glycemia= '200', insulin='3', comment='pizza e coca', time='00:00']", measurement.toString());
    }

    /**
     * Test BH:
     * Verifico con il metodo getTime di non avere una stringa vuota.
     */
    @Test
    public void BHTestMeasurementTime() {
        String time = measurement.getTime();
        assertFalse(time.isEmpty());
    }
}
