package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;

import java.time.Duration;

public class OrderTest extends BaseTest {

    // 🔹 Utility method to handle alert only if present
    private void handleAlertIfPresent(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert detected: " + alert.getText());
            alert.dismiss();  // dismiss the alert instead of accepting
            Thread.sleep(500); // small wait after dismiss
            System.out.println("Alert dismissed successfully.");
        } catch (Exception ignored) {
            // No alert appeared, continue normally
        }
    }

    @Test(priority = 6)
    public void testOrderConfirmationUsingLogin() throws InterruptedException {
        WebDriver driver = this.driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // User details
        String email = "Som06@gmail.com";
        String password = "Som@12";

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser(email, password);
        Thread.sleep(1000);

        // Step 2: Search and add product to cart
        driver.findElement(By.id("small-searchterms")).sendKeys("Apple iPhone 16 128GB");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button.button-2.product-box-add-to-cart-button")).click();
        Thread.sleep(1000);

        // Optional: close any pop-up
        driver.findElement(By.cssSelector("span.close")).click();
        Thread.sleep(1000);

        // Step 3: Go to Cart
        driver.findElement(By.cssSelector("a.ico-cart")).click();
        Thread.sleep(1000);

        // Step 4: Accept terms and click Checkout
        driver.findElement(By.id("termsofservice")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);

        // Step 5: Skip billing address (already saved) and continue
        driver.findElement(By.cssSelector("button.new-address-next-step-button")).click();
        Thread.sleep(1000);

        // Step 6: Continue checkout steps
        driver.findElement(By.cssSelector("button.shipping-method-next-step-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button.payment-method-next-step-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button.payment-info-next-step-button")).click();
        Thread.sleep(1000);

        // Step 7: Try clicking Confirm Order (handle alert if appears)
        WebElement confirmBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button.confirm-order-next-step-button"))
        );

        try {
            js.executeScript("arguments[0].click();", confirmBtn);
            Thread.sleep(2000);

            // Handle alert if it appears
            handleAlertIfPresent(driver);

            // Retry Confirm Order click
            confirmBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("button.confirm-order-next-step-button"))
            );
            js.executeScript("arguments[0].click();", confirmBtn);
            Thread.sleep(2000);

        } catch (Exception e) {
            System.out.println("No alert appeared. Proceeding normally.");
        }

        // Step 8: Verify Order Confirmation
        WebElement confirmationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.section.order-completed div.title strong")));
        WebElement orderNumber = driver.findElement(By.cssSelector("div.order-number strong"));

        System.out.println("Order Confirmation Message: " + confirmationMsg.getText());
        System.out.println("Order Number: " + orderNumber.getText());

        Assert.assertTrue(confirmationMsg.getText().contains("Your order has been successfully processed"),
                "Order was not completed successfully!");
        Assert.assertTrue(orderNumber.getText() != null && !orderNumber.getText().isEmpty(),
                "Order number is not generated!");

        // Step 9: Click Continue on success page
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.order-completed-continue-button")));
        js.executeScript("arguments[0].click();", continueBtn);
        Thread.sleep(2000);

        System.out.println("Clicked Continue on order success page.");
    }
}
