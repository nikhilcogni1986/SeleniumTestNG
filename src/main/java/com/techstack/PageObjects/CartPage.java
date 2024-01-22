package com.techstack.PageObjects;

import com.techstack.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class CartPage extends AbstractComponent
{
    WebDriver driver;

    public CartPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".cartSection h3")
    private List<WebElement> productTitles;

    @FindBy(css="li.totalRow button.btn.btn-primary")
    WebElement btnCheckout;

    public Boolean verifyProductDisplay(String productName)
    {
        boolean isFound = false;

        for(int i=0; i<productTitles.size(); i++)
        {
            if(productTitles.get(i).getText().equalsIgnoreCase(productName))
            {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    public CheckoutPage goToCheckout()
    {
        btnCheckout.click();
        return new CheckoutPage(driver);
    }

}