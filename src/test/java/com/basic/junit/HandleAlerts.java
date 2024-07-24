package com.basic.junit;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;

import java.time.Duration;

public class HandleAlerts {

    static WebDriver driver;
    static WebDriverWait wait;
    @BeforeAll
    public  static void alertManage()
    {
        driver = new FirefoxDriver();
        wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/");
        wait.until(ExpectedConditions.titleContains("Internet"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/h2")));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/div/h3")));

        driver.findElement(By.linkText("JavaScript Alerts")).click();
    }
    @BeforeEach
    public void setUp()
    {
        wait.until(ExpectedConditions.urlContains("/javascript_alerts"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='example']/h3")));
        Assertions.assertTrue(driver.findElement(By.xpath("//div[@class='example']/h3")).isDisplayed());
    }

    @Test
    public void switchToAlert()
    {
        driver.findElement(By.xpath("//div[@id='content']/div/ul/li/button")).click();
        Alert al= driver.switchTo().alert();
        System.out.println("Text in the alert box is: "+ al.getText());

        //click on ok button
        al.accept();
        verifyConfirmationMessage("You successfully clicked an alert");
    }
    @Test
    public void confirmationBox()
    {
        WebElement e= driver.findElement(By.xpath("//div[@class='example']/ul/li[2]/button"));
        e.click();
        Alert al2= driver.switchTo().alert();
        System.out.println("Text in the confirmation box is: "+ al2.getText());
        //al2.accept();
        al2.dismiss();
        verifyConfirmationMessage("You clicked: Cancel");
    }
    @Test
    public void JsPrompt() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='example']/ul/li[3]/button")).click();
        Alert al3=driver.switchTo().alert();
        System.out.println("Text in the prompt box is: "+ al3.getText());
        // verifyConfirmationMessage("");
        String sendInput="test";
        al3.sendKeys(sendInput);
        Thread.sleep(30000);
        al3.accept();
        driver.findElement(By.xpath("//p[@id='result']")).getText();
        verifyConfirmationMessage("You entered: "+sendInput);
        System.out.println("You entered: " +driver.findElement(By.id("result")).getText() + " in the promt.");
    }
    public void verifyConfirmationMessage(String suc)
    {
        Assert.assertEquals(suc, driver.findElement(By.id("result")).getText());
    }


    @AfterAll
    public static void quitDriver()
    {
        if(driver!=null) {
            driver.quit();
        }
    }



}
