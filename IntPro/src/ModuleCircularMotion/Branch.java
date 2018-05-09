/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleCircularMotion;

import intpro.Element;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author Cédric
 */

public class Branch extends Element {
    double angleRad = 0;
    double radius = 300;
    double centriForce;
    double centerBoundAccel;
    double mass;
    double velocity;
    double angVelocity;
    double frequency;
    double period;
    
    double centerX = 600;
    double centerY = 400;
    
    public Thread animThread;
    public ImageView shuttle = new ImageView();
    
    Circle center = new Circle(600.0, 400.0, 10.0, Color.BLACK);
    Line link = new Line(600, 400, 600, 400);
    Text infoDump = new Text();
    
    
    public Branch() {
        this.radius = 100;
        this.frequency = 1;
        this.mass = 1;
        shuttle.setImage(new Image(new File("src/Assets/CircularMotionShuttle1.png").toURI().toString()));
        link.setStrokeWidth(3);
        
    }
    
    public void setFields(double radius, double frequency, double mass) {
        this.radius = radius;
        this.frequency = frequency;
        this.mass = mass;
        
        module.pane.getChildren().add(center);
        module.pane.getChildren().add(infoDump);
        module.pane.getChildren().add(link);
        infoDump.setX(centerX + radius*0.3);
        infoDump.setY(centerY - radius*0.7);
        module.pane.getChildren().add(shuttle);
        completeFields();
        
        center.setOnMouseDragged(e -> {
            
            module.pane.getChildren().remove(center);
            module.pane.getChildren().remove(infoDump);
            module.pane.getChildren().remove(shuttle);
            module.pane.getChildren().remove(link);
            
            module.elements.remove(this);});
    }
    
    public void setShuttleImage(int n) {
        switch (n) {
            case 1: shuttle.setImage(new Image(new File("src/Assets/CircularMotionShuttle1.png").toURI().toString())); break;
            case 2: shuttle.setImage(new Image(new File("src/Assets/CircularMotionShuttle2_1.png").toURI().toString())); 
                    animThread = new Thread(() -> {
                        while (true) {
                            shuttle.setImage(new Image(new File("src/Assets/CircularMotionShuttle2_1.png").toURI().toString()));
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Branch.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            shuttle.setImage(new Image(new File("src/Assets/CircularMotionShuttle2_2.png").toURI().toString()));
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Branch.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            shuttle.setImage(new Image(new File("src/Assets/CircularMotionShuttle2_3.png").toURI().toString()));
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Branch.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            shuttle.setImage(new Image(new File("src/Assets/CircularMotionShuttle2_2.png").toURI().toString()));
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Branch.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }); animThread.start(); break;
                    
            case 3: shuttle.setImage(new Image(new File("src/Assets/CircularMotionShuttle3.png").toURI().toString())); break;
        }
        
        
    }
    
    public void setCenter(double newX, double newY) {
        centerX = newX;
        centerY = newY;
        center.setCenterX(centerX);
        center.setCenterY(centerY);
        link.setStartX(centerX);
        link.setStartY(centerY);
        infoDump.setX(centerX + radius*0.3);
        infoDump.setY(centerY - radius*0.7);
    }
    
    public void completeFields() {
        this.angVelocity = 2 * Math.PI * this.frequency;
        this.velocity = this.radius * this.angVelocity;
        this.period = 1 / this.frequency;
        this.centerBoundAccel = this.velocity * this.velocity / this.radius;
        this.centriForce = this.centerBoundAccel * this.mass;
        this.centriForce = centerBoundAccel;
        
    }
    
    @Override
     public void draw() {
        angleRad = ((System.currentTimeMillis() / 1000.0) * frequency) * (2 * Math.PI);
        velX = Math.cos(angleRad);
        velY = Math.sin(angleRad);
        
        center.setRadius(((((System.currentTimeMillis() / 1000.0) * frequency) % 1) * 4) + 5);
        shuttle.setRotate(-90 + (angleRad * (360 / (2 * Math.PI))));
        link.setEndX(centerX - (velX * radius));
        link.setEndY(centerY - (velY * radius));
        shuttle.setX(-100 + centerX - (velX * radius));
        shuttle.setY(-100 + centerY - (velY * radius));
        
        infoDump.setText("radius: " + radius/100 + " m\rvelocity: " + velocity/100 + " m/s\rround time: " + period + " s\rcentripetal force: " + centriForce/100 + " N\rmass: " + mass + " kg\rx position: " + (int)shuttle.getX() + " px\ry position: " + (800 - (int)shuttle.getY()) + " px");
        
    }
    
}
