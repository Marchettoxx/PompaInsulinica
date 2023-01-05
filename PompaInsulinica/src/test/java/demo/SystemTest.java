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
        //chrome_options.addArguments("--headless");
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

    @Test
    public void testCreaNuovoUtente() {
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