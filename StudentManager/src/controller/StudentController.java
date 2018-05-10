/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Student;
import java.util.ArrayList;
import java.util.Scanner;
import model.StudentModel;

/**
 *
 * @author hoang
 */
public class StudentController {

    private StudentModel model = new StudentModel();

    public boolean createStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter student rollnumber: ");
        String rollNumber = scanner.nextLine();
        System.out.println("Please enter student name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter student age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter student address: ");
        String address = scanner.nextLine();
        Student student = new Student(rollNumber, name, age, address);
        return model.save(student);
    }

    public void printListStudent() {
        ArrayList<Student> list = model.load();
        System.out.printf("%20s | %20s | %15s | %20s\n", "Rollnumber", "Name", "Age", "Address");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Student student : list) {
            System.out.printf("%20s | %20s | %15s | %20s\n", student.getRollNumber(), student.getName(), student.getAge(), student.getAddress());
        }
        System.out.println("");
    }
}
