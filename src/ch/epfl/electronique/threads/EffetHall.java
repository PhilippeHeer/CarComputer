/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.epfl.electronique.threads;

import ch.epfl.electronique.constants.Constants;
import ch.epfl.electronique.gui.Window;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Philippe Heer
 */
public class EffetHall extends Thread {

    private Window window;
    private int value;

    public EffetHall(Window window) {
        this.window = window;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(Constants.SLEEP);
            } catch (InterruptedException ex) {
                Logger.getLogger(EffetHall.class.getName()).log(Level.SEVERE, null, ex);
            }

            // replace value from analog reading of sensor
            value = window.getjSlider1().getValue();

            window.getjLabel11().setText(Integer.toString(value));
            
            System.out.println((value / (2 * Math.PI) * 360) / (2 * Math.PI * Constants.WHEEL_RADIUS));
        }
    }
}
