/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reading.news.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import reading.news.entity.Md5Password;
import reading.news.entity.User;

/**
 *
 * @author biidz
 */
public class UserModel {
    
    public static User siindeptrai = null;

    public boolean register(User user) {
        try {
            String sqlString = "INSERT INTO users (id, username, password, email) values (?, ?, ?, ?)";
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sqlString);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, Md5Password.md5(user.getPassword()));
            ps.setString(4, user.getEmail());
            ps.execute();
            siindeptrai = user;
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("Lỗi khi làm việc với database, vui lòng thử lại");
        }
        return false;
    }

    public boolean checkExitUser(String username) {
        try {
            String sqlString = "SELECT * FROM users WHERE username = ?";
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sqlString);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("Lỗi khi làm việc với database, vui lòng thử lại");
        }
        return false;
    }

    public User login(String username, String password) {
        try {
            String sqlString = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sqlString);
            ps.setString(1, username);
            ps.setString(2, Md5Password.md5(password));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idLogin = rs.getInt("id");
                String usernameLogin = rs.getString("username");
                String passwordLogin = rs.getString("password");
                String emailLogin = rs.getString("email");
                long createdTimeLogin = rs.getLong("createdTime");
                User user = new User(idLogin, username, password, emailLogin, createdTimeLogin);
                return user;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("Lỗi khi làm việc với database, vui lòng thử lại");
        }
        return null;
    }

    public boolean setInfo(String link, String fullName, String birthDay) {
        try {
            String sqlString = "UPDATE users SET avatar = ?, fullName = ?, birthDay = ? WHERE id = ?";
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sqlString);
            ps.setString(1, link);
            ps.setString(2, fullName);
            ps.setString(3, birthDay);
            ps.setInt(4, siindeptrai.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("Lỗi khi làm việc với database, vui lòng thử lại");
        }
        return false;
    }
}
