
package ModuleIdealGas;
/**
 *
 * @author Amine
 */
import intpro.SpritedElement;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.animation.*;
import javafx.util.Duration;
import java.io.File;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class GasParticle extends SpritedElement{
    public Timeline animation;
    private double velocity;
    Rectangle bounds;
    double radius;
    double angle;
    public GasParticle(Rectangle bounds) {
        super(new ImageView());
        
        this.angle = Math.random() * (2*Math.PI);
        velocity = 0;
        
        this.bounds = bounds;
        
        sprite.setImage(new Image(new File("src/Assets/Molecule1.png").toURI().toString()));
        sprite.setFitHeight(10); sprite.setFitWidth(10);
       
        radius = sprite.getFitHeight()/2;
        posX = Math.random() * bounds.getWidth() + bounds.getX(); 
        posY = Math.random() * bounds.getHeight() + bounds.getY();
        
        sprite.setX(posX - radius);
        sprite.setY(1200 - (posY - radius));
    }
    public GasParticle(Rectangle bounds,double averageKineticEnergy) {
        this(bounds);
        velocity = Math.sqrt((averageKineticEnergy * 2)/(2*(1.6737236 * Math.pow(10, -27))));
        velocity /= 100;
        velX = velocity * Math.cos(angle);
        velY = velocity * Math.sin(angle);

        System.out.println("VelX = " + velX + " VelY = " + velY);
        
    }
    public double getVelocity() {
        return velocity;
    }
    public void setVelocityKineticEnergy(double averageKineticEnergy) {
        velocity = Math.sqrt((averageKineticEnergy * 2)/(2*(1.6737236 * Math.pow(10, -27))));
        velocity /= 100;
        
        velX = velocity * Math.cos(angle);
        velY = velocity * Math.sin(angle);
    }
    @Override
    public double getAngle() {
        return angle;
    }
    @Override
    public void setAngle(double angle) {
        
        if (angle < 0) {
            this.angle = 2*Math.PI + angle;
        }
        else if (angle >= 2 * Math.PI) {
            this.angle = angle % (2 * Math.PI);
        }
        this.angle = angle;
        velX = velocity * Math.cos(angle);
        velY = velocity * Math.sin(angle);
    }
    public void setBounds(Rectangle bou) {
       this.bounds = bou; 
    }
    public void setAnglefromVel() {
        if (this.velX >= 0 && this.velY >= 0) {
            this.angle = Math.acos(Math.abs(velX)/velocity);
        }
        else if (this.velX <= 0 && this.velY >= 0) {
            this.angle = Math.PI - Math.acos(Math.abs(velX)/velocity);
        }
        else if (this.velX <= 0 && this.velY <= 0) {
            this.angle = Math.PI + Math.acos(Math.abs(velX)/velocity);
        }
        else if (this.velX >= 0 && this.velY <= 0) {
            this.angle = 2*Math.PI - Math.acos(Math.abs(velX)/velocity);
        }
    }
    public void setVelocityfromVel() {
        this.velocity = Math.sqrt(velX * velX + velY * velY);
    }
    public void setAnimation(ArrayList<GasParticle> gasParticles,int index) {
        animation = new Timeline(new KeyFrame(Duration.millis(1000/30), e-> { animateGasParticleMotion(gasParticles, index);
        }));
        animation.setCycleCount(Timeline.INDEFINITE);
        
    }
       public void animateGasParticleMotion(ArrayList<GasParticle> gasParticles, int index) {
        for (int i = index + 1; i < gasParticles.size(); i++) { 
            if (2 * radius >= Math.sqrt((gasParticles.get(i).posX - posX) * (gasParticles.get(i).posX - posX)
                    + (gasParticles.get(i).posY - posY)*(gasParticles.get(i).posY - posY)))   {
                double radiusAngle1; double radiusAngle2;
                double radiusAngle3; double radiusAngle4;
                double tempAngle1;
                double tempAngle2;
                double tempVelocity1; double tempVelocity2;
                double tempVelocity3; double tempVelocity4;
                 
                if (gasParticles.get(i).posX > posX) { //This checks the resultant angle for each of the particles
                    
                    if (gasParticles.get(i).posY > posY) {
                        radiusAngle1 = Math.asin((gasParticles.get(i).posY - posY)/(2*radius)); //this
                        if (angle > 2*Math.PI + (radiusAngle1 - Math.PI/2)) {      
                            radiusAngle2 = radiusAngle1 - Math.PI/2;
                            tempAngle1 = radiusAngle1 -((2 * Math.PI) - angle);                          
                        }
                        else if (angle < radiusAngle1) {
                            radiusAngle2 = radiusAngle1 - Math.PI/2;
                            tempAngle1 = (radiusAngle1 - angle); 
                        }
                        else if (angle > radiusAngle1) {
                            radiusAngle2 = radiusAngle1 + Math.PI/2;
                            tempAngle1 = (angle - radiusAngle1);
                        }
                        else {
                            radiusAngle2 = radiusAngle1 + Math.PI/2;
                            tempAngle1 = 0;
                        }
                        tempVelocity1 = this.velocity * Math.cos(tempAngle1);
                        tempVelocity2 = this.velocity * Math.sin(tempAngle1);
                        
                        
                        radiusAngle3 = Math.asin((gasParticles.get(i).posY - posY)/(2*radius)) + Math.PI; //gasParticle.get(i)
                        if (gasParticles.get(i).angle < radiusAngle3) {
                            radiusAngle4 = radiusAngle3 - Math.PI/2;
                            tempAngle2 = radiusAngle3 - gasParticles.get(i).angle;
                        }
                        else if (gasParticles.get(i).angle > radiusAngle1) {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = gasParticles.get(i).angle - radiusAngle3;
                        }
                        else {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = 0;
                        }
                        tempVelocity3 = gasParticles.get(i).velocity * Math.sin(tempAngle2);
                        tempVelocity4 = gasParticles.get(i).velocity * Math.cos(tempAngle2);
                        
                        this.setVelX(tempVelocity2 * Math.cos(radiusAngle2) + tempVelocity4*Math.cos(radiusAngle3));
                        this.setVelY(tempVelocity2 * Math.sin(radiusAngle2) + tempVelocity4*Math.sin(radiusAngle3));
                        this.setAnglefromVel(); this.setVelocityfromVel();
                        
                        gasParticles.get(i).setVelX(tempVelocity1 * Math.cos(radiusAngle1) + tempVelocity3 * Math.cos(radiusAngle4));
                        gasParticles.get(i).setVelY(tempVelocity1 * Math.sin(radiusAngle1) + tempVelocity3 * Math.sin(radiusAngle4));
                        gasParticles.get(i).setAnglefromVel(); gasParticles.get(i).setVelocityfromVel();
                    }
                    else if (gasParticles.get(i).posY == posY) {
                        radiusAngle1 = 0;
                        if (angle > radiusAngle1 && angle <= Math.PI/2) {      
                            radiusAngle2 = radiusAngle1 + Math.PI/2;
                            tempAngle1 = angle - radiusAngle1;                          
                        }
                        else if (angle > (3/2) * Math.PI && angle <= 2*Math.PI) {
                            radiusAngle2 = (3/2 * Math.PI);
                            tempAngle1 = (2 * Math.PI) - angle; 
                        }
                        else {
                            radiusAngle2 = radiusAngle1 + Math.PI/2;
                            tempAngle1 = 0;
                        }
                        tempVelocity1 = this.velocity * Math.cos(tempAngle1);
                        tempVelocity2 = this.velocity * Math.sin(tempAngle1);
                        
                        
                        radiusAngle3 = Math.PI; //gasParticle.get(i)
                        if (gasParticles.get(i).angle < radiusAngle3) {
                            radiusAngle4 = radiusAngle3 - Math.PI/2;
                            tempAngle2 = radiusAngle3 - gasParticles.get(i).angle;
                        }
                        else if (gasParticles.get(i).angle > radiusAngle1) {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = gasParticles.get(i).angle - radiusAngle3;
                        }
                        else {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = 0;
                        }
                        tempVelocity3 = gasParticles.get(i).velocity * Math.sin(tempAngle2);
                        tempVelocity4 = gasParticles.get(i).velocity * Math.cos(tempAngle2);
                        
                        this.setVelX(tempVelocity2 * Math.cos(radiusAngle2) + tempVelocity4*Math.cos(radiusAngle3));
                        this.setVelY(tempVelocity2 * Math.sin(radiusAngle2) + tempVelocity4*Math.sin(radiusAngle3));
                        this.setAnglefromVel(); this.setVelocityfromVel();
                        
                        gasParticles.get(i).setVelX(tempVelocity1 * Math.cos(radiusAngle1) + tempVelocity3 * Math.cos(radiusAngle4));
                        gasParticles.get(i).setVelY(tempVelocity1 * Math.sin(radiusAngle1) + tempVelocity3 * Math.sin(radiusAngle4));
                        gasParticles.get(i).setAnglefromVel(); gasParticles.get(i).setVelocityfromVel();
                    }
                    
                    else if (gasParticles.get(i).posY < posY) {
                        radiusAngle1 = 2*Math.PI - Math.asin((posY - gasParticles.get(i).posY )/(2*radius));
                        if (angle > radiusAngle1 && angle <= 2 * Math.PI) {      
                            radiusAngle2 = (radiusAngle1 + Math.PI/2) % (2 * Math.PI);
                            tempAngle1 = angle - radiusAngle1;                          
                        }
                        else if (angle >= 0 && angle <= (radiusAngle1 + Math.PI/2) % (2 * Math.PI)) {
                            radiusAngle2 = (radiusAngle1 + Math.PI/2) % (2 * Math.PI);
                            tempAngle1 = angle; 
                        }
                        else if (angle < radiusAngle1) {
                            radiusAngle2 = radiusAngle1 - Math.PI/2;
                            tempAngle1 = radiusAngle1 - angle; 
                        }
                        else {
                            radiusAngle2 = radiusAngle1 - Math.PI/2;
                            tempAngle1 = 0;
                        }
                        tempVelocity1 = this.velocity * Math.cos(tempAngle1);
                        tempVelocity2 = this.velocity * Math.sin(tempAngle1);
                        
                        
                        radiusAngle3 = Math.PI - Math.asin((posY - gasParticles.get(i).posY)/(2*radius)) ; //gasParticle.get(i)
                        if (gasParticles.get(i).angle < radiusAngle3) {
                            radiusAngle4 = radiusAngle3 - Math.PI/2;
                            tempAngle2 = radiusAngle3 - gasParticles.get(i).angle;
                        }
                        else if (gasParticles.get(i).angle > radiusAngle1) {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = gasParticles.get(i).angle - radiusAngle3;
                        }
                        else {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = 0;
                        }
                        tempVelocity3 = gasParticles.get(i).velocity * Math.sin(tempAngle2);
                        tempVelocity4 = gasParticles.get(i).velocity * Math.cos(tempAngle2);
                        
                        this.setVelX(tempVelocity2 * Math.cos(radiusAngle2) + tempVelocity4*Math.cos(radiusAngle3));
                        this.setVelY(tempVelocity2 * Math.sin(radiusAngle2) + tempVelocity4*Math.sin(radiusAngle3));
                        this.setAnglefromVel(); this.setVelocityfromVel();
                        
                        gasParticles.get(i).setVelX(tempVelocity1 * Math.cos(radiusAngle1) + tempVelocity3 * Math.cos(radiusAngle4));
                        gasParticles.get(i).setVelY(tempVelocity1 * Math.sin(radiusAngle1) + tempVelocity3 * Math.sin(radiusAngle4));
                        gasParticles.get(i).setAnglefromVel(); gasParticles.get(i).setVelocityfromVel();
                        
                    }
                }
                else if (gasParticles.get(i).posX == posX) {
                     if (gasParticles.get(i).posY > posY) {
                        radiusAngle1 = Math.PI/2;
                        if (angle > radiusAngle1) {      
                            radiusAngle2 = radiusAngle1 + Math.PI/2;
                            tempAngle1 = angle - radiusAngle1;                          
                        }
                        else if (angle < radiusAngle1) {
                            radiusAngle2 = radiusAngle1 - Math.PI/2;
                            tempAngle1 = radiusAngle1 - angle; 
                        }
                        else {
                            radiusAngle2 = radiusAngle1 - Math.PI/2;
                            tempAngle1 = 0;
                        }
                        tempVelocity1 = this.velocity * Math.cos(tempAngle1);
                        tempVelocity2 = this.velocity * Math.sin(tempAngle1);
                        
                        
                        radiusAngle3 = (3/2) * Math.PI; //gasParticle.get(i)
                        if (gasParticles.get(i).angle < radiusAngle3) {
                            radiusAngle4 = radiusAngle3 - angle;
                            tempAngle2 = radiusAngle3 - gasParticles.get(i).angle;
                        }
                        else if (gasParticles.get(i).angle > radiusAngle1) {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = gasParticles.get(i).angle - radiusAngle3;
                        }
                        else {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = 0;
                        }
                        tempVelocity3 = gasParticles.get(i).velocity * Math.sin(tempAngle2);
                        tempVelocity4 = gasParticles.get(i).velocity * Math.cos(tempAngle2);
                        
                        this.setVelX(tempVelocity2 * Math.cos(radiusAngle2) + tempVelocity4*Math.cos(radiusAngle3));
                        this.setVelY(tempVelocity2 * Math.sin(radiusAngle2) + tempVelocity4*Math.sin(radiusAngle3));
                        this.setAnglefromVel(); this.setVelocityfromVel();
                        
                        gasParticles.get(i).setVelX(tempVelocity1 * Math.cos(radiusAngle1) + tempVelocity3 * Math.cos(radiusAngle4));
                        gasParticles.get(i).setVelY(tempVelocity1 * Math.sin(radiusAngle1) + tempVelocity3 * Math.sin(radiusAngle4));
                        gasParticles.get(i).setAnglefromVel(); gasParticles.get(i).setVelocityfromVel();
                        
                        }
                        else if (gasParticles.get(i).posY < posY) {
                        radiusAngle1 = (3/2)*Math.PI;
                        if (angle > radiusAngle1) {      
                            radiusAngle2 = radiusAngle1 + Math.PI/2;
                            tempAngle1 = angle - radiusAngle1;                          
                        }
                        else if (angle < radiusAngle1) {
                            radiusAngle2 = radiusAngle1 - Math.PI/2;
                            tempAngle1 = radiusAngle1 - angle; 
                        }
                        else {
                            radiusAngle2 = radiusAngle1 - Math.PI/2;
                            tempAngle1 = 0;
                        }
                        tempVelocity1 = this.velocity * Math.cos(tempAngle1);
                        tempVelocity2 = this.velocity * Math.sin(tempAngle1);
                        
                        
                        radiusAngle3 = Math.PI/2; //gasParticle.get(i)
                        if (gasParticles.get(i).angle < radiusAngle3) {
                            radiusAngle4 = radiusAngle3 - angle;
                            tempAngle2 = radiusAngle3 - gasParticles.get(i).angle;
                        }
                        else if (gasParticles.get(i).angle > radiusAngle1) {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = gasParticles.get(i).angle - radiusAngle3;
                        }
                        else {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = 0;
                        }
                        tempVelocity3 = gasParticles.get(i).velocity * Math.sin(tempAngle2);
                        tempVelocity4 = gasParticles.get(i).velocity * Math.cos(tempAngle2);
                        
                        this.setVelX(tempVelocity2 * Math.cos(radiusAngle2) + tempVelocity4*Math.cos(radiusAngle3));
                        this.setVelY(tempVelocity2 * Math.sin(radiusAngle2) + tempVelocity4*Math.sin(radiusAngle3));
                        this.setAnglefromVel(); this.setVelocityfromVel();
                        
                        gasParticles.get(i).setVelX(tempVelocity1 * Math.cos(radiusAngle1) + tempVelocity3 * Math.cos(radiusAngle4));
                        gasParticles.get(i).setVelY(tempVelocity1 * Math.sin(radiusAngle1) + tempVelocity3 * Math.sin(radiusAngle4));
                        gasParticles.get(i).setAnglefromVel(); gasParticles.get(i).setVelocityfromVel();
                        }
                    }
                
                else if (gasParticles.get(i).posX < posX) {
                    if (gasParticles.get(i).posY > posY) {
                        radiusAngle1 = Math.PI - Math.asin((gasParticles.get(i).posY - posY)/(2*radius));
                        if (angle < radiusAngle1) {
                            radiusAngle2 = radiusAngle1 - Math.PI/2;
                            tempAngle1 = radiusAngle1 - angle;
                        }
                        else if (angle > radiusAngle1) {
                            radiusAngle2 = radiusAngle1 + Math.PI/2;
                            tempAngle1 = angle - radiusAngle1;
                        }
                        else {
                            radiusAngle2 = radiusAngle1 + Math.PI/2;
                            tempAngle1 = 0;
                        }
                        tempVelocity1 = this.velocity * Math.cos(tempAngle1);
                        tempVelocity2 = this.velocity * Math.sin(tempAngle1);
                        
                        radiusAngle3 = 2*Math.PI - Math.asin((gasParticles.get(i).posY - posY)/(2*radius));
                        if (gasParticles.get(i).angle > radiusAngle3 && gasParticles.get(i).angle <= 2 * Math.PI) {      
                            radiusAngle4 = (radiusAngle3 + Math.PI/2) % (2 * Math.PI);
                            tempAngle2 = gasParticles.get(i).angle - radiusAngle1;                          
                        }
                        else if (gasParticles.get(i).angle >= 0 && gasParticles.get(i).angle <= (radiusAngle3 + Math.PI/2) % (2 * Math.PI)) {
                            radiusAngle4 = (radiusAngle3 + Math.PI/2) % (2 * Math.PI);
                            tempAngle2 = gasParticles.get(i).angle; 
                        }
                        else if (gasParticles.get(i).angle < radiusAngle1) {
                            radiusAngle4 = radiusAngle3 - Math.PI/2;
                            tempAngle2 = radiusAngle3 - gasParticles.get(i).angle; 
                        }
                        else {
                            radiusAngle4 = radiusAngle3 - Math.PI/2;
                            tempAngle2 = 0;
                        }
                        tempVelocity3 = gasParticles.get(i).velocity * Math.sin(tempAngle2);
                        tempVelocity4 = gasParticles.get(i).velocity * Math.cos(tempAngle2);
                        
                        this.setVelX(tempVelocity2 * Math.cos(radiusAngle2) + tempVelocity4*Math.cos(radiusAngle3));
                        this.setVelY(tempVelocity2 * Math.sin(radiusAngle2) + tempVelocity4*Math.sin(radiusAngle3));
                        this.setAnglefromVel(); this.setVelocityfromVel();
                        
                        gasParticles.get(i).setVelX(tempVelocity1 * Math.cos(radiusAngle1) + tempVelocity3 * Math.cos(radiusAngle4));
                        gasParticles.get(i).setVelY(tempVelocity1 * Math.sin(radiusAngle1) + tempVelocity3 * Math.sin(radiusAngle4));
                        gasParticles.get(i).setAnglefromVel(); gasParticles.get(i).setVelocityfromVel();
                    }
                    else if (gasParticles.get(i).posY == posY) {
                        radiusAngle1 = Math.PI;
                        if (angle < radiusAngle1) {
                            radiusAngle2 = radiusAngle1 - Math.PI/2;
                            tempAngle1 = radiusAngle1 - angle;
                        }
                        else if (angle > radiusAngle1) {
                            radiusAngle2 = radiusAngle1 + Math.PI/2;
                            tempAngle1 = angle - radiusAngle1;
                        }
                        else {
                            radiusAngle2 = radiusAngle1 + Math.PI/2;
                            tempAngle1 = 0;
                        }
                        tempVelocity1 = this.velocity * Math.cos(tempAngle1);
                        tempVelocity2 = this.velocity * Math.sin(tempAngle1);

                        radiusAngle3 = 0;
                        if (gasParticles.get(i).angle > radiusAngle3 && gasParticles.get(i).angle <= Math.PI/2) {      
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = angle - radiusAngle3;                          
                        }
                        else if (gasParticles.get(i).angle > (3/2) * Math.PI && angle <= 2*Math.PI) {
                            radiusAngle4 = (3/2 * Math.PI);
                            tempAngle2 = (2 * Math.PI) - angle; 
                        }
                        else {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = 0;
                        }
                        tempVelocity3 = gasParticles.get(i).velocity * Math.sin(tempAngle2);
                        tempVelocity4 = gasParticles.get(i).velocity * Math.cos(tempAngle2);
                        
                        this.setVelX(tempVelocity2 * Math.cos(radiusAngle2) + tempVelocity4*Math.cos(radiusAngle3));
                        this.setVelY(tempVelocity2 * Math.sin(radiusAngle2) + tempVelocity4*Math.sin(radiusAngle3));
                        this.setAnglefromVel(); this.setVelocityfromVel();
                        
                        gasParticles.get(i).setVelX(tempVelocity1 * Math.cos(radiusAngle1) + tempVelocity3 * Math.cos(radiusAngle4));
                        gasParticles.get(i).setVelY(tempVelocity1 * Math.sin(radiusAngle1) + tempVelocity3 * Math.sin(radiusAngle4));
                        gasParticles.get(i).setAnglefromVel(); gasParticles.get(i).setVelocityfromVel();
                    }
                    else if (gasParticles.get(i).posY < posY) {
                        
                        radiusAngle1 = Math.asin((posY - gasParticles.get(i).posY)/(2*radius)) + Math.PI;
                        if (angle < radiusAngle1) {
                            radiusAngle2 = radiusAngle1 - Math.PI/2;
                            tempAngle1 = radiusAngle1 - angle;
                        }
                        else if (angle > radiusAngle1) {
                            radiusAngle2 = radiusAngle1 + Math.PI/2;
                            tempAngle1 = angle - radiusAngle1;
                        }
                        else {
                            radiusAngle2 = radiusAngle1 + Math.PI/2;
                            tempAngle1 = 0;
                        }
                        tempVelocity1 = this.velocity * Math.cos(tempAngle1);
                        tempVelocity2 = this.velocity * Math.sin(tempAngle1);
                        
                        
                        radiusAngle3 = Math.asin((posY - gasParticles.get(i).posY)/(2*radius)); //gasParticle.get(i)
                        if (gasParticles.get(i).angle > 2*Math.PI + (radiusAngle1 - Math.PI/2)) {      
                            radiusAngle4 = radiusAngle3 - Math.PI/2;
                            tempAngle2 = radiusAngle3 -((2 * Math.PI) - gasParticles.get(i).angle);                          
                        }
                        else if (gasParticles.get(i).angle < radiusAngle1) {
                            radiusAngle4 = radiusAngle3 - Math.PI/2;
                            tempAngle2 = (radiusAngle3 - gasParticles.get(i).angle); 
                        }
                        else if (gasParticles.get(i).angle > radiusAngle1) {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = (gasParticles.get(i).angle - radiusAngle3);
                        }
                        else {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = 0;
                        }
                        
                        tempVelocity3 = gasParticles.get(i).velocity * Math.sin(tempAngle2);
                        tempVelocity4 = gasParticles.get(i).velocity * Math.cos(tempAngle2);
                        
                        this.setVelX(tempVelocity2 * Math.cos(radiusAngle2) + tempVelocity4*Math.cos(radiusAngle3));
                        this.setVelY(tempVelocity2 * Math.sin(radiusAngle2) + tempVelocity4*Math.sin(radiusAngle3));
                        this.setAnglefromVel(); this.setVelocityfromVel();
                        
                        gasParticles.get(i).setVelX(tempVelocity1 * Math.cos(radiusAngle1) + tempVelocity3 * Math.cos(radiusAngle4));
                        gasParticles.get(i).setVelY(tempVelocity1 * Math.sin(radiusAngle1) + tempVelocity3 * Math.sin(radiusAngle4));
                        gasParticles.get(i).setAnglefromVel(); gasParticles.get(i).setVelocityfromVel();
                        
                        
                        
                    }    
                }
                
            }
                         
        }
          
        if (sprite.getX() + sprite.getFitWidth() >= bounds.getX() + bounds.getWidth()) {
            sprite.setX(bounds.getX() + bounds.getWidth() - sprite.getFitWidth() - 2);
            posX = sprite.getX() + radius;
            velX *= -1;
           }
        else if (sprite.getX() <= sprite.getFitWidth() + bounds.getX()) {
            sprite.setX(bounds.getX() + sprite.getFitWidth() + 2);
            posX = sprite.getX() + radius;
            velX *= -1;
        }
        if (sprite.getY() + sprite.getFitHeight() >= bounds.getY() + bounds.getHeight()) {
            sprite.setY(bounds.getY() + bounds.getHeight() - sprite.getFitHeight() - 2);
            posY = 1200 + radius - sprite.getY();
            velY *= -1;
           }
        else if (sprite.getY() <= sprite.getFitHeight() + bounds.getY()) {
            sprite.setY(bounds.getY() + sprite.getFitHeight() + 2);
            posY = 1200 + radius - sprite.getY();
            velY *= -1;
        }

        posX += velX/30; posY += velY/30;
        sprite.setX(sprite.getX() + velX/30); sprite.setY(sprite.getY() - velY/30);
       
    }
    
}