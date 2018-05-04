/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWaveSuperposition;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
/**
 *
 * @author Amine
 */
public class WaveSuperpositionModule extends Module {
    int numberOfCurves = 0;
    ArrayList<Curve> listOfCurves = new ArrayList<>();
    ArrayList<BorderPane> listOfWavePanes = new ArrayList<>();
    VBox box = new VBox(100);
    
      public WaveSuperpositionModule(Core creator, int moduleNumber) {
        super(creator, moduleNumber);
        BorderPane mainBord = new BorderPane();
        //ExitButton exit = new ExitButton(creator, this);
        HBox buttons = new HBox(10);
        HBox bottombutton = new HBox(10);
        
        DeleteButton delete = new DeleteButton(creator,this);
        
        //bottombutton.getChildren().add(exit.display);
        bottombutton.setAlignment(Pos.CENTER_LEFT);
        
        pane = new Pane();
        
        LinearFunctionInventoryIcon lfIcon = new LinearFunctionInventoryIcon(creator, this);
        PolynomialInventoryIcon pIcon = new PolynomialInventoryIcon(creator, this);
        SawWaveInventoryIcon sawIcon = new SawWaveInventoryIcon(creator,this);
        SineWaveInventoryIcon sineIcon = new SineWaveInventoryIcon(creator,this);
        SquareWaveInventoryIcon squareIcon = new SquareWaveInventoryIcon(creator,this);
        TriangleWaveInventoryIcon triangleIcon = new TriangleWaveInventoryIcon(creator,this);
        CompositeFunctionInventoryIcon compIcon = new CompositeFunctionInventoryIcon(creator,this);
        ResetButtonWaveSuperposition reset = new ResetButtonWaveSuperposition(creator, this);
                
        buttons.getChildren().addAll(lfIcon.display,pIcon.display,sawIcon.display,sineIcon.display,squareIcon.display ,triangleIcon.display,compIcon.display,
                delete.display, reset.display);
        
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(box);
        
        viewport = new Stage();
        mainBord.setTop(buttons);
        mainBord.setBottom(bottombutton);
        mainBord.setCenter(scroll);
       
        scene = new Scene(mainBord,creator.getRes1X(),creator.getRes1Y());
        viewport.setScene(scene);
        Inventory inventory = new Inventory(getModuleName(), creator);
    }
    @Override
    public void popOut() {
        viewport.setTitle("Wave Superposition Module");
        viewport.show();
        
        this.viewport.setOnCloseRequest(e -> {
            try {
                dataSource.flushModule(moduleNumber);
            } catch (IOException ex) {
                Logger.getLogger(ProjectileMotionModule.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    public void setWavePane(Curve curve) {
        HBox putLabel = new HBox();
        putLabel.setAlignment(Pos.CENTER);
        Pane wpane = new Pane();
        BorderPane bordWPane = new BorderPane();
        
        wpane.getChildren().addAll(curve.xAxis,curve.curve);
        Label index = new Label("" + numberOfCurves);
        numberOfCurves++;
        putLabel.getChildren().add(index);
        
        bordWPane.setTop(putLabel);
        bordWPane.setCenter(wpane);
        listOfWavePanes.add(bordWPane);
        box.getChildren().add(bordWPane);
        curve.setPane(bordWPane);
    }
      
    @Override
    public String getModuleName(){
        return "wave superposition";
    }
    public void resetNumberOfCurves(){
        this.numberOfCurves = 0;
    }
    
}
