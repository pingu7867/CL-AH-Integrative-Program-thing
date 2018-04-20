/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

import ModuleLensOptics.Lens;
import ModuleProjectileMotion.Cannon;
import ModuleProjectileMotion.MountVehicle;
import ModuleSpringSimpleHarmonicMotion.Spring;
import ModuleSpringSimpleHarmonicMotion.Weight;
import ModuleMomentum.PhysicalBody;
import ModuleChargeParticlePath.ChargeParticle;
import ModuleChargeParticlePath.ParallelPlateCapacitor;
import ModuleCircularMotion.Branch;
import ModuleWaveSuperposition.*;
import javafx.scene.image.ImageView;

/**
 *
 * @author Cédric
 */
public class InventoryIcon extends ImageButton {
    public String type = "";
    
    public InventoryIcon(String type, Core core) {
        super(core);
        this.doubleClickDuration = 0;
        this.type = type;
    }
    
    @Override
    public void action() {
        deploy();
    }
    
    public Element deploy() {
        switch(this.type.toLowerCase()) {
            case "cannon": return new Cannon();
            case "vehicle": return new MountVehicle();
            case "lens": return new Lens();
            case "spring": return new Spring();
            case "weight": return new Weight();
            //case "physical body": return new PhysicalBody();
            case "charged particle": return new ChargeParticle();
            case "capacitor": return new ParallelPlateCapacitor();
            case "branch": return new Branch();
            case "sine wave": return new Curve(new SinFunction());
            case "square wave": return new Curve(new SquareWaveFunction());
            case "triangle wave": return new Curve(new TriangleWaveFunction());
            case "saw wave": return new Curve(new SawWaveFunction());
            case "linear function": return new Curve(new PolynomialFunction(1));
            case "quadratic function": return new Curve(new PolynomialFunction(2));
            case "cubic": return new Curve(new PolynomialFunction(3));
            case "polynomial function": return new Curve(new PolynomialFunction());
            case "composite function": return new Curve(new CompositeFunction());
            default: return null;
        }
    }
}
