/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleChargeParticlePath;
import javafx.scene.image.Image;
import java.io.File;
import intpro.SpritedElement;
import javafx.animation.*;
import javafx.util.Duration;
import java.util.ArrayList;
/**
 *
 * @author Amine
 */
public class ChargeParticle extends SpritedElement{
    private double charge = 0;
    private double electricForceX = 0;
    private double electricForceY = 0;
    private double mass = 1;
    private Timeline animation;
    private ArrayList<ParallelPlateCapacitor> listOfCapacitors;
    
    
    public ChargeParticle() {
        super();
        animation = new Timeline(new KeyFrame(Duration.millis(1000/30),eh-> particleMotion()));
        animation.setCycleCount(Timeline.INDEFINITE);
    }
    
    public ChargeParticle(double charge, double mass) {
        this();
        
        this.charge = charge;
    }
    
    public ChargeParticle(double charge,double mass,double velX, double velY) {
        this();
        
        this.velX = velX;
        this.velY = velY;
        this.posX = 0;
        this.posY = 0;
        this.charge = charge;
    }
    
    public ChargeParticle(double charge,double mass,double velX, double velY, double posX, double posY) {
        this();
        
        this.velX = velX;
        this.velY = velY;
        this.posX = posX;
        this.posY = posY;
        this.charge = charge;
    }
    
    public double getCharge(){
        return charge;
    }
    public double getMass() {
        return mass;
    }
    
    public double getElectricForceX() {
        return electricForceX;
    }
    
    public double getElectricForceY() {
        return electricForceY;
    }
    
    public void setCharge(double charge){
        this.charge = charge;
    }
    public void setMass(double mass){
        this.mass = mass;
    }
    
    public void setElectricForce(double electricForceX, double electricForceY) {
        this.electricForceX = electricForceX;
        this.electricForceY = electricForceY;
    }
    
    public void setParticle(){
        
        if (this.charge > 0) {
           this.sprite.setImage(new Image(new File("src/Assets/PositiveCharge.png").toURI().toString()));
        }
        else if (this.charge < 0) {
           this.sprite.setImage(new Image(new File("src/Assets/NegativeCharge.png").toURI().toString()));
        }
        else {
           this.sprite.setImage(new Image(new File("src/Assets/NeutralCharge.png").toURI().toString()));
        }
        posX = this.sprite.getImage().getWidth()/2; posY = this.sprite.getImage().getHeight()/2;
        this.sprite.setX(0); this.sprite.setY(0);
        
        this.sprite.setOnMouseDragged(md -> {
            if (canMove){
            posX = md.getX(); posY = md.getY();
            
            this.sprite.setX(md.getX() - this.sprite.getImage().getWidth()/2);
            this.sprite.setY(md.getY() - this.sprite.getImage().getHeight()/2);
            }
            });
        
    }
    public void play(ArrayList<ParallelPlateCapacitor> listOfCapacitors){
        
        this.listOfCapacitors = listOfCapacitors;
        animation.play();
    }
    public void particleMotion() {
        for(ParallelPlateCapacitor capa: listOfCapacitors){
            if (posX >= capa.getBotPlate().sprite.getX() 
                && posX <= capa.getBotPlate().sprite.getX() + capa.getBotPlate().sprite.getFitWidth()
                && posY >= capa.getTopPlate().sprite.getY()
                && posY <= capa.getBotPlate().sprite.getY()){
                this.electricForceX = charge * capa.getElectricFieldX();
                this.electricForceY = charge * capa.getElectricFieldY();
            }
            
            else if (this.sprite.intersects(capa.getBotPlate().sprite.getX(),capa.getBotPlate().sprite.getY(),capa.getBotPlate().sprite.getFitWidth(),capa.getBotPlate().sprite.getFitHeight()) 
                    || this.sprite.intersects(capa.getTopPlate().sprite.getX(),capa.getTopPlate().sprite.getY(),capa.getTopPlate().sprite.getFitWidth(),capa.getTopPlate().sprite.getFitHeight())) {
                velX = 0; velY=0; animation.stop();
            }
            else {
                this.electricForceX = 0; this.electricForceY = 0;
            }
        }
        velX += (this.electricForceX/mass)/30;
        velY -= (this.electricForceY/mass)/30;
        posX += velX/30; posY -= velY/30;
        this.sprite.setX(sprite.getX() + velX/30); this.sprite.setY(sprite.getY() - velY/30);
        
        
    }
    
}
