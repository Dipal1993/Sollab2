package org.example;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
    public class BaseTest extends Utils{
    //object of browser manager class
    BrowserManager browserManager=new BrowserManager();
    @BeforeMethod
    public void setup()
    { //call the open browser method
     browserManager.openBrowser();
    }

    @AfterMethod
    public void tearDown(ITestResult result)
    { if(!result.isSuccess()){
        takeScreenShort(result.getName());
    }
    //call the close browser method
     browserManager.closeBrowser();
    }
    }
