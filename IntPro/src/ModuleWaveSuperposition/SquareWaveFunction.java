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
public class SquareWaveFunction extends PeriodicFunction {
    public SquareWaveFunction() {
        super(100, 1,50);
    }
    public SquareWaveFunction(double amp, double fre, double wavl) {
        super(amp,fre,wavl);
    }
    @Override
    public double evaluateAt(double x) {
        if ((wavelength + (x + phaseShift) ) % wavelength < wavelength/2) {
            return amplitude;
        }
        else {
            return -amplitude;
        }
    }            
}
