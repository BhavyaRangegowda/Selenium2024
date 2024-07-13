package com.basic.links;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.sound.midi.Soundbank;
import java.util.List;

public class LinkedinFooterLinks
{
    public static void main(String args[])
    {
        WebDriver driver = new ChromeDriver();

        //maximise the window
        driver.manage().window().maximize();

        //launch the app
        driver.get("https://www.linkedin.com/");

        //get the pagetitle
        String pageTitle= driver.getTitle();
        System.out.println("Page title found is: " +pageTitle);
        List<WebElement>  linkElements = driver.findElements(By.xpath("//div[contains(@class,'w-full flex justify-end pl')]/div[1]/ul/li/a"));

        String p1= "//div[contains(@class,'w-full flex justify-end pl')]/div[1]/ul/li[";

        String p2="]/a";
        for(int i=1; i<=linkElements.size(); i++)
        {
            String linkText= driver.findElement(By.xpath(p1+i+p2)).getText();

            System.out.println("Links found in general section are: "+linkText);
            String links= driver.findElement(By.xpath(p1+i+p2)).getAttribute("href");

            System.out.println("Link name is: "+links);


        }

        /*for(WebElement ls: linkElements)
        {
            System.out.println(ls.getText());
            System.out.println(ls.getAttribute("href"));
        }*/


    }

}
