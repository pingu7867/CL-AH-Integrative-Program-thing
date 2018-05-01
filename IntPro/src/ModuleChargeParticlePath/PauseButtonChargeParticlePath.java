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
public class PauseButtonChargeParticlePath extends PauseButton{
    ChargeParticlePathModule module;
    public PauseButtonChargeParticlePath(Core creator, ChargeParticlePathModule module) {
        super(creator);
        this.doubleClickDuration = 0;
        this.module = module;
    }
    @Override
    public void action() {
        for (ParallelPlateCapacitor capa: module.listOfCapacitors){
                capa.unlockPosition();
            }
            for (ChargeParticle e:module.listOfParticles) {
                e.unlockPosition();
                e.stop();
            }
    }
}
