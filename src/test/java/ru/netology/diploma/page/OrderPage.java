package ru.netology.diploma.page;

import static com.codeborne.selenide.Selenide.$x;

public class OrderPage {

    public FormPage order() {
        $x("//button[1]").click();
        return new FormPage();
    }

    public FormPage credit() {
        $x("//button[2]").click();
        return new FormPage();
    }
}
