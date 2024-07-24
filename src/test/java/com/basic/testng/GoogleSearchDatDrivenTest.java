package com.basic.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class GoogleSearchDatDrivenTest
{
    static public WebDriver driver;
    static public WebDriverWait wait;

    @BeforeClass (description = "Launh the browser and the application")
    public static void initDriver()
    {
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.manage().window().maximize();
        wait=new WebDriverWait(driver, Duration.ofSeconds(30000));
    }
    @Test(dataProvider = "testdata")
    public static void googleSearchTest(String searchKeyword)
    {
        driver.get("https://www.google.com");
        wait.until(ExpectedConditions.titleContains("Google"));
        Assert.assertEquals(driver.getTitle(), "Google");
        WebElement searchFiled= driver.findElement(By.name("q"));
        searchFiled.sendKeys(searchKeyword);
        searchFiled.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.titleContains(searchKeyword+" - Google Search"));
    }
    @DataProvider
    public Object[][] testdata()
    {
        Object[][] data= new Object[3][1];
        data[0][0]="selenium";
        data[1][0]="playwright";
        data[2][0]="cypress";
        return data;
    }
    @AfterClass
    public void tearDown()
    {
        if(driver!=null)
        {
            driver.quit();
        }
    }
}
