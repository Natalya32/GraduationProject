package ru.netology.diploma.page;

import com.codeborne.selenide.SelenideElement;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import ru.netology.diploma.data.DataHelper;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class OrderPage {

    private SelenideElement numberCard = $("input[placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement month = $("input[placeholder=\"08\"]");
    private SelenideElement year = $("input[placeholder=\"22\"]");
    private SelenideElement owner = $x("//div[3]/span/span[1]/span/span/span[2]/input");
    private SelenideElement cvv = $("input[placeholder=\"999\"]");
    private SelenideElement button = $x("//div[4]/button");
    String yearLess = LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    String expiredMonth = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    String expiredYear = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yy"));

    public void validDataCardApproved(DataHelper.DataCard info) {
        numberCard.setValue(info.getNumberCard());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        owner.setValue(info.getOwner());
        cvv.setValue(info.getCvvCode());
        button.click();
        $x("//*[text()='Операция одобрена Банком.']").shouldBe(visible, Duration.ofSeconds(15));
    }

    public void validDataCardDeclined(DataHelper.DataCard info) {
        numberCard.setValue(info.getNumberCard());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        owner.setValue(info.getOwner());
        cvv.setValue(info.getCvvCode());
        button.click();
        $x("//*[text()='Ошибка! Банк отказал в проведении операции.']").shouldBe(visible, Duration.ofSeconds(15));
    }

    public void notValidNumberCard(DataHelper.DataCard info) {
        numberCard.setValue("444444444441");
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        owner.setValue(info.getOwner());
        cvv.setValue(info.getCvvCode());
        button.click();
        $x("//*[text()='Неверный формат']").shouldBe(visible);
    }
    public void notValidMonthMore(DataHelper.DataCard info) {
        numberCard.setValue(info.getNumberCard());
        month.setValue("13");
        year.setValue(info.getYear());
        owner.setValue(info.getOwner());
        cvv.setValue(info.getCvvCode());
        button.click();
        $x("//*[text()='Неверно указан срок действия карты']").shouldBe(visible);
    }

    public void notValidMonth(DataHelper.DataCard info) {
        numberCard.setValue(info.getNumberCard());
        month.setValue("8");
        year.setValue(info.getYear());
        owner.setValue(info.getOwner());
        cvv.setValue(info.getCvvCode());
        button.click();
        $x("//*[text()='Неверный формат']").shouldBe(visible);
    }

    public void notValidYear(DataHelper.DataCard info) {
        numberCard.setValue(info.getNumberCard());
        month.setValue(info.getMonth());
        year.setValue("2");
        owner.setValue(info.getOwner());
        cvv.setValue(info.getCvvCode());
        button.click();
        $x("//*[text()='Неверный формат']").shouldBe(visible);
    }

    public void notValidYearLess(DataHelper.DataCard info) {
        numberCard.setValue(info.getNumberCard());
        month.setValue(info.getMonth());
        year.setValue(yearLess);
        owner.setValue(info.getOwner());
        cvv.setValue(info.getCvvCode());
        button.click();
        $x("//*[text()='Истёк срок действия карты']").shouldBe(visible);
    }

    public void CardExpired(DataHelper.DataCard info) {
        numberCard.setValue(info.getNumberCard());
        month.setValue(expiredMonth);
        year.setValue(expiredYear);
        owner.setValue(info.getOwner());
        cvv.setValue(info.getCvvCode());
        button.click();
        $x("//*[text()='Неверно указан срок действия карты']").shouldBe(visible);
    }

    public void notValidOwner(DataHelper.DataCard info) {
        numberCard.setValue(info.getNumberCard());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        owner.setValue("Костикова-Наталья1989!'&`");
        cvv.setValue(info.getCvvCode());
        button.click();
        $x("//*[text()='Неверный формат']").shouldBe(visible);
    }

    public void notValidCvv(DataHelper.DataCard info) {
        numberCard.setValue(info.getNumberCard());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        owner.setValue(info.getOwner());
        cvv.setValue("08");
        button.click();
        $x("//*[text()='Неверный формат']").shouldBe(visible);
    }
}
