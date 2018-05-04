/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Cédric
 */

public class RenderSet {
    public ArrayList<javafx.scene.Node> elements;
    public Module module;
    
    public RenderSet() {
       elements = new ArrayList();
    }
    
    public RenderSet(Module module) {
        elements = new ArrayList();
        injectModuleRef(module);
    }
    
    public void add(javafx.scene.Node newItem) {
        elements.add(newItem);
    }
    
    public void addList(ArrayList<javafx.scene.Node> list) {
        for (int i = 0; i < list.size(); i++) {
            elements.add(list.get(i));
        }
    }
    
    public void remove(javafx.scene.Node binnedItem) {
        elements.remove(binnedItem);
    }
    
    public void removeList(ArrayList<javafx.scene.Node> list) {
        for (int i = 0; i < list.size(); i++) {
            elements.remove(list.get(i));
        }
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
            element.setScaleX(module.getScaling());
            element.setScaleY(module.getScaling());
        }
    }
    
    public void injectModuleRef(Module module) {
        this.module = module;
    }
}
