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

public abstract class ResScalable extends VelocityClass {
    Core core;
    Module module;
    
    public double posX() {
        return (getPosX()) * (core.getRes2X() / core.getRes1X());
    }
    
    public double posY() {
        return (this.getPosY()) * (core.getRes2Y() * (1 - (this.getPosY() / core.getRes1Y())));
    }
    
    public double getScaling() {
        return core.res2x / core.res1x;
    }
    
    public double yTransform(double y) {
        return core.getRes2Y() - (getScaling() * y);
    }
    
    public double xTransform(double x) {
        return core.getRes2X() - (getScaling() * x);
    }
    
}
