package com.basic.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class ZKDemoTest
{
    static public WebDriver driver;
    static public WebDriverWait wait;

    @BeforeClass (description = "Launch the browser and the application")
    public static void initDriver()
    {
        driver = new ChromeDriver();
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

    public static void verifyNextPage() throws InterruptedException
    {
        Assert.assertEquals(driver.getTitle(), "ZK Live Demo - Featured Demo");
        wait.until(ExpectedConditions.titleContains("ZK Live Demo"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'st-node st-others')]/ul/li[9]")));
        WebElement inputLink = driver.findElement(By.xpath("//div[contains(@class,'st-node st-others')]/ul/li[9]/a"));
        System.out.println(inputLink.getText());
        inputLink.click();
        System.out.println(driver.getTitle());
        WebElement tw=driver.findElement(By.xpath("//iframe[contains(@id,'twitter-widget-0')]"));
        driver.switchTo().frame(tw);
        System.out.println("*************");
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'widget')]/div/a")));
        WebElement xpostLink=driver.findElement(By.xpath("//div[contains(@id,'widget')]/div/a"));
        if(xpostLink!=null)
        {
            xpostLink.click();
            Thread.sleep(2000);
            System.out.println("Clickd xpost link");
        }
       else
       {
           System.out.println("Clickd xpost link not available");
       }
       handlePopUp();
    }
    public static void handlePopUp() throws InterruptedException
    {
        String mainWindowHandle = driver.getWindowHandle();
        System.out.println("Main window handle: " + mainWindowHandle);

        Set<String>handles=driver.getWindowHandles();
        Iterator it=handles.iterator();

        //fetchign main window id
        String pid=(String)it.next();
        //fetching the child window id
        String chwinid=(String)it.next();
        //switch to the child window id
        driver.switchTo().window(chwinid);
        WebElement popupElement = driver.findElement(By.xpath("//div[contains(@class,'css-175oi2r r-1wtj0ep')]"));
        //System.out.println("Found popup element: " + popupElement.getText());
        WebElement loginButton=driver.findElement(By.xpath("//button[2][contains(@role,'button')]/div/span/span/span"));
        System.out.println(loginButton.getText());
        loginButton.click();
        //System.out.println(driver.getCurrentUrl());
        WebElement closeIcon=driver.findElement(By.xpath("//button[contains(@aria-label,'Close')]/div"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@aria-label,'Close')]/div")));

        // closeIcon.click();
        WebElement userName=driver.findElement(By.xpath("//input[@autocomplete='username']"));

        Thread.sleep(2000);
        userName.sendKeys("Bhavya");


        Thread.sleep(2000);
        // Example: Finding and clicking on a span element by its text
        String nextButtonText = "Next";
        WebElement nextButton = driver.findElement(By.xpath("//span[text()='" + nextButtonText + "']"));
        nextButton.click();

        Thread.sleep(2000);
        WebElement passWord=driver.findElement(By.xpath("//input[@autocomplete='current-password']"));

        passWord.sendKeys("pass");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(@data-testid,'LoginForm_Login_Button')]")).click();

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
