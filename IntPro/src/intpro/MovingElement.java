/**
 *
 * @author 1630954
*/
package intpro;

public abstract class MovingElement extends GeneralFunctionalitiesStackedInheritanceTower {
    double velX = 0;
    double velY = 0;
    
    public MovingElement() {
        
    }
    
    public MovingElement(double velX, double velY) {
        this.velY = velY;
        this.velX = velX;
    }
    
    public MovingElement(double velX, double velY, double posX, double posY) {
        this.posY = posY;
        this.posX = posX;
        this.velY = velY;
        this.velX = velX;
    }
    
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
    
}
