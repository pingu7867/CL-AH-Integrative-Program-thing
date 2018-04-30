/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleLensOptics;

import intpro.Module;
import intpro.*;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
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
/**
 *
 * @author Cédric
 */
public class LensOpticsModule extends Module {
    ArrayList<SystemOfLens> listOfSystemOfLens = new ArrayList<>();
    public LensOpticsModule(Core creator) {        
        super(creator);
        
    }
    public Stage generateSystemOfLensWindow() {
        Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);
        
        Label labelNumberOfLens = new Label(0 + " lens");
        
        HBox buttons = new HBox(8);
        buttons.setAlignment(Pos.BOTTOM_RIGHT);
        Button Create = new Button("Create");        
        Button Cancel = new Button("Cancel");
        Button addALen = new Button("Add a lens");
        buttons.getChildren().addAll(Cancel, addALen,Create);
        SystemOfLens system = new SystemOfLens(); 
        
        Cancel.setOnAction(eh-> {
            stage.close();
        });
        addALen.setOnAction(eh -> {
            generateLensWindow(System);
        }) ;
        Create.setOnAction(eh -> {
          
           len.setLens();
           pane.getChildren().addAll(system.group);
           stage.close();
        });
        
        WindowLayout.getChildren().addAll(labelNumberOfLens, buttons);
        if (!viewport.isShowing()) {
            stage.close();
        }
        stage.setScene(new Scene(WindowLayout));
        stage.requestFocus();
        return stage;
    }
    
    public Stage generateLensWindow(SystemOfLens system) {
        Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);

        TextField fieldforHeight = new TextField("1");
        fieldforHeight.setPrefColumnCount(3);
        fieldforHeight.textProperty().addListener(ov-> {
            String text = fieldforHeight.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldforHeight.setText(text);
            }
        });
        
        TextField fieldforRadiusLeft = new TextField("1");
        fieldforRadiusLeft.setPrefColumnCount(3);
        fieldforRadiusLeft.textProperty().addListener(ov-> {
            String text = fieldforRadiusLeft.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1);
               text.replace("-", "");
               fieldforRadiusLeft.setText(text);
            }
        });
        
        TextField fieldforRadiusRight = new TextField("0");
        fieldforRadiusRight.setPrefColumnCount(3);
        fieldforRadiusRight.textProperty().addListener(ov-> {
            String text = fieldforRadiusRight.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text.replace("-", "");
               text = text.substring(0, text.length() - 1);
               fieldforRadiusRight.setText(text);
            }
        });

        Label labelforHeight = new Label("Sheet Charge Density (C/m^2)", fieldforHeight);
        labelforSheetChargeDensity.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforRadiusLeft = new Label("Separation Distance of the 2 plates (m)", fieldforRadiusLeft);
        labelforSeparationDistance.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforRadiusRight = new Label("Orientation (degrees)", fieldforRadiusRight);
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
           double heightValue = Double.parseDouble(fieldforHeight.getText());
           double radiusLeftValue = Double.parseDouble(fieldforRadiusLeft.getText());
           double radiusRightValue = Double.parseDouble(fieldforRadiusRight.getText());
           Lens len = new Lens(heightValue,radiusLeftValue,radiusRightValue);
           len.setLens();
           
           system.addLen(len);
           stage.close();
        });
        
        WindowLayout.getChildren().addAll(labelforHeight, labelforRadiusLeft, labelforRadiusRight, buttons);
        if (!viewport.isShowing()) {
            stage.close();
        }
        stage.setScene(new Scene(WindowLayout));
        stage.requestFocus();
        return stage;
    }
    
}
