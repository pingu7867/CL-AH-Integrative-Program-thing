/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleChargeParticlePath;
import intpro.Element;
/**
 *
 * @author Amine
 */
public class ChargeParticle extends Element{
    private double charge = 0;
    private double electricForceX = 0;
    private double electricForceY = 0;
   
    public ChargeParticle() {
        this(0);
    }
    
    public ChargeParticle(double charge) {
        this.charge = charge;
    }
    
    public ChargeParticle(double charge,double velX, double velY) {
        super(velX,velY,0,0);
        this.charge = charge;
    }
    
    public ChargeParticle(double charge,double velX, double velY, double posX, double posY) {
        super(velX, velY, posX, posY);
        this.charge = charge;
    }
    
    public double getCharge(){
        return charge;
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
    
    public void setElectricForceX(double electricForceX) {
        this.electricForceX = electricForceX;
    }
    public void setElectricForceY(double electricForceY) {
        this.electricForceY = electricForceY;
    }
}
