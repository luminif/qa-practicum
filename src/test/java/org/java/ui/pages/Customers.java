package org.java.ui.pages;

import org.java.ui.core.BaseSeleniumPage;
import org.java.ui.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Customers extends BaseSeleniumPage {
    @FindBy(xpath = "//button[@ng-click='showCust()']")
    private WebElement clickCustomers;

    @FindBy(xpath = "//a[contains(text(),'First Name')]")
    private WebElement firstNameSort;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody/tr/td[1]")
    private List<WebElement> firstNames;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody/tr")
    private List<WebElement> customerRows;

    public Customers(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickCustomers() {
        clickCustomers.click();
    }

    public void sortCustomers() {
        firstNameSort.click();
        firstNameSort.click();
    }

    public void deleteCustomer() {
        Utils.deleteByAverage(getFirstNames(), customerRows);
    }

    public List<String> getFirstNames() {
        return firstNames.stream()
            .map(WebElement::getText)
            .toList();
    }
}
