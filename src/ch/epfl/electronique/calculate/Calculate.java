/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.epfl.electronique.calculate;

import ch.epfl.electronique.constants.Constants;
import ch.epfl.electronique.gui.Window;

/**
 *
 * @author Philippe Heer
 */
public class Calculate {

    private int vitesseInstantane = 0;
    private int consommationInstantane = 0;
    private int vitesseMoyene = 0;
    private int consommationMoyenne = 0;
    private int kilometrageParcours = 0;
    private int volumeDessenceDisponible = 0;

    // given in ms
    private long previousTimeEffetHall = 0;
    private long previousTimeJaugeVolum = 0;
    private long previousTimeCapteurInjection = 0;
    
    private final long beginTime = System.currentTimeMillis();

    // kilometres disponible
    private int autonomieDisponible = 0;
    private int distancePrevue = 0;

    private final Window window;

    public Calculate(Window window) {
        this.window = window;
    }

    public void setEffetHall(int sensorValue, long Actualtime) {
        vitesseInstantane = (int) (sensorValue * (Constants.WHEEL_RADIUS / 1000) * 3.6);

        display();
    }

    public void setJaugeVolume(int sensorValue, long actualTime) {
        if (previousTimeJaugeVolum == 0) {
            previousTimeJaugeVolum = System.currentTimeMillis();
        }
        
        consommationMoyenne = (int) (((previousTimeJaugeVolum - beginTime) / 1000) * consommationMoyenne + ((actualTime - previousTimeJaugeVolum) / 1000) * sensorValue);
        
        previousTimeJaugeVolum = actualTime;

        
        volumeDessenceDisponible = (int) ((double) sensorValue * ((double) Constants.TANK_VOLUME / (double) Constants.TANK_HEIGHT));

        if (consommationMoyenne != 0) {
            autonomieDisponible = volumeDessenceDisponible / consommationMoyenne;
        }

        display();
    }

    public void setCapteurInject(int sensorValue, long Actualtime) {
        consommationInstantane = sensorValue / 1000;

        display();
    }

    private synchronized void display() {
        window.getjTextField1().setText(Integer.toString(vitesseInstantane));
        window.getjTextField2().setText(Integer.toString(consommationInstantane));
        window.getjTextField3().setText(Integer.toString(kilometrageParcours));
        window.getjTextField4().setText(Integer.toString(vitesseMoyene));
        window.getjTextField5().setText(Integer.toString(consommationMoyenne));
        window.getjTextField6().setText(Integer.toString(volumeDessenceDisponible));
        window.getjTextField7().setText(Integer.toString(autonomieDisponible));
        window.getjTextField8().setText(Integer.toString(distancePrevue));
    }

    public Window getWindow() {
        return window;
    }
}
