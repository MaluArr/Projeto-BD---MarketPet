package com.MarketPet.MarketPet.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBC_Connection {

    private static final String URL = "jdbc:mysql://localhost:3306/MarketPet";
    private static final String USER = "root";
    private static final String PASSWORD = "12345mjfr";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final Logger logger = LoggerFactory.getLogger(JDBC_Connection.class);

    static {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            logger.error("Driver JDBC não encontrado", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.error("Erro ao obter conexão com o banco de dados", e);
            throw e;
        }
    }
}