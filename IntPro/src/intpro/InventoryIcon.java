/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

import ModuleLensOptics.Lens;
import ModuleProjectileMotion.Cannon;
import ModuleProjectileMotion.MountVehicle;
import javafx.scene.image.ImageView;

/**
 *
 * @author Cédric
 */
public class InventoryIcon extends ImageButton {
    char ch = 0;
    
    public InventoryIcon(char type) {
        this.doubleClickDuration = 0;
        this.ch = type;
    }
    
    @Override
    public void action() {
        deploy();
    }
    
    public Element deploy() {
        switch(this.ch) {
            case 'c': return(new Cannon());
            case 'v': return(new MountVehicle());
            case 'l': return(new Lens());
            default: return null;
        }
    }
}
