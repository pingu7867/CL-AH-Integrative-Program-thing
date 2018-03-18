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
public class Inventory {
    public InventoryToggleButton deployIcon;
    public ArrayList<InventoryIcon> icons;
    public boolean showing = false;
    
    public Inventory(String moduleName) {
        deployIcon = new InventoryToggleButton(this);
        
        if (moduleName.equals("projectile motion")) {
            icons.add(new InventoryIcon('c'));
            icons.add(new InventoryIcon('v'));
        }
        if (moduleName.equals("lens optics")) {
            icons.add(new InventoryIcon('l'));
        }
    }
    
    public void toggle() {
        if (!showing) {
            for (int i = 0; i < icons.size(); i++) {
                //show
            }
        }
    }
}
