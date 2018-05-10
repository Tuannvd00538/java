/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hoang
 */
public class StudentModel {

    public boolean save(Student student) {
        try {
            String sqlString = "insert into students (rollNumber, name, age, address) values (?,?,?,?)";
            PreparedStatement ps = ConnectionHandle.getInstance().getConnection().prepareStatement(sqlString);
            ps.setString(1, student.getRollNumber());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getAddress());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("Lỗi khi làm việc với database, vui lòng thử lại");
        }
        return false;
    }

    public ArrayList<Student> load() {
        ArrayList<Student> list = new ArrayList<>();
        try {
            String sqlString = "select * from students";
            PreparedStatement ps = ConnectionHandle.getInstance().getConnection().prepareStatement(sqlString);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String rollNumber = rs.getString("rollNumber");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                Student student = new Student(rollNumber, name, age, address);
                list.add(student);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("Lỗi khi làm việc với database, vui lòng thử lại");
        }
        return list;
    }

    public boolean update(Student student) {
        try {
            String query = "UPDATE students SET name = ?, age = ?,address = ? where rollNumber = ?";
            PreparedStatement ps = ConnectionHandle.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getAddress());
            ps.setString(4, student.getRollNumber());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean delete(String rollNumber) {
        try {
            String query = "DELETE FROM students WHERE rollNumber = ?";
            PreparedStatement ps = ConnectionHandle.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, rollNumber);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String[] args) {

        StudentModel model = new StudentModel();
        Student student = new Student("A002", "Khánh", 18, "Hà Nội");
        ArrayList<Student> list = model.load();
        for (Student student1 : list) {
            System.out.println(student1.getName());
        }
        model.save(student);
        model.update(new Student("A002", "Class", 11, "Hanoi"));
        model.delete("A002");
    }
}
