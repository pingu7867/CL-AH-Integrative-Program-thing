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
    public int moduleNumber = 0;
    
    public Core core;
    public Module module;
    
    public Inventory(String moduleName, Core core) {
        this.core = core;
        deployIcon = new InventoryToggleButton(this, core);
        
        icons = new ArrayList<>();
        
        if (moduleName.equals("projectile motion")) {
            moduleNumber = 1;
            icons.add(new InventoryIcon("cannon", core));
            icons.add(new InventoryIcon("vehicle", core));
            System.out.println("created " + moduleName + "inventory");
            System.out.println("success? " + (icons.get(0) != null));
            
        }
        if (moduleName.equals("lens optics")) {
            moduleNumber = 2;
            icons.add(new InventoryIcon("lens", core));
        }
        if (moduleName.equals("spring simple harmonic motion")) {
            moduleNumber = 4;
            icons.add(new InventoryIcon("spring", core));
            icons.add(new InventoryIcon("weight", core));
        }
        if (moduleName.equals("momentum")) {
            moduleNumber = 7;
            icons.add(new InventoryIcon("physical body", core));
        }
        if (moduleName.equals("charge particle path")) {
            moduleNumber = 3;
            icons.add(new InventoryIcon("charged particle", core));
            icons.add(new InventoryIcon("capacitor", core));
        }
        if (moduleName.equals("circular motion")) {
            moduleNumber = 6;
            icons.add(new InventoryIcon("branch", core));
        }
        if (moduleName.equals("wave superposition")) {
            moduleNumber = 5;
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
        
        module = core.module[moduleNumber - 1];
        
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
        System.out.print("starts_process_with_" + icons.size() + "_length_arrayist ");
        for (int i = 0; i < icons.size(); i++) {
            //show
            icons.get(i).expandDown((i + 1) * 100);
            core.module[moduleNumber - 1].pane.getChildren().add(icons.get(i).display);
            
        }
    }
    
    @Override
    public void retract() {
        for (int i = 0; i < icons.size(); i++) {
            //hide
            icons.get(i).retractUp(i * 100);
        }
    }
}
