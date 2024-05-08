package org.example;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;
public class BrowserManager extends Utils{
   static LoadPage loadPage=new LoadPage();
    public static String browserUrl =loadPage.getProperty("Browser");
    public void openBrowser()
    {
        if(browserUrl.equalsIgnoreCase("Chrome")){
            driver= new ChromeDriver();
        } else if (browserUrl.equalsIgnoreCase("FireFox")) {
            driver=new FirefoxDriver();
        }
        //maximize window
        driver.manage().window().maximize();
        //open url
        driver.get(loadPage.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    public void closeBrowser()
    {
       driver.quit();
    }
}
