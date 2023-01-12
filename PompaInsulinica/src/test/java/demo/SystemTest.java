package demo;

import PO.*;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
        createAccountPO.insertCredential("Marco", "Massagrande", "il.com", "123marco", "123marco.");
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
        modificaUtentePO.insertEmail("nonunaemail");
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

    // test in cui modifico password in modo errato
    @Test
    public void EEtestProfilo(){
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
        modificaUtentePO.insertEmail("marcomassa@gmail.com");
        ProfiloPO profiloPO1 = modificaUtentePO.clickSaveModifica();

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

    // inserisco insulina errata
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
        insulinaPO.insertMisurazione("200", "15", "ho pranzato con una pizza");
        InsulinaPO insulinaPO1 = insulinaPO.saveMisurazione();

        assertEquals("INSERISCI VALORI VALIDI", insulinaPO1.getErrore());
        assertEquals("", insulinaPO1.getLastMisurazione());
    }

    // inserisco commento errato
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
        insulinaPO.insertMisurazione("200", "3", "ho pranzato con una pizza ma non mi è piaciuta troppo perchè non era buona");
        InsulinaPO insulinaPO1 = insulinaPO.saveMisurazione();

        assertEquals("INSERISCI VALORI VALIDI", insulinaPO1.getErrore());
        assertEquals("", insulinaPO1.getLastMisurazione());
    }

    // inserisco misurazione corretta
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
        insulinaPO.insertMisurazione("200", "3", "ho pranzato con una pizza");
        InsulinaPO insulinaPO1 = insulinaPO.saveMisurazione();

        assertEquals("", insulinaPO1.getErrore());
        assertNotEquals("", insulinaPO1.getLastMisurazione());
    }

    /*@Test
    public void GtestIndietro (){
        driver.get("http://localhost:8080");
        WebElement title = driver.findElement(By.tagName("h1"));
        String titleMessage = title.getText();
        assertEquals("LOGIN POMPA INSULINICA", titleMessage);
        driver.findElement(By.name("username")).sendKeys("Marco123");
        driver.findElement(By.name("password")).sendKeys("Marco123.");
        WebElement link1 = driver.findElement(By.className("btn"));
        link1.click();

        WebElement title1 = driver.findElement(By.tagName("h1"));
        String titleMessage1 = title1.getText();
        assertEquals("BENVENUTO NELLA HOME", titleMessage1);
        WebElement link2 = driver.findElement(By.xpath("//input[@value='Pompa insulinica']"));
        link2.click();

        WebElement title2 = driver.findElement(By.tagName("h1"));
        String titleMessage2 = title2.getText();
        assertEquals("POMPA INSULINICA", titleMessage2);
        WebElement link4 = driver.findElement(By.className("btnBack"));
        link4.click();

        WebElement title4 = driver.findElement(By.tagName("h1"));
        String titleMessage4 = title4.getText();
        assertEquals("BENVENUTO NELLA HOME", titleMessage4);
    }
*/
    // controllo che l'iniezione sia in cronologia e la cancello con il tasto cancella riga
    @Test
    public void HAtestCronologia (){
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
    public void HBtestCronologia (){
        FDtestPompaInsulinica(); //inserisco una misurazione
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

    /*
    // test LogOut
    @Test
    public void ItestLogOut(){
        driver.get("http://localhost:8080");
        WebElement title = driver.findElement(By.tagName("h1"));
        String titleMessage = title.getText();
        assertEquals("LOGIN POMPA INSULINICA", titleMessage);
        driver.findElement(By.name("username")).sendKeys("Marco123");
        driver.findElement(By.name("password")).sendKeys("Marco123.");
        WebElement link1 = driver.findElement(By.className("btn"));
        link1.click();
        WebElement title1 = driver.findElement(By.tagName("h1"));
        String titleMessage1 = title1.getText();
        assertEquals("BENVENUTO NELLA HOME", titleMessage1);

        WebElement link2 = driver.findElement(By.className("btnLogOut"));
        link2.click();
        WebElement title2 = driver.findElement(By.tagName("h1"));
        String titleMessage2 = title2.getText();
        assertEquals("LOGIN POMPA INSULINICA", titleMessage2);
    }

    // test per eliminare l'utente
    @Test
    public void ZtestDelete(){
        driver.get("http://localhost:8080");
        WebElement title = driver.findElement(By.tagName("h1"));
        String titleMessage = title.getText();
        assertEquals("LOGIN POMPA INSULINICA", titleMessage);
        driver.findElement(By.name("username")).sendKeys("Marco123");
        driver.findElement(By.name("password")).sendKeys("Marco123.");
        WebElement link1 = driver.findElement(By.className("btn"));
        link1.click();
        WebElement title1 = driver.findElement(By.tagName("h1"));
        String titleMessage1 = title1.getText();
        assertEquals("BENVENUTO NELLA HOME", titleMessage1);

        WebElement link2 = driver.findElement(By.xpath("//input[@value='Profilo']"));
        link2.click();
        WebElement title2 = driver.findElement(By.tagName("h1"));
        String titleMessage2 = title2.getText();
        assertEquals("PROFILO", titleMessage2);

        WebElement link3 = driver.findElement(By.className("btnLogOut"));
        link3.click();
        WebElement title3 = driver.findElement(By.tagName("h1"));
        String titleMessage3 = title3.getText();
        assertEquals("LOGIN POMPA INSULINICA", titleMessage3);

        driver.findElement(By.name("username")).sendKeys("Marco123");
        driver.findElement(By.name("password")).sendKeys("Marco123.");
        WebElement link4 = driver.findElement(By.className("btn"));
        link4.click();
        WebElement title4 = driver.findElement(By.tagName("h1"));
        String titleMessage4 = title4.getText();
        assertEquals("LOGIN POMPA INSULINICA", titleMessage4);

    }
    */
}