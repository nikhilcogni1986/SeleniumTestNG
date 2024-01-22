package com.techstack.PageObjects;

import com.techstack.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent
{
    WebDriver driver;

    public LandingPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="userEmail")
    WebElement txtUsername;

    @FindBy(id="userPassword")
    WebElement txtPassword;


    @FindBy(id="login")
    WebElement btnLogin;

    @FindBy(css="[class*='flyInOut']")
    WebElement errormessage;

    public ProductCatalogue loginToApplication(String username, String password)
    {
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
        return new ProductCatalogue(driver);
    }

    public String getErrorMessage()
    {
        waitForWebElementToAppear(errormessage);
        return errormessage.getText();
    }

    public void launchURL()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }
}