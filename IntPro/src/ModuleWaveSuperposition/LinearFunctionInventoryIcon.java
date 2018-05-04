/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWaveSuperposition;

import intpro.Core;
import intpro.InventoryIcon;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Amine
 */
public class LinearFunctionInventoryIcon extends InventoryIcon{
    WaveSuperpositionModule module;
    public LinearFunctionInventoryIcon(Core creator, WaveSuperpositionModule module) {
        super("linear function",creator);
        display.setImage(new Image(new File("src/Assets/LinearFuncInventoryIcon.png").toURI().toString()));
        this.module = module;
    }
    @Override
    public void action(){
        Stage window = generateLinearCurveWindow();
        window.show();
    }
    public Stage generateLinearCurveWindow() {
        Stage stage = new Stage();
        
        VBox WindowLayout = new VBox(20);
        
        HBox buttons = new HBox(8);
            buttons.setAlignment(Pos.BOTTOM_RIGHT);
            Button Create = new Button("Create");        
            Button Cancel = new Button("Cancel");
            buttons.getChildren().addAll(Cancel, Create);
            
        TextField fieldForSlope = new TextField("0");
        fieldForSlope.setPrefColumnCount(2);
        fieldForSlope.textProperty().addListener(cl-> {
            String text = fieldForSlope.getText();
            if (text.equals("")) {
                text = "0";
                fieldForSlope.setText(text);
            }
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForSlope.setText(text);
            }          
            
        });
        TextField fieldForYIntercept = new TextField("0");
        fieldForYIntercept.setPrefColumnCount(2);
        fieldForYIntercept.textProperty().addListener(cl-> {
            String text = fieldForYIntercept.getText();
            if (text.equals("")) {
                text = "1";
                fieldForYIntercept.setText(text);
            }
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForYIntercept.setText(text);
            }          
            
        });
        
        Label labelforSlope = new Label("Linear slope", fieldForSlope);
        labelforSlope.setContentDisplay(ContentDisplay.LEFT);
        
        Label labelforYIntercept = new Label("YIntercept",fieldForYIntercept);
        labelforYIntercept.setContentDisplay(ContentDisplay.LEFT);
        
        WindowLayout.getChildren().addAll(labelforSlope,labelforYIntercept,buttons);
        Create.setOnAction(eh -> {
           
           double slopeValue = Double.parseDouble(fieldForSlope.getText());
           double yInterceptValue = Double.parseDouble(fieldForYIntercept.getText());
           
           
           Curve curve = new Curve(new LinearFunction(slopeValue,yInterceptValue), module.getResolutionX());
           module.listOfCurves.add(curve);
           module.setWavePane(curve);
           curve.curve.setOnMouseClicked(eg-> {
           if (module.deleteModeActivated) {
                   module.elements.remove(curve);
                   module.listOfCurves.remove(curve);
                   module.listOfWavePanes.remove(curve.getPane());
                   module.box.getChildren().remove(curve.getPane());
               }
           });
           
           stage.close();
            });
        Cancel.setOnAction(eh-> {
              stage.close();
            });
        stage.setScene(new Scene(WindowLayout));
        return stage;
    }
}
