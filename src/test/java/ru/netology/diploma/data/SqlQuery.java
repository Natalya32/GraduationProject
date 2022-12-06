package ru.netology.diploma.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlQuery {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Query {
        private String orderApproved;
        private String orderDeclined;
        private String creditApproved;
        private String creditDeclined;
    }

    public static Query getQuery() {
        String orderApproved = "select count(o.id) from payment_entity p left join order_entity o on o.payment_id=p.transaction_id where p.status=\"APPROVED\"";
        String orderDeclined = "select count(o.id) from payment_entity p left join order_entity o on o.payment_id=p.transaction_id where p.status=\"DECLINED\"";
        String creditApproved = "select count(o.id) from credit_request_entity c left join order_entity o on o.credit_id=c.bank_id where c.status=\"APPROVED\"";
        String creditDeclined = "select count(o.id) from credit_request_entity c left join order_entity o on o.credit_id=c.bank_id where c.status=\"DECLINED\"";
        return new Query(orderApproved, orderDeclined, creditApproved, creditDeclined);
    }

    public static long quantityRecords(String query) {
        var runner = new QueryRunner();
        var url = System.getProperty("url");
        var username = System.getProperty("username");
        var password = System.getProperty("password");

        try (
                var conn = DriverManager.getConnection(url, username, password);
        ) {
            return runner.query(conn, query, new ScalarHandler<>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
