/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

/**
 *
 * @author Cédric
 */

import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class UIWindow {
    
    int sizeX;
    int sizeY;
    
    public String windowName = "";
    public Pane pane = new Pane();
    public Stage viewport = new Stage();
    
    public UIWindow() {
        viewport.setTitle(windowName);
    }
    
    public Pane getPane() {
        return this.pane;
    }
    
    public String getWindowName() {
        return this.windowName;
    }
    
    public void display() {
        viewport.setScene(new Scene(pane, sizeX, sizeY));
        viewport.show();
    }
}
