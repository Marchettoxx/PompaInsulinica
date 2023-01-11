package PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfiloPO extends PageObject{

    @FindBy(tagName = "h1")
    private WebElement title;

    @FindBy(className = "btn")
    private WebElement buttonModificaUtente;

    public ProfiloPO(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return this.title.getText();
    }

    public ModificaUtentePO clickModificaUtente() {
        this.buttonModificaUtente.click();
        return new ModificaUtentePO(driver);
    }
}
