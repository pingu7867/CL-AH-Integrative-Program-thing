/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleMomentum;
import intpro.SpritedElement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.*;
import javafx.util.Duration;
import java.io.File;
import java.util.ArrayList;
/**
 *
 * @author Amine
 */
public class PhysicalBody extends SpritedElement{
    private double mass = 1;
    private double momentumX = 0;
    private double momentumY = 0;
    private double momentum =0;
    double angle = 0;
    double velocity = 0;
    private Timeline animation;
    String typeOfCollision = "elastic";
    ArrayList<PhysicalBody> list = new ArrayList<>();
    ArrayList<Boolean> connectedtoList = new ArrayList<>();
    int index;
    double minRadius;
    double maxRadius;
    
    public PhysicalBody() {
        super(new ImageView());
        this.momentumX = mass * velX;
        this.momentumY = mass * velY;
        sprite.setImage(new Image(new File("src/Assets/PhysicalBody.png").toURI().toString()));
        sprite.setX(0); sprite.setY(0);
       
        sprite.setFitHeight(50); sprite.setFitWidth(50);
        
        posX = sprite.getX() + sprite.getFitWidth(); posY = 800 - (sprite.getY()+sprite.getFitHeight());
        angle = 0;
        sprite.setOnMouseDragged(eh-> {
            if (canMove && eh.getX() >= 0 && eh.getX() + sprite.getFitWidth() <= 1200  
                    && eh.getY() >= 0 && eh.getY() + sprite.getFitHeight() <= 700
                    && !checkIntersectsBodiesBeforeAnimation()) {
                
            sprite.setX(eh.getX()); sprite.setY(eh.getY());
            posX = sprite.getX() + sprite.getFitWidth();
            posY = 800 - (sprite.getY()+sprite.getFitHeight());
            }
        });
        
        minRadius = sprite.getFitWidth()/2; 
        maxRadius = Math.sqrt((sprite.getFitHeight()/2) * (sprite.getFitHeight()/2) + (sprite.getFitWidth()/2) * (sprite.getFitWidth()/2));
        
    }
    public PhysicalBody(double mass, double velX, double velY) {
        this();
        this.velX = velX;
        this.velY = velY;
        this.mass = mass;
        this.momentumX = mass * velX;
        this.momentumY = mass * velY;
        
    }
    public double getMass() {
        return mass;
    }
    public double getMomentumX() {
        return momentumX;
    }
    public double getMomentumY() {
        return momentumY;
    }
    public double getMomentum() {
        return momentum;
    }
        @Override
    public double getAngle() {
        return angle;
    }
    public double getVelocity() {
        return velocity;
    }
    public void setMass(double mass) {
        this.mass = mass;
        setMomentumX(mass * velX); setMomentumY(mass * velY);
    }
    public void setMomentumX(double momentumX) {
        this.momentumX = momentumX;
        velX = momentumX/mass;
        setMomentum(Math.sqrt(momentumX*momentumX + momentumY*momentumY));
    }
    public void setMomentumY(double momentumY) {
        this.momentumY = momentumY;
        velY = momentumY/mass;
        setMomentum(Math.sqrt(momentumX*momentumX + momentumY*momentumY));
    }
    @Override
    public void setVelX(double velX) {
        this.velX = velX;
        setMomentumX(mass * velX);
    }
    @Override
    public void setVelY(double velY) {
        this.velY = velY;
        setMomentumY(mass * velY);
    }
    public void setVelocity(double vel) {
        this.velocity = vel;
        setVelX(velocity * Math.cos(angle));
        setVelY(velocity * Math.sin(angle));
        
        
    }
    @Override
    public void setAngle(double angle) {
        if (angle < 0) {
            this.angle = 2*Math.PI + angle;
        }
        else if (angle >= 2 * Math.PI) {
            this.angle = angle % (2 * Math.PI);
        }
        
        setVelX(velocity * Math.cos(angle));
        setVelY(velocity * Math.sin(angle));
    }
    public void setMomentum(double momentum){
        this.momentum = momentum;
    }
    
