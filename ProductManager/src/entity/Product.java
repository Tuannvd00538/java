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
public class Product {

    private String id;
    private String name;
    private int price;

    public Product() {
    }

    public Product(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
