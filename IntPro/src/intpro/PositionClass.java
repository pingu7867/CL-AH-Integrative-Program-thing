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
public abstract class PositionClass {
    double posX = 0;
    double posY = 0;
    
    public void setPosY(double posY) {
        this.posY = posY;
    }
    
    public void setPosX(double posX) {
        this.posX = posX;
    }
    
    public double getPosX() {
       return this.posX;
    }
    
    public double getPosY() {
       return this.posY;
    }
    
}
