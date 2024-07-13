package com.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrokenLinkTest
{

    static WebDriver driver;  // Static WebDriver instance
    static WebDriverWait wait; //Static WebDriverWait instance

    // initialize WebDriver
    public static void driverInit(WebDriver driverInstance)
    {
        driver = driverInstance;
        driver.manage().window().maximize();
    }
    //Launch amazon website
    public static void launchApp(String appName)
    {
        // Open the given URL in the browser
        driver.get(appName);
    }
    // Method to find all the links and verify broken links
    public  static void verifyBrokenLinks() throws IOException {
        List<WebElement> listElements = driver.findElements(By.tagName("a"));
        for (WebElement element : listElements) {
            System.out.println(element.getText());
            String url = element.getAttribute("href");
            verifyLinkStatus(url);
        }
    }
    //TO verify link status
    public static void verifyLinkStatus(String linkUrl)
    {
        try
        {
            // Check if linkUrl is not null and is not empty
            if (linkUrl != null && !linkUrl.trim().isEmpty())
            {
                //creates a url object with the link url
                URL url = new URL(linkUrl);

                // Opens a connection to the URL
                HttpURLConnection hc = (HttpURLConnection) url.openConnection();
                hc.connect();
                // Retrieves the HTTP response code
                int statusCode = hc.getResponseCode();

                if (statusCode == 200)
                {
                    System.out.println("URL is working fine. Status code is: " + statusCode + " URL is: " + url);
                }
                else if (statusCode == 404)
                {
                    System.out.println("URL is not working fine. Status code is: " + statusCode + " URL is: " + url);
                }
                hc.disconnect();
            }
            else
            {
                System.out.println("Skipping empty or null URL: " + linkUrl);
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception occurred while checking the URL: " + linkUrl);
           // e.printStackTrace();
        }
    }

    public static void main(String args[]) throws IOException {
        //create instance of chromeDriver class
        //interface referencevariable=new implementedclass();
        WebDriver chromeDriver = new ChromeDriver();

        // Initialize WebDriver and open Chrome browser
        driverInit(chromeDriver);

        // Launch Amazon website
        launchApp("https://www.amazon.com/");
        verifyBrokenLinks();
    }

}
