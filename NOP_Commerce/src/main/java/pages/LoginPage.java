package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(linkText = "Log in")
    WebElement loginLink;

    @FindBy(id = "Email")
    WebElement emailField;

    @FindBy(id = "Password")
    WebElement passwordField;

    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    WebElement loginButton;

    @FindBy(linkText = "Log out")
    WebElement logoutLink;

    // Methods
    public void clickLoginLink() throws InterruptedException {
        Thread.sleep(1000);
        loginLink.click();
    }

    public void enterEmail(String email) throws InterruptedException {
        Thread.sleep(1000);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String pass) throws InterruptedException {
        Thread.sleep(1000);
        passwordField.clear();
        passwordField.sendKeys(pass);
    }

    public void clickLoginButton() throws InterruptedException {
        Thread.sleep(1000);
        loginButton.click();
    }

    public void logout() throws InterruptedException {
        Thread.sleep(1000);
        logoutLink.click();
    }

    // Full login flow
    public void loginUser(String email, String password) throws InterruptedException {
        clickLoginLink();
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    // Verify if logout link is displayed
    public boolean isLogoutVisible() {
        return logoutLink.isDisplayed();
    }
}
