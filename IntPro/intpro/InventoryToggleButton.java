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
    public InventoryToggleButton(Inventory inv) {
        this.attached = inv;
    }
    @Override
    public void action() {
        attached.toggle();
    }
}
