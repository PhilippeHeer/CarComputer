/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.epfl.electronique.main;

import ch.epfl.electronique.main.gui.threads.Time;
import ch.epfl.electronique.main.gui.Window;

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

        window.setVisible(true);
    }
}
