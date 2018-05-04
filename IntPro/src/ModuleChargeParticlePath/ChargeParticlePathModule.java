/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleChargeParticlePath;

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

/**
 *
 * @author Amine
 */
public class ChargeParticlePathModule extends Module {
    ArrayList<ParallelPlateCapacitor> listOfCapacitors = new ArrayList<>();
    ArrayList<ChargeParticle> listOfParticles = new ArrayList<>();
    boolean IsPlaying = false;
    
    public ChargeParticlePathModule(Core creator, int moduleNumber) {
        super(creator,moduleNumber);
        moduleNumber = 3;
        BorderPane bord = new BorderPane();
        
        pane = new Pane();
        ExitButton exit = new ExitButton(creator, this);
        HBox buttons = new HBox(15);
        HBox bottombutton = new HBox(10);
        
        bottombutton.getChildren().add(exit.display);
        bottombutton.setAlignment(Pos.CENTER_LEFT);
        
        bord.setCenter(pane);
        bord.setTop(buttons);
        bord.setBottom(bottombutton);
        scene = new Scene(bord,creator.getRes1X(),creator.getRes1Y());
        viewport = new Stage();
        viewport.setScene(scene); viewport.setTitle("Cedamine Physics Toolkit: Charged Particle Path");
        
        ChargeParticleInventoryIcon cppIcon = new ChargeParticleInventoryIcon(creator,this);
        ParallelPlateCapacitorInventoryIcon ppcIcon = new ParallelPlateCapacitorInventoryIcon(creator,this);
        RunSimulationChargeParticlePath RunSimulation = new RunSimulationChargeParticlePath(creator, this);
        DeleteButton delete = new DeleteButton(creator,this);
        PauseButtonChargeParticlePath pausebutton = new PauseButtonChargeParticlePath(creator, this);
        ResetButtonChargeParticlePath resetbutton = new ResetButtonChargeParticlePath(creator, this);
        buttons.getChildren().addAll(ppcIcon.display,cppIcon.display,RunSimulation.display, pausebutton.display,delete.display,resetbutton.display);
        
    }
    @Override
    public void popOut() {
        viewport.setTitle("Charge Particle Path Module");
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
        return "charge particle path";
    }
    public ArrayList<ChargeParticle> getListOfParticles(){
        return this.listOfParticles;
    }
}
