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
    double charge = 0;
    double electricForce = 0;
    
    public ChargeParticle(double charge) {
        super(1,1,0,0);
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
    public double getElectricForce() {
        return electricForce;
    }
    public void setCharge(double charge){
        this.charge = charge;
    }
    public void setElectricForce(double electricForce) {
        this.electricForce = electricForce;
    }
}
