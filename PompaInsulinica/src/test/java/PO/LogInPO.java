package PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPO extends PageObject{

    @FindBy(tagName = "h1")
    private WebElement title;

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(className = "btnNewAccount")
    private WebElement buttonNewAccount;

    @FindBy(className = "btn")
    private WebElement buttonLogin;

    public LogInPO(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return this.title.getText();
    }

    public CreatePersonPO clickNewAccount() {
        this.buttonNewAccount.click();
        return new CreatePersonPO(driver);
    }

    public void insertCredential(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
    }

    public HomePO clickLogin() {
        this.buttonLogin.click();
        return new HomePO(driver);
    }
}
