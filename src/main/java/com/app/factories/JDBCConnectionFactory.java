package com.app.factories;

import com.app.utils.PropertiesManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionFactory {
    private final static String USERNAME = "db.username";
    private final static String PASSWORD = "db.password";
    private final static String URL = "db.url";

    private JDBCConnectionFactory() {}

    public static Connection openConnection() throws SQLException{
        return DriverManager.getConnection(PropertiesManager.getPropertyByKey(URL),
                PropertiesManager.getPropertyByKey(USERNAME),
                PropertiesManager.getPropertyByKey(PASSWORD));
    }
}
