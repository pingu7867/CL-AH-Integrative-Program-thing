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
public class InventoryToggleButton extends ImageButton {
    Inventory attached;
    public InventoryToggleButton(Inventory inv, Core core) {
        super(core);
        doubleClickDuration = 0;
        this.attached = inv;
        simpleGraphicSetUp("idle", "InventoryIcon");
        setPosX(40);
        setPosY(200);
        
    }
    
    @Override
    public void action() {
        System.out.print("toggle ");
        attached.toggle();
    }
}
