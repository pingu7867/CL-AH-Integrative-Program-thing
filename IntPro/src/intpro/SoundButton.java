/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

import java.io.File;
import java.math.BigInteger;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 *
 * @author Cédric
 */
public class SoundButton extends ImageButton {
    
    Image activatedGraphic;
    TextField volField = new TextField();
    
    public SoundButton(Core core) {
        super(core);
        doubleClickDuration = 0;
        idleGraphic = new Image(new File("src/Assets/soundButtonidle.png").toURI().toString());
        activatedGraphic = new Image(new File("src/Assets/soundButtonpress.png").toURI().toString());
        graphicOff();
    }
    
    @Override
    public void graphicOn() {displayedImage = activatedGraphic; display.setImage(displayedImage);}
    
    @Override
    public void flipFlop() {
        if (display.getImage() == (activatedGraphic)) {
            graphicOff();
        }
        else if (display.getImage() == (idleGraphic)) {
            graphicOn();
        }
    }
    
    @Override
    public void action() {
        flipFlop();
        
        if (display.getImage() == idleGraphic) {
            
            volField.setLayoutX(this.getPosX());
            volField.setLayoutY(this.getPosY());
            core.intro.addToPane(volField);
            volField.setOnAction(e -> {
                String number = volField.getText().toLowerCase();
                String buildNum = "";
                for (int i = 0; i < number.length(); i++) {
                    if ((number.charAt(i) >= '0') && (number.charAt(i) <= '9')) {
                        buildNum = buildNum + number.charAt(i);
                    }
                }
                core.setVolume(Math.min(new BigInteger(buildNum).intValue(), 100));
                core.intro.pane.getChildren().remove(volField);
                volField.clear();
            });
            volField.setOnMouseDragged(e -> {core.intro.pane.getChildren().remove(volField); volField.clear();});
        }
        if (display.getImage() == activatedGraphic) {
            core.setVolume(0);
        }
    }
}
