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
    protected double posX = 0;
    protected double posY = 0;
    
    protected double init_posX = 0;
    protected double init_posY = 0;
    
    public void setPosY(double posY) {
        this.init_posY = posY;
    }
    
    public void setPosX(double posX) {
        this.init_posX = posX;
    }
    
    public void changePosX(double posX) {
        this.posX = posX;
    }
    
    public void changePosY(double posY) {
        this.posY = posY;
    }
    
    public double getPosX() {
       return this.posX;
    }
    
    public double getPosY() {
       return this.posY;
    }
    
    public double getInitPosX() {
       return this.init_posX;
    }
    
    public double getInitPosY() {
       return this.init_posY;
    }
    
    public void initializePosition() {
        posX = this.init_posX;
        posY = this.init_posY;
    }
}
