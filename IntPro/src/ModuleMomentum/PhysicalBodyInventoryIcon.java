/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleMomentum;

import intpro.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Amine
 */
public class PhysicalBodyInventoryIcon  extends InventoryIcon{
    MomentumModule module;
    public PhysicalBodyInventoryIcon(Core creator, MomentumModule module) {
        super("physical body", creator);
        this.simpleGraphicSetUp("display", "PhysicalBodyInventoryIcon");
        this.module = module;
    }
    @Override 
    public void action() {
        Stage window = generatePhysicalBodyWindow();
        window.show();
    }
    public Stage generatePhysicalBodyWindow() {
     Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);

        TextField fieldforMass = new TextField("1");
        fieldforMass.setPrefColumnCount(3);
        fieldforMass.textProperty().addListener(ov-> {
            String text = fieldforMass.getText();
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldforMass.setText(text);
            }
        });
        
        TextField fieldforVelocity = new TextField("1");
        fieldforVelocity .setPrefColumnCount(3);
        fieldforVelocity .textProperty().addListener(ov-> {
            String text = fieldforVelocity.getText();
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldforVelocity .setText(text);
            }
        });
        
        TextField fieldforAngle = new TextField("0");
        fieldforAngle.setPrefColumnCount(3);
        fieldforAngle.textProperty().addListener(ov-> {
            String text = fieldforAngle.getText();
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldforAngle.setText(text);
            }
            
        });

        Label labelforMass = new Label("Mass (kg)", fieldforMass);
        labelforMass.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforVelocity = new Label("Velocity(cm/s)", fieldforVelocity);
        labelforVelocity.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforAngle = new Label("Path Orientation(degrees)", fieldforAngle);
        labelforAngle.setContentDisplay(ContentDisplay.RIGHT);
        
        
        HBox buttons = new HBox(8);
        buttons.setAlignment(Pos.BOTTOM_RIGHT);
        Button Create = new Button("Create");        
        Button Cancel = new Button("Cancel");
        buttons.getChildren().addAll(Cancel, Create);
        
        Cancel.setOnAction(eh-> {
            stage.close();
        });
        Create.setOnAction(eh -> {
           double massValue = Double.parseDouble(fieldforMass.getText());
           double velocityValue = Double.parseDouble(fieldforVelocity.getText());
           double angleValue = Double.parseDouble(fieldforAngle.getText()) * (Math.PI/180);
           PhysicalBody body = (PhysicalBody)deploy();
           body.setMass(massValue); body.setVelocity(velocityValue); body.setAngle(angleValue);
           
           module.listOfPhysicalBodies.add(body);
           module.pane.getChildren().add(body.sprite);
           stage.close();
        });
        
        WindowLayout.getChildren().addAll(labelforMass, labelforVelocity, labelforAngle, buttons);
        if (!module.viewport.isShowing()) {
            stage.close();
        }
        stage.setScene(new Scene(WindowLayout));
        stage.requestFocus();
        return stage;
    }  
}
