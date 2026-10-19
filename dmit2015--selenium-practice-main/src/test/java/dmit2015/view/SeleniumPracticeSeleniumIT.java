package dmit2015.view;

import org.openqa.selenium.By; //we added that
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement; // we added that
import static  org.assertj.core.api.Assertions.assertThat; //we added that

//already there for use
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumPracticeSeleniumIT {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String baseUrl = "http://localhost:8080";

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void shouldOpenPracticePage() {
        // Activity 1

        //step1 : Open the web page

        driver.get(baseUrl + "/selenium-practice.xhtml");

        //setp 2: Verify page title
        assertThat(driver.getTitle()).contains("Functional UI Testing Practice");

        //step 3: find page heading

        WebElement heading = driver.findElement(By.id("practiceHeading"));

        //setp 4: verfy the heading text

        assertThat(heading.getText()).contains("Functional UI Testing Practice");

    }

    @Test
    void shouldSubmitSearchRequest() {
        // Activities 3 and 4

        //setp 1: Opent the web page

        driver.get(baseUrl + "/selenium-practice.xhtml");

        //step2: find te search field and enter text

        WebElement searchField = driver.findElement(By.id("practiceForm:searchTerm"));

        //sendkey simulates typing

        searchField.sendKeys("keyword");

        //step3: find the seach button and click it

        WebElement submitButton = driver.findElement(By.id("practiceForm:submitButton"));

        submitButton.click();

        //step4: wait for the the result to appear

        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("practiceForm:resultPanel")));

        assertThat(result.getText()).contains("Search Request Submitted").contains("keyword");

    }



    @Test
    void shouldNavigateHome() {
        // Activity 5

        //step1: Open the webpage
        driver.get(baseUrl + "/selenium-practice.xhtml");

        //step2: find the go home button
        WebElement homeButton = driver.findElement(By.id("practiceForm:goHomeButton"));

        //step 4: click the button
        homeButton.click();

        //step 5: wait unit the home page is dislplayed

        WebElement heading= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("homeHeading")));

        //setp6: verfy the home page heading

        assertThat(heading.getText()).isEqualTo("Lesson 15 Home");

    }





    @Test
    void shouldDisplayValidationMessage() {
        // Activity 6
        //step1: Open the webpage
        driver.get(baseUrl + "/selenium-practice.xhtml");

        //step2: click submit button without any value
        WebElement submitButton = driver.findElement(By.id("practiceForm:submitButton"));

        submitButton.click();

        //step 3: wait utill valdation message apears
        By messageLocator = By.id ("practiceForm:searchTermMessage");

        //different code
        wait.until(ExpectedConditions.textToBePresentInElementLocated(messageLocator, "Search term is required."));

        //step 4:
        assertThat(driver.findElement(messageLocator).getText()).contains("Search term is required.");

    }


    //has error
    @Test
    void shouldClearSearchRequest() {
        // Activity 7
        //step1: Open the webpage
        driver.get(baseUrl + "/selenium-practice.xhtml");

        //step 2: enter search term

        driver.findElement(By.id("practiceForm:searchTerm")).sendKeys("monitor");

        //set3: find submit bitton and  click it

        driver.findElement(By.id("practiceForm:submitButton")).click();

        //step4: wait for the result to apear

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("practiceForm:resultPanel")));

        //setp 5: find and click the clear button

        driver.findElement(By.id("practiceForm:clearButton")).click();

        //step6: wait till it clears data

        wait.ignoring(StaleElementReferenceException.class).until(driver->{WebElement field = driver.findElement(By.id("practiceForm:searchTerm"));

            return "".equals(field.getDomProperty("value"));


        });

        //setp7: verfy seaerch filed  is empty

        assertThat(driver.findElement(By.id("practiceForm:searchTerm")).getDomProperty("value")).isEmpty();

        //step 8: verify the result is removed

        assertThat(driver.findElement(By.id("practiceForm:resultPanel")).isEmpty();




    }

}
