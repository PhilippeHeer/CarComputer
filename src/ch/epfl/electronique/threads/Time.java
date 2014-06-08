/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.epfl.electronique.threads;

import ch.epfl.electronique.calculate.Calculate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Philippe Heer
 */
public class Time extends Thread {

    private final Calculate calculate;
    private final SimpleDateFormat df1;

    private Date dateobj1;
    
    private long chronoZero = System.currentTimeMillis();

    public Time(Calculate calculate) {
        this.calculate = calculate;

        df1 = new SimpleDateFormat("dd/MM/YY  HH:mm:ss");
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(EffetHall.class.getName()).log(Level.SEVERE, null, ex);
            }

            dateobj1 = new Date();

            calculate.getWindow().getjLabel14().setText(df1.format(dateobj1));
            
            long miliseconds = (System.currentTimeMillis() - chronoZero);
            
            int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(miliseconds);
            int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(miliseconds);
            
            calculate.getWindow().getjTextField9().setText(Integer.toString(minutes) + "m " + Integer.toString(seconds) + "s " + Integer.toString((int) (miliseconds % 1000)) + "ms");
        }
    }
}
