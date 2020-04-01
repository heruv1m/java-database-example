package org.example;

import org.h2.tools.Server;

import java.sql.DriverManager;
import java.sql.SQLException;


public class H2Server {

    public static void main(String[] args) throws SQLException {
        Server server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers", "-ifNotExists");
        server.start();
        System.out.println(server.getURL());

        try (var con = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/test;USER=sa;PASSWORD=");
             var stm = con.createStatement();
        ) {
            stm.executeQuery("select 1");
            System.out.println("h2 started");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
