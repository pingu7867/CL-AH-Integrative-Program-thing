/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

import java.math.BigInteger;
import javafx.application.Platform;

/**
 *
 * @author Cédric
 */
public class GlobalSoundButton extends SoundButton {
    
    public GlobalSoundButton(Core core) {
        super(core);
    }
    
    @Override
    public void action() {
        flipFlop();
        if (display.getImage() == idleGraphic) {
            volField.setLayoutX(this.getPosX());
            volField.setLayoutY(this.getPosY());
            instruction.setLayoutX(this.getPosX() + 150);
            instruction.setLayoutY(this.getPosY() + 10);
            core.intro.addToPane(instruction);
            core.intro.addToPane(volField);
            
            
            volField.setOnAction(e -> {
                String number = volField.getText().toLowerCase();
                String buildNum = "0";
                for (int i = 0; i < number.length(); i++) {
                    if ((number.charAt(i) >= '0') && (number.charAt(i) <= '9')) {
                        buildNum = buildNum + number.charAt(i);
                    }
                }
                core.setVolume(Math.min(new BigInteger(buildNum).intValue(), 100));
                core.intro.content.getChildren().remove(volField);
                core.intro.content.getChildren().remove(instruction);
                volField.clear();
            });
            
        }
        if (display.getImage() == activatedGraphic) {
            if (targetModule == null) {
                core.setVolume(0);
                core.intro.content.getChildren().remove(volField);
                core.intro.content.getChildren().remove(instruction);
            }
            else {
                targetModule.setVolume(0);
            }
        }
        
    }
    
}
