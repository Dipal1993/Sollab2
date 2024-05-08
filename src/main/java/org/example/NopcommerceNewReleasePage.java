package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;
public class NopcommerceNewReleasePage extends Utils {
    //crate object of load page to read the data from dataconfigfile
    LoadPage loadPage = new LoadPage();
    //crate variable to store the locator for NopcommerceNewReleasePageTitle
    private By _pageTitle = By.xpath("//div[@class='page-title']//h1[text()='nopCommerce new release!']");
    private By _NewCommentTitle = By.xpath("//input[@id='AddNewComment_CommentTitle']");
    private By _NewComment = By.xpath("//textarea[@id ='AddNewComment_CommentText']");
    private By _newCommentBtn = By.xpath("//button[@name='add-comment']");
    private By _actualSuccessMsg = By.xpath("//div[text()='News comment is successfully added.']");
    private By _Commentbox = By.xpath("//div[@class='comment-list']/div[2]/div/div[2]");

    //method for verify the title of page
    public void verifyTheNopcommerceNewReleasePageTitle() {
        //call the method to gettext using locator and store in variable
        String ActualTitle = getElement(_pageTitle);
        //print the value of Actual Title
        System.out.println("Actual Title :" + ActualTitle);
        //print the value of Expected title
        System.out.println("Expected Title: " + loadPage.getProperty("ExpectedTitleOfNopCommerceNewReleasePage"));
        System.out.println("=-------------------------------------------------=");
        //compare the actual and expected title
        Assert.assertEquals(ActualTitle, loadPage.getProperty("ExpectedTitleOfNopCommerceNewReleasePage"), "Your Page title is not NopCommerce new release!");
    }
    //method for add news comment and verify its placement
    public void AddNewsCommentAndVerifyPlacement() {
        //call the method for sending the title
        sendKeyElement(_NewCommentTitle, loadPage.getProperty("commentTitle"));
        //call the method for sending the comment
        sendKeyElement(_NewComment, loadPage.getProperty("commentForLeaveYourComment"));
        //click on New Comment button
        clickOnElement(_newCommentBtn);
        //store the "News comment is successfully added."
        String ActualSuccessMsg = getElement(_actualSuccessMsg);
        //print the actual success message
        System.out.println("Actual Success Message: " + ActualSuccessMsg);
        //store the expected success message in variable which is get from config file using loadpage object
        String ExpectedSuccessMsg = loadPage.getProperty("ExpectedSuccessMsg");
        //print the Expected  success message
        System.out.println("Expected Success Message: " + ExpectedSuccessMsg);
        System.out.println("=-------------------------------------------------=");
        //compare the actual and expected result
        Assert.assertEquals(ActualSuccessMsg, ExpectedSuccessMsg, "News comment isn't added.");
        //Wait for reload the page after add the comment
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        //crate list to find total number of comments in comment box
        List<WebElement> comments = driver.findElements(_Commentbox);
        System.out.println("Total Number of Comments in Comment Box: " + comments.size());
        // Get the text of the last comment
        WebElement lastCommentElement = comments.get(comments.size() - 1);
        String lastCommentText = lastCommentElement.getText();
        System.out.println(lastCommentText);
        System.out.println("=-------------------------------------------------=");
        // Compare the last comment text with your newly added comment text
        Assert.assertTrue(lastCommentText.contains(loadPage.getProperty("commentTitle")) && lastCommentText.contains(loadPage.getProperty("commentForLeaveYourComment")), "Last comment in the comment box does not match the newly added comment.");
    }

}

