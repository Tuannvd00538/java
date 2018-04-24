/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botthread;

import model.BotModel;

/**
 *
 * @author Ngo Van Tuan
 */
public class BotThreadDelay extends Thread {

    @Override
    public void run() {
        BotModel bm = new BotModel();
        try {
            Thread.sleep(15000);
            bm.botDelay();
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }
}
