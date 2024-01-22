package com.techstack.AbstractComponents;

import com.techstack.PageObjects.CartPage;
import com.techstack.PageObjects.OrderHistoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent
{
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css="button[routerlink*='/dashboard/cart']")
    WebElement btnAddToCart;

    @FindBy(css="button[routerlink*='/myorders']")
    WebElement btnOrders;

    public AbstractComponent(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
    }

    public OrderHistoryPage clickOrders()
    {
        btnOrders.click();
        return new OrderHistoryPage(driver);
    }

    public CartPage clickCart()
    {
        btnAddToCart.click();
        return new CartPage(driver);
    }

    public void waitForElementToAppear(By locator)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForWebElementToAppear(WebElement element)
    {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToDisappear(WebElement element)
    {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
