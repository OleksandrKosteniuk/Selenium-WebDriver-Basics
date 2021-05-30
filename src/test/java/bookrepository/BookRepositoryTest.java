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
        
        WebElement signInRegister = waitForElementLocatedBy(driver, By.xpath("/html/body/div[4]/div[1]/div/ul[2]/li[3]/a"));

        Assert.assertTrue("Sign in/Register element is absent", signInRegister.isDisplayed());
        
        WebElement searchInputField = waitForElementLocatedBy(driver, By.xpath("//*[@class='text-input']"));

        Assert.assertTrue("Search input field is absent", searchInputField.isDisplayed());
        
        WebElement navigationMenu = waitForElementLocatedBy(driver, By.xpath("//*[@class='secondary-header']"));

        Assert.assertTrue("Navigation menu is absent", navigationMenu.isDisplayed());

        
        searchInputField.sendKeys("The Hidden Child");
        WebElement searchBtn = driver.findElement(By.xpath("//*[@aria-label='Search']"));
        searchBtn.click();

        List<WebElement> searchResults = driver.findElements(By.xpath("//*[@class='book-item']"));

        Assert.assertTrue("Search results are empty!",searchResults.size()>0);

        List<WebElement> addToBasketBtn = driver.findElements(By.xpath("//*[contains(text(), 'Add to basket')]"));
        
        addToBasketBtn.get(0).click();
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        
        }

        WebElement addToBasketConfirmationPopup = waitForElementLocatedBy(driver, By.xpath("//*[@class='modal-content']"));

        Assert.assertTrue("Item is not added to Cart", addToBasketConfirmationPopup.isDisplayed());
        

        driver.quit();
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(by));
    }
    
    

    
    
}

