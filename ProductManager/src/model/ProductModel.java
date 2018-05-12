/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author biidz
 */
public class ProductModel {

    Scanner input = new Scanner(System.in);

    public boolean saveToDB(Product product) {
        try {
            String sqlString = "insert into products (name, price) values (?, ?)";
            PreparedStatement ps = ProductDBConnection.getInstance().getConnection().prepareStatement(sqlString);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("Lỗi khi làm việc với database, vui lòng thử lại");
        }
        return false;
    }

    public Product edit(int id) {
        try {
            String sqlString = "SELECT * FROM products WHERE id = ?";
            PreparedStatement ps = ProductDBConnection.getInstance().getConnection().prepareStatement(sqlString);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String idNew = rs.getString("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                Product pr = new Product(idNew, name, price);
                return pr;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("Lỗi khi làm việc với database, vui lòng thử lại");
        }
        return null;
    }

    public boolean saveEdit(Product rs) {
        System.out.println("Mã sản phẩm: " + rs.getId() + " - Tên sản phẩm: " + rs.getName() + " - Giá: " + rs.getPrice());
        System.out.println("Vui lòng nhập tên mới của sản phẩm:");
        String name = input.nextLine();
        System.out.println("Vui lòng nhập giá mới của sản phẩm:");
        int price = Integer.parseInt(input.nextLine());
        try {
            String query = "UPDATE products SET name = ?, price = ? WHERE id = ?";
            PreparedStatement psm = ProductDBConnection.getInstance().getConnection().prepareStatement(query);
            psm.setString(1, name);
            psm.setInt(2, price);
            psm.setString(3, rs.getId());
            psm.executeUpdate();
            System.out.println("Lưu thành công!");
        } catch (SQLException ex) {
            Logger.getLogger(ProductModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
