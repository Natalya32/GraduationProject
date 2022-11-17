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
        $x("//button[1]").click();
    }

    @Test
    public void shouldOrderIfValidDataCardApproved() {
        var orderPage = new OrderPage();
        orderPage
                .validDataCardApproved(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldOrderIfValidDataCardDeclined() {
        var orderPage = new OrderPage();
        orderPage
                .validDataCardDeclined(DataHelper.getValidDataCardDeclined());
    }

    @Test
    public void shouldOrderIfNotValidNumberCard() {
        var orderPage = new OrderPage();
        orderPage
                .notValidNumberCard(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldOrderIfNotValidMonthMore() {
        var orderPage = new OrderPage();
        orderPage
                .notValidMonthMore(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldOrderIfNotValidMonth() {
        var orderPage = new OrderPage();
        orderPage
                .notValidMonth(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldOrderIfNotValidYearLess() {
        var orderPage = new OrderPage();
        orderPage
                .notValidYearLess(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldOrderIfCardExpired() {
        var orderPage = new OrderPage();
        orderPage
                .CardExpired(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldOrderIfNotValidOwner() {
        var orderPage = new OrderPage();
        orderPage
                .notValidOwner(DataHelper.getValidDataCardApproved());
    }

    @Test
    public void shouldOrderIfNotValidCvv() {
        var orderPage = new OrderPage();
        orderPage
                .notValidCvv(DataHelper.getValidDataCardApproved());
    }
}
