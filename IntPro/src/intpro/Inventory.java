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
    
    public Core core;
    
    public Inventory(String moduleName, Core core) {
        this.core = core;
        deployIcon = new InventoryToggleButton(this, core);
        
        
        if (moduleName.equals("projectile motion")) {
            icons.add(new InventoryIcon("cannon", core));
            icons.add(new InventoryIcon("vehicle", core));
        }
        if (moduleName.equals("lens optics")) {
            icons.add(new InventoryIcon("lens", core));
        }
        if (moduleName.equals("spring simple harmonic motion")) {
            icons.add(new InventoryIcon("spring", core));
            icons.add(new InventoryIcon("weight", core));
        }
        if (moduleName.equals("momentum")) {
            icons.add(new InventoryIcon("physical body", core));
        }
        if (moduleName.equals("charge particle path")) {
            icons.add(new InventoryIcon("charged particle", core));
            icons.add(new InventoryIcon("capacitor", core));
        }
        if (moduleName.equals("circular motion")) {
            icons.add(new InventoryIcon("branch", core));
        }
        if (moduleName.equals("wave superposition")) {
            icons.add(new InventoryIcon("sine wave", core));
            icons.add(new InventoryIcon("square wave", core));
            icons.add(new InventoryIcon("triangle wave", core));
            icons.add(new InventoryIcon("saw wave", core));
            icons.add(new InventoryIcon("linear function", core));
            icons.add(new InventoryIcon("quadratic function", core));
            icons.add(new InventoryIcon("cubic function", core));
            icons.add(new InventoryIcon("polynomial function", core));
            icons.add(new InventoryIcon("composite function", core));
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
            //hide
            
        }
    }
}
