/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

/**
 *
 * @author Cédric
 */
public class GameTickTimer {
    
    GameTickActor host;
    
    double fps;
    long tick = 0;
    double milliDelay;
    boolean running;
    
    public GameTickTimer(double fps) {
        this.fps = fps;
        milliDelay = 1000 / fps;
        running = true;
        this.startTimer();
    }
    
    public void declareHost(GameTickActor host) {
        this.host = host;
    }
    
    public void setFPS(double newfps) {
        this.fps = newfps;
        milliDelay = 1000.0 / newfps;
    }
    
    public void setMilliDelay(double newMilliDelay) {
        this.milliDelay = newMilliDelay;
        this.fps = 1000.0 / newMilliDelay;
    }
    
    public double getFPS() {
        return this.fps;
    }
    
    public double getMilliDelay() {
        return this.milliDelay;
    }
    
    public long getTickNumber() {
        return this.tick;
    }
    
    public void startTimer() {
      //long startTimeM = 0;
      //  long endTimeM = 0;
        long startTimeN = 0;
      //  long endTimeN = 0;
        this.tick = 0;
        while (running) {
            startTimeN = System.nanoTime();
            while ((System.nanoTime() - startTimeN) <= milliDelay * 1000000) {
            }
            tick++;
            host.tickProcess();
        }
    }
    
    public void stopTimer() {
        this.running = false;
    }
}
