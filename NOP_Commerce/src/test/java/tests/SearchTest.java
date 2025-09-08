package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SearchPage;

public class SearchTest extends BaseTest {

    @Test(priority = 3)
    public void searchAndAddProductsTest() throws InterruptedException {
        // First login (depends on Register)
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("Som06@gmail.com", "Som@12");

        // Search and add products
        SearchPage searchPage = new SearchPage(driver);

        // 1. Search iPhone and add to cart
        searchPage.searchProduct("iphone");
        searchPage.clickProductLink("Apple iPhone 16 128GB");
        searchPage.addToCart();

        // 2. Search Samsung Galaxy and add to cart
        searchPage.searchProduct("samsung");
        searchPage.clickProductLink("Samsung Galaxy S24 256GB");
        searchPage.addToCart();

        // 3. Search Samsung Ultrabook and add to cart
        searchPage.searchProduct("samsung");
        searchPage.clickProductLink("Samsung Premium Ultrabook");
        searchPage.addToCart();

        // 4. Add one product to wishlist
        searchPage.searchProduct("samsung");
        searchPage.clickProductLink("Samsung Galaxy S24 256GB");
        searchPage.addToWishlist();

        // 5. Add one product to compare list
        searchPage.addToCompareList();
    }
}
