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
    @Override
    public double evaluateAt(double x) {
        return slope * (x + motionOffsetX);
    }
}
