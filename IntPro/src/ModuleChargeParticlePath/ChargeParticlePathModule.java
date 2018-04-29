/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleChargeParticlePath;

import intpro.Module;
import intpro.*;
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
public class ChargeParticlePathModule extends Module {
    ArrayList<ParallelPlateCapacitor> listOfCapacitors = new ArrayList<>();
    ArrayList<ChargeParticle> listOfParticles = new ArrayList<>();
    boolean IsPlaying = false;
    
    public ChargeParticlePathModule(Core creator) {
        super(creator);
        moduleNumber = 3;
        BorderPane bord = new BorderPane();
        
        pane = new Pane();
        HBox buttons = new HBox(10);
        bord.setCenter(pane);
        bord.setTop(buttons);
        scene = new Scene(bord,creator.getRes1X(),creator.getRes1Y());
        viewport = new Stage();
        viewport.setScene(scene); viewport.setTitle("Cedamine Physics Toolkit: Charged Particle Path");
        
        ChargeParticleInventoryIcon cppIcon = new ChargeParticleInventoryIcon(creator,this);
        ParallelPlateCapacitorInventoryIcon ppcIcon = new ParallelPlateCapacitorInventoryIcon(creator,this);
        
        Button addParallelPlateCapacitor = new Button("Create a Parallel Plate Capacitor");
        Button addChargeParticle = new Button("Create a Charge Particle");
        Button RunSimulation = new Button("Run Simulation");
        
        addParallelPlateCapacitor.setOnAction(eh-> {
            Stage window = generateParallelPlateCapacitorWindow();
            window.show();
            
        });
        addChargeParticle.setOnAction(eh-> {
            Stage window = generateChargedParticleWindow();
            window.show();
        });
        RunSimulation.setOnAction(eh-> {
            for (ParallelPlateCapacitor capa: listOfCapacitors){
                capa.lockPosition();
            }
            for (ChargeParticle e:listOfParticles) {
                e.lockPosition();
                e.play(listOfCapacitors);
            }
        });
        
        buttons.getChildren().addAll(ppcIcon.display,cppIcon.display,RunSimulation);
                
    }
    public Stage generateParallelPlateCapacitorWindow() {
        Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);
        
        Text warningMessage = new Text("WARNING: this field can only contain digits");
        warningMessage.setStroke(Color.RED);
        
        TextField FieldforSheetChargeDensity = new TextField("1");
        FieldforSheetChargeDensity.setPrefColumnCount(3);
        FieldforSheetChargeDensity.textProperty().addListener(ov-> {
            String text = FieldforSheetChargeDensity.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               FieldforSheetChargeDensity.setText(text);
            }
        });
        
        TextField FieldforSeparationDistance = new TextField("50");
        FieldforSeparationDistance.setPrefColumnCount(3);
        FieldforSeparationDistance.textProperty().addListener(ov-> {
            String text = FieldforSeparationDistance.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               FieldforSeparationDistance.setText(text);
            }
        });
        
        TextField FieldforOrientation = new TextField("0");
        FieldforOrientation.setPrefColumnCount(3);
        FieldforOrientation.textProperty().addListener(ov-> {
            String text = FieldforOrientation.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               FieldforOrientation.setText(text);
            }
        });

        Label labelforSheetChargeDensity = new Label("Sheet Charge Density (C/m^2)", FieldforSheetChargeDensity);
        labelforSheetChargeDensity.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforSeparationDistance = new Label("Separation Distance of the 2 plates (m)", FieldforSeparationDistance);
        labelforSeparationDistance.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforOrientation = new Label("Orientation (degrees)", FieldforOrientation);
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
           
           double densityValue = Double.parseDouble(FieldforSheetChargeDensity.getText().trim());
           double distanceValue = Double.parseDouble(FieldforSeparationDistance.getText().trim());
           double orientationValue = Double.parseDouble(FieldforOrientation.getText().trim());
           ParallelPlateCapacitor capacitor = new ParallelPlateCapacitor(distanceValue,densityValue,orientationValue);
           capacitor.setCapacitor();
           pane.getChildren().addAll(capacitor.getTopPlate().sprite, capacitor.getBotPlate().sprite);
           elements.add(capacitor);
           listOfCapacitors.add(capacitor);
           stage.close();
        });
        
        WindowLayout.getChildren().addAll(labelforSheetChargeDensity, labelforSeparationDistance, labelforOrientation, buttons);
        
        stage.setScene(new Scene(WindowLayout));
        return stage;
    }
    
    public Stage generateChargedParticleWindow() {
        Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);
        
        Text warningMessage = new Text("WARNING: this field can only contain digits");
        warningMessage.setStroke(Color.RED);
        
        TextField fieldForVelocityX = new TextField("1");
        fieldForVelocityX.setPrefColumnCount(3);
        fieldForVelocityX.textProperty().addListener(ov-> {
            String text = fieldForVelocityX.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForVelocityX.setText(text);
            }
        });
        
        TextField fieldForVelocityY = new TextField("1");
        fieldForVelocityY.setPrefColumnCount(3);
        fieldForVelocityY.textProperty().addListener(ov-> {
            String text = fieldForVelocityY.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForVelocityY.setText(text);
            }
        });
        
        TextField fieldForCharge = new TextField("1");
        fieldForCharge.setPrefColumnCount(3);
        fieldForCharge.textProperty().addListener(ov-> {
            String text = fieldForCharge.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForCharge.setText(text);
            }
        });
        
        TextField fieldForMass = new TextField("1");
        fieldForMass.setPrefColumnCount(3);
        fieldForMass.textProperty().addListener(ov-> {
            String text = fieldForMass.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForMass.setText(text);
            }
        });
        
        
        Label labelForVelocityX = new Label("Velocity in X (m/s)", fieldForVelocityX);
        labelForVelocityX.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelForVelocityY = new Label("Velocity in Y (m/s)", fieldForVelocityY);
        labelForVelocityY.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelForCharge = new Label("Charge (C)", fieldForCharge);
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
           
           double velXValue = Double.parseDouble(fieldForVelocityX.getText().trim());
           double velYValue = Double.parseDouble(fieldForVelocityY.getText().trim());
           double chargeValue = Double.parseDouble(fieldForCharge.getText().trim());
           double massValue = Double.parseDouble(fieldForMass.getText().trim());
           ChargeParticle particle = new ChargeParticle(chargeValue, massValue, velXValue, velYValue);
           particle.setParticle();
           pane.getChildren().add(particle.sprite);
           elements.add(particle);
           this.listOfParticles.add(particle);
           stage.close();
        });
        
        WindowLayout.getChildren().addAll(labelForVelocityX,labelForVelocityY, labelForCharge, labelForMass, buttons);
        
        stage.setScene(new Scene(WindowLayout));
        return stage;
    }
    
}
