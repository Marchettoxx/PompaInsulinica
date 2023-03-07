package PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePO extends PageObject {

    @FindBy(tagName = "h1")
    private WebElement title;

    public HomePO(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@value='Profilo']")
    private WebElement buttonProfile;

    @FindBy(xpath = "//input[@value='Pompa insulinica']")
    private WebElement buttonRegistration;

    @FindBy(xpath = "//input[@value='Cronologia']")
    private WebElement buttonChronology;

    @FindBy(className = "btnLogOut")
    private WebElement buttonLogOut;

    public String getTitle() {
        return this.title.getText();
    }

    public ProfilePO clickProfile() {
        this.buttonProfile.click();
        return new ProfilePO(driver);
    }

    public InsertMeasurementPO clickInsulinPump() {
        this.buttonRegistration.click();
        return new InsertMeasurementPO(driver);
    }

    public ChronologyPO clickChronology() {
        this.buttonChronology.click();
        return new ChronologyPO(driver);
    }

    public LogInPO clickLogout() {
        this.buttonLogOut.click();
        return new LogInPO(driver);
    }
}
