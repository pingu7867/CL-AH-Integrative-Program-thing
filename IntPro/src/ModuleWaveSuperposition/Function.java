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
public class Function {
    double motionOffsetX = 0;
    double velocity;
    public double evaluateAt(double x,double posY) {
        return 0 + motionOffsetX + posY;
    }
    
}
