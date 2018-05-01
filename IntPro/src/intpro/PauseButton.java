/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;
import java.io.File;
import javafx.scene.image.Image;
/**
 *
 * @author Amine
 */
public class PauseButton extends ImageButton{
    public PauseButton(Core creator) {
        super(creator);
        this.doubleClickDuration = 0;
        display.setImage(new Image(new File("src/Assets/Pause.png").toURI().toString()));
    }
    
}
