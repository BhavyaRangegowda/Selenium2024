package com.basic;
import static com.basic.AmazonWebsiteLaunchCaptureScreen.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.List;

public class ImdbLaunchFetchTopNMovies
{
    public static void fetchAllMovies()
    {
        List<WebElement> listElements = driver.findElements(By.xpath("//ul[contains(@class,'ipc-metadata-list')]/li/div[2]/div/div/div/a"));
        //loop through the listelements
        for(WebElement ls:listElements)
        {
            System.out.println(ls.getText());
        }
    }
    //parametarised method to find top N movies
    public static void fetchTopNMovies(int numberOfMovies)
    {
        // Loop through the specified number of movies
        for (int i = 1; i <= numberOfMovies; i++)
        {
            WebElement movieElement = driver.findElement(By.xpath("//ul[contains(@class,'ipc-metadata-list')]/li[" + i + "]/div[2]/div/div/div[1]/a"));
            System.out.println("Movie name is: "+movieElement.getText());
            System.out.println("Movie link is: "+movieElement.getAttribute("href"));
        }
    }
    public static void main(String[] args) throws IOException
    {
        //create instance of chromeDriver class
        //interface referencevariable=new implementedclass();
        driver = new ChromeDriver();

        // Initialize WebDriver and open Chrome browser
        driverInit(driver);

        // Launch Amazon website
        launchApp("https://www.imdb.com/chart/top/");

        //Verify page title
        String title1 = "IMDb Top 250 Movies";
        verifyPageTitle(title1);
        //fetch all the movie names
        //fetchTopMovies();

        //fetch only N number of movies
        int num=10;
        fetchTopNMovies(num);
    }
}
