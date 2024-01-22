package com.techstack.PageObjects;

import com.techstack.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderHistoryPage extends AbstractComponent
{
    WebDriver driver;

    @FindBy(css ="tbody tr td:nth-child(3)")
    List<WebElement> productNames;

    public OrderHistoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean verifyProductIsPresent(String productName)
    {
        boolean isFound=false;
        for(int i=0; i<productNames.size(); i++)
        {
            String productTitle = productNames.get(i).getText();
            if(productName.equalsIgnoreCase(productTitle)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

}
