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

import static com.codeborne.selenide.Selenide.*;

public class OrderTest {

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
    public void shouldOrderIfValidDataCardApproved() {
        var orderPage = new OrderPage();
        orderPage
                .order()
                .validDataCardApproved(DataHelper.getValidDataCardApproved(), SqlQuery.getQuery().getOrderApproved());
    }

    @Test
    public void shouldOrderIfValidDataCardDeclined() {
        var orderPage = new OrderPage();
        orderPage
                .order()
                .validDataCardDeclined(DataHelper.getValidDataCardDeclined(), SqlQuery.getQuery().getOrderDeclined());
    }

    @Test
    public void shouldOrderIfNotValidNumberCard() {
        var orderPage = new OrderPage();
        orderPage
                .order()
                .notValidNumberCard(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldOrderIfNotValidMonthMore() {
        var orderPage = new OrderPage();
        orderPage
                .order()
                .notValidMonthMore(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldOrderIfNotValidMonth() {
        var orderPage = new OrderPage();
        orderPage
                .order()
                .notValidMonth(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldOrderIfNotValidYearLess() {
        var orderPage = new OrderPage();
        orderPage
                .order()
                .notValidYearLess(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldOrderIfCardExpired() {
        var orderPage = new OrderPage();
        orderPage
                .order()
                .CardExpired(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldOrderIfNotValidOwner() {
        var orderPage = new OrderPage();
        orderPage
                .order()
                .notValidOwner(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldOrderIfNotValidCvv() {
        var orderPage = new OrderPage();
        orderPage
                .order()
                .notValidCvv(DataHelper.getValidDataCardApproved());
    }
}
