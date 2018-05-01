/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWaveSuperposition;
import java.util.ArrayList;
/**
 *
 * @author Cédric
 */
public class CompositeFunction extends Function {
    ArrayList<Function> functions = new ArrayList<>();
    
    public CompositeFunction(){
        
    }
    public CompositeFunction(ArrayList<Function> fcts) {
        functions.addAll(fcts);
    }
    @Override
    public double evaluateAt(double x, double posY) {
        double result = 0;
        for (Function e: functions) {
            result += e.evaluateAt(x,posY);
        }
        return result;
    }
}
