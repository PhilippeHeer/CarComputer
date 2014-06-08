/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.epfl.electronique.main;

import ch.epfl.electronique.threads.Time;
import ch.epfl.electronique.gui.Window;
import ch.epfl.electronique.threads.CapteurInjection;
import ch.epfl.electronique.threads.EffetHall;
import ch.epfl.electronique.threads.GaugeVolum;

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

        new Time(window).start();

        new EffetHall(window).start();
        new GaugeVolum(window).start();
        new CapteurInjection(window).start();

        window.setVisible(true);
    }
}
