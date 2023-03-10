package demo;

import PO.*;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import java.nio.file.Paths;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SystemTest {

    private WebDriver driver;
    @Before
    public void setUp() {
        org.openqa.selenium.chrome.ChromeOptions chrome_options = new ChromeOptions();
        chrome_options.addArguments("--headless");
        if(SystemUtils.IS_OS_WINDOWS){
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_win32_96/chromedriver.exe").toString());
        }
        else if (SystemUtils.IS_OS_MAC){
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_mac64_96/chromedriver").toString());
        }
        else if (SystemUtils.IS_OS_LINUX){
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_linux64_96/chromedriver").toString());
        }
        if (driver == null)
            driver = new ChromeDriver(chrome_options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // TEST CREAZIONE UTENTE

    /**
     * Test AA:
     * Viene creata una nuova persona in cui viene lasciato in bianco il campo relativo al nome.
     * Verifico che l'account non sia stato creato e venga chiesto d'inserire nuovamente le credenziali.
     */
    @Test
    public void AATestCreateNewAccount() {
        driver.get("http://localhost:8080");
        LogInPO logInPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", logInPO.getTitle());
        CreatePersonPO createPersonPO = logInPO.clickNewAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", createPersonPO.getTitle());
        createPersonPO.insertCredential("", "Massagrande", "marco@gmail.com", "123marco", "123marco.");
        LogInPO logInPO1 = createPersonPO.saveAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", logInPO1.getTitle());
    }

    /**
     * Test AB:
     * Viene creata una nuova persona in cui viene lasciato in bianco il campo relativo al cognome.
     * Verifico che l'account non sia stato creato e venga chiesto d'inserire nuovamente le credenziali.
     */
    @Test
    public void ABTestCreateNewAccount() {
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        CreatePersonPO createPersonPO = loginPO.clickNewAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", createPersonPO.getTitle());
        createPersonPO.insertCredential("Marco", "", "marco@gmail.com", "123marco", "123marco.");
        LogInPO logInPO1 = createPersonPO.saveAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", logInPO1.getTitle());
    }

    /**
     * Test AC:
     * Viene creata una nuova persona in cui viene inserita una mail non valida.
     * Verifico che l'account non sia stato creato e venga chiesto d'inserire nuovamente le credenziali.
     */
    @Test
    public void ACTestCreateNewAccount() {
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        CreatePersonPO createPersonPO = loginPO.clickNewAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", createPersonPO.getTitle());
        createPersonPO.insertCredential("Marco", "Massagrande", "la@hotmal.com", "123marco", "123marco.");
        LogInPO logInPO1 = createPersonPO.saveAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", logInPO1.getTitle());
    }

    /**
     * Test AD:
     * Viene creata una nuova persona in cui viene inserito un username non valido.
     * Verifico che l'account non sia stato creato e venga chiesto d'inserire nuovamente le credenziali.
     */
    @Test
    public void ADTestCreateNewAccount() {
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        CreatePersonPO createPersonPO = loginPO.clickNewAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", createPersonPO.getTitle());
        createPersonPO.insertCredential("Marco", "Massagrande", "marco@gmail.com", "12", "123marco.");
        LogInPO logInPO1 = createPersonPO.saveAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", logInPO1.getTitle());
    }

    /**
     * Test AE:
     * Viene creata una nuova persona in cui viene inserita una password che non rispetta i requisiti richiesti.
     * Verifico che l'account non sia stato creato e venga chiesto d'inserire nuovamente le credenziali.
     */
    @Test
    public void AETestCreateNewAccount() {
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        CreatePersonPO createPersonPO = loginPO.clickNewAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", createPersonPO.getTitle());
        createPersonPO.insertCredential("Marco", "Massagrande", "marco@gmail.com", "123marco", "123mar");
        LogInPO logInPO1 = createPersonPO.saveAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", logInPO1.getTitle());
    }

    /**
     * Test AF:
     * Viene creato un utente con le credenziali corrette (i valori rispettano i requisiti stabiliti).
     * Verifico che l'account sia stato effettivamente creato.
     */
    @Test
    public void AFTestCreateNewAccount() {
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        CreatePersonPO createPersonPO = loginPO.clickNewAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", createPersonPO.getTitle());
        createPersonPO.insertCredential("Marco", "Massagrande", "marco@gmail.com", "123marco", "123marco.");
        LogInPO logInPO1 = createPersonPO.saveAccount();

        assertEquals("LOGIN POMPA INSULINICA", logInPO1.getTitle());
    }

    /**
     * Test AG:
     * Viene creata una nuova persona e viene scelto un username già esistente.
     * Verifico che l'account non sia stato creato e venga chiesto d'inserire nuovamente le credenziali.
     */
    @Test
    public void AGTestCreateNewAccount() {
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        CreatePersonPO createPersonPO = loginPO.clickNewAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", createPersonPO.getTitle());
        createPersonPO.insertCredential("Marco", "Piccoli", "marco@gmail.com", "123marco", "123marco.");
        LogInPO logInPO1 = createPersonPO.saveAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", logInPO1.getTitle());
    }

    /**
     * Test AH:
     * Viene creata una nuova persona diverso da quello creato nel test AF.
     * Verifico che l'account venga effettivamente creato.
     */
    @Test
    public void AHTestCreateNewAccount() {
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        CreatePersonPO createPersonPO = loginPO.clickNewAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", createPersonPO.getTitle());
        createPersonPO.insertCredential("Luca", "Peppino", "luca@gmail.com", "123luca", "123luca.");
        LogInPO logInPO1 = createPersonPO.saveAccount();

        assertEquals("LOGIN POMPA INSULINICA", logInPO1.getTitle());
    }

    //TEST LOGIN

    /**
     * Test BA:
     * Eseguo il login inserendo un username errato.
     * Verifico di non aver fatto il login.
     */
    @Test
    public void BATestLogIn() {
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("12", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("LOGIN POMPA INSULINICA", homePO.getTitle());
    }

    /**
     * Test BB:
     * Eseguo il login inserendo una password errata.
     * Verifico di non aver fatto il login.
     */
    @Test
    public void BBTestLogIn() {
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123ma");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("LOGIN POMPA INSULINICA", homePO.getTitle());
    }

    /**
     * Test BC:
     * Eseguo il login inserendo sia l'username che la password errati.
     * Verifico di non aver fatto il login.
     */
    @Test
    public void BCTestLogIn() {
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("12", "");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("LOGIN POMPA INSULINICA", homePO.getTitle());
    }

    /**
     * Test BD:
     * Eseguo il login con le credenziali corrette.
     * Verifico che il login sia avvenuto con successo e di essere nella pagina home.
     */
    @Test
    public void BDTestLogIn() {
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
    }

    //TEST PAGINA HOME

    /**
     * Test DA:
     * Premo il tasto Profilo e verifico di essere entrato effettivamente nella pagina desiderata.
     */
    @Test
    public void DATestAccount(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfilePO profilePO = homePO.clickProfile();

        assertEquals("PROFILO", profilePO.getTitle());
    }

    /**
     * Test DB:
     * Premo il tasto Pompa Insulinica e verifico di essere entrato effettivamente nella pagina desiderata.
     */
    @Test
    public void DBTestAccount(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsertMeasurementPO insertMeasurementPO = homePO.clickInsulinPump();

        assertEquals("POMPA INSULINICA", insertMeasurementPO.getTitle());
    }

    /**
     * Test DC:
     * Premo il tasto Cronologia e verifico di rimanere nella pagina Home,
     * in quanto non avendo ancora fatto nessuna iniezione la cronologia sarà vuota.
     */
    @Test
    public void DCTestAccount(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ChronologyPO chronologyPO = homePO.clickChronology();

        // essendo che non sono ancora state fatte misurazioni
        // non è possibile visualizzare la cronologia
        assertEquals("BENVENUTO NELLA HOME", chronologyPO.getTitle());
    }

    //TEST PROFILO

    /**
     * Test EA:
     * Modifico il nome in modo errato e verifico che la modifica non sia avvenuta.
     */
    @Test
    public void EATestProfile(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfilePO profilePO = homePO.clickProfile();

        assertEquals("PROFILO", profilePO.getTitle());
        EditPersonPO editPersonPO = profilePO.clickModifyUser();

        assertEquals("MODIFICA CREDENZIALI UTENTE", editPersonPO.getTitle());
        editPersonPO.insertName("");
        ProfilePO profilePO1 = editPersonPO.clickSaveEdit();

        assertEquals("MODIFICA CREDENZIALI UTENTE", profilePO1.getTitle());
    }

    /**
     * Test EB:
     * Modifico il cognome in modo errato e verifico che la modifica non sia avvenuta.
     */
    @Test
    public void EBTestProfile(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfilePO profilePO = homePO.clickProfile();

        assertEquals("PROFILO", profilePO.getTitle());
        EditPersonPO editPersonPO = profilePO.clickModifyUser();

        assertEquals("MODIFICA CREDENZIALI UTENTE", editPersonPO.getTitle());
        editPersonPO.insertSurname("");
        ProfilePO profilePO1 = editPersonPO.clickSaveEdit();

        assertEquals("MODIFICA CREDENZIALI UTENTE", profilePO1.getTitle());
    }

    /**
     * Test EC:
     * Modifico il l'email in modo errato e verifico che la modifica non sia avvenuta.
     */
    @Test
    public void ECTestProfile(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfilePO profilePO = homePO.clickProfile();

        assertEquals("PROFILO", profilePO.getTitle());
        EditPersonPO editPersonPO = profilePO.clickModifyUser();

        assertEquals("MODIFICA CREDENZIALI UTENTE", editPersonPO.getTitle());
        editPersonPO.insertEmail("la@hotmal.com");
        ProfilePO profilePO1 = editPersonPO.clickSaveEdit();

        assertEquals("MODIFICA CREDENZIALI UTENTE", profilePO1.getTitle());
    }

    /**
     * Test ED:
     * Modifico l'username in modo errato e verifico che la modifica non sia avvenuta.
     */
    @Test
    public void EDTestProfile(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfilePO profilePO = homePO.clickProfile();

        assertEquals("PROFILO", profilePO.getTitle());
        EditPersonPO editPersonPO = profilePO.clickModifyUser();

        assertEquals("MODIFICA CREDENZIALI UTENTE", editPersonPO.getTitle());
        editPersonPO.insertUsername("user");
        ProfilePO profilePO1 = editPersonPO.clickSaveEdit();

        assertEquals("MODIFICA CREDENZIALI UTENTE", profilePO1.getTitle());
    }

    /**
     * Test EE:
     * Modifico l'username utilizzandone uno già esistente.
     * Verifico quindi che la modifica non sia avvenuta.
     */
    @Test
    public void EETestProfile(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123luca", "123luca.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfilePO profilePO = homePO.clickProfile();

        assertEquals("PROFILO", profilePO.getTitle());
        EditPersonPO editPersonPO = profilePO.clickModifyUser();

        assertEquals("MODIFICA CREDENZIALI UTENTE", editPersonPO.getTitle());
        editPersonPO.insertUsername("123marco");
        ProfilePO profilePO1 = editPersonPO.clickSaveEdit();

        assertEquals("MODIFICA CREDENZIALI UTENTE", profilePO1.getTitle());
    }

    /**
     * Test EF:
     * Modifico la password in modo errato e verifico che la modifica non sia avvenuta.
     */
    @Test
    public void EFTestProfile(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfilePO profilePO = homePO.clickProfile();

        assertEquals("PROFILO", profilePO.getTitle());
        EditPersonPO editPersonPO = profilePO.clickModifyUser();

        assertEquals("MODIFICA CREDENZIALI UTENTE", editPersonPO.getTitle());
        editPersonPO.insertPassword("passw1");
        ProfilePO profilePO1 = editPersonPO.clickSaveEdit();

        assertEquals("MODIFICA CREDENZIALI UTENTE", profilePO1.getTitle());
    }

    /**
     * Test EG:
     * Modifico la email in modo corretto.
     * Verifico che la modifica sia avvenuta con successo.
     */
    @Test
    public void EGTestProfile(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfilePO profilePO = homePO.clickProfile();

        assertEquals("PROFILO", profilePO.getTitle());
        EditPersonPO editPersonPO = profilePO.clickModifyUser();

        assertEquals("MODIFICA CREDENZIALI UTENTE", editPersonPO.getTitle());
        editPersonPO.insertEmail("marcomassa@gmail.com");
        ProfilePO profilePO1 = editPersonPO.clickSaveEdit();

        assertEquals("PROFILO", profilePO1.getTitle());
    }

    /**
     * Test EH:
     * Modifico l'username in modo corretto.
     * Verifico che la modifica sia avvenuta con successo.
     */
    @Test
    public void EHTestProfile(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123luca", "123luca.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfilePO profilePO = homePO.clickProfile();

        assertEquals("PROFILO", profilePO.getTitle());
        EditPersonPO editPersonPO = profilePO.clickModifyUser();

        assertEquals("MODIFICA CREDENZIALI UTENTE", editPersonPO.getTitle());
        editPersonPO.insertUsername("unbelnome");
        ProfilePO profilePO1 = editPersonPO.clickSaveEdit();

        assertEquals("PROFILO", profilePO1.getTitle());
    }

    /**
     * Test EI:
     * Premo il tasto indietro presente nella pagina Modifica Credenziali Utente e verifico di essere tornato nella pagina Profilo.
     */
    @Test
    public void EITestBack(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfilePO profilePO = homePO.clickProfile();

        assertEquals("PROFILO", profilePO.getTitle());
        EditPersonPO editPersonPO = profilePO.clickModifyUser();

        assertEquals("MODIFICA CREDENZIALI UTENTE", editPersonPO.getTitle());
        ProfilePO profilePO1 = editPersonPO.clickBack();

        assertEquals("PROFILO", profilePO1.getTitle());
    }

    //TEST POMPA INSULINICA

    /**
     * Test FA:
     * Inserisco una nuova misurazione.
     * Nel campo glycemia si inserisce un valore sbagliato (non compreso tra 100 e 300).
     * Verifico che la registrazione non sia andata a buon fine.
     */
    @Test
    public void FATestRegistration(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsertMeasurementPO insertMeasurementPO = homePO.clickInsulinPump();

        assertEquals("POMPA INSULINICA", insertMeasurementPO.getTitle());
        insertMeasurementPO.insertMeasurement("500", "3", "ho pranzato con una pizza");
        InsertMeasurementPO insertMeasurementPO1 = insertMeasurementPO.saveMeasurement();

        assertEquals("INSERISCI VALORI VALIDI", insertMeasurementPO1.getError());
        assertEquals("", insertMeasurementPO1.getLastMeasurement());
    }

    /**
     * Test FB:
     * Inserisco una nuova misurazione.
     * Nel campo glycemia si inserisce un valore non numerico.
     * Verifico che la registrazione non sia andata a buon fine.
     */
    @Test
    public void FBTestRegistration(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsertMeasurementPO insertMeasurementPO = homePO.clickInsulinPump();

        assertEquals("POMPA INSULINICA", insertMeasurementPO.getTitle());
        insertMeasurementPO.insertMeasurement("glicemia", "3", "ho pranzato con una pizza");
        InsertMeasurementPO insertMeasurementPO1 = insertMeasurementPO.saveMeasurement();

        assertEquals("INSERISCI VALORI VALIDI", insertMeasurementPO1.getError());
        assertEquals("", insertMeasurementPO1.getLastMeasurement());
    }

    /**
     * Test FC:
     * Inserisco una nuova misurazione.
     * Nel campo glycemia si inserisce un valore vuoto.
     * Verifico che la registrazione non sia andata a buon fine.
     */
    @Test
    public void FCTestRegistration(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsertMeasurementPO insertMeasurementPO = homePO.clickInsulinPump();

        assertEquals("POMPA INSULINICA", insertMeasurementPO.getTitle());
        insertMeasurementPO.insertMeasurement("", "3", "ho pranzato con una pizza");
        InsertMeasurementPO insertMeasurementPO1 = insertMeasurementPO.saveMeasurement();

        assertEquals("INSERISCI VALORI VALIDI", insertMeasurementPO1.getError());
        assertEquals("", insertMeasurementPO1.getLastMeasurement());
    }

    /**
     * Test FD:
     * Inserisco una nuova misurazione.
     * Nel campo insulin si inserisce un valore che non rispetta i requisiti.
     * Verifico che la registrazione non sia andata a buon fine.
     */
    @Test
    public void FDTestRegistration(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsertMeasurementPO insertMeasurementPO = homePO.clickInsulinPump();

        assertEquals("POMPA INSULINICA", insertMeasurementPO.getTitle());
        insertMeasurementPO.insertMeasurement("200", "15", "ho pranzato con una pizza");
        InsertMeasurementPO insertMeasurementPO1 = insertMeasurementPO.saveMeasurement();

        assertEquals("INSERISCI VALORI VALIDI", insertMeasurementPO1.getError());
        assertEquals("", insertMeasurementPO1.getLastMeasurement());
    }

    /**
     * Test FE:
     * Inserisco una nuova misurazione.
     * Nel campo insulin si inserisce un valore in lettere.
     * Verifico che la registrazione non sia andata a buon fine.
     */
    @Test
    public void FETestRegistration(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsertMeasurementPO insertMeasurementPO = homePO.clickInsulinPump();

        assertEquals("POMPA INSULINICA", insertMeasurementPO.getTitle());
        insertMeasurementPO.insertMeasurement("200", "insulina", "ho pranzato con una pizza");
        InsertMeasurementPO insertMeasurementPO1 = insertMeasurementPO.saveMeasurement();

        assertEquals("INSERISCI VALORI VALIDI", insertMeasurementPO1.getError());
        assertEquals("", insertMeasurementPO1.getLastMeasurement());
    }

    /**
     * Test FF:
     * Inserisco una nuova misurazione.
     * Lascio il valore relativo all'insulina vuoto.
     * Verifico che la registrazione non sia andata a buon fine.
     */
    @Test
    public void FFTestRegistration(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsertMeasurementPO insertMeasurementPO = homePO.clickInsulinPump();

        assertEquals("POMPA INSULINICA", insertMeasurementPO.getTitle());
        insertMeasurementPO.insertMeasurement("200", "", "ho pranzato con una pizza");
        InsertMeasurementPO insertMeasurementPO1 = insertMeasurementPO.saveMeasurement();

        assertEquals("INSERISCI VALORI VALIDI", insertMeasurementPO1.getError());
        assertEquals("", insertMeasurementPO1.getLastMeasurement());
    }

    /**
     * Test FG:
     * Inserisco una nuova misurazione.
     * Inserisco un commento che non rispetta i requisiti.
     * Verifico che la registrazione non sia andata a buon fine.
     */
    @Test
    public void FGTestRegistration(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsertMeasurementPO insertMeasurementPO = homePO.clickInsulinPump();

        assertEquals("POMPA INSULINICA", insertMeasurementPO.getTitle());
        insertMeasurementPO.insertMeasurement("200", "3", "ho pranzato con una pizza ma non mi è piaciuta troppo perché non era buona");
        InsertMeasurementPO insertMeasurementPO1 = insertMeasurementPO.saveMeasurement();

        assertEquals("INSERISCI VALORI VALIDI", insertMeasurementPO1.getError());
        assertEquals("", insertMeasurementPO1.getLastMeasurement());
    }

    /**
     * Test FH:
     * Inserisco una nuova misurazione.
     * Inserisco la glicemia, l'insulina e il commento rispettando i requisiti richiesti.
     * Verifico che la registrazione sia stata fatta correttamente.
     */
    @Test
    public void FHTestRegistration(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsertMeasurementPO insertMeasurementPO = homePO.clickInsulinPump();

        assertEquals("POMPA INSULINICA", insertMeasurementPO.getTitle());
        insertMeasurementPO.insertMeasurement("200", "3", "ho pranzato con una pizza");
        InsertMeasurementPO insertMeasurementPO1 = insertMeasurementPO.saveMeasurement();

        assertEquals("", insertMeasurementPO1.getError());
        assertNotEquals("", insertMeasurementPO1.getLastMeasurement());
    }

    /**
     * Test FI:
     * Premo il tasto indietro presente nella pagina Pompa Insulinica e verifico di essere tornato alla pagina Home.
     */
    @Test
    public void FITestBack(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsertMeasurementPO insertMeasurementPO = homePO.clickInsulinPump();

        assertEquals("POMPA INSULINICA", insertMeasurementPO.getTitle());
        HomePO homePO1 = insertMeasurementPO.clickBack();

        assertEquals("BENVENUTO NELLA HOME", homePO1.getTitle());
    }

    //TEST CRONOLOGIA

    /**
     * Test GA:
     * Controllo che la misurazione appena registrata sia presente in cronologia e verifico che usando il tasto "Cancella" questa riga venga eliminata.
     */
    @Test
    public void GATestChronology(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ChronologyPO chronologyPO = homePO.clickChronology();

        assertEquals("CRONOLOGIA INIEZIONI", chronologyPO.getTitle());
        assertEquals("200", chronologyPO.getCell1String());
        assertEquals("3.0", chronologyPO.getCell2String());
        assertEquals("ho pranzato con una pizza", chronologyPO.getCell4String());
        ChronologyPO chronologyPO1 = chronologyPO.clickDeleteMeasurement();

        assertEquals("CRONOLOGIA INIEZIONI", chronologyPO1.getTitle());
        assertEquals(0, chronologyPO1.getCell1());
        assertEquals(0, chronologyPO1.getCell2());
        assertEquals(0, chronologyPO1.getCell4());
    }

    /**
     * Test GB:
     * Inserisco una misurazione, controllo che questa sia presente in cronologia e premo il tasto "Cancella tutto".
     * Verifico che non sia più presente nulla nella cronologia.
     */
    @Test
    public void GBTestChronology(){
        FHTestRegistration(); //inserisco una misurazione
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ChronologyPO chronologyPO = homePO.clickChronology();

        assertEquals("CRONOLOGIA INIEZIONI", chronologyPO.getTitle());
        assertEquals("200", chronologyPO.getCell1String());
        assertEquals("3.0", chronologyPO.getCell2String());
        assertEquals("ho pranzato con una pizza", chronologyPO.getCell4String());
        ChronologyPO chronologyPO1 = chronologyPO.clickDeleteChronology();

        assertEquals("CRONOLOGIA INIEZIONI", chronologyPO1.getTitle());
        assertEquals(0, chronologyPO1.getCell1());
        assertEquals(0, chronologyPO1.getCell2());
        assertEquals(0, chronologyPO1.getCell4());
    }

    /**
     * Test GC:
     * Premo il tasto indietro presente nella pagina Cronologia e verifico di essere tornato alla pagina Home.
     */
    @Test
    public void GCTestBack(){
        FHTestRegistration(); //inserisco una misurazione
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ChronologyPO chronologyPO = homePO.clickChronology();

        assertEquals("CRONOLOGIA INIEZIONI", chronologyPO.getTitle());
        HomePO homePO1 = chronologyPO.clickBack();

        assertEquals("BENVENUTO NELLA HOME", homePO1.getTitle());
    }

    //TEST LOGOUT

    /**
     * Test HA:
     * Controllo che dalla pagina home se schiaccio il pulsante "Logout" l'utente viene reindirizzato alla pagina di login.
     */
    @Test
    public void HATestLogOut(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        LogInPO logInPO1 = homePO.clickLogout();

        assertEquals("LOGIN POMPA INSULINICA", logInPO1.getTitle());
    }

    //TEST ELIMINA UTENTE

    /**
     * Test IA:
     * Controllo che una volta nella pagina Profilo se si preme il tasto "Elimina account" si venga reindirizzati nella pagina di Login.
     * Verifico che l'account sia stato effettivamente eliminato provando a eseguire nuovamente il login.
     */
    @Test
    public void IATestDelete(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfilePO profilePO = homePO.clickProfile();

        assertEquals("PROFILO", profilePO.getTitle());
        LogInPO logInPO1 = profilePO.clickDeleteAccount();

        assertEquals("LOGIN POMPA INSULINICA", logInPO1.getTitle());
        logInPO1.insertCredential("123marco", "123marco.");
        HomePO homePO1 = logInPO1.clickLogin();

        assertEquals("LOGIN POMPA INSULINICA", homePO1.getTitle());
    }

    /**
     * Test IB:
     * Lo faccio anche con l'altro account che mi ero creato e in cui nel corso dei test ho modificato l'username,
     * in modo da non avere un errore (username già utilizzato) se dovessi rifare i test in futuro.
     */
    @Test
    public void IBTestDelete(){
        driver.get("http://localhost:8080");
        LogInPO loginPO = new LogInPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.insertCredential("unbelnome", "123luca.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfilePO profilePO = homePO.clickProfile();

        assertEquals("PROFILO", profilePO.getTitle());
        LogInPO logInPO1 = profilePO.clickDeleteAccount();

        assertEquals("LOGIN POMPA INSULINICA", logInPO1.getTitle());
        logInPO1.insertCredential("unbelnome", "123luca.");
        HomePO homePO1 = logInPO1.clickLogin();

        assertEquals("LOGIN POMPA INSULINICA", homePO1.getTitle());
    }
}