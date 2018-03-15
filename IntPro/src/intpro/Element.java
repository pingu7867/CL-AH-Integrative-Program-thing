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

public class Element extends GeneralFunctionalitiesStackedInheritanceTower {
    
    public String name;
    
    public Element child = null;
    
    public void setName(String newName) {
        name = newName;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public void setPosY(double posY) {
        if (child != null) {
            child.setPosY(child.getPosY() - this.posY + posY);
        }
        this.posY = posY;
    }
    
    @Override
    public void setPosX(double posX) {
        if (child != null) {
            child.setPosX(child.getPosX() - this.posX + posX);
        }
        this.posX = posX;
    }
    
    @Override
    public void setVelY(double velY) {
        if (child != null) {
            child.setVelX(child.getVelX() - this.velX + velX);
        }
        this.velY = velY;
    }
    
    @Override
    public void setVelX(double velX) {
        if (child != null) {
            child.setVelX(child.getVelX() - this.velX + velX);
        }
        this.velX = velX;
    }
    
}
