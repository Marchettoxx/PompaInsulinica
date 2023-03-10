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

    @FindBy(name = "glycemia")
    private WebElement glycemia;

    @FindBy(name = "insulin")
    private WebElement insulin;

    @FindBy(name = "comment")
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

    public void insertMeasurement(String glycemia, String insulin, String comment) {
        this.glycemia.clear();
        this.glycemia.sendKeys(glycemia);
        this.insulin.clear();
        this.insulin.sendKeys(insulin);
        this.comment.clear();
        this.comment.sendKeys(comment);
    }

    public InsertMeasurementPO saveMeasurement() {
        this.btnSave.click();
        return new InsertMeasurementPO(driver);
    }

    public HomePO clickBack() {
        this.btnBack.click();
        return new HomePO(driver);
    }
}
