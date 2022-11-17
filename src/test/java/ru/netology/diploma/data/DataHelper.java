package ru.netology.diploma.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    private static final Faker faker = new Faker();

    private DataHelper() {
    }

    public static class DataCard {

        private final String numberCard;
        private final String month;
        private final String year;
        private final String owner;
        private final String cvvCode;

        public DataCard(String numberCard, String month, String year, String owner, String cvvCode) {
            this.numberCard = numberCard;
            this.month = month;
            this.year = year;
            this.owner = owner;
            this.cvvCode = cvvCode;
        }

        public String getNumberCard() {return numberCard;}
        public String getMonth() {
            return month;
        }
        public String  getYear() {
            return year;
        }
        public String getOwner() {
            return owner;
        }
        public String getCvvCode() {
            return cvvCode;
        }
    }

    public static DataCard getValidDataCardApproved() {
        String numberCard = "4444444444444441";
        String month = LocalDate.now().plusMonths(3).format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().plusMonths(3).format(DateTimeFormatter.ofPattern("yy"));
        String owner = faker.name().fullName().toUpperCase(Locale.ENGLISH);
        double cvvD = (Math.random() * 1000);
        String cvv = String.valueOf(cvvD);
        return new DataCard(numberCard, month, year, owner, cvv);
    }

    public static DataCard getValidDataCardDeclined() {
        String numberCard = "4444444444444442";
        String month = LocalDate.now().plusMonths(3).format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().plusMonths(3).format(DateTimeFormatter.ofPattern("yy"));
        String owner = faker.name().fullName().toUpperCase(Locale.ENGLISH);
        double cvvD = (Math.random() * 1000);
        String cvv = String.valueOf(cvvD);
        return new DataCard(numberCard, month, year, owner, cvv);
    }
}
