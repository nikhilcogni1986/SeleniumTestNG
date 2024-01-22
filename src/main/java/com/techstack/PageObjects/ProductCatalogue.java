package com.techstack.PageObjects;

import com.techstack.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent
{
    WebDriver driver;

    public ProductCatalogue(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css ="div.mb-3 .card-body b")
            List<WebElement> productList;

    @FindBy(css =".ng-animating")
    WebElement spinner;


    By products = By.cssSelector("div.mb-3 .card-body b");
    By btnAddToCart = By.xpath("//button[text()=' Add To Cart']");
    By msgToast = By.cssSelector("#toast-container");

    public List<WebElement> getProducts()
    {
        waitForElementToAppear(products);
        return productList;
    }

    public void addProductToCart(String prodName)
    {
        List<WebElement> products = getProducts();
        for(int i=0; i< products.size(); i++) {
            String productName = products.get(i).getText().trim();
            System.out.println(productName);

            if (productName.equalsIgnoreCase(prodName)) {
                products.get(i).findElement(btnAddToCart).click();
                break;
            }
        }
        waitForElementToAppear(msgToast);
        waitForElementToDisappear(spinner);
    }
}
