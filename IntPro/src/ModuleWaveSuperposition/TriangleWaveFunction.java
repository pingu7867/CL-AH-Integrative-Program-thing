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
public class TriangleWaveFunction extends PeriodicFunction {
    public TriangleWaveFunction() {
        super(100,1);
    }
    @Override
    public double evaluateAt(double x) {
        return (2 * amplitude/wavelength) * (Math.abs(((wavelength + (x + phaseShift) ) % wavelength)- wavelength/2) - wavelength/4);
    }
}
