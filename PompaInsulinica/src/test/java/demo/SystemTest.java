package demo;

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

    // test sulla creazione di un nuovo utente
    @Test
    public void AtestCreaNuovoUtente() {
        driver.get("http://localhost:8080");
        WebElement title = driver.findElement(By.tagName("h1"));
        String titleMessage = title.getText();
        assertEquals("LOGIN POMPA INSULINICA", titleMessage);
        WebElement link1 = driver.findElement(By.className("btnNewAccount"));
        link1.click();

        WebElement titleCreaUtente = driver.findElement(By.tagName("h1"));
        assertEquals("CREAZIONE DI UN NUOVO UTENTE", titleCreaUtente.getText());
        driver.findElement(By.name("nome")).sendKeys("Marco");
        driver.findElement(By.name("cognome")).sendKeys("Massagrande");
        driver.findElement(By.name("email")).sendKeys("Marco@gmail.com");
        driver.findElement(By.name("username")).sendKeys("Marco123");
        driver.findElement(By.name("password")).sendKeys("Marco123.");
        driver.findElement(By.name("password")).submit();
        WebElement link2 = driver.findElement(By.className("btn"));
        link2.click();

        WebElement titleLogin = driver.findElement(By.tagName("h1"));
        assertEquals("LOGIN POMPA INSULINICA", titleLogin.getText());
    }

    // test login errato
    @Test
    public void BAtestLogInErrato() {
        driver.get("http://localhost:8080");
        WebElement title = driver.findElement(By.tagName("h1"));
        String titleMessage = title.getText();
        assertEquals("LOGIN POMPA INSULINICA", titleMessage);
        driver.findElement(By.name("username")).sendKeys("Marco123");
        driver.findElement(By.name("password")).sendKeys("Marco123");
        WebElement link1 = driver.findElement(By.className("btn"));
        link1.click();

        WebElement title1 = driver.findElement(By.tagName("h1"));
        String titleMessage1 = title1.getText();
        assertEquals("LOGIN POMPA INSULINICA", titleMessage1);
    }

    @Test
    public void BBtestLogInErrato() {
        driver.get("http://localhost:8080");
        WebElement title = driver.findElement(By.tagName("h1"));
        String titleMessage = title.getText();
        assertEquals("LOGIN POMPA INSULINICA", titleMessage);
        driver.findElement(By.name("username")).sendKeys("Marco12");
        driver.findElement(By.name("password")).sendKeys("Marco123.");
        WebElement link1 = driver.findElement(By.className("btn"));
        link1.click();

        WebElement title1 = driver.findElement(By.tagName("h1"));
        String titleMessage1 = title1.getText();
        assertEquals("LOGIN POMPA INSULINICA", titleMessage1);
    }

    @Test
    public void BCtestLogInErrato() {
        driver.get("http://localhost:8080");
        WebElement title = driver.findElement(By.tagName("h1"));
        String titleMessage = title.getText();
        assertEquals("LOGIN POMPA INSULINICA", titleMessage);
        driver.findElement(By.name("username")).sendKeys("Marco12");
        driver.findElement(By.name("password")).sendKeys("Marco12");
        WebElement link1 = driver.findElement(By.className("btn"));
        link1.click();

        WebElement title1 = driver.findElement(By.tagName("h1"));
        String titleMessage1 = title1.getText();
        assertEquals("LOGIN POMPA INSULINICA", titleMessage1);
    }

    // test login corretto
    @Test
    public void CtestLogInGiusto() {
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
    }

    // test bottone profilo
    @Test
    public void DAtestUtente(){
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

    }

    // test bottone pompa insulinica
    @Test
    public void DBtestUtente(){
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

    }

    // test sul bottone della cronologia senza aver fatto delle iniezioni
    @Test
    public void DCtestUtente(){
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

        WebElement link2 = driver.findElement(By.xpath("//input[@value='Cronologia']"));
        link2.click();
        WebElement title2 = driver.findElement(By.tagName("h1"));
        String titleMessage2 = title2.getText();
        assertEquals("BENVENUTO NELLA HOME", titleMessage2); //questo perchè senza delle iniezioni
        // la cronologia da errore e rimane nella stessa pagina di benvenuto

    }

    // test in cui modifico le credenziali del mio utente
    @Test
    public void EAtestProfilo(){
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

        WebElement link3 = driver.findElement(By.className("btn"));
        link3.click();
        WebElement title3 = driver.findElement(By.tagName("h1"));
        String titleMessage3 = title3.getText();
        assertEquals("MODIFICA CREDENZIALI UTENTE", titleMessage3);

        driver.findElement(By.name("cognome")).clear();
        driver.findElement(By.name("cognome")).sendKeys("Palmieri");
        driver.findElement(By.className("btn")).submit();
        WebElement title4 = driver.findElement(By.tagName("h1"));
        String titleMessage4 = title4.getText();
        assertEquals("PROFILO", titleMessage4);
        //WebElement cell2 = driver.findElement(By.xpath("//table//tbody//tr[2]//td[2]"));
        //assertEquals("Palmieri", cell2.getText());

    }

    @Test
    public void FtestPompaInsulinica (){
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

        driver.findElement(By.name("glicemia")).sendKeys("150");
        driver.findElement(By.name("insulina")).sendKeys("2");
        driver.findElement(By.name("commento")).sendKeys("due unità di insulina");
        WebElement link3 = driver.findElement(By.className("btn"));
        link3.click();
        WebElement title3 = driver.findElement(By.tagName("h1"));
        String titleMessage3 = title3.getText();
        assertEquals("POMPA INSULINICA", titleMessage3);
    }

    @Test
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


    // controllo che l'iniezione sia in cronologia e la cancello con il tasto cancella riga
    @Test
    public void HAtestCronologia (){
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

        WebElement link2 = driver.findElement(By.xpath("//input[@value='Cronologia']"));
        link2.click();
        WebElement title2 = driver.findElement(By.tagName("h1"));
        String titleMessage2 = title2.getText();
        assertEquals("CRONOLOGIA INIEZIONI", titleMessage2);

        WebElement title3 = driver.findElement(By.xpath("//td[1]"));
        String titleMessage3 = title3.getText();
        assertEquals("150", titleMessage3);

        WebElement link3 = driver.findElement(By.xpath("//input[@value='cancella']"));
        link3.click();
        assertEquals("CRONOLOGIA INIEZIONI", driver.findElement(By.tagName("h1")).getText());
        assertEquals(driver.findElements(By.name("glicemia")).size(), 0);
        assertEquals(driver.findElements(By.name("insulina")).size(), 0);
        assertEquals(driver.findElements(By.name("commento")).size(), 0);

    }

    /*
    // controllo che l'iniezione sia in cronologia e la cancello con il tasto cancella tutto
    @Test
    public void HBtestCronologia (){
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

        WebElement link2 = driver.findElement(By.xpath("//input[@value='Cronologia']"));
        link2.click();
        WebElement title2 = driver.findElement(By.tagName("h1"));
        String titleMessage2 = title2.getText();
        assertEquals("CRONOLOGIA INIEZIONI", titleMessage2);

        WebElement title3 = driver.findElement(By.xpath("//td[1]"));
        String titleMessage3 = title3.getText();
        assertEquals("150", titleMessage3);

        WebElement link3 = driver.findElement(By.xpath("//input[@value='cancella tutto']"));
        link3.click();
        assertEquals("CRONOLOGIA INIEZIONI", driver.findElement(By.tagName("h1")).getText());
        assertEquals(driver.findElements(By.name("id")).size(), 0);

    }
    */
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

    /*@Test
    public void testAddPerson() {
        driver.get("http://localhost:8080");
        WebElement title = driver.findElement(By.tagName("h1"));
        String titleMessage = title.getText();
        assertEquals("People list", titleMessage);
        WebElement link = driver.findElement(By.linkText("Add new person"));
        link.click();

        WebElement titleAddPerson = driver.findElement(By.tagName("h1"));
        assertEquals("Create a new record", titleAddPerson.getText());
        driver.findElement(By.name("firstname")).sendKeys("Marco");
        driver.findElement(By.name("lastname")).sendKeys("Massagrande");
        driver.findElement(By.name("lastname")).submit();

        assertEquals("People list", driver.findElement(By.tagName("h1")).getText());
        WebElement cell1 = driver.findElement(By.xpath("//table//tbody//td[2]"));
        assertEquals("Marco", cell1.getText());
        WebElement cell2 = driver.findElement(By.xpath("//table//tbody//td[3]"));
        assertEquals("Massagrande", cell2.getText());
    }

    @Test
    public void testChangePerson() {
        driver.get("http://localhost:8080/list");
        WebElement title = driver.findElement(By.tagName("h1"));
        String titleMessage = title.getText();
        assertEquals("People list", titleMessage);
        WebElement link = driver.findElement(By.linkText("edit"));
        link.click();

        WebElement titleEditPerson = driver.findElement(By.tagName("h1"));
        assertEquals("Edit a record", titleEditPerson.getText());
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys("Luca");
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys("Piccolo");
        driver.findElement(By.name("lastname")).submit();

        assertEquals("People list", driver.findElement(By.tagName("h1")).getText());
        WebElement cell1 = driver.findElement(By.xpath("//table//tbody//td[2]"));
        assertEquals("Luca", cell1.getText());
        WebElement cell2 = driver.findElement(By.xpath("//table//tbody//td[3]"));
        assertEquals("Piccolo", cell2.getText());
    }

    @Test
    public void testDeletePerson() {
        driver.get("http://localhost:8080/list");
        WebElement title = driver.findElement(By.tagName("h1"));
        String titleMessage = title.getText();
        assertEquals("People list", titleMessage);
        WebElement link = driver.findElement(By.linkText("delete"));
        link.click();

        assertEquals("People list", driver.findElement(By.tagName("h1")).getText());
        assertEquals(driver.findElements(By.xpath("//table//tbody//td[2]")).size(), 0);
        assertEquals(driver.findElements(By.xpath("//table//tbody//td[3]")).size(), 0);
    }
    */
}