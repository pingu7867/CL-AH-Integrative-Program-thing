
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
    private Timeline animation;
    ArrayList<Arc> arcs = new ArrayList<>();
    private double velocity;
    
    public GasParticle() {
        super(new ImageView());
        this.angle = Math.random() * (2*Math.PI);
        velocity = 0;
        sprite.setImage(new Image(new File("src/Assets/Molecule0.png").toURI().toString()));
    }
    public GasParticle(double averageKineticEnergy) {
        this();
        velocity = (averageKineticEnergy * 2)/1.6737236 * Math.pow(10, -27);
        velX = velocity * Math.cos(angle);
        velY = velocity * Math.sin(angle);
        posX = 0; posY = 0;
        sprite.setX(posX); sprite.setY(posY);
        sprite.setFitHeight(10); sprite.setFitWidth(10);
    }
    public double getVelocity() {
        return velocity;
    }
    public void setHitBox() {
        double startingAngle = 0;
        while (startingAngle < 360) {
            arcs.add(new Arc(sprite.getX() + sprite.getFitWidth(), sprite.getY() + sprite.getFitHeight(), sprite.getFitWidth()/2, sprite.getFitHeight()/2
            ,startingAngle,10));
            startingAngle+=10;
        }
    }
    public void animateGasParticleMotion(ArrayList<GasParticle> gasParticles,int index,Rectangle bounds) {
        boolean quit1 = false;
        
        for (int i = index + 1; i < gasParticles.size(); i++) {
            if (this.sprite.intersects(gasParticles.get(i).sprite.getX(), gasParticles.get(i).sprite.getY(),
               gasParticles.get(i).sprite.getFitWidth(), gasParticles.get(i).sprite.getFitHeight())) {
               for (int j = 0; j < arcs.size(); j++) {
                   for (int k = 0; k < gasParticles.get(i).arcs.size(); k++) {
                       if (arcs.get(j).intersects(gasParticles.get(i).arcs.get(k).getBoundsInLocal())) {
                           
                           gasParticles.get(i).setAngle((gasParticles.get(i).getAngleRad() + arcs.get(j).getStartAngle()) % (2*Math.PI));
                           gasParticles.get(i).setVelX(gasParticles.get(i).getVelocity() * Math.cos(gasParticles.get(i).getAngleRad()));
                           gasParticles.get(i).setVelY(gasParticles.get(i).getVelocity() * Math.sin(gasParticles.get(i).getAngleRad()));
                           
                           angle = (angle + gasParticles.get(i).arcs.get(k).getStartAngle()) % (2*Math.PI);
                           velX = velocity * Math.cos(angle);
                           velY = velocity * Math.sin(angle);
                           quit1 = true;
                           break;
                           
                       }
                   }
                   if (quit1) {
                       break;
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
        posX += velX/60; posY -= velY/60;
        sprite.setX(sprite.getX() + velX/60); sprite.setY(sprite.getY() - velY/60);
        
        for (int i = 0; i < arcs.size(); i++) {
            arcs.get(i).setCenterX(arcs.get(i).getCenterX() + velX);
            arcs.get(i).setCenterY(arcs.get(i).getCenterY() - velY);
        }
    }
    
}
