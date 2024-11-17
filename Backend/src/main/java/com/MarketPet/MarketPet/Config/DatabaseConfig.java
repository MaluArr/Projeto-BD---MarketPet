package com.MarketPet.MarketPet.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/marketpet?useSSL=false&serverTimezone=UTC",
                "root",
                "1234"
        );
    }
}