package com.techstack.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techstack.PageObjects.LandingPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest
{
    public WebDriver driver;
    public LandingPage LP1;
    public WebDriver initializeDriver() throws IOException
    {
        Properties prop = new Properties();
        String rootDir = System.getProperty("user.dir");
        System.out.println(rootDir);
        FileInputStream fis = new FileInputStream(rootDir+"\\src\\main\\java\\com\\techstack\\Resources\\global.properties");
        prop.load(fis);

        switch (prop.getProperty("browser"))
        {
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("No Browser name provided");
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }

    public List<HashMap<String, String>> getJSONData(String filePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//java//com//techstack//TestData//PurchaseOrder.json"),
                StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = ((TakesScreenshot) this.driver);
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir")+"\\Reports\\"+testCaseName+".png");
        FileUtils.copyFile(src, dest);
        return System.getProperty("user.dir")+"\\Reports\\"+testCaseName+".png";
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApp() throws IOException
    {
        WebDriver driver = initializeDriver();
        LP1 = new LandingPage(driver);
        LP1.launchURL();
        return new LandingPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        driver.close();
    }
}