package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegisterPage;

public class RegisterTest extends BaseTest {

    @Test(priority = 1)
    public void userRegistrationTest() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver);

        // Updated test data
        String firstName = "Som";
        String lastName = "Mukh";
        String email = "Som06@gmail.com";
        String password = "Som@12";

        // Perform registration
        registerPage.registerUser(firstName, lastName, email, password);

        // Assert success message
        String expectedMsg = "Your registration completed";
        Assert.assertEquals(registerPage.getSuccessMessage(), expectedMsg, "Registration failed!");
    }
}
