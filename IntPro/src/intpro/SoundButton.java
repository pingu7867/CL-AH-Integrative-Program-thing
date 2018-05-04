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
import javafx.scene.text.Text;

/**
 *
 * @author Cédric
 */
public class SoundButton extends ImageButton {
    
    Image activatedGraphic;
    TextField volField = new TextField();
    Text instruction = new Text("type volume as percentage (0-100)");
    Module targetModule = null;
    
    public SoundButton(Core core) {
        super(core);
        doubleClickDuration = 0;
        idleGraphic = new Image(new File("src/Assets/soundButtonidle.png").toURI().toString());
        activatedGraphic = new Image(new File("src/Assets/soundButtonpress.png").toURI().toString());
        graphicOff();
    }
    
    public SoundButton(Module sourceModule, Core core) {
        super(core);
        declareTargetModule(targetModule);
    }
    
    public void declareTargetModule(Module sourceModule) {
        this.targetModule = sourceModule;
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
            instruction.setLayoutX(this.getPosX() + 150);
            instruction.setLayoutY(this.getPosY() + 10);
            if (targetModule != null) {
                targetModule.pane.getChildren().add(instruction);
                targetModule.pane.getChildren().add(volField);
                volField.setOnAction(e -> {
                    String number = volField.getText().toLowerCase();
                    String buildNum = "0";
                    for (int i = 0; i < number.length(); i++) {
                        if ((number.charAt(i) >= '0') && (number.charAt(i) <= '9')) {
                            buildNum = buildNum + number.charAt(i);
                        }
                    }
                    
                    targetModule.setVolume(Math.min(new BigInteger(buildNum).intValue(), 100));
                    targetModule.pane.getChildren().remove(volField);
                    targetModule.pane.getChildren().remove(instruction);
                    
                    volField.clear();
                });
                
                volField.setOnMouseDragged(e -> {
                    targetModule.pane.getChildren().remove(volField); targetModule.pane.getChildren().remove(instruction); volField.clear();
                });
            
            }
            
        }
        if (display.getImage() == activatedGraphic) {
            targetModule.setVolume(0);
        }
            
    }
}
