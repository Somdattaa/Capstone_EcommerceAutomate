package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;

public class CheckoutTest_ extends BaseTest {

    @Test(priority = 4)
    public void testCreditCardPayment_StopAfterAddress() throws InterruptedException {
        WebDriver driver = this.driver;

        // Step 1: Login
        String email = "Som06@gmail.com";
        String password = "Som@12";
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser(email, password);
        Thread.sleep(1000);

        // Step 2: Search and Add Product
        driver.findElement(By.id("small-searchterms")).sendKeys("Apple iPhone 16 128GB");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button.button-2.product-box-add-to-cart-button")).click();
        Thread.sleep(1000);

        // Close popup if appears
        try {
            WebElement closePopup = driver.findElement(By.cssSelector("span.close"));
            if (closePopup.isDisplayed()) closePopup.click();
            Thread.sleep(500);
        } catch (Exception ignored){}

        // Step 3: Go to Cart and Checkout
        driver.findElement(By.cssSelector("a.ico-cart")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("termsofservice")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);

        // Step 4: Fill billing info
        driver.findElement(By.id("BillingNewAddress_CountryId")).sendKeys("United States of America");
        Thread.sleep(1000);
        driver.findElement(By.id("BillingNewAddress_StateProvinceId")).sendKeys("New York");
        Thread.sleep(1000);
        driver.findElement(By.id("BillingNewAddress_City")).sendKeys("New York City");
        Thread.sleep(1000);
        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("123 Test Street");
        Thread.sleep(1000);
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("10001");
        Thread.sleep(1000);
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("1234567890");
        Thread.sleep(1000);

        // Step 5: Click continue and handle alert if present
        try {
            WebElement continueButton = driver.findElement(By.cssSelector("button.new-address-next-step-button"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);
            Thread.sleep(1000);

            // Handle alert if it appears
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert appeared: " + alert.getText());
            alert.accept(); // Accept or dismiss the alert

            // Stop the test here
            System.out.println("Test stopped after handling alert post address entry.");
            return; // stop further execution
        } catch (Exception e) {
            System.out.println("No alert appeared. Test stops here after address entry.");
            return; // stop further execution
        }
    }
}
