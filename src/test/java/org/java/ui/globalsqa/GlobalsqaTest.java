package org.java.ui.globalsqa;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.java.ui.core.BaseSeleniumTest;
import org.java.ui.pages.AddCustomer;
import org.java.ui.utils.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Клиенты")
@Epic("Работа с клиентами")
public class GlobalsqaTest extends BaseSeleniumTest {
    @Test
    @DisplayName("TC1")
    @Story("Добавление клиента")
    @Description("Добавление клиента с заданными данными и проверка его наличия в списке")
    public void addCustomer() {
        String postCode = "0734567890";
        String firstName = "hieam";
        mainPage.addCustomerClick();
        new AddCustomer(driver).addCustomer(postCode);
        customers.clickCustomers();
        assertTrue(customers.getFirstNames().contains(firstName));
    }

    @Test
    @DisplayName("TC2")
    @Story("Сортировка клиентов")
    @Description("Проверка сортировки списка клиентов по имени")
    public void sortCustomersByFirstName() {
        customers.clickCustomers();
        customers.sortCustomers();
        assertTrue(Utils.isSorted(customers.getFirstNames()));
    }

    @Test
    @DisplayName("TC3")
    @Story("Удаление клиента")
    @Description("Удаление клиента из списка")
    public void deleteCustomer() {
        customers.clickCustomers();
        List<String> expected = List.of("Hermoine", "Ron", "Albus", "Neville");
        customers.deleteCustomer();
        assertEquals(expected.toString(), customers.getFirstNames().toString());
    }
}