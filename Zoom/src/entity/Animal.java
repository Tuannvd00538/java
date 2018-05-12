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
public abstract class Animal {
    private String ten;
    private int tuoi;
    private String mota;

    public Animal() {
    }

    public Animal(String ten) {
        this.ten = ten;
    }

    public Animal(String ten, int tuoi) {
        this.ten = ten;
        this.tuoi = tuoi;
    }

    public Animal(String ten, int tuoi, String mota) {
        this.ten = ten;
        this.tuoi = tuoi;
        this.mota = mota;
    }

    public String getTen() {
        return ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public String getMota() {
        return mota;
    }
    
    public void xemThongTin() {
        getTen();
        getTuoi();
        getMota();
    }
    
    abstract void tiengKeu();
}
