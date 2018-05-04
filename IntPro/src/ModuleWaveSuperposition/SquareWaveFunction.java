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
public class SquareWaveFunction extends PeriodicFunction {
    public SquareWaveFunction() {
        super(100, 1,50,50);
    }
    public SquareWaveFunction(double amp, double fre, double wavl, double velocity) {
        super(amp,fre,wavl,velocity);
    }
    public SquareWaveFunction(double amp, double fre, double wavl, double velocity, double phS) {
        super(amp,fre,wavl,velocity,phS);
    }
    @Override
    public double evaluateAt(double x, double posY) {
        if ((wavelength + (x + phaseShift + motionOffsetX) ) % wavelength < wavelength/2) {
            return posY - amplitude;
        }
        else {
            return posY + amplitude;
        }
    }            
}
