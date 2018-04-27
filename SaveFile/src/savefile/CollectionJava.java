/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savefile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Ngo Van Tuan
 */
public class CollectionJava {

    private ArrayList<Student> listStudent;

    private ArrayList<Student> listStudentReadFile;

    public ArrayList<Student> getListStudent() {
        return listStudent;
    }

    public void setListStudent(ArrayList<Student> listStudent) {
        this.listStudent = listStudent;
    }

    public void generateStudents() {
        listStudent = new ArrayList<>();
        listStudent.add(new Student("A001", "Xuân Hùng", 100000, "20/10/2018"));
        listStudent.add(new Student("A002", "Nguyễn Minh", 10000, "20/10/2018"));
        listStudent.add(new Student("A003", "Nguyễn An", 20000, "20/10/2018"));
        listStudent.add(new Student("A004", "Mai Trà", 10000, "21/10/2018"));
        listStudent.add(new Student("A005", "Phương Anh", 20000, "21/10/2018"));
        listStudent.add(new Student("A006", "Trịnh Thúy", 40000, "22/10/2018"));
        listStudent.add(new Student("A004", "Mai Trà", 50000, "22/10/2018"));
        listStudent.add(new Student("A004", "Mai Trà", 60000, "23/10/2018"));
        listStudent.add(new Student("A001", "Xuân Hùng", 200000, "24/10/2018"));
        listStudent.add(new Student("A002", "Nguyễn Minh", 20000, "25/10/2018"));
        listStudent.add(new Student("A003", "Nguyễn An", 20000, "26/10/2018"));
        listStudent.add(new Student("A001", "Xuân Hùng", 50000, "27/10/2018"));
        listStudent.add(new Student("A001", "Xuân Hùng", 70000, "28/10/2018"));
    }

    public void saveToFile() throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("danhsachnoptien.txt");
        try (OutputStreamWriter osw = new OutputStreamWriter(fos); BufferedWriter bw = new BufferedWriter(osw)) {
            bw.write("----------------------------------------------------------------------------");
            bw.newLine();
            bw.write(String.format("%-20s | %-19s | %-19s | %-20s", "Ma Sinh Vien", "Ten Sinh Vien", "So Tien", "Ngay Nop"));
            bw.newLine();
            bw.write("----------------------------------------------------------------------------");
            bw.newLine();
            int balance = 0;
            for (Student student : listStudent) {
                bw.write(String.format("%-20s |", student.getRollNumber()));
                bw.write(String.format("%-20s |", student.getName()));
                bw.write(String.format("%-20s |", String.valueOf(student.getBalance())));
                bw.write(String.format("%-20s", student.getCreateAt()));
                bw.newLine();
                balance += student.getBalance();
            }
            bw.write("----------------------------------------------------------------------------");
            bw.newLine();
            bw.write(String.format("%70s", "Tong so tien: " + balance + " (VND)"));
        }
    }

    public void printFile() throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream("danhsachnoptien.txt");
        InputStreamReader isp = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isp);
        StringBuilder sb = new StringBuilder();
        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            sb.append(inputLine).append("\n");
        }
        System.out.println(sb.toString());
    }

    public ArrayList<Student> readFile() throws FileNotFoundException, IOException {
        listStudentReadFile = new ArrayList<>();
        FileInputStream fis = new FileInputStream("danhsachnoptien.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            String[] siin = inputLine.split("\\|");
            if (siin.length == 4) {
                String strBalance = siin[2].trim();
                int balance;
                try {
                    balance = Integer.parseInt(strBalance);
                } catch (NumberFormatException e) {
                    continue;
                }
                String rollNumber = siin[0].trim();
                String name = siin[1].trim();
                String createAt = siin[3].trim();
                Student st = new Student(rollNumber, name, balance, createAt);
                listStudentReadFile.add(st);
            }
        }
        return listStudentReadFile;
    }
    
    public HashMap<String, Student> hashSetStudent() throws FileNotFoundException, IOException {
        HashMap<String, Student> siinHashMap = new HashMap<>();
        FileInputStream fis = new FileInputStream("danhsachnoptien.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            String[] siin = inputLine.split("\\|");
            if (siin.length == 4) {
                String strBalance = siin[2].trim();
                int balance;
                try {
                    balance = Integer.parseInt(strBalance);
                } catch (NumberFormatException e) {
                    continue;
                }
                String rollNumber = siin[0].trim();
                String name = siin[1].trim();
                String createAt = siin[3].trim();
                Student st = new Student(rollNumber, name, balance, createAt);
                if (siinHashMap.containsKey(st.getRollNumber())) {
                    Student stNew = siinHashMap.get(st.getRollNumber());
                    st.setBalance(st.getBalance() + stNew.getBalance());
                }
                siinHashMap.put(rollNumber, st);
            }
        }
        return siinHashMap;
    }
}
