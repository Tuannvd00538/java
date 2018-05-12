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
public class Tiger extends Animal {

    public Tiger() {
    }

    public Tiger(String ten) {
        super(ten);
    }

    public Tiger(String ten, int tuoi) {
        super(ten, tuoi);
    }

    public Tiger(String ten, int tuoi, String mota) {
        super(ten, tuoi, mota);
    }

    @Override
    public void tiengKeu() {
        System.out.println("*Hổ Kêu*");
    }

}
