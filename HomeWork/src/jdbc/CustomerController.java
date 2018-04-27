/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author biidz
 */
public class CustomerController {
    Scanner input = new Scanner(System.in);
    CustomerModel cm = new CustomerModel();

    public void getHome() throws SQLException {
        System.out.println("---------- Đăng ký tài khoản ----------");
        System.out.println("Full Name:");
        String fullname = input.nextLine();
        System.out.println("Username:");
        String username = input.nextLine();
        System.out.println("Password:");
        String password = input.nextLine();
        Customer ct = new Customer(fullname, username, password);
        cm.addCustomer(ct);
    }
    
}
