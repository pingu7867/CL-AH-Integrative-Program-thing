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
public class LinearFunction extends Function {
    double slope;
    double yintercept;
    public LinearFunction() {
        
    }
    public LinearFunction(double slo, double yinter) {
        slope = slo;
        yintercept = yinter;
    }
    @Override
    public double evaluateAt(double x, double posY) {
        return posY - slope * (x + motionOffsetX);
    }
}
