/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

import java.util.ArrayList;

/**
 *
 * @author Cédric
 */
public class Inventory extends Popout {
    public InventoryToggleButton deployIcon;
    public ArrayList<InventoryIcon> icons;
    public boolean showing = false;
    
    public Inventory(String moduleName) {
        deployIcon = new InventoryToggleButton(this);
        
        if (moduleName.equals("projectile motion")) {
            icons.add(new InventoryIcon("cannon"));
            icons.add(new InventoryIcon("vehicle"));
        }
        if (moduleName.equals("lens optics")) {
            icons.add(new InventoryIcon("lens"));
        }
        if (moduleName.equals("spring simple harmonic motion")) {
            icons.add(new InventoryIcon("spring"));
            icons.add(new InventoryIcon("weight"));
        }
        if (moduleName.equals("momentum")) {
            icons.add(new InventoryIcon("physical body"));
        }
        if (moduleName.equals("charge particle path")) {
            icons.add(new InventoryIcon("charged particle"));
            icons.add(new InventoryIcon("capacitor"));
        }
        if (moduleName.equals("circular motion")) {
            icons.add(new InventoryIcon("branch"));
        }
        if (moduleName.equals("wave superposition")) {
            icons.add(new InventoryIcon("sine wave"));
            icons.add(new InventoryIcon("square wave"));
            icons.add(new InventoryIcon("triangle wave"));
            icons.add(new InventoryIcon("saw wave"));
            icons.add(new InventoryIcon("linear function"));
            icons.add(new InventoryIcon("quadratic function"));
            icons.add(new InventoryIcon("cubic function"));
            icons.add(new InventoryIcon("polynomial function"));
            icons.add(new InventoryIcon("composite function"));
        }
    }
    
    public void toggle() {
        if (!showing) {
            deploy();
        }
        else {
            retract();
        }
    }
    
    @Override
    public void deploy() {
        for (int i = 0; i < icons.size(); i++) {
            //show
        }
    }
    
    @Override
    public void retract() {
        for (int i = 0; i < icons.size(); i++) {
            //show
        }
    }
}
