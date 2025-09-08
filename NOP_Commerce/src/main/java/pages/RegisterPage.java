package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(linkText = "Register")
    WebElement registerLink;

    @FindBy(id = "gender-female")
    WebElement genderFemale;

    @FindBy(id = "FirstName")
    WebElement firstName;

    @FindBy(id = "LastName")
    WebElement lastName;

    @FindBy(id = "Email")
    WebElement email;

    @FindBy(id = "Password")
    WebElement password;

    @FindBy(id = "ConfirmPassword")
    WebElement confirmPassword;

    @FindBy(id = "register-button")
    WebElement registerButton;

    @FindBy(xpath = "//div[contains(text(),'Your registration completed')]")
    WebElement registrationSuccessMessage;

    // Methods
    public void clickRegisterLink() throws InterruptedException {
        Thread.sleep(1000);
        registerLink.click();
    }

    public void selectGender() throws InterruptedException {
        Thread.sleep(1000);
        genderFemale.click();
    }

    public void enterFirstName(String fName) throws InterruptedException {
        Thread.sleep(1000);
        firstName.clear();
        firstName.sendKeys(fName);
    }

    public void enterLastName(String lName) throws InterruptedException {
        Thread.sleep(1000);
        lastName.clear();
        lastName.sendKeys(lName);
    }

    public void enterEmail(String mail) throws InterruptedException {
        Thread.sleep(1000);
        email.clear();
        email.sendKeys(mail);
    }

    public void enterPassword(String pass) throws InterruptedException {
        Thread.sleep(1000);
        password.clear();
        password.sendKeys(pass);
        confirmPassword.clear();
        confirmPassword.sendKeys(pass);
    }

    public void clickRegister() throws InterruptedException {
        Thread.sleep(1000);
        registerButton.click();
    }

    public String getSuccessMessage() {
        return registrationSuccessMessage.getText();
    }

    // Full registration flow
    public void registerUser(String fName, String lName, String mail, String pass) throws InterruptedException {
        clickRegisterLink();
        selectGender();
        enterFirstName(fName);
        enterLastName(lName);
        enterEmail(mail);
        enterPassword(pass);
        clickRegister();
    }
}
