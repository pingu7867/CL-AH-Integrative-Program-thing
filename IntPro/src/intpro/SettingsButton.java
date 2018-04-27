/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

import java.io.File;
import java.math.BigInteger;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 *
 * @author Cédric
 */
public class SettingsButton extends ImageButton {
    
    public SettingsButton(Core core) {
        super(core);
        idleGraphic = new Image(new File("src/Assets/settingsButton.png").toURI().toString());
        doubleClickDuration = 0;
        graphicOff();
    }
    
    @Override
    public void action() {
        
    }
}
