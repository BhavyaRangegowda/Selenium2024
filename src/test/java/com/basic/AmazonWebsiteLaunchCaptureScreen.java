package com.basic;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class AmazonWebsiteLaunchCaptureScreen
{
   static WebDriver driver;  // Static WebDriver instance
   static JavascriptExecutor jsx ; //Static JavascriptExecutor instance
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
    //verify the page title
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
    //parametarised method to select the options from the department dropdown
    public static void selectDropdownOption(By loc, String optionValue)
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(loc));

        //shortcut in intellij is type sout and press tab
        //find dropdownbox or select box
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
    public static String verifyHeading(String str)
    {
        WebElement e = driver.findElement(By.cssSelector("div[class='fst-h1-st pageBanner'] h1 b"));
        System.out.println("Page heading displayed as: " +e.getText());
        if(e.getText().contains(str))
        {
            System.out.println("Selected category displayed. Title matches. Pass!");
        }
        else
        {
            System.out.println("Selected category not displayed. Title not matched. Failed!");
        }
        return e.getText();
    }
    private static void captureScreenshot(String screenName) throws IOException {
        //take screenshot
        File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        // create Object for Date class
        Date d = new Date();
        screenName = screenName + "-" + d.toString().replace(":", "-").replace(" ", "-") + ".jpg";

        // copy the file name under project directory
        FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\src\\test\\com\\basic\\screenshots\\" + screenName));
    }
    public static void searchItems(String searchItemName) throws IOException {
        WebElement searchInputField=driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchInputField.sendKeys(searchItemName);
        //click on SearchIcon
        clickSearchIcon();//span[normalize-space()='No results for']
        // WebElement searchResultsNumber = driver.findElement(By.xpath("span[normalize-space()(.,\"results\")]"));
         WebElement searchResultsNumber = driver.findElement(By.xpath("//span[contains(text(),'results')]"));

        //scroll down until you find backToTop text
        jsx = (JavascriptExecutor) driver;
        jsx.executeScript("arguments[0].scrollIntoView(true);", searchResultsNumber);
        captureScreenshot("SearchItems");
    }
    public static void scrollDownPage() throws IOException
    {
        jsx = (JavascriptExecutor) driver; //typecasting driver type to JavascriptExecutor type
        // Scroll down using JavascriptExecutor
        jsx.executeScript("window.scrollBy(0,2000)","");
        captureScreenshot("ScrollDown");

        // Scroll up using JavascriptExecutor
       // jsx.executeScript("window.scrollBy(0,-1500)","");
       // captureScreenshot("ScrollUp");

        //Scroll for back to top element
        WebElement backToTop = driver.findElement(By.xpath("//span[@class='navFooterBackToTopText'][contains(.,'Back to top')]"));

        //Scroll down until you find backToTop text
        jsx.executeScript("arguments[0].scrollIntoView(true);", backToTop);
        captureScreenshot("ScrollDownForBackToTopBtn");
    }
    public static void backToTop()
    {
        WebElement backToTopLink=driver.findElement(By.xpath("//span[@class='navFooterBackToTopText']"));
        backToTopLink.click();
        driver.navigate().back();
        driver.navigate().back();
    }
    public static void main(String[] args) throws IOException
    {
        //create instance of chromeDriver class
        //interface referencevariable=new implementedclass();
        driver = new ChromeDriver();

        // Initialize WebDriver and open Chrome browser
        driverInit(driver);

        // Launch Amazon website
        launchApp("https://www.amazon.com/");

        //Verify page title
        String title1="Amazon";
        verifyPageTitle(title1);

        //Select an option form department dropdown
        String title2="Electronics";
        selectDropdownOption(By.id("searchDropdownBox"), title2);

        //click on SearchIcon
        clickSearchIcon();

        //Verify page title
        verifyPageTitle(title2,"subpages");

        //verify page heading
        verifyHeading(title2);

        //Search for specific items
        String searchItem="Iphone";
        searchItems(searchItem);

        //scroll the page down using JavascriptExecutor
        scrollDownPage();

        //click backtotop link
        backToTop();
        // Quit the WebDriver session
        //driver.quit();
    }
}