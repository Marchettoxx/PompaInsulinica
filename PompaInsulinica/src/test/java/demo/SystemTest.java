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
     * Viene creato un nuovo utente in cui viene lasciato in bianco il campo relativo al nome.
     * Verifico che l'account non sia stato creato e venga chiesto d'inserire nuovamente le credenziali.
     */
    @Test
    public void AAtestCreaNuovoUtente() {
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        CreateAccountPO createAccountPO = loginPO.clickNewAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", createAccountPO.getTitle());
        createAccountPO.insertCredential("", "Massagrande", "marco@gmail.com", "123marco", "123marco.");
        LoginPO loginPO1 = createAccountPO.saveAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", loginPO1.getTitle());
    }

    /**
     * Test AB:
     * Viene creato un nuovo utente in cui viene lasciato in bianco il campo relativo al cognome.
     * Verifico che l'account non sia stato creato e venga chiesto d'inserire nuovamente le credenziali.
     */
    @Test
    public void ABtestCreaNuovoUtente() {
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        CreateAccountPO createAccountPO = loginPO.clickNewAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", createAccountPO.getTitle());
        createAccountPO.insertCredential("Marco", "", "marco@gmail.com", "123marco", "123marco.");
        LoginPO loginPO1 = createAccountPO.saveAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", loginPO1.getTitle());
    }

    /**
     * Test AC:
     * Viene creato un nuovo utente in cui viene inserita una mail non valida.
     * Verifico che l'account non sia stato creato e venga chiesto d'inserire nuovamente le credenziali.
     */
    @Test
    public void ACtestCreaNuovoUtente() {
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        CreateAccountPO createAccountPO = loginPO.clickNewAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", createAccountPO.getTitle());
        createAccountPO.insertCredential("Marco", "Massagrande", "la@hotmal.com", "123marco", "123marco.");
        LoginPO loginPO1 = createAccountPO.saveAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", loginPO1.getTitle());
    }

    /**
     * Test AD:
     * Viene creato un nuovo utente in cui viene inserito un username non valido.
     * Verifico che l'account non sia stato creato e venga chiesto d'inserire nuovamente le credenziali.
     */
    @Test
    public void ADtestCreaNuovoUtente() {
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        CreateAccountPO createAccountPO = loginPO.clickNewAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", createAccountPO.getTitle());
        createAccountPO.insertCredential("Marco", "Massagrande", "marco@gmail.com", "12", "123marco.");
        LoginPO loginPO1 = createAccountPO.saveAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", loginPO1.getTitle());
    }

    /**
     * Test AE:
     * Viene creato un nuovo utente in cui viene inserita una password che non rispetta i requisiti richiesti.
     * Verifico che l'account non sia stato creato e venga chiesto d'inserire nuovamente le credenziali.
     */
    @Test
    public void AEtestCreaNuovoUtente() {
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        CreateAccountPO createAccountPO = loginPO.clickNewAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", createAccountPO.getTitle());
        createAccountPO.insertCredential("Marco", "Massagrande", "marco@gmail.com", "123marco", "123mar");
        LoginPO loginPO1 = createAccountPO.saveAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", loginPO1.getTitle());
    }

    /**
     * Test AF:
     * Viene creato un utente con le credenziali corrette (i valori rispettano i requisiti stabiliti).
     * Verifico che l'account sia stato effettivamente creato.
     */
    @Test
    public void AFtestCreaNuovoUtente() {
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        CreateAccountPO createAccountPO = loginPO.clickNewAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", createAccountPO.getTitle());
        createAccountPO.insertCredential("Marco", "Massagrande", "marco@gmail.com", "123marco", "123marco.");
        LoginPO loginPO1 = createAccountPO.saveAccount();

        assertEquals("LOGIN POMPA INSULINICA", loginPO1.getTitle());
    }

    /**
     * Test AG:
     * Viene creato un nuovo utente e viene scelto un username già esistente.
     * Verifico che l'account non sia stato creato e venga chiesto d'inserire nuovamente le credenziali.
     */
    @Test
    public void AGtestCreaNuovoUtente() {
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        CreateAccountPO createAccountPO = loginPO.clickNewAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", createAccountPO.getTitle());
        createAccountPO.insertCredential("Marco", "Piccoli", "marco@gmail.com", "123marco", "123marco.");
        LoginPO loginPO1 = createAccountPO.saveAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", loginPO1.getTitle());
    }

    /**
     * Test AH:
     * Viene creato un nuovo utente diverso da quello creato nel test AF.
     * Verifico che l'account venga effettivamente creato.
     */
    @Test
    public void AHtestCreaNuovoUtente() {
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        CreateAccountPO createAccountPO = loginPO.clickNewAccount();

        assertEquals("CREAZIONE DI UN NUOVO UTENTE", createAccountPO.getTitle());
        createAccountPO.insertCredential("Luca", "Peppino", "luca@gmail.com", "123luca", "123luca.");
        LoginPO loginPO1 = createAccountPO.saveAccount();

        assertEquals("LOGIN POMPA INSULINICA", loginPO1.getTitle());
    }

    //TEST LOGIN

    /**
     * Test BA:
     * Eseguo il login inserendo un username errato.
     * Verifico di non aver fatto il login.
     */
    @Test
    public void BAtestLogIn() {
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("12", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("LOGIN POMPA INSULINICA", homePO.getTitle());
    }

    /**
     * Test BB:
     * Eseguo il login inserendo una password errata.
     * Verifico di non aver fatto il login.
     */
    @Test
    public void BBtestLogIn() {
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123ma");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("LOGIN POMPA INSULINICA", homePO.getTitle());
    }

    /**
     * Test BC:
     * Eseguo il login inserendo sia l'username che la password errati.
     * Verifico di non aver fatto il login.
     */
    @Test
    public void BCtestLogIn() {
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("12", "");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("LOGIN POMPA INSULINICA", homePO.getTitle());
    }

    /**
     * Test BD:
     * Eseguo il login con le credenziali corrette.
     * Verifico che il login sia avvenuto con successo e di essere nella pagina home.
     */
    @Test
    public void BDtestLogIn() {
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
    }

    //TEST PAGINA HOME

    /**
     * Test DA:
     * Premo il tasto Profilo e verifico di essere entrato effettivamente nella pagina desiderata.
     */
    @Test
    public void DAtestUtente(){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfiloPO profiloPO = homePO.clickProfilo();

        assertEquals("PROFILO", profiloPO.getTitle());
    }

    /**
     * Test DB:
     * Premo il tasto Pompa Insulinica e verifico di essere entrato effettivamente nella pagina desiderata.
     */
    @Test
    public void DBtestUtente(){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InserisciMisurazionePO inserisciMisurazionePO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", inserisciMisurazionePO.getTitle());
    }

    /**
     * Test DC:
     * Premo il tasto Cronologia e verifico di rimanere nella pagina Home,
     * in quanto non avendo ancora fatto nessuna iniezione la cronologia sarà vuota.
     */
    @Test
    public void DCtestUtente(){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        CronologiaPO cronologiaPO = homePO.clickCronologia();

        // essendo che non sono ancora state fatte misurazioni
        // non è possibile visualizzare la cronologia
        assertEquals("BENVENUTO NELLA HOME", cronologiaPO.getTitle());
    }

    //TEST PROFILO

    /**
     * Test EA:
     * Modifico il nome in modo errato e verifico che la modifica non sia avvenuta.
     */
    @Test
    public void EAtestProfilo(){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfiloPO profiloPO = homePO.clickProfilo();

        assertEquals("PROFILO", profiloPO.getTitle());
        ModificaUtentePO modificaUtentePO = profiloPO.clickModificaUtente();

        assertEquals("MODIFICA CREDENZIALI UTENTE", modificaUtentePO.getTitle());
        modificaUtentePO.insertNome("");
        ProfiloPO profiloPO1 = modificaUtentePO.clickSaveModifica();

        assertEquals("MODIFICA CREDENZIALI UTENTE", profiloPO1.getTitle());
    }

    /**
     * Test EB:
     * Modifico il cognome in modo errato e verifico che la modifica non sia avvenuta.
     */
    @Test
    public void EBtestProfilo(){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfiloPO profiloPO = homePO.clickProfilo();

        assertEquals("PROFILO", profiloPO.getTitle());
        ModificaUtentePO modificaUtentePO = profiloPO.clickModificaUtente();

        assertEquals("MODIFICA CREDENZIALI UTENTE", modificaUtentePO.getTitle());
        modificaUtentePO.insertCognome("");
        ProfiloPO profiloPO1 = modificaUtentePO.clickSaveModifica();

        assertEquals("MODIFICA CREDENZIALI UTENTE", profiloPO1.getTitle());
    }

    /**
     * Test EC:
     * Modifico il l'email in modo errato e verifico che la modifica non sia avvenuta.
     */
    @Test
    public void ECtestProfilo(){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfiloPO profiloPO = homePO.clickProfilo();

        assertEquals("PROFILO", profiloPO.getTitle());
        ModificaUtentePO modificaUtentePO = profiloPO.clickModificaUtente();

        assertEquals("MODIFICA CREDENZIALI UTENTE", modificaUtentePO.getTitle());
        modificaUtentePO.insertEmail("la@hotmal.com");
        ProfiloPO profiloPO1 = modificaUtentePO.clickSaveModifica();

        assertEquals("MODIFICA CREDENZIALI UTENTE", profiloPO1.getTitle());
    }

    /**
     * Test ED:
     * Modifico l'username in modo errato e verifico che la modifica non sia avvenuta.
     */
    @Test
    public void EDtestProfilo(){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfiloPO profiloPO = homePO.clickProfilo();

        assertEquals("PROFILO", profiloPO.getTitle());
        ModificaUtentePO modificaUtentePO = profiloPO.clickModificaUtente();

        assertEquals("MODIFICA CREDENZIALI UTENTE", modificaUtentePO.getTitle());
        modificaUtentePO.insertUsername("user");
        ProfiloPO profiloPO1 = modificaUtentePO.clickSaveModifica();

        assertEquals("MODIFICA CREDENZIALI UTENTE", profiloPO1.getTitle());
    }

    /**
     * Test EE:
     * Modifico l'username utilizzandone uno già esistente.
     * Verifico quindi che la modifica non sia avvenuta.
     */
    @Test
    public void EEtestProfilo(){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123luca", "123luca.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfiloPO profiloPO = homePO.clickProfilo();

        assertEquals("PROFILO", profiloPO.getTitle());
        ModificaUtentePO modificaUtentePO = profiloPO.clickModificaUtente();

        assertEquals("MODIFICA CREDENZIALI UTENTE", modificaUtentePO.getTitle());
        modificaUtentePO.insertUsername("123marco");
        ProfiloPO profiloPO1 = modificaUtentePO.clickSaveModifica();

        assertEquals("MODIFICA CREDENZIALI UTENTE", profiloPO1.getTitle());
    }

    /**
     * Test EF:
     * Modifico la password in modo errato e verifico che la modifica non sia avvenuta.
     */
    @Test
    public void EFtestProfilo(){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfiloPO profiloPO = homePO.clickProfilo();

        assertEquals("PROFILO", profiloPO.getTitle());
        ModificaUtentePO modificaUtentePO = profiloPO.clickModificaUtente();

        assertEquals("MODIFICA CREDENZIALI UTENTE", modificaUtentePO.getTitle());
        modificaUtentePO.insertPassword("passw1");
        ProfiloPO profiloPO1 = modificaUtentePO.clickSaveModifica();

        assertEquals("MODIFICA CREDENZIALI UTENTE", profiloPO1.getTitle());
    }

    /**
     * Test EG:
     * Modifico la mail in modo corretto.
     * Verifico che la modifica sia avvenuta con successo.
     */
    @Test
    public void EGtestProfilo(){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfiloPO profiloPO = homePO.clickProfilo();

        assertEquals("PROFILO", profiloPO.getTitle());
        ModificaUtentePO modificaUtentePO = profiloPO.clickModificaUtente();

        assertEquals("MODIFICA CREDENZIALI UTENTE", modificaUtentePO.getTitle());
        modificaUtentePO.insertEmail("marcomassa@gmail.com");
        ProfiloPO profiloPO1 = modificaUtentePO.clickSaveModifica();

        assertEquals("PROFILO", profiloPO1.getTitle());
    }

    /**
     * Test EH:
     * Modifico l'username in modo corretto.
     * Verifico che la modifica sia avvenuta con successo.
     */
    @Test
    public void EHtestProfilo(){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123luca", "123luca.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfiloPO profiloPO = homePO.clickProfilo();

        assertEquals("PROFILO", profiloPO.getTitle());
        ModificaUtentePO modificaUtentePO = profiloPO.clickModificaUtente();

        assertEquals("MODIFICA CREDENZIALI UTENTE", modificaUtentePO.getTitle());
        modificaUtentePO.insertUsername("unbelnome");
        ProfiloPO profiloPO1 = modificaUtentePO.clickSaveModifica();

        assertEquals("PROFILO", profiloPO1.getTitle());
    }

    /**
     * Test EI:
     * Premo il tasto indietro presente nella pagina Modifica Credenziali Utente e verifico di essere tornato nella pagina Profilo.
     */
    @Test
    public void EItestIndietro (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfiloPO profiloPO = homePO.clickProfilo();

        assertEquals("PROFILO", profiloPO.getTitle());
        ModificaUtentePO modificaUtentePO = profiloPO.clickModificaUtente();

        assertEquals("MODIFICA CREDENZIALI UTENTE", modificaUtentePO.getTitle());
        ProfiloPO profiloPO1 = modificaUtentePO.clickBack();

        assertEquals("PROFILO", profiloPO1.getTitle());
    }

    //TEST POMPA INSULINICA

    /**
     * Test FA:
     * Inserisco una nuova iniezione.
     * Nel campo glicemia si inserisce un valore sbagliato (non compreso tra 100 e 300).
     * Verifico che l'iniezione non sia andata a buon fine.
     */
    @Test
    public void FAtestPompaInsulinica (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InserisciMisurazionePO inserisciMisurazionePO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", inserisciMisurazionePO.getTitle());
        inserisciMisurazionePO.insertMisurazione("500", "3", "ho pranzato con una pizza");
        InserisciMisurazionePO inserisciMisurazionePO1 = inserisciMisurazionePO.saveMisurazione();

        assertEquals("INSERISCI VALORI VALIDI", inserisciMisurazionePO1.getErrore());
        assertEquals("", inserisciMisurazionePO1.getLastMisurazione());
    }

    /**
     * Test FB:
     * Inserisco una nuova iniezione.
     * Nel campo glicemia si inserisce un valore non numerico.
     * Verifico che l'iniezione non sia andata a buon fine.
     */
    @Test
    public void FBtestPompaInsulinica (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InserisciMisurazionePO inserisciMisurazionePO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", inserisciMisurazionePO.getTitle());
        inserisciMisurazionePO.insertMisurazione("glicemia", "3", "ho pranzato con una pizza");
        InserisciMisurazionePO inserisciMisurazionePO1 = inserisciMisurazionePO.saveMisurazione();

        assertEquals("INSERISCI VALORI VALIDI", inserisciMisurazionePO1.getErrore());
        assertEquals("", inserisciMisurazionePO1.getLastMisurazione());
    }

    /**
     * Test FC:
     * Inserisco una nuova iniezione.
     * Nel campo glicemia si inserisce un valore vuoto.
     * Verifico che l'iniezione non sia andata a buon fine.
     */
    @Test
    public void FCtestPompaInsulinica (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InserisciMisurazionePO inserisciMisurazionePO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", inserisciMisurazionePO.getTitle());
        inserisciMisurazionePO.insertMisurazione("", "3", "ho pranzato con una pizza");
        InserisciMisurazionePO inserisciMisurazionePO1 = inserisciMisurazionePO.saveMisurazione();

        assertEquals("INSERISCI VALORI VALIDI", inserisciMisurazionePO1.getErrore());
        assertEquals("", inserisciMisurazionePO1.getLastMisurazione());
    }

    /**
     * Test FD:
     * Inserisco una nuova iniezione.
     * Nel campo insulina si inserisce un valore che non rispetta i requisiti.
     * Verifico che l'iniezione non sia andata a buon fine.
     */
    @Test
    public void FDtestPompaInsulinica (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InserisciMisurazionePO inserisciMisurazionePO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", inserisciMisurazionePO.getTitle());
        inserisciMisurazionePO.insertMisurazione("200", "15", "ho pranzato con una pizza");
        InserisciMisurazionePO inserisciMisurazionePO1 = inserisciMisurazionePO.saveMisurazione();

        assertEquals("INSERISCI VALORI VALIDI", inserisciMisurazionePO1.getErrore());
        assertEquals("", inserisciMisurazionePO1.getLastMisurazione());
    }

    /**
     * Test FE:
     * Inserisco una nuova iniezione.
     * Nel campo insulina si inserisce un valore in lettere.
     * Verifico che l'iniezione non sia andata a buon fine.
     */
    @Test
    public void FEtestPompaInsulinica (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InserisciMisurazionePO inserisciMisurazionePO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", inserisciMisurazionePO.getTitle());
        inserisciMisurazionePO.insertMisurazione("200", "insulina", "ho pranzato con una pizza");
        InserisciMisurazionePO inserisciMisurazionePO1 = inserisciMisurazionePO.saveMisurazione();

        assertEquals("INSERISCI VALORI VALIDI", inserisciMisurazionePO1.getErrore());
        assertEquals("", inserisciMisurazionePO1.getLastMisurazione());
    }

    /**
     * Test FF:
     * Inserisco una nuova iniezione.
     * Lascio il valore relativo all'insulina vuoto.
     * Verifico che l'iniezione non sia andata a buon fine.
     */
    @Test
    public void FFtestPompaInsulinica (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InserisciMisurazionePO inserisciMisurazionePO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", inserisciMisurazionePO.getTitle());
        inserisciMisurazionePO.insertMisurazione("200", "", "ho pranzato con una pizza");
        InserisciMisurazionePO inserisciMisurazionePO1 = inserisciMisurazionePO.saveMisurazione();

        assertEquals("INSERISCI VALORI VALIDI", inserisciMisurazionePO1.getErrore());
        assertEquals("", inserisciMisurazionePO1.getLastMisurazione());
    }

    /**
     * Test FG:
     * Inserisco una nuova iniezione.
     * Inserisco un commento che non rispetta i requisiti.
     * Verifico che l'iniezione non sia andata a buon fine.
     */
    @Test
    public void FGtestPompaInsulinica (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InserisciMisurazionePO inserisciMisurazionePO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", inserisciMisurazionePO.getTitle());
        inserisciMisurazionePO.insertMisurazione("200", "3", "ho pranzato con una pizza ma non mi è piaciuta troppo perchè non era buona");
        InserisciMisurazionePO inserisciMisurazionePO1 = inserisciMisurazionePO.saveMisurazione();

        assertEquals("INSERISCI VALORI VALIDI", inserisciMisurazionePO1.getErrore());
        assertEquals("", inserisciMisurazionePO1.getLastMisurazione());
    }

    /**
     * Test FH:
     * Inserisco una nuova iniezione.
     * Inserisco la glicemia, l'insulina e il commento rispettando i requisiti richiesti.
     * Verifico che l'iniezione sia stata fatta correttamente.
     */
    @Test
    public void FHtestPompaInsulinica (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InserisciMisurazionePO inserisciMisurazionePO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", inserisciMisurazionePO.getTitle());
        inserisciMisurazionePO.insertMisurazione("200", "3", "ho pranzato con una pizza");
        InserisciMisurazionePO inserisciMisurazionePO1 = inserisciMisurazionePO.saveMisurazione();

        assertEquals("", inserisciMisurazionePO1.getErrore());
        assertNotEquals("", inserisciMisurazionePO1.getLastMisurazione());
    }

    /**
     * Test FI:
     * Premo il tasto indietro presente nella pagina Pompa Insulinica e verifico di essere tornato alla pagina Home.
     */
    @Test
    public void FItestIndietro (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InserisciMisurazionePO inserisciMisurazionePO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", inserisciMisurazionePO.getTitle());
        HomePO homePO1 = inserisciMisurazionePO.clickBack();

        assertEquals("BENVENUTO NELLA HOME", homePO1.getTitle());
    }

    //TEST CRONOLOGIA

    /**
     * Test GA:
     * Controllo che l'iniezione appena fatta sia presente in cronologia e verifico che usando il tasto "Cancella" questa riga venga eliminata.
     */
    @Test
    public void GAtestCronologia (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        CronologiaPO cronologiaPO = homePO.clickCronologia();

        assertEquals("CRONOLOGIA INIEZIONI", cronologiaPO.getTitle());
        assertEquals("200", cronologiaPO.getCell1String());
        assertEquals("3.0", cronologiaPO.getCell2String());
        assertEquals("ho pranzato con una pizza", cronologiaPO.getCell4String());
        CronologiaPO cronologiaPO1 = cronologiaPO.clickCancellaMisurazione();

        assertEquals("CRONOLOGIA INIEZIONI", cronologiaPO1.getTitle());
        assertEquals(0, cronologiaPO1.getCell1());
        assertEquals(0, cronologiaPO1.getCell2());
        assertEquals(0, cronologiaPO1.getCell4());
    }

    /**
     * Test GB:
     * Inserisco una misurazione, controllo che questa sia presente in cronologia e premo il tasto "Cancella tutto".
     * Verifico che non sia più presente nulla nella cronologia.
     */
    @Test
    public void GBtestCronologia (){
        FHtestPompaInsulinica(); //inserisco una misurazione
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        CronologiaPO cronologiaPO = homePO.clickCronologia();

        assertEquals("CRONOLOGIA INIEZIONI", cronologiaPO.getTitle());
        assertEquals("200", cronologiaPO.getCell1String());
        assertEquals("3.0", cronologiaPO.getCell2String());
        assertEquals("ho pranzato con una pizza", cronologiaPO.getCell4String());
        CronologiaPO cronologiaPO1 = cronologiaPO.clickCancellaCronologia();

        assertEquals("CRONOLOGIA INIEZIONI", cronologiaPO1.getTitle());
        assertEquals(0, cronologiaPO1.getCell1());
        assertEquals(0, cronologiaPO1.getCell2());
        assertEquals(0, cronologiaPO1.getCell4());
    }

    /**
     * Test GC:
     * Premo il tasto indietro presente nella pagina Cronologia e verifico di essere tornato alla pagina Home.
     */
    @Test
    public void GCtestIndietro (){
        FHtestPompaInsulinica(); //inserisco una misurazione
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        CronologiaPO cronologiaPO = homePO.clickCronologia();

        assertEquals("CRONOLOGIA INIEZIONI", cronologiaPO.getTitle());
        HomePO homePO1 = cronologiaPO.clickBack();

        assertEquals("BENVENUTO NELLA HOME", homePO1.getTitle());
    }

    //TEST LOGOUT

    /**
     * Test HA:
     * Controllo che dalla pagina home se schiaccio il pulsante "Logout" l'utente viene reindirizzato alla pagina di login.
     */
    @Test
    public void HAtestLogOut(){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        LoginPO loginPO1 = homePO.clickLogout();

        assertEquals("LOGIN POMPA INSULINICA", loginPO1.getTitle());
    }

    //TEST ELIMINA UTENTE

    /**
     * Test IA:
     * Controllo che una volta nella pagina Profilo se si preme il tasto "Elimina account" si venga reindirizzati nella pagina di Login.
     * Verifico che l'account sia stato effettivamente eliminato provando a eseguire nuovamente il login.
     */
    @Test
    public void IAtestDelete(){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfiloPO profiloPO = homePO.clickProfilo();

        assertEquals("PROFILO", profiloPO.getTitle());
        LoginPO loginPO1 = profiloPO.clickDeleteAccount();

        assertEquals("LOGIN POMPA INSULINICA", loginPO1.getTitle());
        loginPO1.inserCredential("123marco", "123marco.");
        HomePO homePO1 = loginPO1.clickLogin();

        assertEquals("LOGIN POMPA INSULINICA", homePO1.getTitle());
    }

    /**
     * Test IB:
     * Lo faccio anche con l'altro account che mi ero creato e in cui nel corso dei test ho modificato l'username,
     * in modo da non avere un errore (username già utilizzato) se dovessi rifare i test in futuro.
     */
    @Test
    public void IBtestDelete(){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("unbelnome", "123luca.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        ProfiloPO profiloPO = homePO.clickProfilo();

        assertEquals("PROFILO", profiloPO.getTitle());
        LoginPO loginPO1 = profiloPO.clickDeleteAccount();

        assertEquals("LOGIN POMPA INSULINICA", loginPO1.getTitle());
        loginPO1.inserCredential("unbelnome", "123luca.");
        HomePO homePO1 = loginPO1.clickLogin();

        assertEquals("LOGIN POMPA INSULINICA", homePO1.getTitle());
    }

    //TEST CHANGE ID

    /**
     * Test JA:
     * Verifico se il cambio d'ID di un utente funziona.
     */
    @Test
    public void JAtestChangeIdPerson() {
        Misurazione misurazione = new Misurazione(1L, 200, 3, "pizza e coca");
        misurazione.setIdUtente(2L);
        assertEquals(Long.valueOf(2), misurazione.getIdUtente());
    }

    //TEST TOSTRING

    /**
     * Test KA:
     * Verifico che il metodo toString della classe Person mi ritorni la stringa da me desiderata.
     */
    @Test
    public void KAtestToStringPersona() {
        Person person = new Person("Luca", "Piccolo", "piccolo@gmail.com", "piccololu", "luca123.");
        assertEquals("Customer[id=null, nome= 'Luca', cognome='Piccolo', email='piccolo@gmail.com', username='piccololu', password='luca123.']", person.toString());
    }

    /**
     * Test KB:
     * Vorrei anche verificare lo stesso metodo della classe PompaInsulinica
     * ma essendo che abbiamo come variabile il tempo reale in cui viene effettuata un'iniezione non riesco a fare ciò.
     */
    @Test
    public void KBtestToStringMisurazione() {
        Misurazione misurazione = new Misurazione(1L, 200, 3, "pizza e coca");
        assertEquals("Misurazione[id=null, idUtente='1', glicemia= '200', insulina='3', commento='pizza e coca', time='00:00']", misurazione.toString());
    }
}