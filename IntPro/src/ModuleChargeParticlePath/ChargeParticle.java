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
    double charge;
    double electricForce = 0;
    
    public ChargeParticle(double charge) {
        this.charge = charge;
    }
    public ChargeParticle(double charge,double velX, double velY) {
        this.charge = charge;
    }
}
