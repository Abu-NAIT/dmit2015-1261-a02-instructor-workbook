package dmit2015.view;

import org.openqa.selenium.By; //we added that
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
    }

    @Test
    void shouldDisplayValidationMessage() {
        // Activity 6
    }

    @Test
    void shouldClearSearchRequest() {
        // Activity 7
    }

}
