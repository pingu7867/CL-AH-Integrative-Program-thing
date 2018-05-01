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
public class ElasticButton extends ImageButton {
    MomentumModule module;
    
    public ElasticButton(Core creator, MomentumModule mod) {
        super(creator);
        this.simpleGraphicSetUp("idle", "Elastic");
        this.simpleGraphicSetUp("press", "ElasticActivated");
        module = mod;
    }
    @Override
    public void action() {
        module.typeOfCollision = "elastic";
        module.labelTypeOfCollision.setText("Type Of Collision: Elastic");
        for (PhysicalBody body: module.listOfPhysicalBodies) {
            body.typeOfCollision = "elastic";
        }
    }
}
