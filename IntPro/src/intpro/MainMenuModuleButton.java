/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 *
 * @author Cédric
 */

public class MainMenuModuleButton extends ImageButton {
    
    public int moduleNumber;
    public Core core;
    
    public MainMenuModuleButton(int num) {
        moduleNumber = num;
        initializeButton();
        doubleClickDuration = 500;
        
    }
    
    public void initializeButton() {
        pressGraphic = new Image(new File("src/Assets/MainMenuModule" + moduleNumber + "press.png").toURI().toString());
        idleGraphic = new Image(new File("src/Assets/MainMenuModule" + moduleNumber + "idle.png").toURI().toString());
    }
    
    public void injectCoreRef(Core c) {
        core = c;
    }
    
    @Override
    public void action() {
        core.pushModule(moduleNumber);
    }
    
}
