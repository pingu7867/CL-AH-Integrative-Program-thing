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
public class RunSimulationMomentum extends RunSimulationButton{
    MomentumModule module;
    public RunSimulationMomentum(Core creator, MomentumModule module) {
        super(creator);
        this.doubleClickDuration = 0;
        this.module = module;
    }
    @Override
    public void action() {
        for (PhysicalBody body: module.listOfPhysicalBodies){
                body.setUp(module.typeOfCollision, module.listOfPhysicalBodies);
                body.play();
            }
      
    }
    
}
