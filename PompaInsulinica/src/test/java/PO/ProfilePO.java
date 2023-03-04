package PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePO extends PageObject{

    @FindBy(tagName = "h1")
    private WebElement title;

    @FindBy(className = "btn")
    private WebElement buttonEditProfile;

    @FindBy(className = "btnLogOut")
    private WebElement buttonDeleteAccount;

    public ProfilePO(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return this.title.getText();
    }

    public EditPersonPO clickModificaUtente() {
        this.buttonEditProfile.click();
        return new EditPersonPO(driver);
    }

    public LogInPO clickDeleteAccount() {
        this.buttonDeleteAccount.click();
        return new LogInPO(driver);
    }
}
