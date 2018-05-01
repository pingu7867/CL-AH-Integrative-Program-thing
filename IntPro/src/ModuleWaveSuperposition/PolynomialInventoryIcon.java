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
        super("sine wave",creator);
        display.setImage(new Image(new File("src/Assets/SawWaveInventoryIcon.png").toURI().toString()));
        this.module = module;
    }
    @Override
    public void action(){
        Stage window = generatePolynomialCurveWindow();
        window.show();
    }
    public Stage generatePolynomialCurveWindow() {
        Stage stage = new Stage();
        BorderPane bordWindow = new BorderPane();
        VBox WindowLayout = new VBox(20);
        
        HBox buttons = new HBox(8);
            buttons.setAlignment(Pos.BOTTOM_RIGHT);
            Button Create = new Button("Create");        
            Button Cancel = new Button("Cancel");
            buttons.getChildren().addAll(Cancel, Create);
            
            stage.setScene(new Scene(WindowLayout));
        return stage;
    }
}
