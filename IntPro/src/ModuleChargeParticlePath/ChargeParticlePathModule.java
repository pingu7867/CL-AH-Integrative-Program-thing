/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleChargeParticlePath;

import ModuleProjectileMotion.ProjectileMotionModule;
import intpro.Module;
import intpro.*;
import java.io.IOException;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amine
 */
public class ChargeParticlePathModule extends Module {
    ArrayList<ParallelPlateCapacitor> listOfCapacitors = new ArrayList<>();
    ArrayList<ChargeParticle> listOfParticles = new ArrayList<>();
    boolean IsPlaying = false;
    
    public ChargeParticlePathModule(Core creator, int moduleNumber) {
        super(creator,moduleNumber);
        moduleNumber = 3;
        BorderPane bord = new BorderPane();
        
        pane = new Pane();
        ExitButton exit = new ExitButton(creator, this);
        HBox buttons = new HBox(10);
        HBox bottombutton = new HBox(10);
        
        bottombutton.getChildren().add(exit.display);
        bottombutton.setAlignment(Pos.CENTER_LEFT);
        
        bord.setCenter(pane);
        bord.setTop(buttons);
        bord.setBottom(bottombutton);
        scene = new Scene(bord,creator.getRes1X(),creator.getRes1Y());
        viewport = new Stage();
        viewport.setScene(scene); viewport.setTitle("Cedamine Physics Toolkit: Charged Particle Path");
        
        ChargeParticleInventoryIcon cppIcon = new ChargeParticleInventoryIcon(creator,this);
        ParallelPlateCapacitorInventoryIcon ppcIcon = new ParallelPlateCapacitorInventoryIcon(creator,this);
        RunSimulationChargeParticlePath RunSimulation = new RunSimulationChargeParticlePath(creator, this);
        DeleteButton delete = new DeleteButton(creator,this);
        PauseButtonChargeParticlePath pausebutton = new PauseButtonChargeParticlePath(creator, this);
        ResetButtonChargeParticlePath resetbutton = new ResetButtonChargeParticlePath(creator, this);
        buttons.getChildren().addAll(ppcIcon.display,cppIcon.display,RunSimulation.display, pausebutton.display,delete.display,resetbutton.display);
        
    }
    @Override
    public void popOut() {
        viewport.setTitle("Charge Particle Path Module");
        viewport.show();
        this.viewport.setOnCloseRequest(e -> {
            try {
                dataSource.flushModule(moduleNumber);
            } catch (IOException ex) {
                Logger.getLogger(ProjectileMotionModule.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    public Stage generateParallelPlateCapacitorWindow() {
        Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);
        
        Text warningMessage = new Text("WARNING: this field can only contain digits");
        warningMessage.setStroke(Color.RED);
        
        TextField FieldforSheetChargeDensity = new TextField("0");
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
            if (!checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1);
               text.replace("-", "");
               FieldforSeparationDistance.setText(text);
            }
        });
        
        TextField FieldforOrientation = new TextField("0");
        FieldforOrientation.setPrefColumnCount(3);
        FieldforOrientation.textProperty().addListener(ov-> {
            String text = FieldforOrientation.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text.replace("-", "");
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
           
           double densityValue = Double.parseDouble(FieldforSheetChargeDensity.getText());
           double distanceValue = Double.parseDouble(FieldforSeparationDistance.getText());
           double orientationValue = Double.parseDouble(FieldforOrientation.getText());
           ParallelPlateCapacitor capacitor = new ParallelPlateCapacitor(distanceValue,densityValue,orientationValue);
           capacitor.setCapacitor();
           pane.getChildren().addAll(capacitor.getTopPlate().sprite, capacitor.getBotPlate().sprite);
           elements.add(capacitor);
           listOfCapacitors.add(capacitor);
           
           capacitor.getTopPlate().sprite.setOnMouseClicked(oh-> {
               if (deleteModeActivated) {
                   elements.remove(capacitor);
                   listOfCapacitors.remove(capacitor);
                   pane.getChildren().removeAll(capacitor.getTopPlate().sprite,capacitor.getBotPlate().sprite);
               }
           });
           capacitor.getBotPlate().sprite.setOnMouseClicked(oh-> {
               if (deleteModeActivated) {
                   elements.remove(capacitor);
                   listOfCapacitors.remove(capacitor);
                   pane.getChildren().removeAll(capacitor.getTopPlate().sprite,capacitor.getBotPlate().sprite);
               }
           });
           
           stage.close();
        });
        
        WindowLayout.getChildren().addAll(labelforSheetChargeDensity, labelforSeparationDistance, labelforOrientation, buttons);
        if (!viewport.isShowing()) {
            stage.close();
        }
        stage.setScene(new Scene(WindowLayout));
        stage.requestFocus();
        return stage;
    }
    
    public Stage generateChargedParticleWindow() {
        Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);
        
        Text warningMessage = new Text("WARNING: this field can only contain digits");
        warningMessage.setStroke(Color.RED);
        
        TextField fieldForVelocityX = new TextField("0");
        fieldForVelocityX.setPrefColumnCount(3);
        fieldForVelocityX.textProperty().addListener(ov-> {
            String text = fieldForVelocityX.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForVelocityX.setText(text);
            }
        });
        
        TextField fieldForVelocityY = new TextField("0");
        fieldForVelocityY.setPrefColumnCount(3);
        fieldForVelocityY.textProperty().addListener(ov-> {
            String text = fieldForVelocityY.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForVelocityY.setText(text);
            }
        });
        
        TextField fieldForCharge = new TextField("0");
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
            if (!checkDecimal(text) && text.contains("-") && text.equals("0")) {
               text.replace("-", "");
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
           
           double velXValue = Double.parseDouble(fieldForVelocityX.getText());
           double velYValue = Double.parseDouble(fieldForVelocityY.getText());
           double chargeValue = Double.parseDouble(fieldForCharge.getText());
           double massValue = Double.parseDouble(fieldForMass.getText());
           ChargeParticle particle = new ChargeParticle(chargeValue, massValue, velXValue, velYValue);
           particle.setParticle();
           pane.getChildren().add(particle.sprite);
           elements.add(particle);
           this.listOfParticles.add(particle);
           particle.sprite.setOnMouseClicked(oh -> {
               if(deleteModeActivated) {
                   elements.remove(particle);
                   listOfParticles.remove(particle);
                   pane.getChildren().remove(particle.sprite);
               }
           });
           
           stage.close();
        });
        if (!viewport.isShowing()) {
            stage.close();
        }
        WindowLayout.getChildren().addAll(labelForVelocityX,labelForVelocityY, labelForCharge, labelForMass, buttons);
        
        stage.setScene(new Scene(WindowLayout));
        stage.requestFocus();
        return stage;
    }
    @Override
    public String getModuleName(){
        return "charge particle path";
    }
}
