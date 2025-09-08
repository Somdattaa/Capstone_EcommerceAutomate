package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void testCartFlow() throws InterruptedException {
        // 🔹 Step 1: Login
        driver.findElement(By.className("ico-login")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("Email")).sendKeys("Som06@gmail.com");
        Thread.sleep(1000);
        driver.findElement(By.id("Password")).sendKeys("Som@12");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button.login-button")).click();
        Thread.sleep(2000);

        // 🔹 Step 2: Search Product
        driver.findElement(By.id("small-searchterms")).sendKeys("Apple iPhone 16 128GB");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button.search-box-button")).click();
        Thread.sleep(2000);

        // 🔹 Step 3: Click Add to Cart
        driver.findElement(By.cssSelector("button.button-2.product-box-add-to-cart-button")).click();
        Thread.sleep(3000);

        // Close the success popup if it appears
        try {
            WebElement closeBtn = driver.findElement(By.cssSelector("span.close"));
            if (closeBtn.isDisplayed()) {
                closeBtn.click();
            }
        } catch (Exception e) {
            System.out.println("No popup appeared.");
        }
        Thread.sleep(2000);

        // 🔹 Step 4: Go to Cart
        driver.findElement(By.cssSelector("a.ico-cart")).click();
        Thread.sleep(2000);

        // 🔹 Step 5: Verify Cart Title
        String cartTitle = driver.findElement(By.cssSelector("div.page-title h1")).getText();
        Assert.assertTrue(cartTitle.contains("Shopping cart"), "Cart title mismatch!");

        // 🔹 Step 6: Verify Product Name
        String productName = driver.findElement(By.cssSelector(".product .product-name")).getText();
        Assert.assertTrue(productName.contains("Apple iPhone 16 128GB"), "Product not found in cart!");

        System.out.println("✅ Cart test completed successfully with product: " + productName);
    }
}
