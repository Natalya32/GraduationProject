package ru.netology.diploma.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.diploma.data.DataHelper;
import ru.netology.diploma.data.SqlQuery;
import ru.netology.diploma.page.OrderPage;

import static com.codeborne.selenide.Selenide.open;

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
    }

    @Test
    public void shouldCreditIfValidDataCardApproved() {
        var orderPage = new OrderPage();
        orderPage
                .credit()
                .validDataCardApproved(DataHelper.getValidDataCardApproved(), SqlQuery.getQuery().getCreditApproved());
    }

    @Test
    public void shouldCreditIfValidDataCardDeclined() {
        var orderPage = new OrderPage();
        orderPage
                .credit()
                .validDataCardDeclined(DataHelper.getValidDataCardDeclined(), SqlQuery.getQuery().getCreditDeclined());
    }

    @Test
    public void shouldCreditIfNotValidNumberCard() {
        var orderPage = new OrderPage();
        orderPage
                .credit()
                .notValidNumberCard(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldCreditIfNotValidMonthMore() {
        var orderPage = new OrderPage();
        orderPage
                .credit()
                .notValidMonthMore(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldCreditIfNotValidMonth() {
        var orderPage = new OrderPage();
        orderPage
                .credit()
                .notValidMonth(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldCreditIfNotValidYearLess() {
        var orderPage = new OrderPage();
        orderPage
                .credit()
                .notValidYearLess(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldCreditIfCardExpired() {
        var orderPage = new OrderPage();
        orderPage
                .credit()
                .CardExpired(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldCreditIfNotValidOwner() {
        var orderPage = new OrderPage();
        orderPage
                .credit()
                .notValidOwner(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldCreditIfNotValidCvv() {
        var orderPage = new OrderPage();
        orderPage
                .credit()
                .notValidCvv(DataHelper.getValidDataCardApproved());
    }
}
