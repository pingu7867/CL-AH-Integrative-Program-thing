/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWaveSuperposition;

/**
 *
 * @author Amine
 */
public class TriangleWaveFunction extends PeriodicFunction {
    public TriangleWaveFunction() {
        super(100, 1,50,50);
    }
    public TriangleWaveFunction(double amp, double fre, double wavl,double velocity) {
        super(amp,fre,wavl,velocity);
    }
    public TriangleWaveFunction(double amp, double fre, double wavl,double velocity,double phS) {
        super(amp,fre,wavl,velocity,phS);
    }
    @Override
    public double evaluateAt(double x, double posY) {
        return posY - (2 * amplitude/wavelength) * (Math.abs(((wavelength + (x + phaseShift + motionOffsetX) ) % wavelength)- wavelength/2) - wavelength/4);
    }
}
