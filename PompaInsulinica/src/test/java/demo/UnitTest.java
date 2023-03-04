package demo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnitTest {

    private Person person;
    private Misurazione misurazione;

    @Before
    public void setUp() {
        person = new Person("Luca", "Piccolo", "piccolo@gmail.com", "piccololu", "luca123.");
        misurazione = new Misurazione(1L, 200, 3, "pizza e coca");
    }

    @Test
    public void AATestPersonName() {
        person.setNome("Pietro");
        assertEquals("Pietro", person.getNome());
    }

    @Test
    public void ABTestPersonSurname() {
        person.setCognome("DiLuca");
        assertEquals("DiLuca", person.getCognome());
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
        assertTrue(true);
    }

    @Test
    public void AGTestPersonId() {
        Long id = person.getId();
        assertTrue(true);
    }

    //TEST TOSTRING

    /**
     * Test AH:
     * Verifico che il metodo toString della classe Person mi ritorni la stringa da me desiderata.
     */
    @Test
    public void AHTestPersonToString() {
        assertEquals("Customer[id=null, nome= 'Luca', cognome='Piccolo', email='piccolo@gmail.com', username='piccololu', password='luca123.']", person.toString());
    }

    //TEST CHANGE ID

    /**
     * Test BA:
     * Verifico se il cambio d'ID di un utente funziona.
     */
    @Test
    public void BATestMisurazioneChangeId() {
        misurazione.setIdUtente(2L);
        assertEquals(Long.valueOf(2), misurazione.getIdUtente());
    }

    @Test
    public void BBTestMisurazioneBlankConstructor() {
        Misurazione misurazione1 = new Misurazione();
        assertTrue(true);
    }

    @Test
    public void BCTestMisurazioneId() {
        Long id = misurazione.getId();
        assertTrue(true);
    }

    @Test
    public void BDTestMisurazioneGlicemia() {
        assertEquals(Integer.valueOf(200), misurazione.getGlicemia());
    }

    @Test
    public void BETestMisurazioneInsulina() {
        assertEquals(Double.parseDouble("3"), misurazione.getInsulina(), Double.parseDouble("3"));
    }

    @Test
    public void BFTestMisurazioneComment() {
        assertEquals("pizza e coca", misurazione.getCommento());
    }

    /**
     * Test BG:
     * Vorrei anche verificare lo stesso metodo della classe PompaInsulinica
     * ma essendo che abbiamo come variabile il tempo reale in cui viene effettuata un'iniezione non riesco a fare ciò.
     */
    @Test
    public void BGTestToStringMisurazione() {
        assertEquals("Misurazione[id=null, idUtente='1', glicemia= '200', insulina='3', commento='pizza e coca', time='00:00']", misurazione.toString());
    }

    @Test
    public void BHTestMisurazioneTime() {
        String time = misurazione.getTime();
        assertTrue(true);
    }
}
