/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleProjectileMotion;
import intpro.*;
import java.io.File;
import javafx.scene.image.ImageView;

/**
 *
 * @author Cédric
 */
public class Cannon extends SpritedElement {
    MountVehicle vehicle;
    public ImageView barrel;
    
    public Cannon() {
        sprite = new ImageView(new File("src/Assets/default.png").toURI().toString());
        barrel = new ImageView(new File("src/Assets/defaultCannon.png").toURI().toString());
    }
    
    public Cannon(ImageView cannon) {
        sprite = new ImageView(new File("src/Assets/default.png").toURI().toString());
        barrel = cannon;
    }
    
    public Cannon(ImageView cannon, ImageView base) {
        sprite = base;
        barrel = cannon;
    }
}
