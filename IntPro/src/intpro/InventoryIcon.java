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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

/**
 *
 * @author Cédric
 */
public class InventoryIcon extends ImageButton {
    public String type = "";
    public Inventory inventory;
    public Thread transitionThread;
    
    public InventoryIcon(String type, Core core) {
        super(core);
        this.doubleClickDuration = 0;
        this.type = type;
        
        switch(this.type.toLowerCase()) {
            case "cannon": simpleGraphicSetUp("idle", "CannonInventoryIcon"); break;
            case "vehicle": simpleGraphicSetUp("idle", "VehicleInventoryIcon"); break;
            case "lens": simpleGraphicSetUp("idle", "LensInventoryIcon"); break;
            case "spring": simpleGraphicSetUp("idle", "SpringInventoryIcon"); break;
            case "weight": simpleGraphicSetUp("idle", "WeightInventoryIcon"); break;
            case "physical body": simpleGraphicSetUp("idle", "PhysicalBodyInventoryIcon"); break;
            case "charged particle": simpleGraphicSetUp("idle", "ChargeParticleInventoryIcon"); break;
            case "capacitor": simpleGraphicSetUp("idle", "CapacitorInventoryIcon"); break;
            case "branch": simpleGraphicSetUp("idle", "CircularMotionBranchInventoryIcon"); break;
            case "sine wave": simpleGraphicSetUp("idle", "SineWaveInventoryIcon"); break;
            case "square wave": simpleGraphicSetUp("idle", "SquareWaveInventoryIcon"); break;
            case "triangle wave": simpleGraphicSetUp("idle", "TriangleWaveInventoryIcon"); break;
            case "saw wave": simpleGraphicSetUp("idle", "SawWaveInventoryIcon"); break;
            case "linear function": simpleGraphicSetUp("idle", "LinearFuncInventoryIcon"); break;
            case "quadratic function": simpleGraphicSetUp("idle", "QuadraticFuncInventoryIcon"); break;
            case "cubic function": simpleGraphicSetUp("idle", "CubicFuncInventoryIcon"); break;
            case "polynomial function":simpleGraphicSetUp("idle", "PolynomialFuncInventoryIcon"); break;
            case "composite function": simpleGraphicSetUp("idle", "CompositeFuncInventoryIcon"); break;
            default: 
        }
        
        display.setOnMouseClicked(e -> {
            inventory.module.newestCreatedElement = deploy();
        });
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
            //case "sine wave": return new Curve(new SinWaveFunction());
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
    
    public void expandDown() {
        transitionThread.start();
    }
    public void primeExpandThread(double fallLength) {
        
        transitionThread = new Thread(() -> {
            long nanoTime = 0;
            long startNanoTime = System.nanoTime();
            long stepNanoTime = 0;
            long frame = 0;
            int overflows = 0;
            double keyframeProportion = 0;

            while ((frame < 100) || (display.getOpacity() != 1)) {
                
                nanoTime = System.nanoTime();
                stepNanoTime = nanoTime;

                while (nanoTime - stepNanoTime < 10000000) {
                    nanoTime = System.nanoTime();
                }

                frame++;
                frame = Math.min(frame, 100);
                keyframeProportion = ((double)frame / 100.0);
                
                display.setOpacity(keyframeProportion);
                
                if (display.getLayoutY() > (module.scene.getHeight() - 100)) {
                    //System.out.println("inventory overflow, moving to next column");
                    overflows = overflows + 1;
                    //changePosY(inventory.deployIcon.display.getLayoutY() + (keyframeProportion * fallLength) - (overflows * 100));
                }
                
                changePosX(inventory.deployIcon.display.getLayoutX() + (overflows * 100));
                changePosY(inventory.deployIcon.display.getLayoutY() + (keyframeProportion * fallLength) - (overflows * 600));
                
                //System.out.println("frame" + frame + " at " + keyframeProportion + " proportion of " + fallLength);
                
            }
            display.setOpacity(1);
            changePosX(inventory.deployIcon.display.getLayoutX() + (overflows * 100));
        });
        
        
    }
    
    public void retractUp() {
        transitionThread.start();
    }
    public void primeRetractThread(double riseLength) {
        transitionThread = new Thread(() -> {
            long nanoTime = 0;
            long startNanoTime = System.nanoTime();
            long stepNanoTime = 0;
            long frame = 0;
            double keyframeProportion = 0;

            while ((frame < 100) || (display.getOpacity() != 0)) {
                
                nanoTime = System.nanoTime();
                stepNanoTime = nanoTime;

                while (nanoTime - stepNanoTime < 10000000) {
                    nanoTime = System.nanoTime();
                }

                frame++;
                frame = Math.min(frame, 100);
                keyframeProportion = 1.0 - ((double)frame / 100.0);
                
                display.setOpacity(keyframeProportion);
                
                changePosY(display.getLayoutY() - keyframeProportion * riseLength);
                
                //System.out.println("frame" + frame + " at " + keyframeProportion + " proportion of " + fallLength);
                
            }
            
            display.setOpacity(0);
            Platform.runLater(() -> {
                module.pane.getChildren().remove(display);
            });
            
        });
        
    }
    
    public void injectThisAndModuleRefs(Inventory inv, Module mod) {
        
        if (!((inv == null) || (mod == null))) {
            inventory = inv;
            module = mod;
        }
        else {
            System.out.println("catastrophic failure: ");
            if (inv == null) {System.out.println("inventory unassigned");}
            if (mod == null) {System.out.println("module unassignable");}
        }
        
        
        
    }
    
    @Override
    public void changePosY(double y) {
        posY = y;
        display.setLayoutY(y);
        //System.out.println("position changed to " + posY);
    }
    
    
}
