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

public class ItemPopout extends Popout {
    
    PopoutCloseButton closeButton;
    Element associatedElement;
    
    public ItemPopout(Element element) {
        associate(element);
    }
    
    public void associate(Element element) {
        this.associatedElement = element;
    }
    
    @Override
    public void deploy() {
        
    }
    
    @Override
    public void retract() {
        
    }
    
}
