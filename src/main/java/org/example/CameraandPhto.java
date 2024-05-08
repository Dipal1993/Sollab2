package org.example;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

        public class CameraandPhto extends Utils {
        //create method to check each product have Add to cart button
        public void addToCartButtonForEachProduct() {
        //creating array list for products
        List<WebElement> products = driver.findElements(By.xpath("//div[@class='item-grid']/div/div[@class='product-item']"));
        //count the total number of products
        System.out.println("Total number of products: " + products.size());
        //create list for total number of add to cart button
        List<WebElement> addToCartButtons = driver.findElements(By.xpath(".//button[contains(text(), 'Add to cart')]"));
        //total number of add to cart buttons
        System.out.println("Total Number of Add :" + addToCartButtons.size());
        //compare the count of add to cart button and product
        if (products.size() != addToCartButtons.size()) {
            //finding the missing number of product
            int missingProducts = Math.abs(products.size() - addToCartButtons.size());
            System.out.println("Number of missing products: " + missingProducts);
            // Fail the test case with an assertion error
            Assert.fail("Number of 'Add to cart' buttons does not match the number of products.");
        } else {
            System.out.println("Each product has an 'Add to cart' button.");
        }
    }
}













