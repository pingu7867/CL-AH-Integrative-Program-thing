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
        pane = new Pane();
        scene = new Scene(pane, sizeX, sizeY);
        
        switch(chartype) {
            case 's':
                scroll = new ScrollPane();
                content = new Pane();
                pane.getChildren().add(scroll);
                scroll.setContent(content);
                
            case 'p':
                
        }
        type = chartype;
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
