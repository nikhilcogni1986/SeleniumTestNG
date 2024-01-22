package com.techstack.Tests;

import com.techstack.PageObjects.*;
import com.techstack.TestComponents.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ECommerceE2ETest extends BaseTest
{
    @Test(dataProvider = "getData")

    public void submitOrder(HashMap<String,String> data) throws IOException {
        ProductCatalogue PC1 = LP1.loginToApplication(data.get("emailAddress"), data.get("password"));

        List<WebElement> products = PC1.getProducts();
        PC1.addProductToCart(data.get("productname"));

        CartPage CP1 = PC1.clickCart();
        Assert.assertTrue(CP1.verifyProductDisplay(data.get("productname")));
        CheckoutPage CK1 = CP1.goToCheckout();

        //Validate the email address
        Assert.assertTrue(CK1.verifyEmailAddress(data.get("emailAddress")));
        CK1.selectCountry("India");

        //place order
        OrderPage OP1 = CK1.placeOrder();

        //Visit Order page
        Assert.assertTrue(OP1.isOrderSummaryDisplayed());

        //click on Order history page
        driver.findElement(By.cssSelector("label[routerlink='/dashboard/myorders']")).click();
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void validateOnOrderHistoryPage()
    {
        String emailAddress = "nikhilrao@test.com";
        ProductCatalogue PC1 = LP1.loginToApplication(emailAddress, "Password1234");
        OrderHistoryPage orderHistoryPage = PC1.clickOrders();
        Assert.assertTrue(orderHistoryPage.verifyProductIsPresent("ZARA COAT 3"));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
//        HashMap<Object,Object> map = new HashMap<>();
//        map.put("emailAddress","nikhilrao@test.com");
//        map.put("password","Password1234");
//        map.put("productName","ZARA COAT 3");

        List<HashMap<String, String>> data = getJSONData(System.getProperty("user.dir")+"//src/test//java//com//techstack//TestData//purchaseOrder.json");
        return new Object[][] {{data.get(0)}};
    }
}
