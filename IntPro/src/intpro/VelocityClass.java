/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

/**
 *
 * @author Cédric
 */
public abstract class VelocityClass extends PositionClass {
    protected double velX = 0;
    protected double velY = 0;
    //radians
    protected double angle = 0;
    
    public void setVelY(double velY) {
        this.velY = velY;
    }
    
    public void setVelX(double velX) {
        this.velX = velX;
    }
    
    public double getVelX() {
       return this.velX;
    }
    
    public double getVelY() {
       return this.velY;
    }
    
    public double getAngleRad() {
        
        int signX = (int)Math.signum(velX);
        int signY = (int)Math.signum(velY);
        
        if (signX == 0) {
            switch (signY) {
                case 0: return 0;
                case 1: return 1/2 * Math.PI;
                case -1: return 3/2 * Math.PI;
                default: break;
            }
        }
        
        if (signY == 0) {
            switch (signX) {
                case 0: return 0;
                case 1: return 0;
                case -1: return Math.PI;
                default: break;
            }
        }
        
        if ((signX == -1) && (signY == -1)) {
            return Math.atan2(-velX, -velY);
        }
        if ((signX == -1) && (signY == 1)) {
            return -Math.atan2(velX, velY);
        }
        if ((signX == 1) && (signY == -1)) {
            return (2 * Math.PI) + Math.atan2(velX, velY);
        }
        if ((signX == 1) && (signY == 1)) {
            return Math.atan2(velX, velY);
        }
        return 0;
    }
    
    public double getAngleDeg() {
        return this.getAngleRad() * 2 * Math.PI;
    }
    
    public double getAngle() {
        return angle;
    }
    
    public void setAngle(double newAngle) {
        angle = newAngle;
    }
}
