/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import static entity.Chuong.AnimalList;
import java.util.Scanner;

/**
 *
 * @author biidz
 */
public class TestZoo {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Zoo zoo = new Zoo();
        Chuong c = new Chuong();

        while (true) {
            System.out.println("==========================");
            System.out.println("1. Thêm chuồng");
            System.out.println("2. Xóa chuồng");
            System.out.println("3. Thêm con vật");
            System.out.println("4. Xóa con vật");
            System.out.println("5. Xem tất cả các con vật");
            System.out.println("6. Thoát");
            System.out.println("==========================");
            System.out.print("Vui lòng chọn: ");
            int choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Nhập mã chuồng:");
                    int machuong = Integer.parseInt(input.nextLine());
                    Chuong chuong = new Chuong(machuong);
                    zoo.themChuong(chuong);
                    break;
                case 2:
                    System.out.println("Nhập mã chuồng muốn xóa:");
                    int machuongxoa = Integer.parseInt(input.nextLine());
                    zoo.xoaChuong(machuongxoa);
                    break;
                case 3:
                    System.out.println("==========================");
                    System.out.println("Chọn con vật muốn thêm:");
                    System.out.println("1. Tiger");
                    System.out.println("2. Dog");
                    System.out.println("3. Cat");
                    System.out.println("==========================");
                    System.out.print("Vui lòng chọn:");
                    int luachon = Integer.parseInt(input.nextLine());
                    switch (luachon) {
                        case 1:
                            System.out.println("Nhập tên:");
                            String nameTiger = input.nextLine();
                            System.out.println("Nhập tuổi:");
                            int tuoiTiger = Integer.parseInt(input.nextLine());
                            System.out.println("Nhập mô tả:");
                            String motaTiger = input.nextLine();
                            Tiger tiger = new Tiger(nameTiger, tuoiTiger, motaTiger);
                            c.themConVat(tiger);
                            break;
                        case 2:
                            System.out.println("Nhập tên:");
                            String nameDog = input.nextLine();
                            System.out.println("Nhập tuổi:");
                            int tuoiDog = Integer.parseInt(input.nextLine());
                            System.out.println("Nhập mô tả:");
                            String motaDog = input.nextLine();
                            Dog dog = new Dog(nameDog, tuoiDog, motaDog);
                            c.themConVat(dog);
                            break;
                        case 3:
                            System.out.println("Nhập tên:");
                            String nameCat = input.nextLine();
                            System.out.println("Nhập tuổi:");
                            int tuoiCat = Integer.parseInt(input.nextLine());
                            System.out.println("Nhập mô tả:");
                            String motaCat = input.nextLine();
                            Cat cat = new Cat(nameCat, tuoiCat, motaCat);
                            c.themConVat(cat);
                            break;
                        default:
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Nhập tên con vật muốn xóa:");
                    String ten = input.nextLine();
                    c.xoaConVat(ten);
                    break;
                case 5:
                    for (int i = 0; i < AnimalList.size(); i++) {
                        System.out.println(AnimalList.get(i).getTen());
                    }
                    break;
                case 6:
                    return;
                default:
                    break;
            }
        }
    }
}
