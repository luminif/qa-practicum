package org.java.ui.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.java.ui.pages.Customers;
import org.java.ui.pages.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public abstract class BaseSeleniumTest {
    protected WebDriver driver;
    protected MainPage mainPage;
    protected Customers customers;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        mainPage = new MainPage(driver);
        customers = new Customers(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
