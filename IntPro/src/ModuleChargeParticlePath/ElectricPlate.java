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
public class ElectricPlate extends SpritedElement {
    final private Image NegPlate = new Image("");
    final private Image PosPlate = new Image("");
    private String sign = "-";
    
    public ElectricPlate() {
        super(new ImageView());
        sprite.setImage(NegPlate);
    }
    public ElectricPlate(String sign) {
        super(new ImageView());
        if (sign.equals("-")) {
            sprite.setImage(NegPlate);
        }
        else {
            sprite.setImage(PosPlate);
        }
        
    }
    public void changeSign() {
        if (sign.equals("+")) {
            sign = "-";
            sprite.setImage(NegPlate);
        }
        else {
            sign = "+";
            sprite.setImage(PosPlate);
        }
    }
}
