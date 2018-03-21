/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

import java.io.File;
import java.util.HashSet;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Cédric
 */
public class Module implements GameTickActor {
    
    public Core dataSource;
    public RenderSet renderSet;
    public HashSet<Element> elements;
    
    public Pane pane;
    public Scene scene;
    public Stage viewport;
    
    public double framerate = 60;
    public GameTickTimer ticker;
    
    public File save;
    
    public Module(Core creator) {
        this.dataSource = creator;
        renderSet = new RenderSet();
        elements = new HashSet<Element>();
        this.ticker = new GameTickTimer(framerate);
        this.ticker.declareHost(this);
    }
    
    public int getResolutionX() {
        return dataSource.getRes2X();
    }
    
    public int getResolutionY() {
        return dataSource.getRes2Y();
    }
    
    public double getScaling() {
        return dataSource.res2x / dataSource.res1x;
    }
    
    public void saveState() {
        //o o f
    }
    
    public Element[] getElementsArray() {
        return null; //o o f
    }
    
    @Override
    public void tickProcess() {
        //o o f
    }
}
