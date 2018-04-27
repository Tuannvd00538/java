/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savefile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author biidz
 */
public class Main {

    public static void main(String[] args) throws IOException {
        CollectionJava colJava = new CollectionJava();
        colJava.generateStudents();
        colJava.saveToFile();
        colJava.printFile();
        System.out.println("----------------------------------");
        ArrayList<Student> st = colJava.readFile();
        for (int i = 0; i < st.size(); i++) {
            System.out.println(st.get(i).getName());
        }
        System.out.println("----------------------------------");
        HashMap<String, Student> hs = colJava.hashSetStudent();
        for (Map.Entry<String, Student> entry : hs.entrySet()) {
            String key = entry.getKey();
            Student value = entry.getValue();
            System.out.println(value.getName() + " - " + value.getBalance());
        }
    }
}
