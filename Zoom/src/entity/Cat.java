/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author biidz
 */
public class Cat extends Animal {

    public Cat() {
    }

    public Cat(String ten) {
        super(ten);
    }

    public Cat(String ten, int tuoi) {
        super(ten, tuoi);
    }

    public Cat(String ten, int tuoi, String mota) {
        super(ten, tuoi, mota);
    }

    @Override
    public void tiengKeu() {
        System.out.println("*Mèo Kêu*");
    }

}
