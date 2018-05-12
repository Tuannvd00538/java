/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Product;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import model.ProductModel;

/**
 *
 * @author biidz
 */
public class ProductController {

    Scanner input = new Scanner(System.in);
    ProductModel pm = new ProductModel();
    ArrayList<Product> listProduct = new ArrayList<>(Arrays.asList());

    {
        Product p1 = new Product("iPhone 8 Plus 64GB", 23990000);
        Product p2 = new Product("Galaxy J7 Pro", 6090000);
        Product p3 = new Product("Oppo F7 128GB", 9990000);
        Product p4 = new Product("Vivo Y71", 3990000);
        Product p5 = new Product("Galaxy J7 Prime", 4990000);
        Product p6 = new Product("Macbook Air i5 (2017)", 23990000);
        Product p7 = new Product("HP Pavilion x360", 13490000);
        Product p8 = new Product("iPad Pro 10.5 inch", 16990000);
        Product p9 = new Product("iPad Cellular 32GB", 10990000);
        Product p10 = new Product("iPad Mini 4 Wifi 128GB", 10990000);
        listProduct.add(p1);
        listProduct.add(p2);
        listProduct.add(p3);
        listProduct.add(p4);
        listProduct.add(p5);
        listProduct.add(p6);
        listProduct.add(p7);
        listProduct.add(p8);
        listProduct.add(p9);
        listProduct.add(p10);
    }

    public int getMenu() {
        System.out.println("================================");
        System.out.println("1. Lưu danh sách sản phẩm.");
        System.out.println("2. Sửa thông tin sản phẩm.");
        System.out.println("3. Thoát");
        System.out.println("================================");
        System.out.println("Vui lòng nhập lựa chọn của bạn:");
        int choice = Integer.parseInt(input.nextLine());
        return choice;
    }

    public void saveProduct() {
        try {
            for (int i = 0; i < listProduct.size(); i++) {
                pm.saveToDB(listProduct.get(i));
            }
            System.out.println("Lưu thành công!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean editProduct() {
        System.out.println("================================");
        System.out.println("Vui lòng nhập mã sản phẩm:");
        int id = Integer.parseInt(input.nextLine());
        if (pm.edit(id) == null) {
            System.err.println("Sản phẩm không tồn tại hoặc đã bị xóa!");
        } else {
            Product pr = pm.edit(id);
            pm.saveEdit(pr);
        }
        return true;
    }

}
