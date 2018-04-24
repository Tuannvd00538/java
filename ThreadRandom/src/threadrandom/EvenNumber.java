/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadrandom;

/**
 *
 * @author biidz
 */
public class EvenNumber extends Thread {
    int a;
    
    public EvenNumber (int a) {
        this.a = a;
    }

    @Override
    public void run() {
        System.out.println("Random Number: " + this.a + " - la so chan.");
    }
}
