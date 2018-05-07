/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computermanager;

/**
 *
 * @author biidz
 */
public class Computer {
    private int ID;
    private String name;
    private String pruducer;
    private int yearmaking;
    private int price;

    public Computer() {
    }

    public Computer(int ID, String name, String pruducer, int yearmaking, int price) {
        this.ID = ID;
        this.name = name;
        this.pruducer = pruducer;
        this.yearmaking = yearmaking;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPruducer() {
        return pruducer;
    }

    public void setPruducer(String pruducer) {
        this.pruducer = pruducer;
    }

    public int getYearmaking() {
        return yearmaking;
    }

    public void setYearmaking(int yearmaking) {
        this.yearmaking = yearmaking;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
