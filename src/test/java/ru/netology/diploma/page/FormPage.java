package ru.netology.diploma.page;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import ru.netology.diploma.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class FormPage {

    private SelenideElement numberCardField = $("input[placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement monthField = $("input[placeholder=\"08\"]");
    private SelenideElement yearField = $("input[placeholder=\"22\"]");
    private SelenideElement ownerField = $x("//div[3]/span/span[1]/span/span/span[2]/input");
    private SelenideElement cvvField = $("input[placeholder=\"999\"]");
    private SelenideElement button = $x("//div[4]/button");

    public void fillingForm(String number, String month, String year, String owner, String cvv) {
        numberCardField.setValue(number);
        monthField.setValue(month);
        yearField.setValue(year);
        ownerField.setValue(owner);
        cvvField.setValue(cvv);
        button.click();
    }

    public void validDataCardApproved(DataHelper.DataCard info, String query) {
        long preQuantity = DataHelper.quantityRecords(query);
        fillingForm(info.getNumberCard(), info.getMonth(), info.getYear(), info.getOwner(), info.getCvvCode());
        $x("//*[text()='Операция одобрена Банком.']").shouldBe(visible, Duration.ofSeconds(15));
        long postQuantity = DataHelper.quantityRecords(query);
        long expected = 1;
        long actual = postQuantity - preQuantity;
        Assertions.assertEquals(expected, actual);
    }

    public void validDataCardDeclined(DataHelper.DataCard info, String query) {
        long preQuantity = DataHelper.quantityRecords(query);
        fillingForm(info.getNumberCard(), info.getMonth(), info.getYear(), info.getOwner(), info.getCvvCode());
        $x("//*[text()='Ошибка! Банк отказал в проведении операции.']").shouldBe(visible, Duration.ofSeconds(15));
        long postQuantity = DataHelper.quantityRecords(query);
        Assertions.assertEquals(preQuantity, postQuantity);
    }

    public void notValidNumberCard(DataHelper.DataCard info) {
        fillingForm("444444444441", info.getMonth(), info.getYear(), info.getOwner(), info.getCvvCode());
        $x("//*[text()='Неверный формат']").shouldBe(visible);
    }

    public void notValidMonthMore(DataHelper.DataCard info) {
        fillingForm(info.getNumberCard(), "13", info.getYear(), info.getOwner(), info.getCvvCode());
        $x("//*[text()='Неверно указан срок действия карты']").shouldBe(visible);
    }

    public void notValidMonth(DataHelper.DataCard info) {
        fillingForm(info.getNumberCard(), "8", info.getYear(), info.getOwner(), info.getCvvCode());
        $x("//*[text()='Неверный формат']").shouldBe(visible);
    }

    public void notValidYear(DataHelper.DataCard info) {
        fillingForm(info.getNumberCard(), info.getMonth(), "2", info.getOwner(), info.getCvvCode());
        $x("//*[text()='Неверный формат']").shouldBe(visible);
    }

    public void notValidYearLess(DataHelper.DataCard info) {
        fillingForm(info.getNumberCard(), info.getMonth(), DataHelper.yearLess(), info.getOwner(), info.getCvvCode());
        $x("//*[text()='Истёк срок действия карты']").shouldBe(visible);
    }

    public void CardExpired(DataHelper.DataCard info) {
        fillingForm(info.getNumberCard(), DataHelper.expiredMonth(), DataHelper.expiredYear(), info.getOwner(), info.getCvvCode());
        $x("//*[text()='Неверно указан срок действия карты']").shouldBe(visible);
    }

    public void notValidOwner(DataHelper.DataCard info) {
        fillingForm(info.getNumberCard(), info.getMonth(), info.getYear(), "Костикова-Наталья1989!'&`", info.getCvvCode());
        $x("//*[text()='Неверный формат']").shouldBe(visible);
    }

    public void notValidCvv(DataHelper.DataCard info) {
        fillingForm(info.getNumberCard(), info.getMonth(), info.getYear(), info.getOwner(), "08");
        $x("//*[text()='Неверный формат']").shouldBe(visible);
    }
}
