/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWaveSuperposition;

import intpro.*;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Amine
 */
public class SineWaveInventoryIcon extends InventoryIcon{
    WaveSuperpositionModule module;
    public SineWaveInventoryIcon(Core creator, WaveSuperpositionModule module) {
        super("sine wave",creator);
        display.setImage(new Image(new File("src/Assets/SineWaveInventoryIcon.png").toURI().toString()));
        this.module = module;
    }
    @Override
    public void action(){
        Stage window = generateSineCurveWindow();
        window.show();
    }
    public Stage generateSineCurveWindow() {
        Stage stage = new Stage();
        BorderPane bordWindow = new BorderPane();
        VBox WindowLayout = new VBox(20);
        TextField fieldForAmplitude = new TextField("20");
        
        HBox buttons = new HBox(8);
            buttons.setAlignment(Pos.BOTTOM_RIGHT);
            Button Create = new Button("Create");        
            Button Cancel = new Button("Cancel");
            buttons.getChildren().addAll(Cancel, Create);
        
        fieldForAmplitude.setPrefColumnCount(3);
        fieldForAmplitude.textProperty().addListener(ov-> {
            String text = fieldForAmplitude.getText();
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForAmplitude.setText(text);
            }
        });
        
        
        TextField fieldForVelocity = new TextField("200");
        fieldForVelocity.setPrefColumnCount(3);
        fieldForVelocity.textProperty().addListener(ov-> {
            String text = fieldForVelocity.getText();
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForVelocity.setText(text);
            }
        });
        TextField fieldForWavelength = new TextField("100");
        fieldForWavelength.setPrefColumnCount(3);
        
        
        TextField fieldForFrequency = new TextField("2");
        fieldForFrequency.setPrefColumnCount(3);
        fieldForFrequency.textProperty().addListener(ov-> {
            String text = fieldForFrequency.getText();
            if (text.equals("")) {
                text = "0";
                fieldForFrequency.setText("0");
            }
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForFrequency.setText(text);
            }
            double velocity = Double.parseDouble(fieldForVelocity.getText());
            if (velocity < 0) {
                fieldForVelocity.setText(""+(-1 * Double.parseDouble(text) * Double.parseDouble(fieldForWavelength.getText())));
            }
            else if (velocity > 0) {
                fieldForVelocity.setText(""+(1 * Double.parseDouble(text) * Double.parseDouble(fieldForWavelength.getText())));
            }
            
        });
        fieldForWavelength.textProperty().addListener(ov-> {
            String text = fieldForWavelength.getText();
            if (text.equals("")) {
                text = "0";
                fieldForWavelength.setText("0");
            }
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForWavelength.setText(text);
            }
            double velocity = Double.parseDouble(fieldForVelocity.getText());
            if (velocity < 0) {
                fieldForVelocity.setText(""+(-1 * Double.parseDouble(text) * Double.parseDouble(fieldForFrequency.getText())));
            }
            else if (velocity > 0) {
                fieldForVelocity.setText(""+(1 * Double.parseDouble(text) * Double.parseDouble(fieldForFrequency.getText())));
            }
        });
        TextField fieldForPhaseShift= new TextField("0");
        fieldForPhaseShift.setPrefColumnCount(3);
        fieldForPhaseShift.textProperty().addListener(ov-> {
            String text = fieldForPhaseShift.getText();
            if (text.equals("")) {
                text = "0";
                fieldForPhaseShift.setText("0");
            }
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForPhaseShift.setText(text);
            }
            
        });
        
        Label labelForAmplitude = new Label("Amplitude (cm)", fieldForAmplitude);
        labelForAmplitude.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelForWavelength = new Label("Wavelength (cm)", fieldForWavelength);
        labelForWavelength.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelForFrequency = new Label("Frequency (Hz)", fieldForFrequency);
        labelForFrequency.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelForVelocity = new Label("Velocity (cm/s)", fieldForVelocity);
        labelForVelocity.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelForPhaseShift = new Label("Phase Shift (cm)", fieldForPhaseShift);
        labelForPhaseShift.setContentDisplay(ContentDisplay.RIGHT);

        WindowLayout.getChildren().addAll(labelForAmplitude,labelForWavelength, labelForFrequency, labelForVelocity, labelForPhaseShift, buttons);
        
        Create.setOnAction(eh -> {
           
           double amplitudeValue = Double.parseDouble(fieldForAmplitude.getText());
           double wavelengthValue = Double.parseDouble(fieldForWavelength.getText());
           double frequencyValue = Double.parseDouble(fieldForFrequency.getText());
           double velocityValue = Double.parseDouble(fieldForVelocity.getText());
           double phaseShiftValue = Double.parseDouble(fieldForPhaseShift.getText());
           
           Curve curve = new Curve(new SineWaveFunction(amplitudeValue,frequencyValue,wavelengthValue,velocityValue,phaseShiftValue), module.getResolutionX());
           module.curves.add(curve);
           module.setWavePane(curve);
           stage.close();
            });
        Cancel.setOnAction(eh-> {
              stage.close();
            });
       
                
        stage.setScene(new Scene(WindowLayout));
        return stage;
    }

}
