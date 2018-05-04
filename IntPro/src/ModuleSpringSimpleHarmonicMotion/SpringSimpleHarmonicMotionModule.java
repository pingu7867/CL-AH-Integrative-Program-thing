/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleSpringSimpleHarmonicMotion;

import intpro.Module;
import intpro.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Cédric
 */
public class SpringSimpleHarmonicMotionModule extends Module {
    ArrayList<Spring> listOfSprings = new ArrayList<>();
    
    public SpringSimpleHarmonicMotionModule(Core core, int moduleNumber) {
        super(core, moduleNumber);
        pane = new Pane();
        inventory = new Inventory(getModuleName(), dataSource);
        pane.getChildren().add(inventory.deployIcon.display);
        
        inventory.icons.get(0).display.setOnMouseClicked(e -> {
            newestCreatedElement = inventory.icons.get(1).deploy();
            elements.add(newestCreatedElement);
            generateSpringWindow().show();
        });
        inventory.icons.get(1).display.setOnMouseClicked(e -> {
            newestCreatedElement = inventory.icons.get(0).deploy();
            elements.add(newestCreatedElement);
            generateSpringWindow().show();
        });
    }
    
    public Stage generateSpringWindow() {
        Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);
        
        Text textforMass = new Text("weight's mass in kg");
        Text textforLength = new Text("spring's length in cm");
        Text textforKConstant = new Text("spring's hardness in N/m");
        
        TextField fieldforMass = new TextField("1");
        fieldforMass.setPrefColumnCount(3);
        fieldforMass.textProperty().addListener(ov-> {
            String text = fieldforMass.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldforMass.setText(text);
            }
        });
        
        TextField fieldforLength = new TextField("100");
        fieldforLength.setPrefColumnCount(3);
        fieldforLength.textProperty().addListener(ov-> {
            String text = fieldforLength.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1);
               text.replace("-", "");
               fieldforLength .setText(text);
            }
        });
        
        TextField fieldforKConstant = new TextField("0");
        fieldforKConstant.setPrefColumnCount(3);
        fieldforKConstant.textProperty().addListener(ov-> {
            String text = fieldforKConstant.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text.replace("-", "");
               text = text.substring(0, text.length() - 1);
               fieldforKConstant.setText(text);
            }
        });
        
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
           double lengthValue = Double.parseDouble(fieldforLength.getText());
           double kconstantValue = Double.parseDouble(fieldforKConstant.getText());
           
           Spring spring = (Spring)inventory.icons.get(0).deploy();
           spring.injectModRef(this);
           spring.setFields(lengthValue, kconstantValue, new Oscillator(), new Weight(massValue));
           
           listOfSprings.add(spring);
           spring.oscillator.startOsc();
           
           stage.close();
           
        });
        
        WindowLayout.getChildren().addAll(fieldforMass, textforMass, fieldforLength, textforLength, fieldforKConstant, textforKConstant, buttons);
        
        stage.setScene(new Scene(WindowLayout));
        stage.requestFocus();
        return stage;
    }   
    
    public Stage generateWeightWindow() {
        Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);
        
        
        TextField fieldforMass = new TextField("1");
        fieldforMass.setPrefColumnCount(3);
        fieldforMass.textProperty().addListener(ov-> {
            String text = fieldforMass.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldforMass.setText(text);
            }
        });
        
        TextField fieldforLength = new TextField("100");
        fieldforLength.setPrefColumnCount(3);
        fieldforLength.textProperty().addListener(ov-> {
            String text = fieldforLength.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1);
               text.replace("-", "");
               fieldforLength .setText(text);
            }
        });
        
        TextField fieldforKConstant = new TextField("0");
        fieldforKConstant.setPrefColumnCount(3);
        fieldforKConstant.textProperty().addListener(ov-> {
            String text = fieldforKConstant.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text.replace("-", "");
               text = text.substring(0, text.length() - 1);
               fieldforKConstant.setText(text);
            }
        });

        Label labelforMass = new Label("Sheet Charge Density (C/m^2)", fieldforMass);
        labelforMass.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforLength = new Label("Separation Distance of the 2 plates (m)", fieldforLength);
        labelforLength.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforKConstant = new Label("Orientation (degrees)", fieldforKConstant);
        labelforKConstant.setContentDisplay(ContentDisplay.RIGHT);
        
        
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
           double lengthValue = Double.parseDouble(fieldforLength.getText());
           double kconstantValue = Double.parseDouble(fieldforKConstant.getText());
           Spring spring = (Spring)inventory.icons.get(0).deploy();
           listOfSprings.add(spring);
           
           stage.close();
           
        });
        
        WindowLayout.getChildren().addAll(labelforMass, labelforLength, labelforKConstant, buttons);
        if (!viewport.isShowing()) {
            stage.close();
        }
        stage.setScene(new Scene(WindowLayout));
        stage.requestFocus();
        return stage;
    }
    
    @Override
    public void popOut() {
        scene = new Scene(pane, dataSource.res1x, dataSource.res1y);
        
        viewport = new Stage();
        viewport.setScene(scene);
        viewport.show();
        
        this.viewport.setOnCloseRequest(e -> {
            try {
                dataSource.flushModule(moduleNumber);
            } catch (IOException ex) {
                Logger.getLogger(SpringSimpleHarmonicMotionModule.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    @Override
    public void tickProcess() {
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).draw();
        }
    }
    
}
