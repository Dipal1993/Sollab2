package org.example;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
public class HomePage extends Utils{
    LoadPage loadPage=new LoadPage();
    Alert alert;
    //create variable for locator(_clickElectronics)
    private By _clickElectronics=By.xpath(" //div[@class='item-box']/div/h2/a[@title='Show products in category Electronics']");
    //create variable for search button locator
    private By _clickOnSearchBtn =By.xpath("//button[@type='submit']");
    //create variable for vote button locator
    private By _clickOnVoteBtn=By.xpath("//button[@id='vote-poll-1']");
    private By _facebookBtn=By.linkText("Facebook");
    private By _cookies_accept =By.xpath("//*[@id='facebook']/body/div[3]/div[1]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div[1]/div");
    private By _clickOnClose=By.xpath("//div[@aria-label='Close']");
    private By _sendKeyNike=By.xpath("//input[@placeholder='Search store']");
    private By _searchBtn=By.xpath("//button[@type='submit' and @class='button-1 search-box-button']");
    private By _productTitleText=By.xpath("//div[@class='item-box']//h2");
    private By _nopCommercenewreleaseclcik=By.linkText("nopCommerce new release!");
    private By _currency =By.xpath("//select[@id='customerCurrency']");
    private By _priceOfProduct = By.xpath("//div[@class='product-grid home-page-product-grid']/div[2]/div/div/div[2]/div[3]/div/span[@class='price actual-price']");
    String expectedOutcome="Welcome to our store";
    String expectedValue = "NopCommerce";
    public void clickOnElectronics(){
        //click on Electronics
        clickOnElement(_clickElectronics);
    }
    //directly click on search button and handle the alert on homepage
    public void handleSearchAlert(){
        //click on search button
        clickOnElement(_clickOnSearchBtn);
        //Switch to alert
        driver.switchTo().alert();
        //verify the text from search alert
        String alertMsg= driver.switchTo().alert().getText();
        System.out.println(alertMsg);
        //displaying alert message
        System.out.println(alertMsg);
        //click on ok button of alert
        driver.switchTo().alert().accept();
    }
    //Handle the alert of vote button of HomePage
    public void handleTheVoteAlert(){
        //click on vote button
        clickOnElement(_clickOnVoteBtn);
        //wait for alert is present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.alertIsPresent());
        //switch to the alert
        alert=driver.switchTo().alert();
        //verify the alert text
        String alertVoteMsg = driver.switchTo().alert().getText();
        System.out.println(alertVoteMsg);
        // click on ok button of alert
        driver.switchTo().alert().accept();
    }
    public void searchNike(){
        //enter the Nike work in searchBox
        sendKeyElement(_sendKeyNike,loadPage.getProperty("searchKeyword"));
        //click on search button on homepage
        clickOnElement(_searchBtn);
        //store the current URL value in variable
        String currentURL=driver.getCurrentUrl();
        //print the current URL value
        System.out.println("Current URL of Page = "+currentURL);
        // Verify that the current URL contains the keyword "nike"
        Assert.assertTrue(currentURL.contains(loadPage.getProperty("searchKeyword")),"Current URL not contain the Nike Work");
        //create list to store the each product title in list
        List<WebElement> productTitles = driver.findElements(_productTitleText);
        //verify number of product title in list
        System.out.println("Total Number of Products :"+productTitles.size());
        System.out.println("Name Of Products are:");
        //use foreach loop to insert product in element
        for(WebElement element:productTitles){
            String productText = element.getText();
            System.out.println(productText);
            //asser for checking theEach product contains the search keyword or not
            Assert.assertNotNull(productText.toLowerCase().contains(loadPage.getProperty("searchKeyword")), "Product title does not contain search keyword");
        }

    }
    //multi window handle functionality
    public void clickOnFacebook()  {
        //click on facebook button from footer
        clickOnElement(_facebookBtn);
        String MainWindow=driver.getWindowHandle();
        // To handle all new opened window.
        Set<String> s1=driver.getWindowHandles();
        Iterator<String> i1=s1.iterator();
        while(i1.hasNext())
        {
            String ChildWindow=i1.next();
            if(!MainWindow.equalsIgnoreCase(ChildWindow))
            {
                // Switching to Child window
                driver.switchTo().window(ChildWindow);
                // Handle cookies using the explicit wait
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                wait.until(ExpectedConditions.elementToBeClickable(_cookies_accept)).click();
                //click on close button
                clickOnElement(_clickOnClose);
                //verify the NopCommerce title
                String actualValue =getElement(By.xpath("//h1[@class='html-h1 xe8uvvx xdj266r x11i5rnm xat24cr x1mh8g0r xexx8yu x4uap5 x18d9i69 xkhd6sd x1vvkbs x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz']"));
                System.out.println("Actual value from child window :"+actualValue);
                System.out.println("Expected value from child window : "+expectedValue);
                //compare actual and expected value
               if(actualValue.equals(expectedValue)){
                   System.out.println("Your TestCase is Pass");
               }else {
                   System.out.println("your testCase is Fail");
               }
               //close the child window
                driver.close();
            }
        }// Switching to Parent window i.e Main Window.
        driver.switchTo().window(MainWindow);
        //call the getelement method and store its value in variable
        String actualWelcomeMsg= getElement(By.xpath("//h2[text()='Welcome to our store']"));
        System.out.println("Actual outcome message from Main window : "+actualWelcomeMsg);
        System.out.println("Expected outcome message from Main window : "+expectedOutcome);
        //compare expected and actual welcome message on homepage
        Assert.assertEquals(actualWelcomeMsg,expectedOutcome,"Your actual and expected are not match");
        }
         // nopCommerceNewRelease functionality
        public void nopCommerceNewRelease(){
        //click on _nopCommerce new release button
        clickOnElement(_nopCommercenewreleaseclcik);
    }
    public void currencySelectionChangeThePriceSymbol(){
        //select the uro or US Dollar Currency from dropdown
        String ActualCurrency = selectByVisibleText((_currency),loadPage.getProperty("currency"));
        // Get the list of product prices
        List<WebElement> productPriceList =driver.findElements(_priceOfProduct);
        System.out.println("Total number Of Product In List : "+productPriceList.size());

        if(ActualCurrency.equals("Euro")){
            for(WebElement productPrice :productPriceList){
                String priceWithCurrency =productPrice.getText();
                //print only price of each product
                System.out.println(priceWithCurrency);
                // Assert that the product price contains the Euro symbol
                Assert.assertTrue(priceWithCurrency.toLowerCase().contains("£"),"Test case failed: Product price does not contain Dollar symbol");
            }
        }else
           {
               // If the selected currency is US Dollar
               for (WebElement productPrice : productPriceList) {
                   String priceWithCurrency = productPrice.getText();
                   // Print only the price of each product
                   System.out.println(priceWithCurrency);
                   // Assert that the product price contains the Dollar symbol
                   Assert.assertTrue(priceWithCurrency.contains("$"), "Test case failed: Product price does not contain Dollar symbol");
               }

        }
    }
}

