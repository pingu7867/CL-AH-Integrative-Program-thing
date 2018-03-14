/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Cédric
 */
public class RenderSet {
    HashSet<javafx.scene.Node> elements;
    Module module;
    
    public RenderSet() {
        elements = new HashSet();
    }
    
    public void add(javafx.scene.Node newItem) {
        elements.add(newItem);
    }
    
    public void remove(javafx.scene.Node binnedItem) {
        elements.remove(binnedItem);
    }
    
    public javafx.scene.Node find(javafx.scene.Node item) {
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            javafx.scene.Node element = (javafx.scene.Node)it.next();
            if (element.equals(item)) {return element;}
        }
        System.out.println("failed to find object " + item.toString());
        return null;
    }
    
    public void render() {
        elements.clear();
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            javafx.scene.Node element = (javafx.scene.Node)it.next();
            module.pane.getChildren().add(element);
        }
    }
    
    public void rescale() {
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            javafx.scene.Node element = (javafx.scene.Node)it.next();
            
        }
    }
}
