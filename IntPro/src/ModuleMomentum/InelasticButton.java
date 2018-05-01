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
public class InelasticButton extends ImageButton {
    MomentumModule module;
    
    public InelasticButton(Core creator, MomentumModule mod) {
        super(creator);
        this.simpleGraphicSetUp("idle", "Inelastic");
        this.simpleGraphicSetUp("press", "InelasticActivated");
        module = mod;
    }
    @Override
    public void action() {
        module.typeOfCollision = "inelastic";
        module.labelTypeOfCollision.setText("Type Of Collision: Inelastic");
        for (PhysicalBody body: module.listOfPhysicalBodies) {
            body.typeOfCollision = "inelastic";
        }
    }
}

