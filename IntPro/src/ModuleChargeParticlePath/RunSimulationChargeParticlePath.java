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
public class RunSimulationChargeParticlePath extends RunSimulationButton{
    ChargeParticlePathModule module;
    public RunSimulationChargeParticlePath(Core creator, ChargeParticlePathModule module) {
        super(creator);
        this.doubleClickDuration = 0;
        this.module = module;
    }
    @Override
    public void action() {
        for (ParallelPlateCapacitor capa: module.listOfCapacitors){
                capa.lockPosition();
            }
            for (ChargeParticle e:module.listOfParticles) {
                e.lockPosition();
                e.setUpList(module.listOfCapacitors);
                e.play();
            }
    }
}
