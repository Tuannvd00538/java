/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadrandom;

import java.util.Random;

/**
 *
 * @author biidz
 */
public class ThreadRandom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Random rand = new Random();
            int number = rand.nextInt(100);
            if (number % 2 == 0) {
                EvenNumber en = new EvenNumber(number);
                en.start();
            } else {
                OddNumber on = new OddNumber(number);
                on.start();
            }
        }
    }
    
}
