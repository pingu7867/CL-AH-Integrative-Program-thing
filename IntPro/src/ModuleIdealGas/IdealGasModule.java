/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleIdealGas;

import intpro.Module;
import intpro.*;
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
import javafx.scene.shape.Rectangle;
/**
 *
 * @author Cédric
 */
public class IdealGasModule extends Module {
    ArrayList<Container> listOfContainer = ArrayList<>();
    public IdealGasModule(Core creator, int moduleNumber) {
        super(creator, moduleNumber);
        pane = new Pane();
        scene = new Scene(pane);
        viewport = new Stage();
        viewport.setScene(scene);
        Container cont = new Container(11,1,101.3,273);
        Rectangle hitbox = new Rectangle(92, 265, 420, 723);
        hitbox.setFill(Color.BLACK);
        hitbox.setStroke(Color.BLACK);
        pane.getChildren().add(cont.sprite);
        
        
        for (int i = 0; i < cont.particles.size(); i++) {
            pane.getChildren().add(cont.particles.get(i).sprite);
        }
        
        cont.setAnimation();
        
    }
    @Override
    public void popOut() {
        viewport.show();
    }
    
}
