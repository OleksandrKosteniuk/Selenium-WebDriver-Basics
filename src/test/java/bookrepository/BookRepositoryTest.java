package bookrepository;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.List;
import java.util.NoSuchElementException;

public class BookRepositoryTest {

    @Test
    public void bookRepositoryCompletedTest() {
        WebDriver driver = new ChromeDriver();

        Dimension dimension = new Dimension(1920, 1080);
        driver.manage().window().setSize(dimension);
        
        driver.get("https://www.bookdepository.com/");
        
        WebElement logo = waitForElementLocatedBy(driver, By.xpath("//*[@class='brand-link']"));

        Assert.assertTrue( "Logo is absent", logo.isDisplayed());
        
        WebElement signInRegister = waitForElementLocatedBy(driver, By.xpath("(//*[@href='/account/login/to/account'])[1]"));

        Assert.assertTrue("Sign in/Register element is absent", signInRegister.isDisplayed());
        
        WebElement searchInputField = waitForElementLocatedBy(driver, By.xpath("//*[@class='text-input']"));

        Assert.assertTrue("Search input field is absent", searchInputField.isDisplayed());
        
        WebElement navigationMenu = waitForElementLocatedBy(driver, By.xpath("//*[@class='secondary-header']"));

        Assert.assertTrue("Navigation menu is absent", navigationMenu.isDisplayed());

        
        searchInputField.sendKeys("The Hidden Child");
        WebElement searchBtn = waitForElementLocatedBy(driver,By.xpath("//*[@aria-label='Search']"));
        searchBtn.click();

        List<WebElement> searchResults = driver.findElements(By.xpath("//*[@class='book-item']"));

        Assert.assertTrue("Search results are empty!",searchResults.size()>0);

        WebElement productTitle = waitForElementLocatedBy(driver,By.xpath("(//*/h3[@class='title'])[1]"));

        Assert.assertTrue("Product is not found", productTitle.isDisplayed());
        
        productTitle.click();
        
        WebElement addToBasketButton = waitForElementLocatedBy(driver,By.xpath("//*[@class='btn btn-primary add-to-basket']"));
        
        addToBasketButton.click();
        
        WebElement basketCheckoutButton = waitForElementLocatedBy(driver,By.xpath("//a[contains(text(),'Basket / Checkout')]"));
        
        basketCheckoutButton.click();
                
        WebElement productLineItemPrice = waitForElementLocatedBy(driver,By.xpath("//*[@class='item-total']"));
                
        WebElement orderTotalAmount = driver.findElement(By.xpath("//*[@class='total']/dd"));
        
        Assert.assertEquals("Order total amount is incorrect",Double.parseDouble(productLineItemPrice.getText().replaceAll(" €", "").replaceAll(",","")),Double.parseDouble(orderTotalAmount.getText().replaceAll(" €", "").replaceAll(",","")),0);
        
        WebElement checkoutButton = waitForElementLocatedBy(driver,By.xpath("(//*[@class='checkout-btn btn'])[1]"));
        
        checkoutButton.click();
        
        
        //CO - Delivery Address
        
        WebElement emailInputField = waitForElementLocatedBy(driver,By.xpath("//*[@name='emailAddress']"));

        emailInputField.sendKeys("test@user.com");

        WebElement fullName = waitForElementLocatedBy(driver,By.xpath("//*[@name='delivery-fullName']"));

        fullName.sendKeys("Oleksandr Kosteniuk");

        WebElement enterAddressManuallyButton = waitForElementLocatedBy(driver,By.xpath("//*[@id='manualEntryDeliveryAddress']"));
        
        enterAddressManuallyButton.click();

        WebElement addressLine1 = waitForElementLocatedBy(driver,By.xpath("//*[@name='delivery-addressLine1']"));
        addressLine1.sendKeys("Khreschatyk");

        WebElement town = waitForElementLocatedBy(driver,By.xpath("//*[@name='delivery-city']"));
        town.sendKeys("KYIV");
                
        WebElement postcodeZIP = waitForElementLocatedBy(driver,By.xpath("//*[@name='delivery-postCode']"));
        postcodeZIP.sendKeys("04210");
        
        WebElement continueToPaymentButton = waitForElementLocatedBy(driver,By.xpath("//*[@id='hasSubmittedSameAddresses']"));
        continueToPaymentButton.click();

        WebElement productLineItemInTheSummary = waitForElementLocatedBy(driver,By.xpath("//*[@class='item-price text-right']"));
        WebElement subtotalInTheSummary = waitForElementLocatedBy(driver,By.xpath("(//*[@class='text-right'])[3]"));
        WebElement vatInTheSummary = waitForElementLocatedBy(driver,By.xpath("(//*[@class='text-right total-tax'])[2]"));
        WebElement totalInTheSummary = waitForElementLocatedBy(driver,By.xpath("(//*[@class='text-right total-price'])[2]"));
        
        Assert.assertEquals("Order total amount is incorrect",Double.parseDouble(subtotalInTheSummary.getText().replaceAll(" €", "").replaceAll(",",""))+Double.parseDouble(vatInTheSummary.getText().replaceAll(" €", "").replaceAll(",","")),Double.parseDouble(totalInTheSummary.getText().replaceAll(" €", "").replaceAll(",","")),0);
        
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(by));
    }
    
    

    
    
}

