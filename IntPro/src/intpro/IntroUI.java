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
import javafx.scene.media.Media;

/**
 *
 * @author Cédric
 */

public class IntroUI extends UIWindow {
    public Core core;
    
    public IntroUI(Core core) {
        super('s');
        windowName = "Welcome! introduction screen";
        pane = new Pane();
        sizeX = 1200;
        sizeY = 800;
        scene = new Scene(getPane(), sizeX, sizeY);
        scroll.setScaleY(sizeY + 2000);
        content.setScaleY(4000);
        viewport.setTitle(windowName);
        viewport.setScene(scene);
        scroll.setPrefHeight(4000);
        
        SoundButton soundBut = new SoundButton(core);
        addToPane(soundBut.display);
        soundBut.display.setLayoutX(100);
        soundBut.display.setLayoutY(100);
        
        
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
