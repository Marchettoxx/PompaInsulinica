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

    // non inserisco nome
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

    //non inserisco cognome
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

    //inserisco email errata
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

    //inserisco username errato
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

    //inserisco password errata
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

    //creazione utente inserendo campi corretti
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

    //creo utente con username esistente
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

    //creazione utente inserendo campi corretti (per testare la modifica di username)
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

    //inserisco username errato
    @Test
    public void BAtestLogIn() {
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("12", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("LOGIN POMPA INSULINICA", homePO.getTitle());
    }

    // inserisco password errata
    @Test
    public void BBtestLogIn() {
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123ma");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("LOGIN POMPA INSULINICA", homePO.getTitle());
    }

    //inserisco username e password errati
    @Test
    public void BCtestLogIn() {
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("12", "");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("LOGIN POMPA INSULINICA", homePO.getTitle());
    }

    // test login inserendo credenziali utente corrette
    @Test
    public void BDtestLogIn() {
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
    }

    //premo bottone profilo
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

    // test bottone pompa insulinica
    @Test
    public void DBtestUtente(){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsulinaPO insulinaPO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", insulinaPO.getTitle());
    }

    // test sul bottone della cronologia senza aver fatto delle iniezioni
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

    // test in cui modifico il nome in modo errato
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

    // test in cui modifico il cognome in modo errato
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

    // test in cui modifico email in modo errato
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

    // test in cui modifico username in modo errato
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

    // test in cui modifico username con username esistente
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

    // test in cui modifico password in modo errato
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

    // test in cui modifico correttamente le credenziali: modifico la email
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

    // test in cui modifico correttamente le credenziali: modifico username
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

    // da modifica profilo vado indietro a profilo
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

    // inserisco glicemia errata
    @Test
    public void FAtestPompaInsulinica (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsulinaPO insulinaPO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", insulinaPO.getTitle());
        insulinaPO.insertMisurazione("500", "3", "ho pranzato con una pizza");
        InsulinaPO insulinaPO1 = insulinaPO.saveMisurazione();

        assertEquals("INSERISCI VALORI VALIDI", insulinaPO1.getErrore());
        assertEquals("", insulinaPO1.getLastMisurazione());
    }

    // inserisco glicemia errata con valore in lettere
    @Test
    public void FBtestPompaInsulinica (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsulinaPO insulinaPO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", insulinaPO.getTitle());
        insulinaPO.insertMisurazione("glicemia", "3", "ho pranzato con una pizza");
        InsulinaPO insulinaPO1 = insulinaPO.saveMisurazione();

        assertEquals("INSERISCI VALORI VALIDI", insulinaPO1.getErrore());
        assertEquals("", insulinaPO1.getLastMisurazione());
    }

    // inserisco glicemia errata con valore vuoto
    @Test
    public void FCtestPompaInsulinica (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsulinaPO insulinaPO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", insulinaPO.getTitle());
        insulinaPO.insertMisurazione("", "3", "ho pranzato con una pizza");
        InsulinaPO insulinaPO1 = insulinaPO.saveMisurazione();

        assertEquals("INSERISCI VALORI VALIDI", insulinaPO1.getErrore());
        assertEquals("", insulinaPO1.getLastMisurazione());
    }

    // inserisco insulina errata
    @Test
    public void FDtestPompaInsulinica (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsulinaPO insulinaPO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", insulinaPO.getTitle());
        insulinaPO.insertMisurazione("200", "15", "ho pranzato con una pizza");
        InsulinaPO insulinaPO1 = insulinaPO.saveMisurazione();

        assertEquals("INSERISCI VALORI VALIDI", insulinaPO1.getErrore());
        assertEquals("", insulinaPO1.getLastMisurazione());
    }

    // inserisco insulina errata con valore in lettera
    @Test
    public void FEtestPompaInsulinica (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsulinaPO insulinaPO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", insulinaPO.getTitle());
        insulinaPO.insertMisurazione("200", "insulina", "ho pranzato con una pizza");
        InsulinaPO insulinaPO1 = insulinaPO.saveMisurazione();

        assertEquals("INSERISCI VALORI VALIDI", insulinaPO1.getErrore());
        assertEquals("", insulinaPO1.getLastMisurazione());
    }

    // inserisco insulina errata con valore vuoto
    @Test
    public void FFtestPompaInsulinica (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsulinaPO insulinaPO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", insulinaPO.getTitle());
        insulinaPO.insertMisurazione("200", "", "ho pranzato con una pizza");
        InsulinaPO insulinaPO1 = insulinaPO.saveMisurazione();

        assertEquals("INSERISCI VALORI VALIDI", insulinaPO1.getErrore());
        assertEquals("", insulinaPO1.getLastMisurazione());
    }

    // inserisco commento errato
    @Test
    public void FGtestPompaInsulinica (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsulinaPO insulinaPO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", insulinaPO.getTitle());
        insulinaPO.insertMisurazione("200", "3", "ho pranzato con una pizza ma non mi è piaciuta troppo perchè non era buona");
        InsulinaPO insulinaPO1 = insulinaPO.saveMisurazione();

        assertEquals("INSERISCI VALORI VALIDI", insulinaPO1.getErrore());
        assertEquals("", insulinaPO1.getLastMisurazione());
    }

    // inserisco misurazione corretta
    @Test
    public void FHtestPompaInsulinica (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsulinaPO insulinaPO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", insulinaPO.getTitle());
        insulinaPO.insertMisurazione("200", "3", "ho pranzato con una pizza");
        InsulinaPO insulinaPO1 = insulinaPO.saveMisurazione();

        assertEquals("", insulinaPO1.getErrore());
        assertNotEquals("", insulinaPO1.getLastMisurazione());
    }

    // test da pompa insulinica vado indietro a home
    @Test
    public void FItestIndietro (){
        driver.get("http://localhost:8080");
        LoginPO loginPO = new LoginPO(driver);
        assertEquals( "LOGIN POMPA INSULINICA", loginPO.getTitle());
        loginPO.inserCredential("123marco", "123marco.");
        HomePO homePO = loginPO.clickLogin();

        assertEquals("BENVENUTO NELLA HOME", homePO.getTitle());
        InsulinaPO insulinaPO = homePO.clickPompaInsulinica();

        assertEquals("POMPA INSULINICA", insulinaPO.getTitle());
        HomePO homePO1 = insulinaPO.clickBack();

        assertEquals("BENVENUTO NELLA HOME", homePO1.getTitle());
    }

    // controllo che l'iniezione sia in cronologia e la cancello con il tasto cancella riga
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

    // controllo che l'iniezione sia in cronologia e la cancello con il tasto cancella tutto
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

    // test da cronologia vado indietro a home
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

    // test LogOut
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

    // test per eliminare l'utente
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

    // testo se change id funziona
    @Test
    public void JAtestChangeIdPerson() {
        PompaInsulinica pompaInsulinica = new PompaInsulinica(1L, 200, 3, "pizza e coca");
        pompaInsulinica.setIdUtente(2L);
        assertEquals(Long.valueOf(2), pompaInsulinica.getIdUtente());
    }
}