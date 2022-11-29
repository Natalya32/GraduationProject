package ru.netology.diploma.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;
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

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class query {
        private String oA;
        private String oD;
        private String cA;
        private String cD;
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

    public static query getQuery() {
        String oA = "select count(o.id) from payment_entity p left join order_entity o on o.payment_id=p.transaction_id where p.status=\"APPROVED\"";
        String oD = "select count(o.id) from payment_entity p left join order_entity o on o.payment_id=p.transaction_id where p.status=\"DECLINED\"";
        String cA = "select count(o.id) from credit_request_entity c left join order_entity o on o.credit_id=c.bank_id where c.status=\"APPROVED\"";
        String cD = "select count(o.id) from credit_request_entity c left join order_entity o on o.credit_id=c.bank_id where c.status=\"DECLINED\"";
        return new query(oA, oD, cA, cD);
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

    public static long quantityRecords(String query) {
        var runner = new QueryRunner();

        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            return runner.query(conn, query, new ScalarHandler<>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
