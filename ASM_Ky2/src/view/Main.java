/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import entity.Student;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author biidz
 */
public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        Controller ct = new Controller();
        Student st = null;
        while (true) {
            switch (ct.getMenu()) {
                case 1:
                    st = ct.addStudent();
                    break;
                case 2:
                    ct.saveStudent();
                    break;
                case 3:
                    ct.displayStudent();
                    break;
                case 4:
                    return;
                default:
                    break;
            }
        }
    }
}
