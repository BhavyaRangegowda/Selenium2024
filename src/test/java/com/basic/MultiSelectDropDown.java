package com.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import com.basic.AmazonWebsiteLaunchCaptureScreen;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration; // Import Duration from java.time package
import java.util.List;


public class MultiSelectDropDown {
    static WebDriver driver;
    static WebDriverWait wait;

    public static void main(String[] args) throws IOException
    {
        //create instance of chromeDriver class
        //interface referencevariable=new implementedclass();
        driver = new ChromeDriver();
        // Initialize WebDriverWait with timeout of 10 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://openwritings.net/sites/default/files/selenium-test-pages/select.html");
        wait.until(ExpectedConditions.titleContains("Test Page"));
        Select dropdown = new Select(driver.findElement(By.id("single-selection")));

        // Example operations on the dropdown
        // You can uncomment and use one of the following methods to interact with the dropdown

        // 1. Select by visible text
        // dropdown.selectByVisibleText("March");

        // 2. Select by value
        //dropdown.selectByValue("Mar");

        // 3. Select by index (0-based)
         dropdown.selectByIndex(1); // Selects the second option

        Select d = new Select(driver.findElement(By.id("multi-selections")));
        d.selectByIndex(1);
        d.selectByIndex(4);
        d.selectByIndex(6);
        List<WebElement> asd = d.getAllSelectedOptions();


//        for(int i=0; i<asd.size(); i++)
//        {
//            System.out.println(asd.get(i).getText());
//        }
        for (WebElement element : asd) {

            System.out.println(element.getText());
           // d.deselectAll();


        }


    }
}
