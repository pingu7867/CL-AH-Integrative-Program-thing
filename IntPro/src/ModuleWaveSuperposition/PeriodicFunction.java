/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWaveSuperposition;

/**
 *
 * @author Cédric
 */
public class PeriodicFunction extends Function {
    public double frequency = 1;
    public double amplitude = 100;
    
    public PeriodicFunction() {
        
    }
    
    public PeriodicFunction(double a, double f) {
        this.frequency = f;
        this.amplitude = a;
    }
    
    public void setFrequency(double f) {
        this.frequency = f;
    }
    public void setAmplitude(double a) {
        this.amplitude = a;
    }
    public double getFrequency() {
        return this.frequency;
    }
    public double getAmplitude() {
        return this.amplitude;
    }
}
