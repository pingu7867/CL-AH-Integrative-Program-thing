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
public class SineWaveFunction extends PeriodicFunction {
    
    public SineWaveFunction() {
        super(100, 1,50,50);
    }
    public SineWaveFunction(double amp, double fre, double wavl, double velocity) {
        super(amp,fre,wavl,velocity);
    }
    public SineWaveFunction(double amp, double fre, double wavl, double velocity, double phS) {
        super(amp,fre,wavl,velocity,phS);
    }
    @Override
    public double evaluateAt(double x, double posY) {
        return posY - amplitude*(Math.sin((wavelength + (x + motionOffsetX + phaseShift) )/wavelength * 2*Math.PI));
    }
}
