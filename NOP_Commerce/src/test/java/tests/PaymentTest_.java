package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import base.BaseTest;
import java.time.Duration;

public class PaymentTest_ extends BaseTest {

    // 🔹 Utility method to handle alert only if present
    private void handleAlertIfPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert detected: " + alert.getText());
            alert.dismiss();  // dismiss the alert instead of accepting
            System.out.println("Alert dismissed successfully.");
            Thread.sleep(500); // small wait after dismiss
        } catch (NoAlertPresentException e) {
            // No alert appeared, continue normally
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 4)
    public void testCheckoutFlow_StopAfterPaymentMethod() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 🔹 Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("Som06@gmail.com", "Som@12");
        Thread.sleep(1000);

        // 🔹 Step 2: Search and Add Product
        driver.findElement(By.id("small-searchterms")).sendKeys("Apple iPhone 16 128GB");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button.button-2.product-box-add-to-cart-button")).click();
        Thread.sleep(1000);

        // 🔹 Handle alert after add-to-cart
        handleAlertIfPresent();

        // Close popup if appears
        try {
            if(driver.findElement(By.cssSelector("span.close")).isDisplayed())
                driver.findElement(By.cssSelector("span.close")).click();
            Thread.sleep(500);
        } catch (Exception ignored){}

        // 🔹 Step 3: Go to Cart
        driver.findElement(By.cssSelector("a.ico-cart")).click();
        CartPage cartPage = new CartPage(driver);

        // 🔹 Step 4: Accept terms
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.checkboxclick();

        // 🔹 Step 5: Click Checkout
        js.executeScript("arguments[0].click();", driver.findElement(By.id("checkout")));
        Thread.sleep(1000);
        handleAlertIfPresent();

        // 🔹 Step 6: Continue Billing
        checkoutPage.clickContinueBilling();
        handleAlertIfPresent();

        // 🔹 Step 7: Continue Shipping
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.shipping-method-next-step-button")));
        checkoutPage.clickContinueShipping();
        handleAlertIfPresent();

        // 🔹 Step 8: Select Payment Method and Continue
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.payment-method-next-step-button")));
        checkoutPage.clickContinuePayment();
        handleAlertIfPresent();

        // 🔹 Stop the test here after selecting payment method
        System.out.println("Test stopped after selecting payment method and handling alert.");
        return;
    }
}
