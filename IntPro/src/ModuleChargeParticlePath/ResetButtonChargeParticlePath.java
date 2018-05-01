/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleChargeParticlePath;
import intpro.*;
/**
 *
 * @author Amine
 */
public class ResetButtonChargeParticlePath extends ResetButton{
    ChargeParticlePathModule module;
    public ResetButtonChargeParticlePath(Core creator, ChargeParticlePathModule module) {
        super(creator);
        this.doubleClickDuration = 0;
        this.module = module;
    }
    @Override
    public void action() {
        module.pane.getChildren().clear();
        module.listOfCapacitors.clear();
        module.listOfParticles.clear();
        module.elements.clear();
    }
}
