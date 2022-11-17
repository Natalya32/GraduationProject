package ru.netology.diploma.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.diploma.data.DataHelper;
import ru.netology.diploma.page.OrderPage;

import static com.codeborne.selenide.Selenide.*;

public class CreditTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void openBrowser() {
        open("http://localhost:8080");
        $x("//button[2]").click();
    }

    @Test
    public void shouldCreditIfValidDataCardApproved() {
        var orderPage = new OrderPage();
        orderPage
                .validDataCardApproved(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldCreditIfValidDataCardDeclined() {
        var orderPage = new OrderPage();
        orderPage
                .validDataCardDeclined(DataHelper.getValidDataCardDeclined());
    }

    @Test
    public void shouldCreditIfNotValidNumberCard() {
        var orderPage = new OrderPage();
        orderPage
                .notValidNumberCard(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldCreditIfNotValidMonthMore() {
        var orderPage = new OrderPage();
        orderPage
                .notValidMonthMore(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldCreditIfNotValidMonth() {
        var orderPage = new OrderPage();
        orderPage
                .notValidMonth(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldCreditIfNotValidYearLess() {
        var orderPage = new OrderPage();
        orderPage
                .notValidYearLess(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldCreditIfCardExpired() {
        var orderPage = new OrderPage();
        orderPage
                .CardExpired(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldCreditIfNotValidOwner() {
        var orderPage = new OrderPage();
        orderPage
                .notValidOwner(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldCreditIfNotValidCvv() {
        var orderPage = new OrderPage();
        orderPage
                .notValidCvv(DataHelper.getValidDataCardApproved());
    }
}
