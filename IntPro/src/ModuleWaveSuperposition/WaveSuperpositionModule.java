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
import javafx.scene.control.ScrollBar;
/**
 *
 * @author Cédric
 */
public class WaveSuperpositionModule extends Module {
    ArrayList<Curve> curves = new ArrayList<>();
    ArrayList<BorderPane> wavePanes = new ArrayList<>();
    VBox box = new VBox(20);
    
      public WaveSuperpositionModule(Core creator, int moduleNumber) {
        super(creator, moduleNumber);
        BorderPane mainBord = new BorderPane();
        ExitButton exit = new ExitButton(creator, this);
        HBox buttons = new HBox(10);
        HBox bottombutton = new HBox(10);
        
        DeleteButton delete = new DeleteButton(creator,this);
        bottombutton.getChildren().add(exit.display);
        bottombutton.setAlignment(Pos.CENTER_LEFT);
        
        pane = new Pane();
        
        SawWaveInventoryIcon sawIcon = new SawWaveInventoryIcon(creator,this);
        SineWaveInventoryIcon sineIcon = new SineWaveInventoryIcon(creator,this);
        SquareWaveInventoryIcon squareIcon = new SquareWaveInventoryIcon(creator,this);
        TriangleWaveInventoryIcon triangleIcon = new TriangleWaveInventoryIcon(creator,this);
        CompositeFunctionInventoryIcon compIcon = new CompositeFunctionInventoryIcon(creator,this);
        
        buttons.getChildren().addAll(sawIcon.display,sineIcon.display,squareIcon.display ,triangleIcon.display,compIcon.display,delete.display);
        viewport = new Stage();
        mainBord.setTop(buttons);
        mainBord.setBottom(bottombutton);
        mainBord.setCenter(box);
        scene = new Scene(mainBord,creator.getRes1X(),creator.getRes1Y());
        viewport.setScene(scene);
    }
    @Override
    public void popOut() {
        viewport.show();
    }
    public void setWavePane(Curve curve) {
        HBox buttons = new HBox(10);
        Pane wpane = new Pane();
        BorderPane bord = new BorderPane();
        
        RunSimulationOneWave rsowbutton = new RunSimulationOneWave(dataSource,curve);
        PauseButtonOneWave pbowbutton = new PauseButtonOneWave(dataSource,curve);
        
        buttons.getChildren().addAll(rsowbutton.display, pbowbutton.display);
        
        wpane.getChildren().addAll(curve.xAxis,curve.curve);
        
        bord.setTop(buttons);
        bord.setCenter(wpane);
        wavePanes.add(bord);
        box.getChildren().add(bord);
    }
      
    @Override
    public String getModuleName(){
        return "wave superposition";
    }
}
