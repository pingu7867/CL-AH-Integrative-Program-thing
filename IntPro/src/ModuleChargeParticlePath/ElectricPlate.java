/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleChargeParticlePath;
import intpro.SpritedElement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 *
 * @author Amine
 */
public class ElectricPlate extends SpritedElement{
    Image NegPlate = new Image("");
    Image PosPlate = new Image("");
    
    public ElectricPlate() {
        sprite.setImage(NegPlate);
    }
}
