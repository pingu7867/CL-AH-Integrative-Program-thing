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
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;


public class UIWindow {
    
    int sizeX;
    int sizeY;
    
    public String windowName = "";
    public Pane pane = new Pane();
    
    public ScrollPane scroll;
    public Pane content;
    
    public Scene scene;
    public Stage viewport = new Stage();
    
    char type;
    
    public UIWindow(char c) {
        
        switch(c) {
            case 's': 
                scroll = new ScrollPane();
                this.content = new Pane();
                scroll.setContent(content);
                pane.getChildren().add(scroll);
                scene = new Scene(pane, sizeX, sizeY);
                type = c;
            case 'p': 
                this.pane = new Pane();
                scene = new Scene(pane, sizeX, sizeY);
                type = c;
        }
    }
    
    public UIWindow() {
        this.pane = new Pane();
        scene = new Scene(pane, sizeX, sizeY);
    }
    
    public Pane getPane(char c) {
        switch(c) {
            case 'c': return this.content;
            case 'p': return this.pane;
        }
        return this.pane;
    }
    
    public Pane getPane() {
        return this.pane;
    }
    
    public Pane getContent() {
        return this.content;
    }
    
    public String getWindowName() {
        return this.windowName;
    }
    
    public void display() {
        viewport.setScene(scene);
        viewport.show();
    }
    
    public void addToPane(javafx.scene.Node n) {
        switch(type) {
            case 's': 
                ((Pane)(scroll.getContent())).getChildren().add(n);
            case 'p': 
                pane.getChildren().add(n);
        }
    }
}
