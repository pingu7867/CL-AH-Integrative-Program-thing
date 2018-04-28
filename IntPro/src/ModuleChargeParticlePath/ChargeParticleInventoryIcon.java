/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleChargeParticlePath;
import intpro.*;
import java.io.File;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 *
 * @author Amine
 */
public class ChargeParticleInventoryIcon  extends InventoryIcon {
    ChargeParticlePathModule module;
    public ChargeParticleInventoryIcon(Core Creator, ChargeParticlePathModule module) {
        super("charged particle",Creator);
        display.setImage(new Image(new File("src/Assets/ChargeParticleInventoryIcon.png").toURI().toString()));
        this.module = module;
    }
    @Override
    public void action() {
        Stage window = module.generateChargedParticleWindow();
        window.show();
    }
}
