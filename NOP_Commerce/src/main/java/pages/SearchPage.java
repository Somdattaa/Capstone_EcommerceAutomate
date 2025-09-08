package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(id = "small-searchterms")
    WebElement searchBox;

    // Methods
    public void searchProduct(String productName) throws InterruptedException {
        Thread.sleep(1000);
        searchBox.clear();
        searchBox.sendKeys(productName);
        Thread.sleep(1000);
        searchBox.sendKeys(Keys.ENTER); // Press enter to search
    }

    public void clickProductLink(String partialProductName) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText(partialProductName)).click();
    }

    public void addToCart() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]")).click();
    }

    public void addToWishlist() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'Add to wishlist')]")).click();
    }

    public void addToCompareList() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'Add to compare list')]")).click();
    }
}
