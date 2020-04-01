package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Autocommit {
    public static void main(String[] args) throws SQLException {
        try (var con = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/test;USER=sa;PASSWORD=")) {
            try (var stm1 = con.prepareStatement("update account set name=? where id=?");
                 var stm2 = con.prepareStatement("update account set name=? where id=?")
            ) {
                con.setAutoCommit(false);
                stm1.setString(1, "noRollAuto1");
                stm1.setInt(2, 2);

                stm2.setString(1, "noAuto2");
                stm2.setInt(2, 4);

                stm1.executeUpdate();
//                if (true) {
//                    throw new RuntimeException();
//                }
                stm2.executeUpdate();

                con.commit();
            } finally {
                con.rollback();
                con.setAutoCommit(true);
            }
        }
    }
}
