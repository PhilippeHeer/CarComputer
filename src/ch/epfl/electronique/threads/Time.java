/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.epfl.electronique.threads;

import ch.epfl.electronique.calculate.Calculate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Philippe Heer
 */
public class Time extends Thread {

    private final Calculate calculate;
    private final SimpleDateFormat df;

    private Date dateobj;

    public Time(Calculate calculate) {
        this.calculate = calculate;

        df = new SimpleDateFormat("HH:mm:ss");
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(EffetHall.class.getName()).log(Level.SEVERE, null, ex);
            }

            dateobj = new Date();

            calculate.getWindow().getjLabel14().setText(df.format(dateobj));
        }
    }
}
