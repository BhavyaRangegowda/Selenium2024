package com.basic.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class HandleAccordions
{
    public static WebDriver driver;
    public static WebDriverWait wait;
    @BeforeClass
    public static void initWebDriver()
    {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(40)); // Increased timeout duration
        driver.get("https://jqueryui.com/");
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.urlContains("/jqueryui.com"));
    }
    @Test(priority = 1)
    public void handleAccordionsTest() throws InterruptedException {
        // Navigate to Accordion section
        driver.findElement(By.xpath("//a[contains(text(),'Accordion')]")).click();
        wait.until(ExpectedConditions.urlContains("/accordion/"));

        // Switch to iframe containing accordion content
        WebElement iframeElement = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(iframeElement);

        WebElement ee=driver.findElement(By.xpath("//div[@id='accordion']"));
        List<WebElement>lnks=ee.findElements(By.tagName("h3"));
        //size of the collection
        //loop through the list elements
        for(WebElement lit:lnks)
        {
            System.out.println(lit.getText());
            lit.click();
            Thread.sleep(3000);
        }

    }
    @AfterClass
    public static void tearDown()
    {
        // Quit WebDriver after all tests
        if (driver != null)
        {
            driver.quit();
        }
    }
}
