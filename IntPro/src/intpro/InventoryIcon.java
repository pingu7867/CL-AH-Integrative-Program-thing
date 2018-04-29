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
        
        switch(this.type.toLowerCase()) {
            case "cannon": simpleGraphicSetUp("idle", "CannonInventoryIcon"); break;
            case "vehicle": simpleGraphicSetUp("idle", "VehicleInventoryIcon"); break;
            case "lens": simpleGraphicSetUp("idle", ""); break;
            case "spring": simpleGraphicSetUp("idle", "SpringInventoryIcon"); break;
            case "weight": simpleGraphicSetUp("idle", "WeightInventoryIcon"); break;
            case "physical body": simpleGraphicSetUp("idle", ""); break;
            case "charged particle": simpleGraphicSetUp("idle", "ChargeParticleInventoryIcon"); break;
            case "capacitor": simpleGraphicSetUp("idle", "CapacitorInventoryIcon"); break;
            case "branch": simpleGraphicSetUp("idle", "CircularMotionBranchInventoryIcon"); break;
            case "sine wave": simpleGraphicSetUp("idle", "SineWaveInventoryIcon"); break;
            case "square wave": simpleGraphicSetUp("idle", "SquareWaveInventoryIcon"); break;
            case "triangle wave": simpleGraphicSetUp("idle", "TriangleWaveInventoryIcon"); break;
            case "saw wave": simpleGraphicSetUp("idle", "SawWaveInventoryIcon"); break;
            case "linear function": simpleGraphicSetUp("idle", "LinearFuncInventoryIcon"); break;
            case "quadratic function": simpleGraphicSetUp("idle", "QuadraticFuncInventoryIcon"); break;
            case "cubic": simpleGraphicSetUp("idle", "CubicFuncInventoryIcon"); break;
            case "polynomial function":simpleGraphicSetUp("idle", "PolynomialFuncInventoryIcon"); break;
            case "composite function": simpleGraphicSetUp("idle", "CompositeFuncInventoryIcon"); break;
            default: 
        }
        
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
            case "physical body": return new PhysicalBody();
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
