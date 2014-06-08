/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.epfl.electronique.main;

import ch.epfl.electronique.calculate.Calculate;
import ch.epfl.electronique.gui.Window;
import ch.epfl.electronique.threads.CapteurInjection;
import ch.epfl.electronique.threads.EffetHall;
import ch.epfl.electronique.threads.JaugeVolum;
import ch.epfl.electronique.threads.Time;

/**
 *
 * @author Philippe Heer
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Window window = new Window();
        Calculate calculate = new Calculate(window);

        Time time = new Time(calculate);
        time.start();

        new EffetHall(calculate).start();
        new JaugeVolum(calculate).start();
        new CapteurInjection(calculate).start();

        window.setVisible(true);
        window.setTime(time);
    }
}
