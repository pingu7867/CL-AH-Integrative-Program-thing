/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleChargeParticlePath;
import intpro.*;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
        Stage window = generateChargedParticleWindow();
        window.show();
    }
    public Stage generateChargedParticleWindow() {
        Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);
        
        TextField fieldForVelocityX = new TextField("0");
        fieldForVelocityX.setPrefColumnCount(3);
        fieldForVelocityX.textProperty().addListener(ov-> {
            String text = fieldForVelocityX.getText();
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForVelocityX.setText(text);
            }
        });
        
        TextField fieldForVelocityY = new TextField("0");
        fieldForVelocityY.setPrefColumnCount(3);
        fieldForVelocityY.textProperty().addListener(ov-> {
            String text = fieldForVelocityY.getText();
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForVelocityY.setText(text);
            }
        });
        
        TextField fieldForCharge = new TextField("0");
        fieldForCharge.setPrefColumnCount(3);
        fieldForCharge.textProperty().addListener(ov-> {
            String text = fieldForCharge.getText();
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForCharge.setText(text);
            }
        });
        
        TextField fieldForMass = new TextField("1");
        fieldForMass.setPrefColumnCount(3);
        fieldForMass.textProperty().addListener(ov-> {
            String text = fieldForMass.getText();
            if (text.equals("")) {
                text = "1";
                fieldForMass.setText(text);
            }
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForMass.setText(text);
            }
            if (text.contains("-")) {
                text = text.replace("-", "");
                fieldForMass.setText(text);
            }
        });
        
        
        Label labelForVelocityX = new Label("Velocity in X (m/s)", fieldForVelocityX);
        labelForVelocityX.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelForVelocityY = new Label("Velocity in Y (m/s)", fieldForVelocityY);
        labelForVelocityY.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelForCharge = new Label("Charge (dC)", fieldForCharge);
        labelForCharge.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelForMass = new Label("Mass (kg)", fieldForMass);
        labelForMass.setContentDisplay(ContentDisplay.RIGHT);
        
        
        HBox buttons = new HBox(8);
        buttons.setAlignment(Pos.BOTTOM_RIGHT);
        Button Create = new Button("Create");        
        Button Cancel = new Button("Cancel");
        buttons.getChildren().addAll(Cancel, Create);
        
        
        Cancel.setOnAction(eh-> {
            stage.close();
        });
        Create.setOnAction(eh -> {
           
           double velXValue = Double.parseDouble(fieldForVelocityX.getText());
           double velYValue = Double.parseDouble(fieldForVelocityY.getText());
           double chargeValue = Double.parseDouble(fieldForCharge.getText());
           double massValue = Double.parseDouble(fieldForMass.getText());
           ChargeParticle particle = new ChargeParticle(chargeValue, massValue, velXValue, velYValue);
           particle.setParticle();
           module.pane.getChildren().add(particle.sprite);
           module.elements.add(particle);
           module.listOfParticles.add(particle);
           
           particle.sprite.setOnMouseClicked(oh -> {
               if(module.deleteModeActivated) {
                   module.elements.remove(particle);
                   module.listOfParticles.remove(particle);
                   module.pane.getChildren().remove(particle.sprite);
               }
           });
           
           stage.close();
        });
        WindowLayout.getChildren().addAll(labelForVelocityX,labelForVelocityY, labelForCharge, labelForMass, buttons);
        
        stage.setScene(new Scene(WindowLayout));
        stage.requestFocus();
        return stage;
    }
}
