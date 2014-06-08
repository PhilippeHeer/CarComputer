/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.epfl.electronique.threads;

import ch.epfl.electronique.calculate.Calculate;
import ch.epfl.electronique.constants.Constants;
import ch.epfl.electronique.gui.Window;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Philippe Heer
 */
public class JaugeVolum extends Thread {

    private final Calculate calculate;

    public JaugeVolum(Calculate calculate) {
        this.calculate = calculate;
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
            int value = calculate.getWindow().getjSlider2().getValue();
            calculate.setJaugeVolume(value, System.currentTimeMillis());
            
            calculate.getWindow().getjLabel12().setText(Integer.toString(value));
        }
    }
}
