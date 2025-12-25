package ge.tbc.testautomation;
import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Map;

public class RegisteringForEventTest {
        WebDriver driver;
        WebDriverWait wait;
        @BeforeClass
        public void setUp(){
            ChromeOptions options = new ChromeOptions()
                    .setExperimentalOption(
                            "prefs",
                            Map.of(
                                    "profile.password_manager_leak_detection", false,
                                    "credentials_enable_service", false,
                                    "profile.password_manager_enabled", false
                            )
                    );
            options.addArguments("--headless", "--disable-gpu", "--no-sandbox");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get("http://34.52.231.181:4200/auth/login");
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

    @Test(description = "sign in", priority = 1)
    public void signingIn(){
        /// test data - filling form
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        emailField.sendKeys(Constants.USER_EMAIL);

        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Enter password']"));
        passwordField.sendKeys(Constants.USER_PASSWORD);

        WebElement signInButton = driver.findElement(By.xpath("//p-button[@label ='Sign In']//parent::button"));
        signInButton.click();

        //expected result - verifying we are on homepage
        wait.until(ExpectedConditions.urlToBe(Constants.HOME_PAGE_URL));
        String homePageUrl = driver.getCurrentUrl();
        Assert.assertEquals(homePageUrl, Constants.HOME_PAGE_URL);
    }

    @Test(description = "Navigating to Browse event page", priority = 2)
    public void toBrowseEventPage(){
        //// test data - event mgmt page a tag
        WebElement browseEventPage =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href ='/events']")));
        browseEventPage.click();
       //verifying we are on Browse event page
        wait.until(ExpectedConditions.urlToBe(Constants.BROWSE_EVENT_URL));
        String eventMgmtPageUrl = driver.getCurrentUrl();
        Assert.assertEquals(eventMgmtPageUrl, Constants.BROWSE_EVENT_URL);
    }

    @Test(description = "Searching for desired event", priority = 3)
    public void searchingForEvent(){
        // test data - search bar
        WebElement searchBar =  wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[placeholder*='Search']")));
        searchBar.sendKeys(Constants.EVENT_NAME);

       //expected result - desired event appears
        WebElement foundEvent =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.title-link")));
        Assert.assertEquals(foundEvent.getText(),Constants.EVENT_NAME);

    }

    @Test(description = "opening and viewing event details", priority = 4)
    public void OpeningEventDetails() {
        WebElement foundEvent =wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.title-link")));
        foundEvent.click();

        ///verifying we are Even detail page opens
        wait.until(ExpectedConditions.urlToBe(Constants.FOUND_EVENT_URL));
        String eventMgmtPageUrl = driver.getCurrentUrl();
        Assert.assertEquals(eventMgmtPageUrl, Constants.FOUND_EVENT_URL);

    }

    @Test(description = "checking available spots and capacity", priority = 5)
    public void  checkingAvailableSpots() {
        //checking capacity marker
        WebElement capacity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.percentage")));
        Assert.assertNotEquals("100% Full",capacity.getText()); //works ^ ^ checked for equal value

        //checking available spots ( x / y -> splitting and getting x)
       WebElement availability = driver.findElement(By.xpath("//span[text()='Available Spots']//following-sibling::span"));
       int availableSpots = Integer.parseInt(availability.getText().split(" ")[0]);
       Assert.assertNotEquals(0,availableSpots);

    }

    @Test(description = "choosing To Register for event", priority = 6) //tooooo many waits were needed ://///
    public void choosingToRegister() {
       WebElement registerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.register-btn")));
       registerButton.click();
       //expected result
       WebElement cancelButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.cancel-btn")));
       Assert.assertEquals(Constants.ALREADY_REGISTERED_TXT,cancelButton.getText());
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
