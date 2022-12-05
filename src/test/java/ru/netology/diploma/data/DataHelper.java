package ru.netology.diploma.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class DataHelper {

    private static final Faker faker = new Faker();

    private DataHelper() {
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class DataCard {

        private String numberCard;
        private String month;
        private String year;
        private String owner;
        private String cvvCode;
    }

    public static String month() {
        String month = LocalDate.now().plusMonths(3).format(DateTimeFormatter.ofPattern("MM"));
        return month;
    }

    public static String year() {
        String year = LocalDate.now().plusMonths(3).format(DateTimeFormatter.ofPattern("yy"));
        return year;
    }

    public static String owner() {
        String owner = faker.name().fullName().toUpperCase(Locale.ENGLISH);
        return owner;
    }

    public static String cvv() {
        double cvvD = (Math.random() * 1000);
        String cvv = String.valueOf(cvvD);
        return cvv;
    }

    public static DataCard getValidDataCardApproved() {
        String numberCard = "4444444444444441";
        return new DataCard(numberCard, month(), year(), owner(), cvv());
    }

    public static DataCard getValidDataCardDeclined() {
        String numberCard = "4444444444444442";
        return new DataCard(numberCard, month(), year(), owner(), cvv());
    }

    public static String yearLess() {
        String yearLess = LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
        return yearLess;
    }

    public static String expiredMonth() {
        String expiredMonth = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
        return expiredMonth;
    }

    public static String expiredYear() {
        String expiredYear = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yy"));
        return expiredYear;
    }
}
