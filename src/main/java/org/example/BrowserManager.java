package org.example;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.edge.EdgeDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
public class BrowserManager extends Utils{
    static LoadPage loadPage=new LoadPage();
    String sauceURL=loadPage.getProperty("sauceUrl");
    //public static String Browser =loadPage.getProperty("Browser");
    public static String Browser=System.getProperty("Browser");
    //if you want to run on cloud add below line
   boolean cloud =Boolean.parseBoolean(System.getProperty("cloud"));
    public void openBrowser()
            //running in cloud if cloud is true
    {
        if(cloud){
            System.out.println("running in cloud");
            if(Browser.equalsIgnoreCase("Chrome")){
                System.out.println("Browser is chrome");
                ChromeOptions browserOptions = new ChromeOptions();
                browserOptions.setPlatformName("Windows 11");
                browserOptions.setBrowserVersion("latest");
                try {
                    driver = new RemoteWebDriver(new URL(sauceURL), browserOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            } else if (Browser.equalsIgnoreCase("FireFox")) {
                System.out.println("Browser is firefox");
                FirefoxOptions browserOptions = new FirefoxOptions();
                browserOptions.setPlatformName("Windows 10");
                browserOptions.setBrowserVersion("latest");
                try {
                    driver = new RemoteWebDriver(new URL(sauceURL), browserOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            } else if (Browser.equalsIgnoreCase("Safari")) {
                System.out.println("Browser is Safari");
                SafariOptions browserOptions = new SafariOptions();
                browserOptions.setPlatformName("macOS 13");
                browserOptions.setBrowserVersion("latest");
                try {
                    driver = new RemoteWebDriver(new URL(sauceURL), browserOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            } else
            {
                //if Your Browser id Different From All If Condition
                System.out.println("Please Select Valid Browser");
            }
        }
        else{
            System.out.println("running in local");
            if(Browser.equalsIgnoreCase("Chrome")){
            driver= new ChromeDriver();
        } else if (Browser.equalsIgnoreCase("FireFox")) {
            driver=new FirefoxDriver();
        }else { //if Your Browser id Different From All If Condition
                System.out.println("Please Select Valid Browser");
            }
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
