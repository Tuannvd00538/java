/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ProductController;

/**
 *
 * @author biidz
 */
public class Main {

    public static void main(String[] args) {
        ProductController pc = new ProductController();
        while (true) {
            switch (pc.getMenu()) {
                case 1:
                    pc.saveProduct();
                    break;
                case 2:
                    pc.editProduct();
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }
    }
}
