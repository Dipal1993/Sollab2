package org.example;

import org.testng.annotations.Test;

public class TestSuite extends BaseTest{
    HomePage homePage=new HomePage();
    ElectronicsPage electronicsPage=new ElectronicsPage();
    CameraandPhto cameraandPhto=new CameraandPhto();
    NopcommerceNewReleasePage nopcommerceNewReleasePage=new NopcommerceNewReleasePage();
    @Test(priority=1)
    public void verifyAllProductHaveAddToCartButton(){
        //click on Electronics button
        homePage.clickOnElectronics();
        //click on camera and photo
        electronicsPage.clickONCameraAndPhoto();
        //addtocart
        cameraandPhto.addToCartButtonForEachProduct();

    }
    //Verify the  Search AlertOf HomePage
    @Test
    public void verifySearchAlertOfHomePage(){
        //call the handleSearchAlert method for handle the search alert
        homePage.handleSearchAlert();
    }
    @Test
    public void verifyTheAlertForVoteButton(){
        //call the handleTheVoteAlert method for handle the vote alert
        homePage.handleTheVoteAlert();
    }
    @Test
    public void verifyTheFooterFacebookFunctionality(){
        //click on facebook icon from te footer of homepage
        homePage.clickOnFacebook();
    }
    @Test
    public void verifySearchKeywordAvailableInURLandAllProducts(){
        //enter the nike keyword for search
        homePage.searchNike();
    }
    @Test
    public void verifyNopCommerceNewReleaseFunctionality(){
        //call the nopCommerceNewRelease method of homepage
        homePage.nopCommerceNewRelease();
        //verify the title of nopcommerce-new-release page
        nopcommerceNewReleasePage.verifyTheNopcommerceNewReleasePageTitle();
        //verify add new comment and its placement
        nopcommerceNewReleasePage.AddNewsCommentAndVerifyPlacement();
    }
    @Test
    public void VerifyProductPricesDisplaySymbolAfterCurrencySelectionFromDropDown(){
        homePage.currencySelectionChangeThePriceSymbol();
    }
}
