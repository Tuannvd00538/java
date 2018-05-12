/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author biidz
 */
public class ProductDBConnection {

    private Connection connection;
    private final String CONNECTION_PREFIX = "jdbc:mysql://localhost:3306/";
    private final String DATABASE_NAME = "homework";
    private final String UTF8_CONFIG = "?useUnicode=true&characterEncoding=utf-8";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private static ProductDBConnection connectionHandle;

    public static ProductDBConnection getInstance() {
        if (null == connectionHandle) {
            connectionHandle = new ProductDBConnection();
        }
        return connectionHandle;
    }

    public Connection getConnection() throws SQLException {
        if (null == connection || connection.isClosed()) {
            connection = DriverManager.getConnection(CONNECTION_PREFIX + DATABASE_NAME + UTF8_CONFIG, USERNAME, PASSWORD);
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
