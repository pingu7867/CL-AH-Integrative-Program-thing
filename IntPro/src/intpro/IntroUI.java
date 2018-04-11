/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Cédric
 */

public class IntroUI extends UIWindow {
    public Core core;
    
    public IntroUI(Core core) {
        windowName = "Welcome! introduction screen";
        pane = new Pane();
        sizeX = 1200;
        sizeY = 800;
        scene = new Scene(pane, sizeX, sizeY);
        viewport.setTitle(windowName);
        viewport.setScene(scene);
    }
    
    public void inject(MainMenuModuleButton[] buttons) {
        for (int module = 1; module <= buttons.length + 1; module++) {
            inject(buttons[module]);
        }
    }
    
    public void inject(MainMenuModuleButton button) {
        pane.getChildren().add(button.display);
    }
    
}
