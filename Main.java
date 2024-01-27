
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:/Downloads/chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 2 seconds wait

        try {
            driver.get("https://www.amazon.in/");

            //Verify landing on the correct page
            String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";;
            if (driver.getTitle().equals(expectedTitle)) {
                System.out.println("Landed on the correct page!");
            } else {
                System.out.println("Unexpected page title: " + driver.getTitle());
            }

            // Print the URL and Title of the Page
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Current Title: " + driver.getTitle());

            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));
            searchBox.sendKeys("mobile");

            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-search-submit-button")));
            searchButton.click();

            WebElement fourStarFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'p_72%3A1318476031')]")));
            fourStarFilter.click();

            WebElement price = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"p_36/1318506031\"]/span/a")));
            Thread.sleep(2000);
            price.click();

//            Thread.sleep(120000);

            WebElement selectProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/div/div/span/div/div/div/div[1]/div/div[2]/div/span/a"))); // Replace with actual ID
            String originalWindow = driver.getWindowHandle();
            selectProduct.click();

            Set<String> allWindows = driver.getWindowHandles();
            for (String windowHandle : allWindows) {
                if (!originalWindow.contentEquals(windowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

            WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"add-to-cart-button\"]")));
            addToCart.click();

//            if {
                WebElement skipExtra = wait.until(ExpectedConditions.elementToBeClickable(By.id("attachSiNoCoverage")));
                skipExtra.click();
//            }
//            else{
//                WebElement proceed = wait.until(ExpectedConditions.elementToBeClickable(By.id("//*[@id=\"attach-sidesheet-checkout-button\"]/span/input")));
//                proceed.click();
//            }

            WebElement goToCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sw-gtc\"]/span/a")));
            goToCart.click();

            Thread.sleep(30000);

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            //Close the browser
            //driver.quit();
        }

//
    }
}
