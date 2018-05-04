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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.shape.Polyline;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
/**
 *
 * @author Amine
 */
public class CompositeFunctionInventoryIcon extends InventoryIcon{
    WaveSuperpositionModule module;
    public CompositeFunctionInventoryIcon(Core creator, WaveSuperpositionModule module) {
        super("composite function",creator);
        simpleGraphicSetUp("display", "CompositeFuncInventoryIcon");
        this.module = module;
    }
    @Override
    public void action() {
        Stage window = generateSuperposedFunctionCurveWindow();
        window.show();
    }
    public Stage generateSuperposedFunctionCurveWindow() {
        Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);
        
        
        HBox buttons = new HBox(8);
            buttons.setAlignment(Pos.BOTTOM_RIGHT);
            Button Create = new Button("Create");        
            Button Cancel = new Button("Cancel");
            buttons.getChildren().addAll(Cancel, Create);
        
        TextField fieldForCurves = new TextField("");
        fieldForCurves.setPrefColumnCount(20);
        fieldForCurves.textProperty().addListener(eh-> {
            String text = fieldForCurves.getText();
            if(!checkField(text)) {
                text = text.substring(0, text.length() - 1);
                fieldForCurves.setText(text);
            }
        });
        Label labelforCurvesIndex = new Label("Enter the index of the curves (use / to separate the index) \n index of non-existant curves qill be ignored", fieldForCurves);
        labelforCurvesIndex.setContentDisplay(ContentDisplay.RIGHT);
        
        Create.setOnAction(eh -> {
           String text = fieldForCurves.getText();
           String[] indexes = text.split("/");
           ArrayList<Function> listOfFunctions = new ArrayList<>();
           for (int i =0; i < indexes.length; i++) {
               if (Integer.parseInt(indexes[i]) >= module.listOfCurves.size()) {
                   
                   continue;
               }
               if (module.listOfCurves.get(Integer.parseInt(indexes[i])).function instanceof CompositeFunction) {
                   CompositeFunction tempFunc = (CompositeFunction)module.listOfCurves.get(Integer.parseInt(indexes[i])).function;
                   
                   listOfFunctions.addAll(tempFunc.functions);
               }
               else {
               listOfFunctions.add(module.listOfCurves.get(Integer.parseInt(indexes[i])).function);
               }
           }
           Curve curve = new Curve(new CompositeFunction(listOfFunctions), module.getResolutionX());
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
        WindowLayout.getChildren().addAll(labelforCurvesIndex,buttons);
                
        stage.setScene(new Scene(WindowLayout));
        
        return stage;
    }
    public boolean checkField(String value) {
        
        for (int i = 0; i < value.length();i++) {
            if((value.charAt(i) == '/') || ((value.charAt(i) <= '9') && (value.charAt(i) >= '0'))) {
            }
            else {
                return false;
            }
        }
        return true;
    }
   
}
