package com.basic.testng;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.PublicKey;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class DatePickerTest
{
    static public WebDriver driver;
    static public WebDriverWait wait;

    @BeforeClass (description = "Launch the browser and the application")
    public static void initDriver()
    {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30000));
    }

    @Test
    public void launchApp() throws InterruptedException
    {
        driver.get("https://www.zkoss.org/");
        Assert.assertEquals(driver.getTitle(), "Leading Enterprise Java Web Framework | ZK");
        wait.until(ExpectedConditions.titleContains("Leading Enterprise"));
        driver.findElement(By.xpath("//div[contains(@class,'cookie-message-cnt')]/div[2]/button")).click();
        // Wait for the cookie message overlay to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("cookie-message-cnt")));
        //WebElement demoButton = driver.findElement(By.xpath("//div[@class='index-button section1-button section1-button-demo']"));
        //Actions actions = new Actions(driver);
        //actions.moveToElement(demoButton).click().perform();
        WebElement demoButton=driver.findElement(By.xpath("//div[contains(@class,'section1-button-group')]/a"));
        demoButton.click();
        verifyNextPage();
    }
    public static void verifyNextPage() throws InterruptedException {
        Assert.assertEquals(driver.getTitle(), "ZK Live Demo - Featured Demo");
        wait.until(ExpectedConditions.titleContains("ZK Live Demo"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'st-node st-others')]/ul/li[9]")));
        WebElement inputLink = driver.findElement(By.xpath("//div[contains(@class,'st-node st-others')]/ul/li[9]/a"));
        System.out.println(inputLink.getText());
        inputLink.click();
        System.out.println(driver.getTitle());
        WebElement dateLink=driver.findElement(By.xpath("//div[contains(@id,'sidebarArea')]/div[3]/div/ul/li[2]/a"));
        dateLink.click();
        verifyHeading();
    }
    public static void verifyHeading()
    {
        WebElement dateText=driver.findElement(By.xpath("//nav[contains(@id,'breadCrumb')]/a[2]"));
        System.out.println(dateText.getText());
    }
    @AfterClass
    public void tearDown()
    {
        if(driver!=null)
        {
            driver.quit();
        }
        else
        {
            System.out.println("Drive interfacte not found");
        }
    }
}
