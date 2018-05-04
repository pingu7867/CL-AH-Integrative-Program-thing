/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWaveSuperposition;

import intpro.Element;

import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
/**
 *
 * @author Amine
 */
public class Curve extends Element {
    Line xAxis = new Line();
    Polyline curve = new Polyline();
    ObservableList points = curve.getPoints();
    Function function;
    double distanceX;
    double time =0;
    BorderPane bordWPane;
    
    
    public Curve(Function f){
        this(f,1200);
    }
    public Curve(Function f,double resolutionX) {
        function = f;
        this.distanceX = resolutionX;
        xAxis.setStartX(0); xAxis.setEndX(resolutionX);
        xAxis.setStartY(400); xAxis.setEndY(400);
        xAxis.setStroke(Color.RED);
        posY = 400;
        GenerateCurve();
    }
    public Polyline getLine() {
        return curve;
    }
    public void GenerateCurve(){
        
        double x = 0;
            while (x < 1200){
            if (function.evaluateAt(x, posY) >= 0) {
            points.add(x); points.add(function.evaluateAt(x,posY));
            }
            x++;
            
        }
        
    }
    public void setPane(BorderPane bordWPane){
        this.bordWPane = bordWPane;
    }
    public BorderPane getPane() {
        return this.bordWPane;
    }
}
    

