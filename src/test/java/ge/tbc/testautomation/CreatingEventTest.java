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
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Test(description = "publishing new event (TEAM11-T4)")
public class CreatingEventTest {
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
        // test data - filling form
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        emailField.sendKeys(Constants.ADMIN_EMAIL);

        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Enter password']"));
        passwordField.sendKeys(Constants.ADMIN_PASSWORD);

        WebElement signInButton = driver.findElement(By.xpath("//p-button[@label ='Sign In']//parent::button"));
        signInButton.click();

        //expected result - verifying we are on homepage
        wait.until(ExpectedConditions.urlToBe(Constants.HOME_PAGE_URL));
        String homePageUrl = driver.getCurrentUrl();
        Assert.assertEquals(homePageUrl, Constants.HOME_PAGE_URL);
    }

    @Test(description = "navigating to event management page", priority = 2)
    public void openEventMgmtPage(){
        // test data - event mgmt page a tag
        WebElement eventMgmtPage = driver.findElement(By.xpath("//a[@href ='/event-management']"));
        eventMgmtPage.click();
        //verifying we are on event mgmt page
        wait.until(ExpectedConditions.urlToBe(Constants.EVENT_MGMT_PAGE_URL));
        String eventMgmtPageUrl = driver.getCurrentUrl();
        Assert.assertEquals(eventMgmtPageUrl, Constants.EVENT_MGMT_PAGE_URL);
    }

    @Test(description = "creating new event", priority = 3)
    public void creatingNewEvent(){
        // test data - Create New Event button
        WebElement createEventButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@label='Create Event']")));
        createEventButton.click();
        //expected result - new event creation form is displayed
        WebElement EventFormHeader=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Create New Event']")));
        Assert.assertEquals(EventFormHeader.getText(),Constants.EVENT_FORM_HEADER);
    }

    @Test(description = "entering event details", priority = 4)
    public void enteringEventDetails(){
        //test data - Basic Information
        WebElement eventTitle =driver.findElement(By.xpath("//input[@placeholder='e.g. Annual Tech Summit']"));
        eventTitle.sendKeys(Constants.EVENT_TITLE);

        //Category
        WebElement category = driver.findElement(By.xpath("//span[text() ='Select Category']"));
        category.click();
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@role='option']")));
        WebElement chosenOption = options.stream()
                .filter(option -> option.getText().contains(Constants.CHOSEN_CATEGORY))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("no such element found")
                );
        chosenOption.click();

        //description
        WebElement eventDescription=driver.findElement(By.xpath("//textarea[@formcontrolname='description']"));
        eventDescription.sendKeys(Constants.EVENT_DESCRIPTION);

        //test data - Date & Time
        WebElement startDate =driver.findElement(By.cssSelector("p-datepicker[formcontrolname='startDate'] input"));
        startDate.sendKeys(Constants.EVENT_DATE);
        WebElement endDate = driver.findElement(By.cssSelector("p-datepicker[formcontrolname='endDate'] input"));
        endDate.sendKeys(Constants.EVENT_DATE);

        WebElement startTime =driver.findElement(By.cssSelector("p-datepicker[formcontrolname='startTime'] input"));
        startTime.sendKeys(Constants.EVENT_START_TIME);
        WebElement endTime = driver.findElement(By.cssSelector("p-datepicker[formcontrolname='endTime'] input"));
        endTime.sendKeys(Constants.EVENT_END_TIME);

        //test data - Location
        WebElement venueName = driver.findElement(By.cssSelector("input[formcontrolname ='venueName']"));
        venueName.sendKeys(Constants.VENUE_NAME);

        WebElement city = driver.findElement(By.cssSelector("input[formcontrolname ='city']"));
        city.sendKeys(Constants.CITY);

        WebElement address = driver.findElement(By.cssSelector("input[formcontrolname ='address']"));
        address.sendKeys(Constants.ADDRESS);

        WebElement roomNumber = driver.findElement(By.cssSelector("input[formcontrolname ='roomNumber']"));
        roomNumber.sendKeys(Constants.ROOM_NUMBER);

        WebElement floor = driver.findElement(By.cssSelector("input[formcontrolname ='floor']"));
        floor.sendKeys(Constants.FLOOR);

        //test data - Capacity & Registration
        WebElement maxCapacity =driver.findElement(By.cssSelector("p-inputnumber[formcontrolname ='maxCapacity'] input"));
        maxCapacity.sendKeys(Constants.MAX_CAPACITY);
        WebElement minParticipants =driver.findElement(By.cssSelector("p-inputnumber[formcontrolname ='minParticipants'] input"));
        minParticipants.sendKeys(Constants.MIN_PARTICIPANTS);
    }

    @Test(description = "publishing new event ", priority = 5)
    public void PublishingNewEvent(){
        WebElement publishButton = driver.findElement(By.xpath("//button[@label ='Publish Event']"));
        publishButton.click();
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
