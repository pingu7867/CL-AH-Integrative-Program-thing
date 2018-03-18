/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

import java.util.ArrayList;
import java.util.HashSet;
import javafx.scene.Node;

/**
 *
 * @author Cédric
 */

public class Element extends GeneralFunctionalitiesStackedInheritanceTower {
    
    public String name;
    
    public Element child = null;
    public Element parent = null;
    
    public Element() {
        
    }
    
    public Element(double velX, double velY) {
        this.velY = velY;
        this.velX = velX;
    }
    
    public Element(double velX, double velY, double posX, double posY) {
        this.posY = posY;
        this.posX = posX;
        this.velY = velY;
        this.velX = velX;
    }
    
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
    
    public void declareChild(Element child) {
        this.child = child;
        this.child.parent = this;
    }
    
    public void draw() {
        
    }
    
    public ArrayList<Node> getRenderElements() {
        return new ArrayList<>();
    }
    
}
