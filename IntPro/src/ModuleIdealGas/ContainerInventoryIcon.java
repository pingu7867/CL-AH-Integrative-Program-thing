/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleIdealGas;

/**
 *
 * @author Amine
 */
import intpro.*;
import java.io.File;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ContainerInventoryIcon extends InventoryIcon {
    IdealGasModule module;
    public ContainerInventoryIcon(Core Creator, IdealGasModule module) {
        super("charged particle",Creator);
        display.setImage(new Image(new File("src/Assets/ChargeParticleInventoryIcon.png").toURI().toString()));
        this.module = module;
    }
    @Override
    public void action() {
    }
    
}
