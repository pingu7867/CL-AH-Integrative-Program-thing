/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleChargeParticlePath;

import intpro.*;
import java.awt.font.TextAttribute;
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
import java.text.AttributedString;
/**
 *
 * @author Amine
 */
public class ParallelPlateCapacitorInventoryIcon extends InventoryIcon{
    ChargeParticlePathModule module;
    public ParallelPlateCapacitorInventoryIcon(Core Creator, ChargeParticlePathModule module) {
        super("capacitor",Creator);
        display.setImage(new Image(new File("src/Assets/CapacitorInventoryIcon.png").toURI().toString()));
        this.module = module;
    }
    @Override
    public void action() {
        Stage window = generateParallelPlateCapacitorWindow();
        window.show();
    }
    public Stage generateParallelPlateCapacitorWindow() {
        Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);
        
        
        
        TextField fieldforSheetChargeDensity = new TextField("0");
        fieldforSheetChargeDensity.setPrefColumnCount(3);
        fieldforSheetChargeDensity.textProperty().addListener(ov-> {
            String text = fieldforSheetChargeDensity.getText();
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldforSheetChargeDensity.setText(text);
            }

        });
        
        TextField fieldforSeparationDistance = new TextField("50");
        fieldforSeparationDistance.setPrefColumnCount(3);
        fieldforSeparationDistance.textProperty().addListener(ov-> {
            String text = fieldforSeparationDistance.getText();
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldforSeparationDistance.setText(text);
            }
            if (text.contains("-")) {
                text = text.replace("-", "");
                fieldforSeparationDistance.setText(text);
            }
            
        });
        
        TextField fieldforOrientation = new TextField("0");
        fieldforOrientation.setPrefColumnCount(3);
        fieldforOrientation.textProperty().addListener(ov-> {
            String text = fieldforOrientation.getText();
            if (!module.checkDecimal(text) && text.contains("-")) {
               
               text = text.substring(0, text.length() - 1);
               fieldforOrientation.setText(text);
            }
            if (text.contains("-")) {
                text = text.replace("-", "");
                fieldforOrientation.setText(text);
            }
        });
        
        Label labelforSheetChargeDensity = new Label("Sheet Charge Density (nC/m²)", fieldforSheetChargeDensity);
        labelforSheetChargeDensity.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforSeparationDistance = new Label("Separation Distance of the 2 plates (m)", fieldforSeparationDistance);
        labelforSeparationDistance.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforOrientation = new Label("Orientation (degrees)", fieldforOrientation);
        labelforOrientation.setContentDisplay(ContentDisplay.RIGHT);
        
        
        HBox buttons = new HBox(8);
        buttons.setAlignment(Pos.BOTTOM_RIGHT);
        Button Create = new Button("Create");        
        Button Cancel = new Button("Cancel");
        buttons.getChildren().addAll(Cancel, Create);
        
        
        Cancel.setOnAction(eh-> {
            stage.close();
        });
        Create.setOnAction(eh -> {
           
           double densityValue = Double.parseDouble(fieldforSheetChargeDensity.getText());
           double distanceValue = Double.parseDouble(fieldforSeparationDistance.getText());
           double orientationValue = Double.parseDouble(fieldforOrientation.getText());
           ParallelPlateCapacitor capacitor = new ParallelPlateCapacitor(distanceValue,densityValue,orientationValue);
           capacitor.setCapacitor();
           module.pane.getChildren().addAll(capacitor.getTopPlate().sprite, capacitor.getBotPlate().sprite, capacitor.space);
           module.elements.add(capacitor);
           module.listOfCapacitors.add(capacitor);
           
           capacitor.getTopPlate().sprite.setOnMouseClicked(oh-> {
               if (module.deleteModeActivated) {
                   module.elements.remove(capacitor);
                   module.listOfCapacitors.remove(capacitor);
                   module.pane.getChildren().removeAll(capacitor.getTopPlate().sprite,capacitor.getBotPlate().sprite);
               }
           });
           capacitor.getBotPlate().sprite.setOnMouseClicked(oh-> {
               if (module.deleteModeActivated) {
                   module.elements.remove(capacitor);
                   module.listOfCapacitors.remove(capacitor);
                   module.pane.getChildren().removeAll(capacitor.getTopPlate().sprite,capacitor.getBotPlate().sprite);
               }
           });
           
           stage.close();
        });
        
        WindowLayout.getChildren().addAll(labelforSheetChargeDensity, labelforSeparationDistance, labelforOrientation, buttons);
        stage.setScene(new Scene(WindowLayout));
        stage.requestFocus();
        return stage;
    }
}
