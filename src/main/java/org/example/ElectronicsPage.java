package org.example;

import org.openqa.selenium.By;

public class ElectronicsPage extends Utils{
    private By _clickOnCameraAndPhoto = By.xpath("//div[@class='item-box']/div/h2/a[@title='Show products in category Camera & photo']");
    //create method for click on camera nad photo
    public void clickONCameraAndPhoto(){
        //click on cameraAndPhoto
    clickOnElement(_clickOnCameraAndPhoto);
    }
}
