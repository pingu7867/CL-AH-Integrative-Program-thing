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
public class SineWaveFunction extends PeriodicFunction {
    
    public SineWaveFunction() {
        super(100, 1,50);
    }
    public SineWaveFunction(double amp, double fre, double wavl) {
        super(amp,fre,wavl);
    }
    @Override
    public double evaluateAt(double x) {
        return amplitude*(Math.sin((wavelength + (x + phaseShift) )/wavelength * 2*Math.PI));
    }
}