/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Cédric
 */
public class Module {
    
    public Core dataSource;
    public RenderSet renderSet;
    
    public Pane pane;
    public Scene scene;
    public Stage viewport;
    
    
    
    public Module(Core creator) {
        this.dataSource = creator;
    }
    
    public int getResolutionX() {
        return dataSource.getRes2X();
    }
    
    public int getResolutionY() {
        return dataSource.getRes2Y();
    }
}
