/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author biidz
 */
public class Chuong {

    private int maChuong;

    public Chuong() {
    }

    public Chuong(int maChuong) {
        this.maChuong = maChuong;
    }

    public int getMaChuong() {
        return maChuong;
    }

    public void setMaChuong(int maChuong) {
        this.maChuong = maChuong;
    }

    public static ArrayList<Animal> AnimalList = new ArrayList<>(Arrays.asList());
    
    public void themConVat(Animal a) {
        AnimalList.add(a);
        System.out.println("Thêm con " + a.getTen() + " thành công!");
    }
    
    public void xoaConVat(String ten) {
        AnimalList.remove(ten);
        System.out.println("Con " + ten + " đã lên thớt!");
    }
}
