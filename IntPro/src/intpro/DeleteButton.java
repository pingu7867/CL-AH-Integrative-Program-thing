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
public class DeleteButton extends ImageButton{
    public Module module;
    public DeleteButton(Core Creator, Module module){
        super(Creator);
        this.doubleClickDuration = 0;
        simpleGraphicSetUp("display", "GarbageCan");
        simpleGraphicSetUp("press", "GarbageCan");
       
        this.module = module;
    }
    @Override
    public void action() {
        if (!module.deleteModeActivated) {
            module.deleteModeActivated = true;
            display.setImage(new Image(new File("src/Assets/GarbageCanActivated.png").toURI().toString()));
        }
        else if (module.deleteModeActivated) {
            module.deleteModeActivated = false;
            display.setImage(new Image(new File("src/Assets/GarbageCan.png").toURI().toString()));
        }
    }
}
