/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computermanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author biidz
 */
public class ComputerModel {

    public void saveToDatabase(Computer computer) {
        try {
            Connection cnn = ComputerDBConnection.getInstance().getConnection();
            String sql = "insert into computers (ID, name, producer, yearmaking, price) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, computer.getID());
            ps.setString(2, computer.getName());
            ps.setString(3, computer.getPruducer());
            ps.setInt(4, computer.getYearmaking());
            ps.setInt(5, computer.getPrice());
            ps.execute();
            System.out.println("Luu thanh cong!");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public ArrayList<Computer> getTableDb() {
        ArrayList<Computer> list = new ArrayList<>();
        try {
            Connection cnn = ComputerDBConnection.getInstance().getConnection();
            Statement stt = cnn.createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM computers");
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("name");
                String producer = rs.getString("producer");
                int ym = rs.getInt("yearmaking");
                int price = rs.getInt("price");
                Computer cp = new Computer(id, name, producer, price, price);
                list.add(cp);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return list;
    }

}
