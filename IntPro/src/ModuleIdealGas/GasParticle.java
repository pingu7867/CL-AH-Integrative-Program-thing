
package ModuleIdealGas;
/**
 *
 * @author 1630954
 */
import intpro.SpritedElement;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.animation.*;
import javafx.util.Duration;
import java.io.File;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.scene.shape.Arc;

public class GasParticle extends SpritedElement{
    public Timeline animation;
    ArrayList<Arc> arcs = new ArrayList<>();
    private double velocity;
    Rectangle bounds;
    double radius;
    double cooldown = 0;
    
    public GasParticle(Rectangle bounds) {
        super(new ImageView());
        this.angle = Math.random() * (2*Math.PI);
        velocity = 0;
        sprite.setImage(new Image(new File("src/Assets/Molecule0.png").toURI().toString()));
        this.bounds = bounds;
        sprite.setFitHeight(10); sprite.setFitWidth(10);
        radius = sprite.getFitHeight()/2;
        posX = Math.random() * bounds.getWidth() + bounds.getX() + radius; 
        posY = Math.random() * bounds.getHeight() + bounds.getY() + radius;
        
        sprite.setX(posX - radius);
        sprite.setY(resY - (posY - radius));
    }
    public GasParticle(Rectangle bounds,double averageKineticEnergy) {
        this(bounds);
        velocity = (averageKineticEnergy * 2)/(2*(1.6737236 * Math.pow(10, -27)));
        velocity /= 100000;
        velX = velocity * Math.cos(angle);
        velY = velocity * Math.sin(angle);

    }
    public double getVelocity() {
        return velocity;
    }
    @Override
    public void setAngle(double angle) {
        this.angle = angle;
        velX = velocity * Math.cos(angle);
        velY = velocity * Math.sin(angle);
    }
    public void setHitBox() {
        double startingAngle = 0;
        while (startingAngle < 360) {
            arcs.add(new Arc(sprite.getX() + sprite.getFitWidth(), sprite.getY() + sprite.getFitHeight(), sprite.getFitWidth()/2, sprite.getFitHeight()/2
            ,startingAngle,10));
            startingAngle+=10;
        }
    }
    public void setAnimation(ArrayList<GasParticle> gasParticles,int index) {
        animation = new Timeline(new KeyFrame(Duration.millis(1000/30), e-> { animateGasParticleMotion(gasParticles, index);
        }));
        animation.setCycleCount(Timeline.INDEFINITE);
        
    }
    public void animateGasParticleMotion(ArrayList<GasParticle> gasParticles,int index) {
       if (cooldown == 0) {
        for (int i = index + 1; i < gasParticles.size(); i++) {
            if (4*radius * radius <= (gasParticles.get(i).posX - posX) * (gasParticles.get(i).posX - posX)
                    + (gasParticles.get(i).posY - posY)*(gasParticles.get(i).posY - posY))   {
                double radiusAngle;
                double tempAngle1;
                double tempAngle2;
                double tempAngle3;
                double tempAngle4;
                cooldown = 16;
                if (gasParticles.get(i).posX > posX) {
                    
                    if (gasParticles.get(i).posY > posY) {
                        radiusAngle = Math.PI/4;
                        tempAngle1 = (gasParticles.get(i).getAngle() - Math.PI - radiusAngle) * -1;
                        tempAngle2 = tempAngle1 + radiusAngle;
                        
                        radiusAngle = (3*Math.PI/-4);
                        tempAngle3 = (angle - Math.PI - radiusAngle) * -1;
                        tempAngle4 = tempAngle3 + radiusAngle;
                            setAngle(tempAngle4);
                    }
                    else if (gasParticles.get(i).posY == posY) {
                        radiusAngle = 0;
                        tempAngle1 = (gasParticles.get(i).getAngle() - Math.PI - radiusAngle) * -1;
                        tempAngle2 = tempAngle1 + radiusAngle;
                        radiusAngle = Math.PI;
                        tempAngle3 = (angle - Math.PI - radiusAngle) * -1;
                        tempAngle4 = tempAngle3 + radiusAngle;
                            setAngle(tempAngle4);
                        
                    }
                    else if (gasParticles.get(i).posY < posY) {
                        radiusAngle = Math.PI/-4;
                        tempAngle1 = (gasParticles.get(i).getAngle() - Math.PI - radiusAngle) * -1 ;
                        tempAngle2 = tempAngle1 + radiusAngle;
                        gasParticles.get(i).setAngle(tempAngle2);
                        
                        radiusAngle = (3*Math.PI/4);
                        tempAngle3 = (angle - Math.PI - radiusAngle) * -1;
                        tempAngle4 = tempAngle3 + radiusAngle;
                            setAngle(tempAngle4);
                        
                    }
                }
                else if (gasParticles.get(i).posX == posX) {
                        if (gasParticles.get(i).posY > posY) {
                        radiusAngle = Math.PI/2;
                        tempAngle1 = (gasParticles.get(i).getAngle() - Math.PI - radiusAngle) * -1;
                        tempAngle2 = tempAngle1 + radiusAngle;
                        gasParticles.get(i).setAngle(tempAngle2);
                    
                        radiusAngle = Math.PI/-2;
                        tempAngle3 = (angle - Math.PI - radiusAngle) * -1;
                        tempAngle4 = tempAngle3 + radiusAngle;
                            setAngle(tempAngle4);
                        }
                        else if (gasParticles.get(i).posY < posY) {
                        radiusAngle = Math.PI/-2;
                        tempAngle1 = (gasParticles.get(i).getAngle() - Math.PI - radiusAngle) * -1;
                        tempAngle2 = tempAngle1 + radiusAngle;
                        gasParticles.get(i).setAngle(tempAngle2);
                    
                        radiusAngle = Math.PI/2;
                        tempAngle3 = (angle - Math.PI - radiusAngle) * -1;
                        tempAngle4 = tempAngle3 + radiusAngle;
                            setAngle(tempAngle4);
                        }
                    }
                
                else if (gasParticles.get(i).posX < posX) {
                    if (gasParticles.get(i).posY > posY) {
                        radiusAngle = (3*Math.PI)/4;
                        tempAngle1 = (gasParticles.get(i).getAngle() - Math.PI - radiusAngle) * -1;
                        tempAngle2 = tempAngle1 + radiusAngle;
                        gasParticles.get(i).setAngle(tempAngle2);
                        radiusAngle = Math.PI/-4;
                        tempAngle3 = (angle - Math.PI - radiusAngle) * -1;
                        tempAngle4 = tempAngle3 + radiusAngle;
                            setAngle(tempAngle4);
                    }
                    else if (gasParticles.get(i).posY == posY) {
                        radiusAngle = Math.PI;
                        tempAngle1 = (gasParticles.get(i).getAngle() - Math.PI - radiusAngle) * -1;
                        tempAngle2 = tempAngle1 + radiusAngle;
                        radiusAngle = 0;
                        tempAngle3 = (angle - Math.PI - radiusAngle) * -1;
                        tempAngle4 = tempAngle3 + radiusAngle;
                            setAngle(tempAngle4);
                    }
                    else if (gasParticles.get(i).posY < posY) {
                        radiusAngle = (3*Math.PI)/-4;
                        tempAngle1 = (gasParticles.get(i).getAngle() - Math.PI - radiusAngle) * -1;
                        tempAngle2 = tempAngle1 + radiusAngle;
                        gasParticles.get(i).setAngle(tempAngle2);
                        radiusAngle = Math.PI/4;
                        tempAngle3 = (angle - Math.PI - radiusAngle) * -1;
                        tempAngle4 = tempAngle3 + radiusAngle;
                            setAngle(tempAngle4);
                        
                    }    
                }
            }
                         
        }
       }   
        if (posX + 5 > bounds.getX() + bounds.getWidth() || posX < 5 + bounds.getX()) {
               velX *= -1;
           }
        if (posY + 5 > bounds.getY() + bounds.getHeight() || posY < 5 + bounds.getY()) {
               velY *= -1;
           }

        posX += velX/30; posY += velY/30;
        sprite.setX(sprite.getX() + velX/30); sprite.setY(sprite.getY() - velY/30);
        if (cooldown > 0) {
            cooldown--;
        }
    }
    
}
