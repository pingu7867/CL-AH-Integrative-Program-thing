/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleMomentum;
import intpro.Element;
/**
 *
 * @author Amine
 */
public class PhysicalBody extends Element{
    double mass;
    double momentumX;
    double momentumY;
    
    public PhysicalBody(double mass, double velX, double velY) {
        super(velX,velY);
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
        setMomentumX(mass * velX); setMomentumY(mas * velY);
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
}
