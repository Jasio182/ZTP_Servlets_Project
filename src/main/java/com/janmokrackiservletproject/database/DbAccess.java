package com.janmokrackiservletproject.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DbAccess {

    private static String jdbcURL = "jdbc:sqlite:ServletProjectdb.db";

    private static Connection connection;

    public static Connection GetConnection() throws Exception {
        Class.forName("org.sqlite.JDBC");
        if(connection == null)
            connection = DriverManager.getConnection(jdbcURL);
        return connection;
    }
}
