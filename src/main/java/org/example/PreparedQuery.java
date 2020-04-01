package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;

public class PreparedQuery {
    public static void main(String[] args) throws SQLException {
        try (var con = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/test;USER=sa;PASSWORD=");
//             var stm = con.prepareStatement("select id,name from account where id=?")
             var stm = con.prepareStatement("delete account where id=?")
//             var stm = con.prepareStatement("update account set name=? where id=?")
//             var stm = con.prepareStatement("update account set name=? ")
        ) {
            var id = 1;

//            stm.setString(1, "petya");
//            stm.setInt(2, id);
            stm.setInt(1, id);

            //select
//            try (var resultSet = stm.executeQuery()) {
//                while (resultSet.next()) {
//                    System.out.println(resultSet.getString("name"));
//                }
//            }

            //update & delete

            var res = stm.executeUpdate();
            System.out.println(res);
        }
    }
}
