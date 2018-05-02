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

public class PolynomialFunction extends Function {
    
    int degree = 0;
    
    public PolynomialFunction() {
        
    }
    
    public PolynomialFunction(int deg) {
        this.degree = deg;
    }
    @Override
    public double evaluateAt(double x, double posY) {
        double result = 1;
        for (int i = 0; i < degree; i++) {
            result*=x;
        }
        return posY - result;
    }
    
}
