package org.java.ui.pages;

import io.qameta.allure.Step;
import org.java.ui.core.BaseSeleniumPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MainPage extends BaseSeleniumPage {
    @FindBy(xpath = "//button[@ng-click='addCust()']")
    private WebElement addCustomer;

    public MainPage(WebDriver driver) throws IOException {
        super(driver);
        Properties props = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream("application.properties");
        props.load(is);
        String baseUrl = props.getProperty("base-url");
        driver.get(baseUrl);
        PageFactory.initElements(driver, this);
    }

    @Step("Клик по 'Add Customer'")
    public void addCustomerClick() {
        addCustomer.click();
    }
}