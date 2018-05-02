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
    double angle = 0;
    double velocity = 0;
    private Timeline animation;
    String typeOfCollision = "elastic";
    ArrayList<PhysicalBody> list = new ArrayList<>();
    ArrayList<Boolean> connectedtoList = new ArrayList<>();
    int index;
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
            if (canMove) {
            sprite.setX(eh.getX()); sprite.setY(eh.getY());
            posX = sprite.getX() + sprite.getFitWidth();
            posY = 800 - (sprite.getY()+sprite.getFitHeight());
            }
        });
        
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
    
    public void setMass(double mass) {
        this.mass = mass;
        setMomentumX(mass * velX); setMomentumY(mass * velY);
    }
    public void setMomentumX(double momentumX) {
        this.momentumX = momentumX;
    }
    public void setMomentumY(double momentumY) {
        this.momentumY = momentumY;
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
    public void setTypeOfCollision(String type) {
        this.typeOfCollision = type;
    }
    public void setUp(String typeOfCollision, ArrayList<PhysicalBody> list, int index) {
        this.index = index;
        this.list = list;
        for (int i = 0; i < list.size(); i++) {
            connectedtoList.add(false);
        }
        animation = new Timeline(new KeyFrame(Duration.millis(1000/30), e-> bodyMotionAnimation()
        ));
        animation.setCycleCount(Timeline.INDEFINITE);
    }
    @Override
    public double getAngle() {
        return angle;
    }
    public double getVelocity() {
        return velocity;
    }
    public void play() {
        animation.play();
    }
    public void pause() {
        animation.pause();
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
                double radiusAngle;
                double tempAngle1;
                double tempAngle2;
                double tempAngle3;
                double tempAngle4;
                
                if (list.get(i).posX > posX) {
                    
                    if (list.get(i).posY > posY) { //This checks the resultant angle for each of the bodies
                        radiusAngle = Math.PI/4;
                        tempAngle1 = (list.get(i).getAngle() - Math.PI - radiusAngle) * -1;
                        tempAngle2 = tempAngle1 + radiusAngle;
                        
                        radiusAngle = (3*Math.PI/-4);
                        tempAngle3 = (angle - Math.PI - radiusAngle) * -1;
                        tempAngle4 = tempAngle3 + radiusAngle;
                            setAngle(tempAngle4);
                        CollisionHandler(list.get(i));
                    }
                    else if (list.get(i).posY + 15 <= posY && list.get(i).posY - 15 >= posY) {
                        radiusAngle = 0;
                        tempAngle1 = (list.get(i).getAngle() - Math.PI - radiusAngle) * -1;
                        tempAngle2 = tempAngle1 + radiusAngle;
                        radiusAngle = Math.PI;
                        tempAngle3 = (angle - Math.PI - radiusAngle) * -1;
                        tempAngle4 = tempAngle3 + radiusAngle;
                            setAngle(tempAngle4);
                        CollisionHandler(list.get(i));
                        
                    }
                    else if (list.get(i).posY < posY) {
                        radiusAngle = Math.PI/-4;
                        tempAngle1 = (list.get(i).getAngle() - Math.PI - radiusAngle) * -1 ;
                        tempAngle2 = tempAngle1 + radiusAngle;
                        list.get(i).setAngle(tempAngle2);
                        
                        radiusAngle = (3*Math.PI/4);
                        tempAngle3 = (angle - Math.PI - radiusAngle) * -1;
                        tempAngle4 = tempAngle3 + radiusAngle;
                            setAngle(tempAngle4);
                        CollisionHandler(list.get(i));
                    }
                }
                else if (list.get(i).posX + 15 <= posX && list.get(i).posX - 15 >= posX) {
                        if (list.get(i).posY > posY) {
                        radiusAngle = Math.PI/2;
                        tempAngle1 = (list.get(i).getAngle() - Math.PI - radiusAngle) * -1;
                        tempAngle2 = tempAngle1 + radiusAngle;
                        list.get(i).setAngle(tempAngle2);
                    
                        radiusAngle = Math.PI/-2;
                        tempAngle3 = (angle - Math.PI - radiusAngle) * -1;
                        tempAngle4 = tempAngle3 + radiusAngle;
                            setAngle(tempAngle4);
                        CollisionHandler(list.get(i));
                        }
                        else if (list.get(i).posY < posY) {
                        radiusAngle = Math.PI/-2;
                        tempAngle1 = (list.get(i).getAngle() - Math.PI - radiusAngle) * -1;
                        tempAngle2 = tempAngle1 + radiusAngle;
                        list.get(i).setAngle(tempAngle2);
                    
                        radiusAngle = Math.PI/2;
                        tempAngle3 = (angle - Math.PI - radiusAngle) * -1;
                        tempAngle4 = tempAngle3 + radiusAngle;
                            setAngle(tempAngle4);
                        CollisionHandler(list.get(i));
                        }
                    }
                
                else if (list.get(i).posX < posX) {
                    if (list.get(i).posY > posY) {
                        radiusAngle = (3*Math.PI)/4;
                        tempAngle1 = (list.get(i).getAngle() - Math.PI - radiusAngle) * -1;
                        tempAngle2 = tempAngle1 + radiusAngle;
                        list.get(i).setAngle(tempAngle2);
                        radiusAngle = Math.PI/-4;
                        tempAngle3 = (angle - Math.PI - radiusAngle) * -1;
                        tempAngle4 = tempAngle3 + radiusAngle;
                            setAngle(tempAngle4);
                        CollisionHandler(list.get(i));
                    }
                    else if (list.get(i).posY + 15 <= posY && list.get(i).posY - 15 >= posY) {
                        radiusAngle = Math.PI;
                        tempAngle1 = (list.get(i).getAngle() - Math.PI - radiusAngle) * -1;
                        tempAngle2 = tempAngle1 + radiusAngle;
                        radiusAngle = 0;
                        tempAngle3 = (angle - Math.PI - radiusAngle) * -1;
                        tempAngle4 = tempAngle3 + radiusAngle;
                            setAngle(tempAngle4);
                        CollisionHandler(list.get(i));
                    }
                    else if (list.get(i).posY < posY) {
                        radiusAngle = (3*Math.PI)/-4;
                        tempAngle1 = (list.get(i).getAngle() - Math.PI - radiusAngle) * -1;
                        tempAngle2 = tempAngle1 + radiusAngle;
                        list.get(i).setAngle(tempAngle2);
                        radiusAngle = Math.PI/4;
                        tempAngle3 = (angle - Math.PI - radiusAngle) * -1;
                        tempAngle4 = tempAngle3 + radiusAngle;
                            setAngle(tempAngle4);
                        CollisionHandler(list.get(i));
                    }    
                }
            }
                         
        }
        }
        posX += velX/30; posY += velY/30;
        sprite.setX(sprite.getX() + velX/30); sprite.setY(sprite.getY() - velY/30);
    }
    public void CollisionHandler(PhysicalBody body) {
        
        if (typeOfCollision.equals("elastic")) {
            setVelX(velocity * Math.cos(angle));
            setVelY(velocity * Math.sin(angle));
        }
        else if (typeOfCollision.equals("inelastic")) {
            setVelX(velocity * 0.8 * Math.cos(angle));
            setVelY(velocity * 0.8 * Math.sin(angle));
            
            body.setVelX(body.getVelocity() * 0.8 * Math.cos(body.getAngle()));
            body.setVelY(body.getVelocity() * 0.8 * Math.sin(body.getAngle()));
        }
        else if (typeOfCollision.equals("perfectly inelastic")) {
            double mass = body.getMass() + this.getMass();
            double momentumX = body.getMomentumX()+ this.getMomentumX();
            double momentumY = body.getMomentumY()+ this.getMomentumY();
            
            this.setMass(mass); body.setMass(mass);
            this.setVelX(momentumX/mass); body.setVelX(momentumX/mass);
            this.setVelY(momentumY/mass); body.setVelY(momentumY/mass);
            this.setAngleAndVelocityPECollision(this.getVelX(), this.getVelY());
            body.setAngleAndVelocityPECollision(body.getVelX(), body.getVelY());
            this.connectedtoList.set(body.index, true);
            body.connectedtoList.set(this.index, true);
            
        }
    }
    public void setAngleAndVelocityPECollision(double velX, double velY) {
        this.velocity = Math.sqrt(velX * velX + velY * velY);
        
        if (this.velX >= 0 && this.velY >= 0) {
            this.angle = Math.acos(velX/velocity);
        }
        else if (this.velX <= 0 && this.velY >= 0) {
            this.angle = Math.acos(velX/velocity);
        }
        else if (this.velX <= 0 && this.velY <= 0) {
            this.angle = 2*Math.PI - Math.acos(velX/velocity);
        }
        else if (this.velX >= 0 && this.velY <= 0) {
            this.angle = 2*Math.PI + Math.asin(velY/velocity);
        }
    }
    
}
