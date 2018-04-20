/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleChargeParticlePath;
import intpro.SpritedElement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
/**
 *
 * @author Amine
 */
public class ElectricPlate extends SpritedElement {
    private Image negPlateDown;
    private Image negPlateUp;
    private Image posPlateDown;
    private Image posPlateUp;
    
    private String sign = "-";
    private String dir;
    
    public ElectricPlate() {
        super(new ImageView());
        negPlateDown = new Image(new File("src/Assets/NegativeElectricPlateDown.png").toURI().toString());
        posPlateDown = new Image(new File("src/Assets/PositiveElectricPlateDown.png").toURI().toString());
        negPlateUp = new Image(new File("src/Assets/NegativeElectricPlateUp.png").toURI().toString());
        posPlateUp = new Image(new File("src/Assets/PositiveElectricPlateUp.png").toURI().toString());
        sprite.setImage(negPlateUp);
         
    }
    public ElectricPlate(String sign, String dir) {
        this();
        this.sign = sign;
        this.dir = dir;
        
        if (sign.equals("-") && dir.equals("down")) {
            sprite.setImage(negPlateUp);
        }
        else if (sign.equals("+") && dir.equals("down")){
            sprite.setImage(posPlateDown);
        }
        else if (sign.equals("-") && dir.equals("up")) {
            sprite.setImage(negPlateDown);
        }
        else if (sign.equals("+") && dir.equals("up")){
            sprite.setImage(posPlateUp);
        }
        
        
    }
    public void changeSign() {
        if (sign.equals("-") && dir.equals("down")) {
            sign = "+"; dir = "up";
            sprite.setImage(posPlateUp);
        }
        else if (sign.equals("+") && dir.equals("down")){
            sign = "-" ; dir = "up";
            sprite.setImage(negPlateDown);
        }
        else if (sign.equals("-") && dir.equals("up")) {
            sign = "+"; dir = "down";
            sprite.setImage(posPlateDown);
        }
        else if (sign.equals("+") && dir.equals("up")){
            sign = "-"; dir = "down";
            sprite.setImage(negPlateUp);
        }
        
    }
}
