package com.example.publicwifimap;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnTest {

    @Test
    void dbTest() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/SQLite/WIFIINFO.sqlite");
        System.out.println(connection.isClosed());
    }
}
