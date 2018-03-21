/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Cédric
 */

public class SpritedElement extends Element {
    
    public ImageView sprite;
    
    public SpritedElement() {
        sprite = new ImageView(new File("src/Assets/default.png").toURI().toString());
    }
    
    public SpritedElement(ImageView image) {
        sprite = image;
    }
    
    
    
}
