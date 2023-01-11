package PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPO extends PageObject{

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

    public LoginPO(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return this.title.getText();
    }

    public CreateAccountPO clickNewAccount() {
        this.buttonNewAccount.click();
        return new CreateAccountPO(driver);
    }

    public void inserCredential(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
    }

    public HomePO clickLogin() {
        this.buttonLogin.click();
        return new HomePO(driver);
    }
}
