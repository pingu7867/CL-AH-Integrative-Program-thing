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
/**
 *
 * @author Amine
 */
public class PolynomialInventoryIcon extends InventoryIcon{
    WaveSuperpositionModule module;
    public PolynomialInventoryIcon(Core creator, WaveSuperpositionModule module) {
        super("polynomial function",creator);
        display.setImage(new Image(new File("src/Assets/PolynomialFuncInventoryIcon.png").toURI().toString()));
        this.module = module;
    }
    @Override
    public void action(){
        Stage window = generatePolynomialCurveWindow();
        window.show();
    }
    public Stage generatePolynomialCurveWindow() {
        Stage stage = new Stage();
        
        VBox WindowLayout = new VBox(20);
        
        HBox buttons = new HBox(8);
            buttons.setAlignment(Pos.BOTTOM_RIGHT);
            Button Create = new Button("Create");        
            Button Cancel = new Button("Cancel");
            buttons.getChildren().addAll(Cancel, Create);
            
        TextField fieldForDegree = new TextField("0");
        fieldForDegree.setPrefColumnCount(2);
        fieldForDegree.textProperty().addListener(cl-> {
            String text = fieldForDegree.getText();
            if (text.equals("")) {
                text = "0";
                fieldForDegree.setText(text);
            }
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForDegree.setText(text);
            }
            if (text.contains(".")) {
                text = text.replace(".", "");
                fieldForDegree.setText(text);
            }
            if (text.contains("-")) {
                text = text.replace("-", "");
                fieldForDegree.setText(text);
            }
            
        });
        
        Label labelforDegree = new Label("Enter a natural number for the degree of the function", fieldForDegree);
        labelforDegree.setContentDisplay(ContentDisplay.LEFT);
        
        WindowLayout.getChildren().addAll(labelforDegree,buttons);
        Create.setOnAction(eh -> {
           
           int degreeValue = Integer.parseInt(fieldForDegree.getText());
           Curve curve = new Curve(new PolynomialFunction(degreeValue), module.getResolutionX());
           module.listOfCurves.add(curve);
           module.setWavePane(curve);
           stage.close();
           curve.curve.setOnMouseClicked(eg-> {
           if (module.deleteModeActivated) {
                   module.elements.remove(curve);
                   module.listOfCurves.remove(curve);
                   module.listOfWavePanes.remove(curve.getPane());
                   module.box.getChildren().remove(curve.getPane());
               }
           });
           
            });
        Cancel.setOnAction(eh-> {
              stage.close();
            });
            stage.setScene(new Scene(WindowLayout));
        return stage;
    }
}
