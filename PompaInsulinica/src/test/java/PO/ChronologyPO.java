package PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ChronologyPO extends PageObject {

    @FindBy(tagName = "h1")
    private WebElement title;

    @FindBy(xpath = "//td[1]")
    private List<WebElement> cell1;

    @FindBy(xpath = "//td[2]")
    private List<WebElement> cell2;

    @FindBy(xpath = "//td[4]")
    private List<WebElement> cell4;

    @FindBy(xpath = "//input[@value='cancella']")
    private WebElement buttonDeleteMeasurement;

    @FindBy(xpath = "//input[@value='cancella tutto']")
    private WebElement buttonDeleteChronology;

    @FindBy(className = "btnBack")
    private WebElement btnBack;

    public ChronologyPO(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return this.title.getText();
    }

    public String getCell1String() {
        return this.cell1.get(0).getText();
    }

    public String getCell2String() {
        return this.cell2.get(0).getText();
    }

    public String getCell4String() {
        return this.cell4.get(0).getText();
    }

    public int getCell1() {
        return this.cell1.size();
    }

    public int getCell2() {
        return this.cell2.size();
    }

    public int getCell4() {
        return this.cell4.size();
    }

    public ChronologyPO clickDeleteMeasurement() {
        this.buttonDeleteMeasurement.click();
        return new ChronologyPO(driver);
    }

    public ChronologyPO clickDeleteChronology() {
        this.buttonDeleteChronology.click();
        return new ChronologyPO(driver);
    }

    public HomePO clickBack() {
        this.btnBack.click();
        return new HomePO(driver);
    }
}
