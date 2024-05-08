package org.example;
import org.apache.commons.io.FileUtils;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Utils extends BasePage {
    public static String date()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    //method for take screenshot
    public static void takeScreenShort(String Text) {
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        //Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        File DestFile = new File("src\\ScreenShort\\" + Text + date() + ".png");
        //Copy file at destination
        try {
            FileUtils.copyFile(SrcFile,DestFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clickOnElement(By by){

        driver.findElement(by).click();
    }
    public static String getElement(By by){
        return driver.findElement(by).getText();
    }
    public static void sendKeyElement(By by,String Text){

        driver.findElement(by).sendKeys(Text);
    }
    // selectByVisibleText for day
    public static String selectByVisibleText(By by, String Text)
    {
        Select currency = new Select(driver.findElement(by));
        currency.selectByVisibleText(Text);
        return Text;
    }

}
