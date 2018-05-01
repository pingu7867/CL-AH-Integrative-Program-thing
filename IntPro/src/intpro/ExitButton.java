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
public class ExitButton extends ImageButton{
    Module module;
    public ExitButton(Core Creator, Module mod){
        super(Creator);
        this.doubleClickDuration = 0;
        simpleGraphicSetUp("display", "Exit");
        simpleGraphicSetUp("press", "Exit");
        this.module = mod;
    }
    @Override
    public void action() {
        
        module.viewport.close();
    }
}
