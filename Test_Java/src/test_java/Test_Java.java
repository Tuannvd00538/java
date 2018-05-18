/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_java;

import java.util.Scanner;

/**
 *
 * @author Computer
 */
public class Test_Java {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Câu 6
        Scanner input = new Scanner(System.in);
//        System.out.println("Nhập chuỗi cần check:");
//        String ip = input.nextLine();
//        char[] stringCheck = ip.toCharArray();
//        boolean siin = false;
//        for (char c : stringCheck) {
//            if (Character.isUpperCase(c)) {
//                siin = false;
//                break;
//            } else {
//                siin = true;
//            }
//        }
//        System.out.println(siin);
        
        
//        câu 5
        System.out.println("Nhập chuỗi thứ nhất:");
        String string1 = input.nextLine();
        System.out.println("Nhập chuỗi thứ hai:");
        String string2 = input.nextLine();
        String[] parts = string1.split(" ");
        boolean siin = false;
        for (String part : parts) {
            if (part.endsWith(string2)) {
                siin = true;
                break;
            } else {
                siin = false;
            }
        }
        System.out.println(siin);
    }
    
}
