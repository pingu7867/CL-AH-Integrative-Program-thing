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
public class SawWaveFunction extends PeriodicFunction {
    public SawWaveFunction() {
        super(100,1);
    }
    @Override
    public double evaluateAt(double x) {
        
        return amplitude*(((wavelength + (x + phaseShift) ) % wavelength)/wavelength);
    }
}
