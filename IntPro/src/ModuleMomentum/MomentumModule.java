/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleMomentum;

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
/**
 *
 * @author Cédric
 */
public class MomentumModule extends Module {
    ArrayList<PhysicalBody> listOfPhysicalBodies = new ArrayList<>();
    String typeOfCollision = "elastic";
    Label labelTypeOfCollision = new Label("Type of Collision" + typeOfCollision);
    public MomentumModule(Core creator, int moduleNumber) {
        super(creator, moduleNumber);
        BorderPane bord = new BorderPane();
        pane = new Pane();
        ExitButton exit = new ExitButton(creator, this);
        HBox buttons = new HBox(10);
        HBox bottombutton = new HBox(10);
        
        bottombutton.getChildren().add(exit.display);
        bottombutton.setAlignment(Pos.CENTER_LEFT);
        
        bord.setCenter(pane);
        bord.setTop(buttons);
        bord.setBottom(bottombutton);
        
        PhysicalBodyInventoryIcon pbIcon = new PhysicalBodyInventoryIcon(creator,this);
        ElasticButton ebutton = new ElasticButton(creator,this);
        InelasticButton inebutton = new InelasticButton(creator,this);
        PerfectlyInelasticButton pinebutton = new PerfectlyInelasticButton(creator,this);
        RunSimulationMomentum runSimulation = new RunSimulationMomentum(creator,this);
        PauseButtonMomentum pausebutton = new PauseButtonMomentum(creator,this);
        ResetButtonMomentum resetbutton = new ResetButtonMomentum(creator,this);
        DeleteButton delete = new DeleteButton(creator,this);
        
        buttons.getChildren().addAll(pbIcon.display, ebutton.display,inebutton.display,pinebutton.display,labelTypeOfCollision,
                runSimulation.display,pausebutton.display,resetbutton.display, delete.display);
        viewport = new Stage();
        
        scene = new Scene(bord,this.getResolutionX(), this.getResolutionY());
        viewport.setScene(scene);
        
    }
    @Override
    public void popOut() {
        viewport.show();
    }
    @Override
    public String getModuleName(){
        return "momentum";
    }
 }
    

