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
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
/**
 *
 * @author Cédric
 */
public class Curve extends Element {
    /*HashSet<Line> curve = new HashSet<Line>();
    ArrayList<Point> points = new ArrayList<Point>();
    */
    Line xAxis = new Line();
    Polyline curve = new Polyline();
    ObservableList points = curve.getPoints();
    Timeline animation;
    Function function;
    double resolutionX;
    double time =0;
    
    public Curve(Function f,double resolutionX) {
        function = f;
        this.resolutionX = resolutionX;
        GenerateCurve();
        xAxis.setStartX(0); xAxis.setEndX(resolutionX);
        xAxis.setStartY(200); xAxis.setEndY(200);
        posY = 200;
        
        animation = new Timeline(new KeyFrame(Duration.millis(1000/30), e-> AnimateWaveMotion()));
        animation.setCycleCount(Timeline.INDEFINITE);
    }
    public void play() {
        animation.play();
    }
    public void pause() {
        animation.pause();
    }
    public void GenerateCurve(){
        double x = 0;
            while (x < 1200){
            points.add(x); points.add(function.evaluateAt(x,posY));
            x++;
        
        }
        
    }
    public void AnimateWaveMotion() {
        if (function instanceof PeriodicFunction) {
            points.clear();
            time += 1000/30;
            function.motionOffsetX = function.velocity * time;
            
            GenerateCurve();
            
        }
    }
}
