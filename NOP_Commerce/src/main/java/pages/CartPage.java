package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;

    // 🔹 Locators
    private By searchBox = By.id("small-searchterms");
    private By searchButton = By.cssSelector("button.search-box-button");
    private By productLink = By.cssSelector("h2.product-title a"); // first product link from search
    private By addToCartButton = By.id("add-to-cart-button-25");   // Add to Cart button inside product page (ID changes per product)
    private By closePopup = By.cssSelector("span.close");
    private By cartLink = By.cssSelector("a.ico-cart");

    private By discountBox = By.id("discountcouponcode");
    private By voucherBox = By.id("giftcardcouponcode");
    private By applyDiscountButton = By.id("applydiscountcouponcode");
    private By applyVoucherButton = By.id("applygiftcardcouponcode");
    private By productName = By.cssSelector("table.cart tbody tr .product a");

    // 🔹 Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // 🔹 Actions
    public void addProductToCart(String product) throws InterruptedException {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchButton).click();
        Thread.sleep(1000);

        // Click first product link
        driver.findElement(productLink).click();
        Thread.sleep(1000);

        // Click Add to Cart on product page
        driver.findElement(addToCartButton).click();
        Thread.sleep(2000);

        // Close success popup if appears
        driver.findElement(closePopup).click();
    }

    public void openCart() {
        driver.findElement(cartLink).click();
    }

    public void applyDiscount(String code) {
        driver.findElement(discountBox).clear();
        driver.findElement(discountBox).sendKeys(code);
        driver.findElement(applyDiscountButton).click();
    }

    public void applyGiftVoucher(String code) {
        driver.findElement(voucherBox).clear();
        driver.findElement(voucherBox).sendKeys(code);
        driver.findElement(applyVoucherButton).click();
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }
}
