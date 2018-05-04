/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleCircularMotion;

import ModuleProjectileMotion.Cannon;
import ModuleProjectileMotion.ProjectileMotionModule;
import intpro.Module;
import intpro.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
public class CircularMotionModule extends Module {
    
    public CircularMotionModule(Core creator, int moduleNumber) {
        super(creator, moduleNumber);
        pane = new Pane();
        inventory = new Inventory(getModuleName(), this.dataSource);
        pane.getChildren().add(inventory.deployIcon.display);
        GameTickTimer timer = new GameTickTimer(60); timer.declareHost(this);
        timer.setFPS(60);
        
        /*Branch defaultBranch = new Branch();
        defaultBranch.injectModRef(this);
        defaultBranch.setFields(300, 0.3, 300);
        elements.add(defaultBranch);
        defaultBranch.setShuttleImage(1);*/
        timer.startThread();
        
        inventory.icons.get(0).display.setOnMouseClicked(e -> {
            newestCreatedElement = inventory.icons.get(0).deploy();
            elements.add(newestCreatedElement);
            generateBranchWindow().show();
        });
    }
    
    public Stage generateBranchWindow() {
        Stage stage = new Stage();
        VBox windowLayout1 = new VBox(20);
        
        TextField fieldforRadius = new TextField("150"); 
        TextField fieldforFreq = new TextField("2"); 
        TextField fieldforPosX = new TextField("600"); 
        TextField fieldforPosY = new TextField("400"); 
        TextField fieldforShuttleType = new TextField("1"); 
        TextField fieldforMass = new TextField("1"); 
        
        Text textforRadius = new Text("spin radius (m)"); 
        Text textforFreq = new Text("cycles per second (Hz)"); 
        Text textforPosX = new Text("center x position (pixels)"); 
        Text textforPosY = new Text("center y position (pixels)"); 
        Text textforShuttleType = new Text("shuttle type (1-3)");
        Text textforMass = new Text("shuttle mass (kg)"); 
        
        
        fieldforRadius.setPrefColumnCount(3);
        fieldforRadius.textProperty().addListener(ov-> {
            String text = fieldforRadius.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1).replace("-", "");
               fieldforRadius .setText(text);
            }
        });
        
        fieldforFreq.setPrefColumnCount(3);
        fieldforFreq.textProperty().addListener(ov-> {
            String text = fieldforFreq.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1).replace("-", "");
               fieldforFreq.setText(text);
            }
        });


        fieldforPosX .setPrefColumnCount(3);
        fieldforPosX .textProperty().addListener(ov-> {
            String text = fieldforPosX.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1).replace("-", "");
               fieldforRadius .setText(text);
            }
        });

        fieldforPosY.setPrefColumnCount(3);
        fieldforPosY.textProperty().addListener(ov-> {
            String text = fieldforPosY.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1).replace("-", "");
               fieldforFreq.setText(text);
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
           double radius = Double.parseDouble(fieldforRadius.getText());
           double frequency = Double.parseDouble(fieldforFreq.getText());
           double mass = Double.parseDouble(fieldforMass.getText());
           double posXValue = Double.parseDouble(fieldforPosX.getText());
           double posYValue = Double.parseDouble(fieldforPosY.getText());
           int shuttleType = Integer.parseInt(fieldforShuttleType.getText()) % 4;
           if (shuttleType == 0) {shuttleType = 1;}
           
           Branch branch = (Branch)newestCreatedElement;
           branch.injectModRef(this);
           
           branch.setFields(radius, frequency, 10);
           branch.setCenter(posXValue, posYValue);
           branch.setShuttleImage(shuttleType);
           
           elements.add(branch);
           
           stage.close();
        });
        
        windowLayout1.getChildren().addAll(fieldforRadius, textforRadius, fieldforFreq, textforFreq, fieldforMass, textforMass, fieldforShuttleType, textforShuttleType, fieldforPosX, textforPosX, fieldforPosY, textforPosY, buttons);
        
        stage.setScene(new Scene(windowLayout1));
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
                Logger.getLogger(ProjectileMotionModule.class.getName()).log(Level.SEVERE, null, ex);
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
