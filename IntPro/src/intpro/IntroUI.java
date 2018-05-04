/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

import java.io.File;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author Cédric
 */

public class IntroUI extends UIWindow {
    public Core core;
    
    public IntroUI(Core core) {
        super('i');
        windowName = "Welcome! introduction screen";
        
        sizeX = 1200;
        sizeY = 800;
        
        ImageView background = new ImageView(new File("src/Assets/TitleBackground.png").toURI().toString());
        
        pane.setPrefSize(sizeX, sizeY);
        scroll.setPrefSize(sizeX, sizeY);
        content.setPrefSize(sizeX, 2200);
        
        pane.getChildren().add(background);
        pane.getChildren().add(scroll);
        scroll.setContent(content);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        GlobalSoundButton soundBut = new GlobalSoundButton(core);
        addToPane(soundBut.display);
        soundBut.display.setScaleX(0.45); soundBut.display.setScaleY(0.45);
        soundBut.display.setLayoutX(1030); soundBut.display.setLayoutY(-40);
        
        SettingsButton settingsBut = new SettingsButton(core);
        addToPane(settingsBut.display);
        settingsBut.display.setScaleX(0.45); settingsBut.display.setScaleY(0.45);
        settingsBut.display.setLayoutX(925); settingsBut.display.setLayoutY(-40);
        
    }
    
    public void inject(MainMenuModuleButton[] buttons) {
        for (int module = 1; module <= buttons.length + 1; module++) {
            inject(buttons[module]);
        }
    }
    
    public void inject(MainMenuModuleButton button) {
        addToPane(button.display);
    }
    
}
