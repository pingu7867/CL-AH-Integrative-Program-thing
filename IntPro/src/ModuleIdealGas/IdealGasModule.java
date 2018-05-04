/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleIdealGas;
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
import javafx.scene.shape.Rectangle;
/**
 *
 * @author Amine
 */
public class IdealGasModule extends Module {
    ArrayList<Container> listOfContainer = new ArrayList<>();
    Container container;
    public IdealGasModule(Core creator, int moduleNumber) {
        super(creator, moduleNumber);
        pane = new Pane();
        BorderPane bord = new BorderPane();
        //ExitButton exit = new ExitButton(creator, this);
        
        HBox bottombutton = new HBox(10);
        //bottombutton.getChildren().add(exit.display);
        bottombutton.setAlignment(Pos.CENTER_LEFT);
        
        this.container = new Container(1,1,101.3,273,this);
        
        pane.getChildren().add(container.sprite);
        pane.getChildren().add(container.tankFill);
        pane.getChildren().add(container.bounds);
        bord.setCenter(pane);
        bord.setBottom(bottombutton);
        scene = new Scene(bord);
        
    }
    @Override
    public void popOut() {
        
        viewport = new Stage();
        viewport.setTitle("Ideal Gas Module");
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
    public String getModuleName(){
        return "ideal gas";
    }
}

