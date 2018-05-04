/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

import javafx.application.Platform;

/**
 *
 * @author Cédric
 */
public class GameTickTimer {
    
    public GameTickActor host;
    
    public double fps;
    public long tick = 0;
    public double milliDelay;
    public boolean running;
    
    public TimeKeeper clock;
    public Thread clockThread;
    
    public GameTickTimer(double fps) {
        this.fps = fps;
        milliDelay = 1000 / fps;
        running = true;
    }

    public GameTickTimer() {
        this(60);
    }
    
    public void declareHost(GameTickActor host) {
        this.host = host;
        clock = new TimeKeeper();
        clockThread = new Thread(clock);
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
    
    public void startThread() {
        clockThread.start();
    }
    
    public void stopTimer() {
        this.running = false;
    }
    
    public class TimeKeeper implements Runnable {
        
        public GameTickActor send;
        
        public TimeKeeper() {
            this.send = host;
        }
        
        @Override
        public void run() {
            long startTimeN = 0;
            tick = 0;
            while (running) {
                startTimeN = System.nanoTime();
                
                while ((System.nanoTime() - startTimeN) <= milliDelay * 1000000) {
                    
                }
                
                tick++;
                
                send.tickProcess();
            }
        }
        
    }
}
