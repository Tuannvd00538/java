/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author biidz
 */
public class CustomerModel {

    public void addCustomer(Customer customer) throws SQLException {
        Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/homework?useUnicode=true"
                + "&characterEncoding=UTF-8", "root", "");
        Statement stt = cnn.createStatement();
        String sql = "insert into customers (name, username, password) values (?, ?, ?)";
        PreparedStatement ps = cnn.prepareStatement(sql);
        ps.setString(1, customer.getName());
        ps.setString(2, customer.getUsername());
        ps.setString(3, customer.getPassword());
        ps.execute();
        System.out.println("Thêm thành công!");
    }
}
