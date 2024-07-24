package com.basic.testng;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class HandleFrames {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public static void initWebDriver() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        wait.until(ExpectedConditions.urlContains("/nested_frames"));
    }

    @Test(priority = 1)
    public void switchToMiddleFrame() {
        // Switch to frame-top
        driver.switchTo().frame(driver.findElement(By.name("frame-top")));

        // Switch to frame-middle inside frame-top
        driver.switchTo().frame(driver.findElement(By.name("frame-middle")));

        // Verify text in frame-middle
        String txt = driver.findElement(By.id("content")).getText().trim();
        Assertions.assertEquals(txt, "MIDDLE");
    }

    @Test(priority = 2)
    public void switchToBottomFrame() {
        // Switch back to default content first
        driver.switchTo().defaultContent();

        // Switch to frame-bottom
        driver.switchTo().frame(driver.findElement(By.name("frame-bottom")));

        // Verify text in frame-bottom
        String bottomFrameText = driver.findElement(By.tagName("body")).getText().trim();
        System.out.println(bottomFrameText);
    }

    @AfterClass
    public static void tearDown() {
        // Quit WebDriver after all tests
        if (driver != null) {
            driver.quit();
        }
    }
}
