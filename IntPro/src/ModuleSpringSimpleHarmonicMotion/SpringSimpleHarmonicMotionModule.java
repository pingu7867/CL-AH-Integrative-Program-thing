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
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
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
        elements = new ArrayList();
        pane = new Pane();
        inventory = new Inventory(getModuleName(), dataSource);
        pane.getChildren().add(inventory.deployIcon.display);
        
        ticker = new GameTickTimer(this, 60); 
        ticker.declareHost(this);
        ticker.startThread();
        
        inventory.icons.get(0).display.setOnMouseClicked(e -> {
            generateSpringWindow().show();
        });
        
        inventory.icons.get(1).display.setOnMouseClicked(e -> {
            generateWeightWindow().show();
        });
        
    }
    
    public Stage generateSpringWindow() {
        Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);
        
        Text textforMass = new Text("weight's mass in kg");
        Text textforLength = new Text("spring's length in cm");
        Text textforXPosition = new Text("spring's horizontal position in cm");
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
        
        TextField fieldforXPosition = new TextField("400");
        fieldforXPosition.setPrefColumnCount(3);
        fieldforXPosition.textProperty().addListener(ov-> {
            String text = fieldforMass.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldforMass.setText(text);
            }
        });
        
        TextField fieldforKConstant = new TextField("1");
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
           double xPositionValue = Double.parseDouble(fieldforXPosition.getText());
           double kconstantValue = Double.parseDouble(fieldforKConstant.getText());
           
           Spring spring = (Spring)(inventory.icons.get(0).deploy());
           
           spring.injectModRef(this);
           spring.setFields(lengthValue, kconstantValue, new Oscillator(), new Weight(massValue));
           
           spring.xPosition = xPositionValue;
           
           listOfSprings.add(spring);
           spring.oscillator.startOsc();
           
           
           stage.close();
           
        });
        
        WindowLayout.getChildren().addAll(fieldforMass, textforMass, fieldforLength, textforLength, fieldforXPosition, textforXPosition, fieldforKConstant, textforKConstant, buttons);
        
        stage.setScene(new Scene(WindowLayout));
        stage.requestFocus();
        return stage;
    }
    
    public Stage generateWeightWindow() {
        Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);
        
        Text textforMass = new Text("New weight's mass in kg");
        Text textforNum = new Text("which spring to snap new weight to");
        
        TextField fieldforMass = new TextField("1");
        fieldforMass.setPrefColumnCount(3);
        fieldforMass.textProperty().addListener(ov-> {
            String text = fieldforMass.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldforMass.setText(text);
            }
        });
        
        TextField fieldforNum = new TextField("1");
        fieldforNum.setPrefColumnCount(3);
        fieldforNum.textProperty().addListener(ov-> {
            String text = fieldforNum.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1);
               text.replace("-", "");
               fieldforNum .setText(text);
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
            int numValue = Math.min(Integer.parseInt(fieldforNum.getText()), listOfSprings.size() - 1);
            
            System.out.println("NUM " + numValue);
            ((Spring)(listOfSprings.get(numValue))).addWeight(massValue);
            stage.close();
        });
        
        WindowLayout.getChildren().addAll(fieldforMass, textforMass, fieldforNum, textforNum, buttons);
        
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
                ticker.stopTimer();
                ticker.clockThread.interrupt();
                dataSource.flushModule(moduleNumber);
            } catch (IOException ex) {
                Logger.getLogger(SpringSimpleHarmonicMotionModule.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    @Override
    public void tickProcess() {
        for (Iterator i = listOfSprings.iterator(); i.hasNext();) {
            Element e = (Element)(i.next());
            Platform.runLater(() -> {
                e.draw(); ((Spring)e).refreshDisplay();
            });
        }
    }
    
}
