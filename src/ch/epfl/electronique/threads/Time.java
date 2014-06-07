/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.epfl.electronique.threads;

import ch.epfl.electronique.gui.Window;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Philippe Heer
 */
public class Time extends Thread {

    private final Window window;
    private final SimpleDateFormat df;

    private Date dateobj;

    public Time(Window window) {
        this.window = window;

        df = new SimpleDateFormat("HH:mm:ss");
    }

    @Override
    public void run() {
        while (true) {
            dateobj = new Date();
            window.getjLabel14().setText(df.format(dateobj));
        }
    }
}
