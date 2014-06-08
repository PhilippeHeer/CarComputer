/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.epfl.electronique.calculate;

import ch.epfl.electronique.constants.Constants;
import ch.epfl.electronique.gui.Window;
import java.text.DecimalFormat;

/**
 *
 * @author Philippe Heer
 */
public class Calculate {

    private double vitesseInstantane = 0;
    private double consommationInstantane = 0;
    private double vitesseMoyenne = 0;
    private double consommationMoyenne = 0;
    private double kilometrageParcours = 0;
    private double volumeDessenceDisponible = 0;

    // given in ms
    private long previousTimeEffetHall = 0;
    private long previousTimeJaugeVolum = 0;
    private long previousTimeCapteurInjection = 0;
    
    private final long beginTime = System.currentTimeMillis();

    // kilometres disponible
    private double autonomieDisponible = 0;
    private double distancePrevue = 0;
    
    private final double timeChange = 1000;

    private final Window window;

    public Calculate(Window window) {
        this.window = window;
    }

    public void setEffetHall(int sensorValue, long actualTime) {
        vitesseInstantane = (int) (sensorValue * (Constants.WHEEL_RADIUS / (double) 1000) * 3.6);
        
        if (previousTimeEffetHall == 0) {
            previousTimeEffetHall = actualTime;
        }
        
        kilometrageParcours += vitesseInstantane * (actualTime - previousTimeEffetHall) / 3600000;
        
        double timeSinceBegin = (previousTimeEffetHall - beginTime) / timeChange;
        double timeSinceLast = (actualTime - previousTimeEffetHall) / timeChange;
        
        vitesseMoyenne = ( vitesseMoyenne * timeSinceBegin + vitesseInstantane * timeSinceLast ) / (timeSinceBegin + timeSinceLast);
        
        previousTimeEffetHall = actualTime;
        
        display();
    }

    public void setJaugeVolume(int sensorValue, long actualTime) {        
        volumeDessenceDisponible = (int) ((double) sensorValue * ((double) Constants.TANK_VOLUME / (double) Constants.TANK_HEIGHT));

        if (consommationMoyenne != 0) {
            autonomieDisponible = volumeDessenceDisponible / (consommationMoyenne / 100);
        }

        display();
    }

    public void setCapteurInject(double sensorValue, long actualTime) {
        consommationInstantane = sensorValue * 3.6 / vitesseInstantane * 100;
        
        if (previousTimeCapteurInjection == 0) {
            previousTimeCapteurInjection = actualTime;
        }
        
        double timeSinceBegin = (previousTimeCapteurInjection - beginTime) / timeChange;
        double timeSinceLast = (actualTime - previousTimeCapteurInjection) / timeChange;
        
        consommationMoyenne = ( consommationMoyenne * timeSinceBegin + consommationInstantane * timeSinceLast ) / (timeSinceBegin + timeSinceLast);
        
        previousTimeCapteurInjection = actualTime;
        display();
    }

    private synchronized void display() {
        window.getjTextField1().setText(new DecimalFormat("##.#").format(vitesseInstantane));
        window.getjTextField2().setText(new DecimalFormat("##.#").format(consommationInstantane));
        window.getjTextField3().setText(new DecimalFormat("##.#").format(kilometrageParcours));
        window.getjTextField4().setText(new DecimalFormat("##.#").format(vitesseMoyenne));
        window.getjTextField5().setText(new DecimalFormat("##.#").format(consommationMoyenne));
        window.getjTextField6().setText(new DecimalFormat("##.#").format(volumeDessenceDisponible));
        window.getjTextField7().setText(new DecimalFormat("##.#").format(autonomieDisponible));
        window.getjTextField8().setText(new DecimalFormat("##.#").format(distancePrevue));
    }

    public Window getWindow() {
        return window;
    }
}
