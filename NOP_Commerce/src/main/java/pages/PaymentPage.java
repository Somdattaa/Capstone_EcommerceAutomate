
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage {
    private WebDriver driver;

    // 🔹 Locators
    private By creditCardOption = By.id("paymentmethod_1");
    private By continueButton = By.cssSelector("button.payment-method-next-step-button");

    private By cardHolderName = By.id("CardholderName");
    private By cardNumber = By.id("CardNumber");
    private By expireMonth = By.id("ExpireMonth");
    private By expireYear = By.id("ExpireYear");
    private By cardCode = By.id("CardCode");
    private By paymentContinue = By.cssSelector("button.payment-info-next-step-button");

    // 🔹 Constructor
    public PaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    // 🔹 Actions
    public void selectCreditCardPayment() {
        driver.findElement(creditCardOption).click();
        driver.findElement(continueButton).click();
    }

    public void enterCardDetails(String holder, String number, String month, String year, String cvv) {
        driver.findElement(cardHolderName).sendKeys(holder);
        driver.findElement(cardNumber).sendKeys(number);
        driver.findElement(expireMonth).sendKeys(month);
        driver.findElement(expireYear).sendKeys(year);
        driver.findElement(cardCode).sendKeys(cvv);
        driver.findElement(paymentContinue).click();
    }
}
