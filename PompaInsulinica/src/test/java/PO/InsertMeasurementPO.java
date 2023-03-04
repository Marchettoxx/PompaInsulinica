package PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InsertMeasurementPO extends PageObject {

    @FindBy(tagName = "h1")
    private WebElement title;

    @FindBy(tagName = "h3")
    private WebElement error;

    @FindBy(tagName = "p")
    private WebElement lastMeasurement;

    @FindBy(name = "glicemia")
    private WebElement glycemia;

    @FindBy(name = "insulina")
    private WebElement insulin;

    @FindBy(name = "commento")
    private WebElement comment;

    @FindBy(className = "btn")
    private WebElement btnSave;

    @FindBy(className = "btnBack")
    private WebElement btnBack;

    public InsertMeasurementPO(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return this.title.getText();
    }

    public String getError() {
        return this.error.getText();
    }

    public String getLastMeasurement() {
        return this.lastMeasurement.getText();
    }

    public void insertMisurazione(String glicemia, String insulina, String commento) {
        this.glycemia.clear();
        this.glycemia.sendKeys(glicemia);
        this.insulin.clear();
        this.insulin.sendKeys(insulina);
        this.comment.clear();
        this.comment.sendKeys(commento);
    }

    public InsertMeasurementPO saveMisurazione() {
        this.btnSave.click();
        return new InsertMeasurementPO(driver);
    }

    public HomePO clickBack() {
        this.btnBack.click();
        return new HomePO(driver);
    }
}
