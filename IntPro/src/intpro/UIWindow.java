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
    
    int sizeX = 1200;
    int sizeY = 800;
    
    public String windowName = "";
    public Pane pane;
    
    public ScrollPane scroll;
    public Pane content;
    
    public Scene scene;
    public Stage viewport = new Stage();
    
    char type;
    
    public UIWindow(char chartype) {
        type = chartype;
        switch(chartype) {
            case 'i':
                pane = new Pane();
                scroll = new ScrollPane();
                content = new Pane();
                
            case 's':
                pane = new Pane();
                scroll = new ScrollPane();
                content = new Pane();
                scroll.setContent(content);
                pane.getChildren().add(scroll);
                
            case 'p':
                pane = new Pane();
                
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
        scene = new Scene(pane, sizeX, sizeY);
        viewport.setScene(scene);
        viewport.show();
    }
    
    public void addToPane(javafx.scene.Node n) {
        switch(type) {
            case 's': 
                content.getChildren().add(n); return;
            case 'i': 
                content.getChildren().add(n); return;
            default: 
                pane.getChildren().add(n);
        }
    }
}
