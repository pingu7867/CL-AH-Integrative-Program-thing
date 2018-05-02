/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWaveSuperposition;
import intpro.*;
/**
 *
 * @author Amine
 */
public class ResetButtonWaveSuperposition extends ResetButton{
    WaveSuperpositionModule module;
    public ResetButtonWaveSuperposition(Core creator, WaveSuperpositionModule module) {
        super(creator);
        this.doubleClickDuration = 0;
        this.module = module;
    }
    @Override
    public void action() {
        
        module.pane.getChildren().clear();
        module.box.getChildren().clear();
        module.curves.clear();
        module.elements.clear();
    }
    
}
