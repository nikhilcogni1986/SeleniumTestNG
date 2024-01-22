package com.techstack.Tests;

import com.techstack.PageObjects.ProductCatalogue;
import com.techstack.TestComponents.BaseTest;
import org.junit.Assert;
import org.testng.annotations.Test;

public class ErrorValidations extends BaseTest {
    @Test(groups = {"ErrorHandling"})
    public void validateErrorLogin() {
        String emailAddress = "nikhilrao@test.com";
        ProductCatalogue PC1 = LP1.loginToApplication(emailAddress, "Password123");
        Assert.assertTrue(LP1.getErrorMessage().equalsIgnoreCase("Incorrect email or password."));
    }
}
