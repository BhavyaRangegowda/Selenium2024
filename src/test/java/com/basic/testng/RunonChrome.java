package com.basic.testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class RunonChrome {

    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeClass
    public void setUp() {

        // Initialize ChromeDriver instance
        driver = new ChromeDriver();

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Maximize browser window
        driver.manage().window().maximize();
    }

    @Test
    public void testFacebook() {
        // Navigate to Facebook
        driver.get("https://www.facebook.com/");

    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
