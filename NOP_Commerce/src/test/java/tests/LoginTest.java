package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(priority = 2)
    public void validLoginTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);

        // Updated test data
        String email = "Som06@gmail.com";
        String password = "Som@12";

        // Perform login
        loginPage.loginUser(email, password);

        // Assert logout link is visible (login successful)
        Assert.assertTrue(loginPage.isLogoutVisible(), "Login failed!");

        // Logout
        loginPage.logout();
    }
}
