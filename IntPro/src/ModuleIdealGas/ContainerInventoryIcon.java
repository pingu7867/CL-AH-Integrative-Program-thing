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
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ContentDisplay;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class ContainerInventoryIcon extends InventoryIcon {
    IdealGasModule module;
    public ContainerInventoryIcon(Core Creator, IdealGasModule module) {
        super("charged particle",Creator);
        display.setImage(new Image(new File("src/Assets/ChargeParticleInventoryIcon.png").toURI().toString()));
        this.module = module;
    }
    @Override
    public void action() {
        Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);
        
        TextField fieldForMoles = new TextField("1");
        fieldForMoles.setPrefColumnCount(3);
        fieldForMoles.textProperty().addListener(ov-> {
            String text = fieldForMoles.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1);
               fieldForMoles.setText(text);
            }
        });
        
        TextField fieldForVolume = new TextField("1");
        fieldForVolume.setPrefColumnCount(3);
        fieldForVolume.textProperty().addListener(ov-> {
            String text = fieldForVolume.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1);
               text.replace("-", "");
               fieldForVolume.setText(text);
            }
        });
        
        TextField fieldForPressure = new TextField("101.3");
        fieldForPressure.setPrefColumnCount(3);
        fieldForPressure.textProperty().addListener(ov-> {
            String text = fieldForPressure.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text.replace("-", "");
               text = text.substring(0, text.length() - 1);
               fieldForPressure.setText(text);
            }
        });
        TextField fieldForTemperature = new TextField("273");
        fieldForTemperature.setPrefColumnCount(3);
        fieldForTemperature.textProperty().addListener(ov-> {
            String text = fieldForTemperature.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text.replace("-", "");
               text = text.substring(0, text.length() - 1);
               fieldForTemperature.setText(text);
            }
        });

        Label labelforMoles = new Label("Sheet Charge Density (C/m^2)", fieldForMoles);
        labelforSheetChargeDensity.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforVolume = new Label("Separation Distance of the 2 plates (m)", fieldForVolume);
        labelforSeparationDistance.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforPressure = new Label("Orientation (degrees)", fieldForPressure);
        labelforOrientation.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforTemperature = new Label("Orientation (degrees)", fieldForTemperature);
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
           
           double molesValue = Double.parseDouble(fieldForMoles.getText());
           double volumeValue = Double.parseDouble(fieldForVolume.getText());
           double pressureValue = Double.parseDouble(fieldForPressure.getText());
           double temperatureValue = Double.parseDouble(fieldForTemperature.getText());
            
           Container container = new Container(molesValue,volumeValue,pressureValue,temperatureValue);
           container.set();
           module.pane.getChildren().addAll(container.sprite);
           module.elements.add(r);
           listOfContainer.add(container);
           stage.close();
        });
        
        WindowLayout.getChildren().addAll(labelforMoles, labelforVolume, labelforPressure, labelforTemperature, buttons);
        if (!module.viewport.isShowing()) {
            stage.close();
        }
        stage.setScene(new Scene(WindowLayout));
        stage.requestFocus();
        return stage;
    }
    }
    
}
