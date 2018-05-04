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
public class SawWaveFunction extends PeriodicFunction {
    public SawWaveFunction() {
        super(100,1,50,50);
    }
    public SawWaveFunction(double amp, double fre, double wavl, double velocity) {
        super(amp,fre,wavl,velocity);
    }
    public SawWaveFunction(double amp, double fre, double wavl, double velocity, double phS) {
        super(amp,fre,wavl,velocity,phS);
    }
    @Override
    public double evaluateAt(double x, double posY) {
        if (velocity > 0) {
        return posY - amplitude*(((wavelength + (x + phaseShift + motionOffsetX) ) % wavelength)/wavelength);
    
        }
        else if (velocity < 0){
            x = 1200 - x;
        
            return posY- amplitude*(((wavelength + (x + phaseShift + motionOffsetX) ) % wavelength)/wavelength);
        }
        else {
            return 0;
        }
    
    }
}