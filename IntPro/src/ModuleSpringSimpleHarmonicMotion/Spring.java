/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleSpringSimpleHarmonicMotion;

import intpro.Element;
import java.io.File;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        
        if (weight == null) {weights.add(new Weight(0));}
        else {addWeight(weight); weight.sprite.setImage(new Image(new File("src/Assets/SpringWeight.png").toURI().toString()));}
        this.displacement = length + (getWeightSum() * kconstant);
        
        this.oscillator.amplitude = getWeightSum() / this.kconstant;
        
        this.length = this.oscillator.amplitude + this.length;
        this.oscillator.frequency = Math.sqrt(kconstant / (getWeightSum() * 9.8));
        declareOscillatorOwnership();
        
        for (int i = 0; i < ((int)Math.log10(kconstant + 10)) * 10; i++) {
            Line l = new Line(0, 0, 0, 0);
            l.setStrokeWidth(3 * Math.log(kconstant + 10));
            lines.add(l);
        }
        
        {
            Line l = new Line(0, 0, 0, 0);
            l.setStrokeWidth(3 * Math.log(kconstant + 10));
            lines.add(l);
        }
        
        for (int num = 0; num < lines.size(); num++) {
            setLine(num);
        }
        
        initializeLines();
        
    }
    
    public void checkAndFixParameters() {
        if (oscillator.frequency != ((1 / (2 * Math.PI)) * Math.sqrt((kconstant * 9.8) / getWeightSum()))) {
            oscillator.amplitude = getWeightSum() / this.kconstant;
            oscillator.frequency = Math.sqrt(kconstant / (getWeightSum() * 9.8));
        }
        this.displacement = length + (getWeightSum() * kconstant);
    }
    
    public void initializeLines() {
        for (int i = 0; i < lines.size(); i++) {
            module.pane.getChildren().add(lines.get(i));
        }
    }
    
    public void addWeight(double mass) {
        Weight w = new Weight(mass);
        w.sprite.setImage(new Image(new File("src/Assets/SpringWeight.png").toURI().toString()));
        weights.add(w);
        module.pane.getChildren().add(w.sprite);
        checkAndFixParameters();
    }
    
    public void addWeight(Weight w) {
        weights.add(w);
        w.sprite.setImage(new Image(new File("src/Assets/SpringWeight.png").toURI().toString()));
        module.pane.getChildren().add(w.sprite);
        checkAndFixParameters();
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
        //System.out.println("GUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUFFFFFIIN " + (oscillator == null));
        double v = oscillator.value;
        displacement = length + v;
    }
    
    public void declareOscillatorOwnership() {
        oscillator.declareOwnership(this);
    }
    
    public void refreshDisplay() {
        for (int n = 0; n < lines.size(); n++) {
            module.pane.getChildren().remove(lines.get(n));
            module.pane.getChildren().add(lines.get(n));
        }
        
        for (int m = weights.size() - 1; m >= 0; m--) {
            module.pane.getChildren().remove(weights.get(m).sprite);
            module.pane.getChildren().add(weights.get(m).sprite);
        }
        
    }
    
    @Override
    public void draw() {
        setLength();
        for (int n = 0; n < lines.size(); n++) {
            
            setLine(n);
            
        }
        for (int m = weights.size() - 1; m >= 0; m--) {
            ImageView iv = weights.get(m).sprite;
            iv.setScaleX(0.5);
            iv.setScaleY(0.5);
            iv.setX(xPosition);
            iv.setY(displacement + ((m - 0.8) * 70));
            Platform.runLater(() -> {
                iv.toFront();
            });
            
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
        lines.get(num).setStartX(xPosition + getPoint(num, 'x'));
        lines.get(num).setStartY(getPoint(num, 'y'));
        lines.get(num).setEndX(xPosition + getPoint(num + 1, 'x'));
        lines.get(num).setEndY(getPoint(num + 1, 'y'));
        
    }
    
}
