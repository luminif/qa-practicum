package org.java.ui.pages;

import io.qameta.allure.Step;
import org.java.ui.core.BaseSeleniumPage;
import org.java.ui.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BaseSeleniumPage {
    @FindBy(xpath = "//button[@ng-click='addCust()']")
    private WebElement addCustomer;

    public MainPage(WebDriver driver) {
        super(driver);
        driver.get(Utils.getBaseUrl());
        PageFactory.initElements(driver, this);
    }

    @Step("Клик по 'Add Customer'")
    public void addCustomerClick() {
        addCustomer.click();
    }
}