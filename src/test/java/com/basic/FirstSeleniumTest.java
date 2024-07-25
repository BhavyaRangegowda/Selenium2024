package com.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.security.Key;
import java.util.*;
public class FirstSeleniumTest {
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        System.out.println("Open Facebook Website");

        //create instance of chromeDriver class
        //interface referencevariable=new implementedclass();
        WebDriver driver = new ChromeDriver();

        //Maximize the browser
        driver.manage().window().maximize();

        //launch the url
        driver.get("https://www.facebook.com/");

        //get the title of the current page - getTitle()
        String title=driver.getTitle();
        System.out.println("Current page title is: " +title);

        //get current page absolute url
        String url= driver.getCurrentUrl();
        System.out.println("Current page url is: " +url);

        //Current page source code
        String src = driver.getPageSource();
       // System.out.println("current page source is: "+src);

        //fetch current window  id ( Interview question)
        String wid=driver.getWindowHandle();
        System.out.println("current page window id is: "+wid);

        //fetch all the opened window id's
        Set<String>handles = driver.getWindowHandles();
        System.out.println("All the window id's are: "+handles);

        if(title.contains("Facebook"))
        {
            System.out.println("Test case is Pass. Title found the word: " +title);
        }
        else
        {
            System.out.println("Test case failed. Word Maven not found. Title is:");
        }
        //find element email field
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.clear();
        //Input username
        emailInput.sendKeys("bhavya@yahoo.com");

        //find element password field
        WebElement passInput = driver.findElement(By.id("pass"));
        passInput.clear();
        //Input password
        passInput.sendKeys("bbbb");

        WebElement loginbutton = driver.findElement(By.id("loginbutton"));
        loginbutton.click();
        Thread.sleep(4000);
        //close the browser
        //driver.close();

        //quit entire webdriver. All the webdriver instances will be closed (Interview question)
        //driver.quit();
    }
}