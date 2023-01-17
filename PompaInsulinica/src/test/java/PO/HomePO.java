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
    private WebElement buttonProfilo;

    @FindBy(xpath = "//input[@value='Pompa insulinica']")
    private WebElement buttonPompainsulinica;

    @FindBy(xpath = "//input[@value='Cronologia']")
    private WebElement buttonCronologia;

    @FindBy(className = "btnLogOut")
    private WebElement buttonLogout;

    public String getTitle() {
        return this.title.getText();
    }

    public ProfiloPO clickProfilo() {
        this.buttonProfilo.click();
        return new ProfiloPO(driver);
    }

    public InserisciMisurazionePO clickPompaInsulinica() {
        this.buttonPompainsulinica.click();
        return new InserisciMisurazionePO(driver);
    }

    public CronologiaPO clickCronologia() {
        this.buttonCronologia.click();
        return new CronologiaPO(driver);
    }

    public LoginPO clickLogout() {
        this.buttonLogout.click();
        return new LoginPO(driver);
    }
}
