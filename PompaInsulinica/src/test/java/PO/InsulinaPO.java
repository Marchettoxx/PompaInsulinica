package PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InsulinaPO extends PageObject {

    @FindBy(tagName = "h1")
    private WebElement title;

    @FindBy(tagName = "h3")
    private WebElement errore;

    @FindBy(tagName = "p")
    private WebElement lastMisurazione;

    @FindBy(name = "glicemia")
    private WebElement glicemia;

    @FindBy(name = "insulina")
    private WebElement insulina;

    @FindBy(name = "commento")
    private WebElement commento;

    @FindBy(className = "btn")
    private WebElement btnSave;

    public InsulinaPO(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return this.title.getText();
    }

    public String getErrore() {
        return this.errore.getText();
    }

    public String getLastMisurazione() {
        return this.lastMisurazione.getText();
    }

    public void insertMisurazione(String glicemia, String insulina, String commento) {
        this.glicemia.clear();
        this.glicemia.sendKeys(glicemia);
        this.insulina.clear();
        this.insulina.sendKeys(insulina);
        this.commento.clear();
        this.commento.sendKeys(commento);
    }

    public InsulinaPO saveMisurazione() {
        this.btnSave.click();
        return new InsulinaPO(driver);
    }
}
