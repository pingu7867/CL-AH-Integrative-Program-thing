/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleSpringSimpleHarmonicMotion;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cédric
 */
public class Oscillator {
    
    double frequency;
    double amplitude;
    
    int threadStepDelay = 16;
    
    double value;
    
    StepThread oscThread;
    
    public Oscillator() {
        frequency = 1;
        amplitude = 400;
        oscThread = new StepThread();
    }
    
    public void startOsc() {
        oscThread.run();
    }
    
    public void stopOsc() {
        oscThread.running = false;
    }
    
    public class StepThread implements Runnable {
        boolean running = true;
        long time;
        @Override
        public void run() {
            long startTimeM;
            long startTimeN;
            while(running) {
                startTimeM = System.currentTimeMillis();
                startTimeN = System.nanoTime();
                while ((System.nanoTime() - startTimeN) / 1000 <= frequency) {
                }
                time = System.currentTimeMillis();
                value = amplitude * Math.sin(frequency * (time - startTimeM));
            }
        }
    }
    
    
}
