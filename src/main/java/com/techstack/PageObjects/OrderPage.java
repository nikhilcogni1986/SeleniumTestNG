package com.techstack.PageObjects;

import com.techstack.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends AbstractComponent
{
    WebDriver driver;

    public OrderPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//h1[@class='hero-primary']")
    WebElement lblOrderSummary;

    public Boolean isOrderSummaryDisplayed()
    {
        return lblOrderSummary.isDisplayed();
    }


}
