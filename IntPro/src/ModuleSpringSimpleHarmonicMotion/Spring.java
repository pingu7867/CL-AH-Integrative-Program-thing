/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleSpringSimpleHarmonicMotion;

import intpro.Element;
import java.util.ArrayList;
import javafx.scene.shape.Line;

/**
 *
 * @author Cédric
 */
public class Spring extends Element {
    ArrayList<Line> lines;
    Oscillator oscillator;
    double length;
    double kconstant;
    Weight weight
    public Spring(double length, double kconstant, Oscillator oscill, Weight weight) {
        this.lines = new ArrayList<Line>();
        this.length = length;
        this.kconstant = kconstant;
        this.oscillator = oscill;
        this.weigth = weight;
    }
    
    public void setLength() {
        
    }
    
}
