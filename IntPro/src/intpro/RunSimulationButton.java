/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;
import intpro.*;
import java.io.File;
import javafx.scene.image.Image;
/**
 *
 * @author Amine
 */
public class RunSimulationButton extends ImageButton{
    public RunSimulationButton(Core Creator) {
        super(Creator);
        this.doubleClickDuration = 0;
        simpleGraphicSetUp("display", "Run");
        simpleGraphicSetUp("press", "Run");
        simpleGraphicSetUp("idle", "Run");
    }
    @Override
    public void action() {
        
    }
}
