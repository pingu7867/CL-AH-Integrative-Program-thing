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
    public double wavelength = velocity/frequency;
    public double phaseShift = 0;
    
    public PeriodicFunction() {
        
    }
    
    public PeriodicFunction(double a, double f, double w, double vel) {
        this();
        this.frequency = f;
        this.amplitude = a;
        this.wavelength = w;
        this.velocity = vel;
    }
    
    public void setFrequency(double f) {
        this.frequency = f;
        this.wavelength = velocity/f;
    }
    public void setAmplitude(double a) {
        this.amplitude = a;
    }
    public void setWavelength(double w) {
        this.wavelength = w;
        this.frequency = velocity/w;
    }
    public void setVelocity(double v) {
        double ratio = v/velocity;
        this.velocity = v;
        
        this.frequency *= ratio;
        this.wavelength *= ratio;
    }
       
    public double getFrequency() {
        return this.frequency;
    }
    public double getAmplitude() {
        return this.amplitude;
    }
    public double getWavelength() {
        return this.wavelength;
    } 
    public double getVelocity() {
        return this.velocity;
    } 
}
    