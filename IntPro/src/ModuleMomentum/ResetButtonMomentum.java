/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleMomentum;
import intpro.*;
/**
 *
 * @author Amine
 */
public class ResetButtonMomentum extends ResetButton{
    MomentumModule module;
    public ResetButtonMomentum(Core creator, MomentumModule module) {
        super(creator);
        this.doubleClickDuration = 0;
        this.module = module;
    }
    @Override
    public void action() {
        module.pane.getChildren().clear();
        module.listOfPhysicalBodies.clear();
        module.elements.clear();
    }
}
