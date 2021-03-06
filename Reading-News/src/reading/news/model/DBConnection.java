/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reading.news.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author biidz
 */
public class DBConnection {

    private Connection cnn = null;
    private final String CONNECTION_URL = "jdbc:mysql://192.168.64.2:3306/";
    private final String DATABASE = "isiin";
    private final String USERNAME = "siin";
    private final String PASSWORD = "siindz";
    private final String UTF8_URL = "?useUnicode=true&characterEncoding=utf-8";
    public static DBConnection instance;

    public static DBConnection getInstance() {
        if (null == instance) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (null == cnn || cnn.isClosed()) {
                cnn = DriverManager.getConnection(CONNECTION_URL + DATABASE + UTF8_URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return cnn;
    }

    public void closeConnection() {
        try {
            if (cnn != null && !cnn.isClosed()) {
                cnn.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
