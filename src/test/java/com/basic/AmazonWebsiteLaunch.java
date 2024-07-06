package com.basic;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class AmazonWebsiteLaunch {
    static WebDriver driver;
    // initialize WebDriver
    public static void driverInit(WebDriver driverInstance) {
        driver = driverInstance;
        driver.manage().window().maximize();
    }
    //Launch amazon website
    public static void launchApp(String appName) {
        // Open the given URL in the browser
        driver.get(appName);
    }
    public static String verifyPageTitle(String pageTitle1)
    {
        String title = driver.getTitle();

        if(title.contains(pageTitle1))
        {
            System.out.println("Title contains the word " +pageTitle1+ ". Test case passed.");
        }
        else
        {
            System.out.println("Title does not contains the word " +pageTitle1+ ". Test case failed.");
        }
        return title;
    }
    //parametarised method to select the options from dropdown
    public static void selectDropdownOption(By loc, String optionValue)
    {
        //shortcut in intellij is type sout and press tab
        //System.out.println(loc+s);
        WebElement dropdownrefname=driver.findElement(loc);

        List<WebElement>opts = dropdownrefname.findElements(By.tagName("option"));

        //loop the options
        System.out.println("Options in the department dropdown are: ");
        for(WebElement o:opts)
        {
            //print all the options in the department dropdown
            System.out.println(o.getText());
            if(o.getText().equals(optionValue))
            {
                o.click();
                break;
            }
        }
    }
    public static void clickSearchIcon()
    {
        WebElement searchIcon= driver.findElement(By.id("nav-search-submit-button"));
        searchIcon.click();
    }
    public static String verifyPageTitle(String pageTitle2, String subpage)
    {
        String title = driver.getTitle();

        if(title.contains(pageTitle2))
        {
            System.out.println("Title contains the word " +pageTitle2+ ". Test case passed");
        }
        else
        {
            System.out.println("Title does not contains the word " +pageTitle2+ ". Test case failed");
        }
        return title;
    }
    public static void main(String[] args) {
        // Initialize WebDriver for Chrome
        WebDriver chromeDriver = new ChromeDriver();

        // Initialize WebDriver and open Chrome browser
        driverInit(chromeDriver);

        // Launch Amazon website
        launchApp("https://www.amazon.com/");

        //Verify page title
        String title1="Amazon";
        verifyPageTitle(title1);


        //Select an option form department dropdown
        selectDropdownOption(By.id("searchDropdownBox"), "Electronics");

        //click on SearchIcon
        clickSearchIcon();
        //Verify page title
        String title2="Electronics";
        verifyPageTitle(title2,"subpages");
        // Quit the WebDriver session
        //driver.quit();
    }
}