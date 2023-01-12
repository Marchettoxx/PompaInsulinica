package PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateAccountPO extends PageObject {

    @FindBy(tagName = "h1")
    private WebElement title;

    @FindBy(name = "nome")
    private WebElement nome;

    @FindBy(name = "cognome")
    private WebElement cognome;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(className = "btn")
    private WebElement buttonSave;
    public CreateAccountPO(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return this.title.getText();
    }

    public LoginPO saveAccount() {
        this.buttonSave.click();
        return new LoginPO(driver);
    }

    public void insertCredential(String nome, String cognome, String email, String username, String password) {
        this.nome.clear();
        this.nome.sendKeys(nome);
        this.cognome.clear();
        this.cognome.sendKeys(cognome);
        this.email.clear();
        this.email.sendKeys(email);
        this.username.clear();
        this.username.sendKeys(username);
        this.password.clear();
        this.password.sendKeys(password);
    }
}
