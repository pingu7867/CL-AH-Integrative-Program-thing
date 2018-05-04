/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleSpringSimpleHarmonicMotion;

import intpro.Element;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;

/**
 *
 * @author Cédric
 */
public class Spring extends Element {
    public ArrayList<Line> lines;
    public ArrayList<Weight> weights = new ArrayList();
    
    public Oscillator oscillator;
    public double length;
    public double displacement;
    public double kconstant;
    public double width = 200;
    public double xPosition = 400;
    
    public Spring() {
        
    }
    
    public Spring(double length, double kconstant, Oscillator oscill, Weight weight) {
        this();
        this.lines = new ArrayList<Line>();
        this.displacement = length;
        this.kconstant = kconstant;
        this.oscillator = oscill;
        
        if (weight != null) {weights.add(weight); weight.sprite.setImage(new Image(new File("src/Assets/SpringWeight.png").toURI().toString()));}
        
    }
    
    public void setFields(double length, double kconstant, Oscillator oscill, Weight weight) {
        this.length = length;
        this.lines = new ArrayList<Line>();
        this.kconstant = kconstant;
        this.oscillator = oscill;
        this.oscillator.amplitude = getWeightSum() / this.kconstant;
        this.oscillator.frequency = Math.sqrt(kconstant / (getWeightSum() * 9.8));
        
        if (weight == null) {weights.add(new Weight(0));}
        else {weights.add(weight); weight.sprite.setImage(new Image(new File("src/Assets/SpringWeight.png").toURI().toString()));}
        this.displacement = length + (getWeightSum() * kconstant);
        
        setLength();
        for (int i = 0; i < ((int)Math.log10(kconstant + 10)) * 10; i++) {
            Line l = new Line(0, 0, 0, 0);
            l.setStrokeWidth(3 * Math.log(kconstant));
            lines.add(l);
            setLine(i);
            module.pane.getChildren().add(l);
        }
        
        System.out.println(this.length);
        System.out.println(this.displacement);
        System.out.println(lines.size());
        System.out.println(this.kconstant);
        System.out.println(getWeightSum());
        
        System.out.println("weight" + getWeightSum());
        System.out.println("item size " + lines.size());
    }
    
    public void checkAndFixParameters() {
        if (oscillator.frequency != ((1 / (2 * Math.PI)) * Math.sqrt((kconstant * 9.8) / getWeightSum()))) {
            oscillator.amplitude = getWeightSum() / this.kconstant;
            oscillator.frequency = Math.sqrt(kconstant / (getWeightSum() * 9.8));
        }
    }
    
    public void initializeLines() {
        for (int i = 0; i < lines.size(); i++) {
            module.pane.getChildren().add(lines.get(i));
        }
    }
    
    public void addWeight(double mass) {
        weights.add(new Weight(mass));
    }
    
    public double getWeightSum() {
        double weight = 0;
        for (int i = 0; i < weights.size(); i++) {
            if (weights.get(i) != null) {
                weight += weights.get(i).mass * 9.8;
            }
        }
        
        return weight;
    }
    
    public void setLength() {
        double v = oscillator.value;
        displacement = length + v;
        
    }
    
    @Override
    public void draw() {
        setLength();
        
        for (int level = 0; level < (int)kconstant * 20; level++) {
            setLine(2*level);
            setLine(2*level + 1);
        }
        
        for (int m = 0; m < weights.size(); m++) {
            weights.get(m).sprite.setScaleX(0.5);
            weights.get(m).sprite.setScaleY(0.5);
            weights.get(m).sprite.setX(xPosition);
            weights.get(m).sprite.setY(displacement + ((m + 0.3) * 100));
            
        }
        
    }
    
    public double getPoint(int num, char axis) {
        int size = lines.size();
        
        double value;
        if (axis == 'x') {
            value = width * (num % 2);
            return value;
        }
        if (axis == 'y') {
            value = Math.floor(num / 2.0) * (displacement / ((size-1) / 2));
            
            return value;
        }
        
        
        return 0;
    }
    
    public void setLine(int num) {
        System.out.println("num " + num);
        lines.get(num).setStartX(xPosition + getPoint(num, 'x')); System.out.print("x " + getPoint(num, 'x'));
        lines.get(num).setStartY(getPoint(num, 'y')); System.out.println(", y " + getPoint(num, 'y'));
        lines.get(num).setEndX(xPosition + getPoint(num + 1, 'x')); System.out.print("x " + getPoint(num + 1, 'x'));
        lines.get(num).setEndY(getPoint(num + 1, 'y')); System.out.println(", y " + getPoint(num + 1, 'y'));
        
    }
    
}
