/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.epfl.electronique.threads;

import ch.epfl.electronique.calculate.Calculate;
import ch.epfl.electronique.constants.Constants;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Philippe Heer
 */
public class CapteurInjection extends Thread {

    private final Calculate calculate;

    public CapteurInjection(Calculate calculate) {
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
            double value = calculate.getWindow().getjSlider3().getValue() / 10.0;
            calculate.setCapteurInject(value, System.currentTimeMillis());
            
            calculate.getWindow().getjLabel13().setText(Double.toString(value));
        }
    }
}
