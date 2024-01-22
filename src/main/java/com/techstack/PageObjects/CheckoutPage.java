package com.techstack.PageObjects;

import com.techstack.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class CheckoutPage extends AbstractComponent
{
    WebDriver driver;

    public CheckoutPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="div.form-group input")
    WebElement txtCountry;

    @FindBy(css="button span.ng-star-inserted ")
    WebElement countryGrid;

    @FindBy(css="div.user__name.mt-5 label")
    WebElement emailAddress;

    @FindBy(css=".btnn.action__submit.ng-star-inserted")
    WebElement btnPlaceOrder;

    public boolean verifyEmailAddress(String expectEmailAddr)
    {
        String actualEmailAddr = emailAddress.getText();
        System.out.println(actualEmailAddr);
        return actualEmailAddr.equalsIgnoreCase(expectEmailAddr);
    }

    public void selectCountry(String countryToBeSelected)
    {
        //Enter the country
        txtCountry.click();
        txtCountry.sendKeys(countryToBeSelected);

        waitForElementToAppear(By.cssSelector("button span.ng-star-inserted "));

        List<WebElement> countryNames = driver.findElements(By.cssSelector("button span.ng-star-inserted "));
        for(int j=0; j<countryNames.size(); j++)
        {
            String countryName = countryNames.get(j).getText().trim();
            System.out.println(countryName);
            if(countryName.equalsIgnoreCase(countryToBeSelected))
            {
                countryNames.get(j).click();
                break;
            }
        }
    }

    public OrderPage placeOrder()
    {
        btnPlaceOrder.click();
        return new OrderPage(driver);
    }
}