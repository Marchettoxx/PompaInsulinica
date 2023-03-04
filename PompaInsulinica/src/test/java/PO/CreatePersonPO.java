package PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePersonPO extends PageObject {

    @FindBy(tagName = "h1")
    private WebElement title;

    @FindBy(name = "name")
    private WebElement name;

    @FindBy(name = "surname")
    private WebElement surname;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(className = "btn")
    private WebElement buttonSave;

    public CreatePersonPO(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return this.title.getText();
    }

    public LogInPO saveAccount() {
        this.buttonSave.click();
        return new LogInPO(driver);
    }

    public void insertCredential(String name, String surname, String email, String username, String password) {
        this.name.clear();
        this.name.sendKeys(name);
        this.surname.clear();
        this.surname.sendKeys(surname);
        this.email.clear();
        this.email.sendKeys(email);
        this.username.clear();
        this.username.sendKeys(username);
        this.password.clear();
        this.password.sendKeys(password);
    }
}
