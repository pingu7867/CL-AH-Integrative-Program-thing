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
public class PerfectlyInelasticButton extends ImageButton {
    MomentumModule module;
    
    public PerfectlyInelasticButton(Core creator, MomentumModule mod) {
        super(creator);
        this.doubleClickDuration = 0;
        this.simpleGraphicSetUp("idle", "PerfectlyInelastic");
        this.simpleGraphicSetUp("press", "PerfectlyInelasticActivated");
        module = mod;
    }
    @Override
    public void action() {
        module.setTypeOfCollision("perfectly inelastic");
        module.labelTypeOfCollision.setText("Type Of Collision: Perfectly Inelastic");
        for (PhysicalBody body: module.listOfPhysicalBodies) {
            body.setTypeOfCollision("perfectly inelastic");
        }
    }
    
}