    public void setTypeOfCollision(String type) {
        this.typeOfCollision = type;
    }
    public void setUpCheck(String typeOfCollision, ArrayList<PhysicalBody> list, int index) {
        this.index = index;
        this.list = list;
        for (int i = 0; i < list.size(); i++) {
            connectedtoList.add(false);
        }
        animation = new Timeline(new KeyFrame(Duration.millis(1000/30), e-> bodyMotionAnimation()
        ));
        animation.setCycleCount(Timeline.INDEFINITE);
    }

    public void play() {
        animation.play();
    }
    public void pause() {
        animation.pause();
    }
    public boolean checkIntersectsBodiesBeforeAnimation() {
        for (int i = index + 1; i< list.size(); i++) {
            if (this.sprite.intersects(list.get(i).sprite.getBoundsInLocal())) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
     }
    
    public void bodyMotionAnimation() {
        if (sprite.getX() <= 0 || sprite.getX() + sprite.getFitWidth() >= 1200) {
            setVelX(getVelX() * -1);
        }
        if (sprite.getY() <= 0 || sprite.getY() + sprite.getFitHeight() >= 800) {
            setVelY(getVelY() * -1);
        }
        for (int i = index + 1; i < list.size(); i++) {
            
            if (this.sprite.intersects(list.get(i).sprite.getBoundsInLocal())){
                if (!connectedtoList.get(i)) {
                
                double radiusAngle1; double radiusAngle2;
                double radiusAngle3; double radiusAngle4;
                double tempAngle1;
                double tempAngle2;
                double tempMomentum1; double tempMomentum2;
                double tempMomentum3; double tempMomentum4;
                
                
                if (list.get(i).posX > posX + 5) { //This checks the resultant angle for each of the particles
                    
                    if (list.get(i).posY > posY + 5) {
                        radiusAngle1 = Math.asin((list.get(i).posY - posY)/(2*maxRadius)); //this
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
                        tempMomentum1 = this.momentum * Math.cos(tempAngle1);
                        tempMomentum2 = this.momentum * Math.sin(tempAngle1);
                        
                        
                        radiusAngle3 = Math.asin((list.get(i).posY - posY)/(2*maxRadius)) + Math.PI; //gasParticle.get(i)
                        if (list.get(i).angle < radiusAngle3) {
                            radiusAngle4 = radiusAngle3 - Math.PI/2;
                            tempAngle2 = radiusAngle3 - list.get(i).angle;
                        }
                        else if (list.get(i).angle > radiusAngle1) {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = list.get(i).angle - radiusAngle3;
                        }
                        else {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = 0;
                        }
                        tempMomentum3 = list.get(i).getMomentum()* Math.sin(tempAngle2);
                        tempMomentum4 = list.get(i).getMomentum() * Math.cos(tempAngle2);
                        
                        collisionHandler(list.get(i), tempMomentum2 * Math.cos(radiusAngle2) + tempMomentum4*Math.cos(radiusAngle3),
                                tempMomentum2 * Math.sin(radiusAngle2) + tempMomentum4*Math.sin(radiusAngle3),
                                tempMomentum1 * Math.cos(radiusAngle1) + tempMomentum3 * Math.cos(radiusAngle4),
                                tempMomentum1 * Math.sin(radiusAngle1) + tempMomentum3 * Math.sin(radiusAngle4));
                        
                    }
                    else if (list.get(i).posY <= posY + 5 && list.get(i).posY >= posY - 5) {
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
                        tempMomentum1 = this.momentum * Math.cos(tempAngle1);
                        tempMomentum2 = this.momentum * Math.sin(tempAngle1);
                        
                        
                        radiusAngle3 = Math.PI; //gasParticle.get(i)
                        if (list.get(i).angle < radiusAngle3) {
                            radiusAngle4 = radiusAngle3 - Math.PI/2;
                            tempAngle2 = radiusAngle3 - list.get(i).angle;
                        }
                        else if (list.get(i).angle > radiusAngle1) {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = list.get(i).angle - radiusAngle3;
                        }
                        else {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = 0;
                        }
                        tempMomentum3 = list.get(i).getMomentum() * Math.sin(tempAngle2);
                        tempMomentum4 = list.get(i).getMomentum() * Math.cos(tempAngle2);
                        
                        collisionHandler(list.get(i), tempMomentum2 * Math.cos(radiusAngle2) + tempMomentum4*Math.cos(radiusAngle3),
                                tempMomentum2 * Math.sin(radiusAngle2) + tempMomentum4*Math.sin(radiusAngle3),
                                tempMomentum1 * Math.cos(radiusAngle1) + tempMomentum3 * Math.cos(radiusAngle4),
                                tempMomentum1 * Math.sin(radiusAngle1) + tempMomentum3 * Math.sin(radiusAngle4));
                    }
                    
                    else if (list.get(i).posY < posY - 5) {
                        radiusAngle1 = 2*Math.PI - Math.asin((posY - list.get(i).posY )/(2*maxRadius));
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
                        tempMomentum1 = this.momentum * Math.cos(tempAngle1);
                        tempMomentum2 = this.momentum * Math.sin(tempAngle1);
                        
                        
                        radiusAngle3 = Math.PI - Math.asin((posY - list.get(i).posY)/(2*maxRadius)) ; //gasParticle.get(i)
                        if (list.get(i).angle < radiusAngle3) {
                            radiusAngle4 = radiusAngle3 - Math.PI/2;
                            tempAngle2 = radiusAngle3 - list.get(i).angle;
                        }
                        else if (list.get(i).angle > radiusAngle1) {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = list.get(i).angle - radiusAngle3;
                        }
                        else {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = 0;
                        }
                        tempMomentum3 = list.get(i).getMomentum()* Math.sin(tempAngle2);
                        tempMomentum4 = list.get(i).getMomentum() * Math.cos(tempAngle2);
                        
                        collisionHandler(list.get(i), tempMomentum2 * Math.cos(radiusAngle2) + tempMomentum4*Math.cos(radiusAngle3),
                                tempMomentum2 * Math.sin(radiusAngle2) + tempMomentum4*Math.sin(radiusAngle3),
                                tempMomentum1 * Math.cos(radiusAngle1) + tempMomentum3 * Math.cos(radiusAngle4),
                                tempMomentum1 * Math.sin(radiusAngle1) + tempMomentum3 * Math.sin(radiusAngle4));
                        
                    }
                }
                else if (list.get(i).posX <= posX + 5 && list.get(i).posX >= posX - 5  ) {
                     if (list.get(i).posY > posY) {
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
                        tempMomentum1 = this.momentum * Math.cos(tempAngle1);
                        tempMomentum2 = this.momentum * Math.sin(tempAngle1);
                        
                        
                        radiusAngle3 = (3/2) * Math.PI; //gasParticle.get(i)
                        if (list.get(i).angle < radiusAngle3) {
                            radiusAngle4 = radiusAngle3 - angle;
                            tempAngle2 = radiusAngle3 - list.get(i).angle;
                        }
                        else if (list.get(i).angle > radiusAngle1) {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = list.get(i).angle - radiusAngle3;
                        }
                        else {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = 0;
                        }
                        tempMomentum3 = list.get(i).getMomentum() * Math.sin(tempAngle2);
                        tempMomentum4 = list.get(i).getMomentum() * Math.cos(tempAngle2);
                        
                        collisionHandler(list.get(i), tempMomentum2 * Math.cos(radiusAngle2) + tempMomentum4*Math.cos(radiusAngle3),
                                tempMomentum2 * Math.sin(radiusAngle2) + tempMomentum4*Math.sin(radiusAngle3),
                                tempMomentum1 * Math.cos(radiusAngle1) + tempMomentum3 * Math.cos(radiusAngle4),
                                tempMomentum1 * Math.sin(radiusAngle1) + tempMomentum3 * Math.sin(radiusAngle4));
                        
                        }
                        else if (list.get(i).posY < posY) {
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
                        tempMomentum1 = this.momentum * Math.cos(tempAngle1);
                        tempMomentum2 = this.momentum * Math.sin(tempAngle1);
                        
                        
                        radiusAngle3 = Math.PI/2; //gasParticle.get(i)
                        if (list.get(i).angle < radiusAngle3) {
                            radiusAngle4 = radiusAngle3 - angle;
                            tempAngle2 = radiusAngle3 - list.get(i).angle;
                        }
                        else if (list.get(i).angle > radiusAngle1) {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = list.get(i).angle - radiusAngle3;
                        }
                        else {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = 0;
                        }
                        tempMomentum3 = list.get(i).getMomentum() * Math.sin(tempAngle2);
                        tempMomentum4 = list.get(i).getMomentum()* Math.cos(tempAngle2);
                        
                        collisionHandler(list.get(i), tempMomentum2 * Math.cos(radiusAngle2) + tempMomentum4*Math.cos(radiusAngle3),
                                tempMomentum2 * Math.sin(radiusAngle2) + tempMomentum4*Math.sin(radiusAngle3),
                                tempMomentum1 * Math.cos(radiusAngle1) + tempMomentum3 * Math.cos(radiusAngle4),
                                tempMomentum1 * Math.sin(radiusAngle1) + tempMomentum3 * Math.sin(radiusAngle4));
                        }
                    }
                
                else if (list.get(i).posX < posX - 5) {
                    if (list.get(i).posY > posY + 5) {
                        radiusAngle1 = Math.PI - Math.asin((list.get(i).posY - posY)/(2*maxRadius));
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
                        tempMomentum1 = this.momentum * Math.cos(tempAngle1);
                        tempMomentum2 = this.momentum * Math.sin(tempAngle1);
                        
                        radiusAngle3 = 2*Math.PI - Math.asin((list.get(i).posY - posY)/(2*maxRadius));
                        if (list.get(i).angle > radiusAngle3 && list.get(i).angle <= 2 * Math.PI) {      
                            radiusAngle4 = (radiusAngle3 + Math.PI/2) % (2 * Math.PI);
                            tempAngle2 = list.get(i).angle - radiusAngle1;                          
                        }
                        else if (list.get(i).angle >= 0 && list.get(i).angle <= (radiusAngle3 + Math.PI/2) % (2 * Math.PI)) {
                            radiusAngle4 = (radiusAngle3 + Math.PI/2) % (2 * Math.PI);
                            tempAngle2 = list.get(i).angle; 
                        }
                        else if (list.get(i).angle < radiusAngle1) {
                            radiusAngle4 = radiusAngle3 - Math.PI/2;
                            tempAngle2 = radiusAngle3 - list.get(i).angle; 
                        }
                        else {
                            radiusAngle4 = radiusAngle3 - Math.PI/2;
                            tempAngle2 = 0;
                        }
                        tempMomentum3 = list.get(i).getMomentum() * Math.sin(tempAngle2);
                        tempMomentum4 = list.get(i).getMomentum() * Math.cos(tempAngle2);
                        
                        collisionHandler(list.get(i), tempMomentum2 * Math.cos(radiusAngle2) + tempMomentum4*Math.cos(radiusAngle3),
                                tempMomentum2 * Math.sin(radiusAngle2) + tempMomentum4*Math.sin(radiusAngle3),
                                tempMomentum1 * Math.cos(radiusAngle1) + tempMomentum3 * Math.cos(radiusAngle4),
                                tempMomentum1 * Math.sin(radiusAngle1) + tempMomentum3 * Math.sin(radiusAngle4));
                    }
                    else if (list.get(i).posY <= posY + 5 && list.get(i).posY >= posY - 5) {
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
                        tempMomentum1 = this.momentum * Math.cos(tempAngle1);
                        tempMomentum2 = this.momentum* Math.sin(tempAngle1);

                        radiusAngle3 = 0;
                        if (list.get(i).angle > radiusAngle3 && list.get(i).angle <= Math.PI/2) {      
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = angle - radiusAngle3;                          
                        }
                        else if (list.get(i).angle > (3/2) * Math.PI && angle <= 2*Math.PI) {
                            radiusAngle4 = (3/2 * Math.PI);
                            tempAngle2 = (2 * Math.PI) - angle; 
                        }
                        else {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = 0;
                        }
                        tempMomentum3 = list.get(i).getMomentum() * Math.sin(tempAngle2);
                        tempMomentum4 = list.get(i).getMomentum() * Math.cos(tempAngle2);
                        
                        collisionHandler(list.get(i), tempMomentum2 * Math.cos(radiusAngle2) + tempMomentum4*Math.cos(radiusAngle3),
                                tempMomentum2 * Math.sin(radiusAngle2) + tempMomentum4*Math.sin(radiusAngle3),
                                tempMomentum1 * Math.cos(radiusAngle1) + tempMomentum3 * Math.cos(radiusAngle4),
                                tempMomentum1 * Math.sin(radiusAngle1) + tempMomentum3 * Math.sin(radiusAngle4));
                    }
                    else if (list.get(i).posY < posY - 5) {
                        
                        radiusAngle1 = Math.asin((posY - list.get(i).posY)/(2*maxRadius)) + Math.PI;
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
                        tempMomentum1 = this.momentum * Math.cos(tempAngle1);
                        tempMomentum2 = this.momentum * Math.sin(tempAngle1);
                        
                        
                        radiusAngle3 = Math.asin((posY - list.get(i).posY)/(2*maxRadius)); //gasParticle.get(i)
                        if (list.get(i).angle > 2*Math.PI + (radiusAngle1 - Math.PI/2)) {      
                            radiusAngle4 = radiusAngle3 - Math.PI/2;
                            tempAngle2 = radiusAngle3 -((2 * Math.PI) - list.get(i).angle);                          
                        }
                        else if (list.get(i).angle < radiusAngle1) {
                            radiusAngle4 = radiusAngle3 - Math.PI/2;
                            tempAngle2 = (radiusAngle3 - list.get(i).angle); 
                        }
                        else if (list.get(i).angle > radiusAngle1) {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = (list.get(i).angle - radiusAngle3);
                        }
                        else {
                            radiusAngle4 = radiusAngle3 + Math.PI/2;
                            tempAngle2 = 0;
                        }
                        
                        tempMomentum3 = list.get(i).getMomentum() * Math.sin(tempAngle2);
                        tempMomentum4 = list.get(i).getMomentum() * Math.cos(tempAngle2);
                        
                        collisionHandler(list.get(i), tempMomentum2 * Math.cos(radiusAngle2) + tempMomentum4*Math.cos(radiusAngle3),
                                tempMomentum2 * Math.sin(radiusAngle2) + tempMomentum4*Math.sin(radiusAngle3),
                                tempMomentum1 * Math.cos(radiusAngle1) + tempMomentum3 * Math.cos(radiusAngle4),
                                tempMomentum1 * Math.sin(radiusAngle1) + tempMomentum3 * Math.sin(radiusAngle4));
                        
                    }    
                }
            }
                            
        }
        }
        posX += velX/30; posY += velY/30;
        sprite.setX(sprite.getX() + velX/30); sprite.setY(sprite.getY() - velY/30);
    }
    public void collisionHandler(PhysicalBody body, double momentumXthis, double momentumYthis, double momentumXbody, double momentumYbody) {

        if (typeOfCollision.equals("elastic")) {
            this.setMomentumX(momentumXthis);
            body.setMomentumX(momentumXbody);
            
            this.setMomentumY(momentumYthis);
            body.setMomentumY(momentumYbody);
        }
        else if (typeOfCollision.equals("inelastic")) {
            this.setMomentumX(0.8 * momentumXthis);
            body.setMomentumX(0.8 * momentumXbody);
            
            this.setMomentumY(0.8 * momentumYthis);
            body.setMomentumY(0.8 * momentumYbody);
        }
        else if (typeOfCollision.equals("perfectly inelastic")) {
            double totmass = body.getMass() + this.getMass();
            double totMomentumX = momentumXthis + momentumXbody;
            double totMomentumY = momentumYthis + momentumYbody;
            
            this.setMass(mass); body.setMass(mass);
            this.setVelX(totMomentumX/mass); body.setVelX(totMomentumX/mass);
            this.setVelY(totMomentumY/mass); body.setVelY(totMomentumY/mass);
            
            this.connectedtoList.set(body.index, true);
            body.connectedtoList.set(this.index, true);
            
        }
        this.setAngleAfterCollision();
        body.setAngleAfterCollision();
    }
    public void setAngleAfterCollision() {
        
        if (this.momentumX >= 0 && this.momentumY >= 0) {
            this.angle = Math.acos(Math.abs(momentumX)/momentum);
        }
        else if (this.momentumX <= 0 && this.momentumY >= 0) {
            this.angle = Math.PI - Math.acos(Math.abs(momentumX)/momentum);
        }
        else if (this.momentumX <= 0 && this.momentumY <= 0) {
            this.angle = Math.PI + Math.acos(Math.abs(momentumX)/momentum);
        }
        else if (this.velX >= 0 && this.velY <= 0) {
            this.angle = 2*Math.PI - Math.acos(Math.abs(momentumX)/momentum);
        }
    }
    
}
