package seleniumwebdriver;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomeTask {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        Dimension dimension = new Dimension(1920, 1080);
        driver.manage().window().setSize(dimension);

        driver.get("https://www.bookdepository.com/");

        WebElement logo = waitForElementLocatedBy(driver, By.xpath("//*[@class='brand-link']"));

        WebElement signInRegister = waitForElementLocatedBy(driver, By.xpath("/html/body/div[4]/div[1]/div/ul[2]/li[3]/a"));
        
        WebElement searchInputField = waitForElementLocatedBy(driver, By.xpath("//*[@class='text-input']"));
        
        WebElement navigationMenu = waitForElementLocatedBy(driver, By.xpath("//*[@class='secondary-header']"));
        
        searchInputField.sendKeys("The Hidden Child");
        WebElement searchBtn = driver.findElement(By.xpath("//*[@aria-label='Search']"));
        searchBtn.click();

        List<WebElement> searchResults = driver.findElements(By.xpath("//*[@class='book-item']"));

        List<WebElement> addToBasketBtn = driver.findElements(By.xpath("//*[contains(text(), 'Add to basket')]"));

        addToBasketBtn.get(0).click();

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
