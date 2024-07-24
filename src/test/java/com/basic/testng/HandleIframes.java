package com.basic.testng;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class HandleIframes
{
    static WebDriver driver;
    static WebDriverWait wait;

        @BeforeClass
        public static void initWebDriver ()
        {
            driver = new FirefoxDriver();
            driver.get("https://jqueryui.com/");
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            String title = driver.getTitle();
            wait.until(ExpectedConditions.titleContains("jQuery UI"));
        }
        @Test
        public static void handleIframes () throws Exception {
            driver.findElement(By.xpath("//div[@id='sidebar']/aside[2]/ul/li[2]")).click();
            wait.until(ExpectedConditions.urlContains("/autocomplete/"));
            Assertions.assertTrue(driver.findElement(By.xpath("//iframe[@class='demo-frame']")).isDisplayed());
            WebElement iframeElement = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
            switchToIframe(iframeElement);
        }
        public static void switchToIframe(WebElement ee) throws InterruptedException
        {
            try
            {
                if (ee.isDisplayed()) {
                    driver.switchTo().frame(ee);
                    driver.findElement(By.xpath("//div[@class='ui-widget']/input")).sendKeys("Test");
                    Thread.sleep(3000);
                    String inputValue=driver.findElement(By.xpath("//div[@class='ui-widget']/input")).getAttribute("value");
                    System.out.println(inputValue);
                    switchToDefaultContent();
                }
                else
                {
                    System.out.println("Unable to find the iframe :" +ee);
                }
            }
            catch (StaleElementReferenceException elementException)
            {
                System.out.println(elementException.getStackTrace());
            }
        }
        public static void switchToDefaultContent()
        {
            driver.switchTo().defaultContent();
            String h1text=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
            Assertions.assertEquals("Autocomplete", h1text);
            System.out.println(h1text);
        }

        @AfterClass
        public static void quitDriver()
        {
            try
            {
                if (driver!=null)
                {
                    driver.quit();
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getStackTrace());
            }
        }
    }
