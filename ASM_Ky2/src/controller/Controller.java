/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author biidz
 */
public class Controller {

    Scanner input = new Scanner(System.in);

    ArrayList<Student> listStudent = new ArrayList<>(Arrays.asList());

    public int getMenu() {
        System.out.println("=====================================");
        System.out.println("1. Add new student");
        System.out.println("2. Save");
        System.out.println("3. Display all students");
        System.out.println("4. Exit");
        System.out.println("=====================================");
        System.out.println("Please enter your choice:");
        int choice = Integer.parseInt(input.nextLine());
        return choice;
    }

    public Student addStudent() {
        System.out.println("EnrolID:");
        String id = input.nextLine();
        System.out.println("First Name:");
        String firstname = input.nextLine();
        System.out.println("Last Name:");
        String lastname = input.nextLine();
        System.out.println("Age:");
        int age = Integer.parseInt(input.nextLine());
        Student st = new Student(id, firstname, lastname, age);
        System.out.println("Add success!");
        listStudent.add(st);
        return st;
    }

    public void saveStudent() throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("students.dat");
        try (OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw)) {
            bw.write("----------------------------------------------------------------------------");
            bw.newLine();
            bw.write(String.format("%-20s | %-40s | %-20s", "EnrolID", "Full Name", "Age"));
            bw.newLine();
            bw.write("----------------------------------------------------------------------------");
            bw.newLine();
            for (Student student : listStudent) {
                bw.write(String.format("%-20s | ", student.getEnrolID()));
                bw.write(String.format("%-40s | ", student.getFirstName() + " " + student.getLastName()));
                bw.write(String.format("%-20s ", String.valueOf(student.getAge())));
                bw.newLine();
            }
            bw.write("----------------------------------------------------------------------------");
            bw.newLine();
            System.out.println("Save success!");
        }
    }

    public void displayStudent() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("students.dat");
        InputStreamReader isp = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isp);
        StringBuilder sb = new StringBuilder();
        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            sb.append(inputLine).append("\n");
        }
        System.out.println(sb.toString());
    }

}
