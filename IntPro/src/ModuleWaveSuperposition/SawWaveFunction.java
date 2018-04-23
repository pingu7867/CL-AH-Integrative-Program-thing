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
        super(100,1,50);
    }
    public SawWaveFunction(double amp, double fre, double wavl) {
        super(amp,fre,wavl);
    }
    @Override
    public double evaluateAt(double x) {
        if (velocity > 0) {
        return amplitude*(((wavelength + (x + phaseShift + motionOffsetX) ) % wavelength)/wavelength);
    
        }
        else if (velocity < 0){
            x = 1200 - x;
        
            return amplitude*(((wavelength + (x + phaseShift + motionOffsetX) ) % wavelength)/wavelength);
        }
        else {
            return 0;
        }
    
}
}