
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver driver;

    // 🔹 Locators
    private By orderConfirmationMessage = By.cssSelector("div.section.order-completed div.title strong");
    private By orderNumber = By.cssSelector("div.order-number strong");

    // 🔹 Constructor
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // 🔹 Validation
    public String getOrderConfirmationMessage() {
        return driver.findElement(orderConfirmationMessage).getText();
    }

    public String getOrderNumber() {
        return driver.findElement(orderNumber).getText();
    }
}
