package PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ModificaUtentePO extends PageObject {

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

    public ModificaUtentePO(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return this.title.getText();
    }

    public void insertNome(String nome) {
        this.nome.clear();
        this.nome.sendKeys(nome);
    }

    public void insertCognome(String cognome) {
        this.cognome.clear();
        this.cognome.sendKeys(cognome);
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

    public ProfiloPO clickSaveModifica() {
        this.buttonSave.click();
        return new ProfiloPO(driver);
    }
}
