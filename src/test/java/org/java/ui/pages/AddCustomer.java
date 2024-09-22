package org.java.ui.pages;

import io.qameta.allure.Step;
import org.java.ui.core.BaseSeleniumPage;
import org.java.ui.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomer extends BaseSeleniumPage {
    @FindBy(css = "[placeholder='First Name']")
    private WebElement firstNameInput;

    @FindBy(css = "[placeholder='Last Name']")
    private WebElement lastNameInput;

    @FindBy(css = "[placeholder='Post Code']")
    private WebElement postCodeInput;

    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement addCustomer;

    public AddCustomer(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Ввод кода: {postCode}")
    private void setPostCodeInput(String postCode) {
        postCodeInput.sendKeys(postCode);
    }

    @Step("Ввод имени")
    private void setFirstNameInput(String postCode) {
        firstNameInput.sendKeys(Utils.generateFirstName(postCode));
    }

    @Step("Ввод фамилии")
    private void setLastNameInput() {
        lastNameInput.sendKeys("j");
    }

    @Step("Нажатие на кнопку 'Добавить клиента'")
    private void addCustomerClick() {
        addCustomer.click();
    }

    @Step("Подтверждение алерта")
    private void alertClick() {
        driver.switchTo().alert().accept();
    }

    @Step("Добавление клиента")
    public void addCustomer(String postCode) {
        setPostCodeInput(postCode);
        setFirstNameInput(postCode);
        setLastNameInput();
        addCustomerClick();
        alertClick();
    }
}
