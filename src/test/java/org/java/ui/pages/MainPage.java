package org.java.ui.pages;

import org.java.ui.core.BaseSeleniumPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BaseSeleniumPage {
    @FindBy(xpath = "//button[@ng-click='addCust()']")
    private WebElement addCustomer;

    public MainPage(WebDriver driver) {
        super(driver);
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
        PageFactory.initElements(driver, this);
    }

    public void addCustomerClick() {
        addCustomer.click();
    }
}