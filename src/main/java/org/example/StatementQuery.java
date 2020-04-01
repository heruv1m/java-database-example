package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;

public class StatementQuery {
    public static void main(String[] args) throws SQLException {
        try (var con = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/test;USER=sa;PASSWORD=");
             var stm = con.createStatement()
        ) {
            //create
//            stm.execute("CREATE TABLE account(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), type INT);");
            //insert. Не делаю инсерт id потому что авоинкеремент
            stm.execute("insert into account (name, type) values ('22222', 1)");

            //select
//            try (var resultSet = stm.executeQuery("select id,name from account where id=" + 1)) {
////                System.out.println(resultSet.getString("name"));
//                //When a ResultSet object is first created, the cursor is positioned before the first row, so the first call to the next method puts the cursor on the first row
//                while (resultSet.next()) {
//                    System.out.println(resultSet.getString("name"));
//                }
//            }
        }
    }
}
