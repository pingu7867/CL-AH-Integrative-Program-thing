/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWaveSuperposition;

import intpro.Module;
import intpro.*;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
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
public class WaveSuperpositionModule extends Module {
    ArrayList<Curve> curves = new ArrayList<>();
    public WaveSuperpositionModule(Core creator, int moduleNumber) {
        super(creator, moduleNumber);
        pane = new Pane();
        
        viewport = new Stage();
    }
    
    public Stage generateCurveWindow() {
        Stage stage = new Stage();
        BorderPane bordWindow = new BorderPane();
        VBox WindowLayout = new VBox(20);
            
        BorderPane bordComboBoxPane = new BorderPane();
        bordComboBoxPane.setLeft(new Label("Select the type of function"));
        
        String[] typesOfCurves = {"Sine Wave Function", "Saw Wave Function", "Square Wave Function", "Triangle Wave Function"};
        
        
        ComboBox<String> cboTypeOfCurves = new ComboBox<>();
        cboTypeOfCurves.setItems(FXCollections.observableArrayList(typesOfCurves));
        cboTypeOfCurves.setValue("Sine Wave");
        bordComboBoxPane.setCenter(cboTypeOfCurves);
        
        HBox buttons = new HBox(8);
            buttons.setAlignment(Pos.BOTTOM_RIGHT);
            Button Create = new Button("Create");        
            Button Cancel = new Button("Cancel");
            buttons.getChildren().addAll(Cancel, Create);
          
        WindowLayout.getChildren().add(cboTypeOfCurves);
        cboTypeOfCurves.setOnAction(oh-> {
            
            TextField fieldForAmplitude = new TextField("20");
        fieldForAmplitude.setPrefColumnCount(3);
        fieldForAmplitude.textProperty().addListener(ov-> {
            String text = fieldForAmplitude.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForAmplitude.setText(text);
            }
        });
        
        TextField fieldForWavelength = new TextField("2");
        fieldForWavelength.setPrefColumnCount(3);
        fieldForWavelength.textProperty().addListener(ov-> {
            String text = fieldForWavelength.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForWavelength.setText(text);
            }
        });
        
        TextField fieldForFrequency = new TextField("100");
        fieldForFrequency.setPrefColumnCount(3);
        fieldForFrequency.textProperty().addListener(ov-> {
            String text = fieldForFrequency.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForFrequency.setText(text);
            }
        });
        
        TextField fieldForVelocity = new TextField("200");
        fieldForVelocity.setPrefColumnCount(3);
        fieldForVelocity.textProperty().addListener(ov-> {
            String text = fieldForVelocity.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForVelocity.setText(text);
            }
        });
        
        
        Label labelForAmplitude = new Label("Amplitude (m)", fieldForAmplitude);
        labelForAmplitude.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelForWavelength = new Label("Velocity in Y (m/s)", fieldForWavelength);
        labelForWavelength.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelForFrequency = new Label("Charge (C)", fieldForFrequency);
        labelForFrequency.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelForVelocity = new Label("Mass (kg)", fieldForVelocity);
        labelForVelocity.setContentDisplay(ContentDisplay.RIGHT);
        

        WindowLayout.getChildren().addAll(labelForAmplitude,labelForWavelength, labelForFrequency, labelForVelocity);
            Create.setOnAction(eh -> {
           
           
           double amplitudeValue = Double.parseDouble(fieldForAmplitude.getText().trim());
           double wavelengthValue = Double.parseDouble(fieldForWavelength.getText().trim());
           double frequencyValue = Double.parseDouble(fieldForFrequency.getText().trim());
           double velocityValue = Double.parseDouble(fieldForVelocity.getText().trim());
           
           switch (cboTypeOfCurves.getValue()) {
               case "Sine Wave Function" : new Curve(new SineWaveFunction(amplitudeValue,frequencyValue,wavelengthValue)); break;
               case "Saw Wave Function" : new Curve(new SawWaveFunction(amplitudeValue,frequencyValue,wavelengthValue)); break;
               case "Square Wave Function" : new Curve(new SquareWaveFunction(amplitudeValue,frequencyValue,wavelengthValue)); break;
               case "Triangle Wave Function" : new Curve(new TriangleWaveFunction(amplitudeValue,frequencyValue,wavelengthValue)); break;
           }
           
           
           stage.close();
            });
        });
        Cancel.setOnAction(eh-> {
              stage.close();
            });
            
        bordWindow.setCenter(WindowLayout);
        bordWindow.setBottom(buttons);
                
        stage.setScene(new Scene(WindowLayout));
        
        return new Stage();
    }

    public Stage generateSuperposedFunctionCurveWindow() {
        return new Stage();
    }
    @Override
    public String getModuleName(){
        return "charge particle path";
    }
}
