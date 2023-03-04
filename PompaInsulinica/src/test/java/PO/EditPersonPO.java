package PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPersonPO extends PageObject {

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

    @FindBy(className = "btnBack")
    private WebElement btnBack;

    public EditPersonPO(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return this.title.getText();
    }

    public void insertName(String name) {
        this.name.clear();
        this.name.sendKeys(name);
    }

    public void insertSurname(String surname) {
        this.surname.clear();
        this.surname.sendKeys(surname);
    }

    public void insertEmail(String email) {
        this.email.clear();
        this.email.sendKeys(email);
    }

    public void insertUsername(String username) {
        this.username.clear();
        this.username.sendKeys(username);
    }

    public void insertPassword(String password) {
        this.password.clear();
        this.password.sendKeys(password);
    }

    public ProfilePO clickSaveEdit() {
        this.buttonSave.click();
        return new ProfilePO(driver);
    }

    public ProfilePO clickBack() {
        this.btnBack.click();
        return new ProfilePO(driver);
    }
}
