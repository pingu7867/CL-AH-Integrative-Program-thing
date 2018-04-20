/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWaveSuperposition;

import intpro.Element;
import java.util.ArrayList;
import java.util.HashSet;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.collections.ObservableList;
import intpro.Module;

/**
 *
 * @author Cédric
 */
public class Curve extends Element {
    /*HashSet<Line> curve = new HashSet<Line>();
    ArrayList<Point> points = new ArrayList<Point>();
    */
    Line xAxis = new Line();
    Line yAxis = new Line();
    Polyline curve = new Polyline();
    ObservableList points = curve.getPoints();
    
    Function function;
    
    public Curve(Function f) {
        function = f;
        
    }
    public void GenerateCurve(double resolutionX){
        if (function instanceof PeriodicFunction) {
            
            double x = 0;
            while (x < resolutionX){
            points.add(x); points.add(function.evaluateAt(x));
            x++;
        }
        }
        
    }
}
