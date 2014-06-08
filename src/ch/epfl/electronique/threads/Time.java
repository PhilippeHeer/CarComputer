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
    
    private long miliseconds = 0;
    private long chronoZero = System.currentTimeMillis();
    private long timeStopped = 0;
    private long lastTimeStopped = 0;
    
    private boolean isWorking = true;
    private boolean restarted = false;

    public Time(Calculate calculate) {
        this.calculate = calculate;

        df1 = new SimpleDateFormat("dd/MM/YY  HH:mm:ss");
    }

    public void startChrono(){
        if(!isWorking && !restarted){
            timeStopped += System.currentTimeMillis() - lastTimeStopped;
        }
        if(restarted){
            chronoZero = System.currentTimeMillis();
            restarted = false;
        }
        isWorking = true;
    }
    
    public void stopChrono(){
        if(isWorking){
            lastTimeStopped = System.currentTimeMillis();
        }
        isWorking = false;
    }
    
    public void restartChrono(){
        chronoZero = System.currentTimeMillis();
        timeStopped = 0;
        miliseconds = 0;
        restarted = true;
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
            
            if(isWorking) {
                miliseconds = (System.currentTimeMillis() - chronoZero) - timeStopped;
            }
            
            int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(miliseconds);
            int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(miliseconds);
            
            calculate.getWindow().getjTextField9().setText(Integer.toString(minutes) + "m " + Integer.toString(seconds) + "s " + Integer.toString((int) (miliseconds % 1000)) + "ms");
        }
    }
}
