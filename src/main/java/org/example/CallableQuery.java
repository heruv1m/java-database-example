package org.example;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CallableQuery {
    public static void main(String[] args) throws SQLException {
//        DataSource dataSource = DataSourceUtil.getDataSource();
//        try (var con = dataSource.getConnection();
        try (var con = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/test;USER=sa;PASSWORD=");
             var stm = con.prepareCall("CALL EXAMPLE_FUNC (?);")
        ) {
            var nameVar = "ping";
            stm.setString(1, nameVar);
            try (var resultSet = stm.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1));
                }
            }
        }
    }
}
