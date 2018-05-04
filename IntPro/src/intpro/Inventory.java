/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.media.Media;

/**
 *
 * @author Cédric
 */
public class Inventory extends Popout implements SoundPlay {
    public InventoryToggleButton deployIcon;
    public ArrayList<InventoryIcon> icons;
    public boolean showing = false;
    public int moduleNumber = 0;
    
    public Core core;
    public Module module;
    
    public Inventory(String moduleName, Core coreOrigin) {
        this.core = coreOrigin;
        System.out.println("module" + (this.core.module[0] == null));
        deployIcon = new InventoryToggleButton(this, core);
        icons = new ArrayList<>();
        
        if (moduleName.equals("projectile motion")) {
            moduleNumber = 1;
            icons.add(new InventoryIcon("cannon", core));
            icons.add(new InventoryIcon("vehicle", core));
        }
        if (moduleName.equals("lens optics")) {
            moduleNumber = 2;
            icons.add(new InventoryIcon("lens", core));
        }
        if (moduleName.equals("spring simple harmonic motion")) {
            moduleNumber = 4;
            icons.add(new InventoryIcon("spring", core));
            icons.add(new InventoryIcon("weight", core));
        }
        if (moduleName.equals("momentum")) {
            moduleNumber = 7;
            icons.add(new InventoryIcon("physical body", core));
        }
        if (moduleName.equals("charge particle path")) {
            moduleNumber = 3;
            icons.add(new InventoryIcon("charged particle", core));
            icons.add(new InventoryIcon("capacitor", core));
        }
        if (moduleName.equals("circular motion")) {
            moduleNumber = 6;
            icons.add(new InventoryIcon("branch", core));
        }
        if (moduleName.equals("wave superposition")) {
            moduleNumber = 5;
            icons.add(new InventoryIcon("sine wave", core));
            icons.add(new InventoryIcon("square wave", core));
            icons.add(new InventoryIcon("triangle wave", core));
            icons.add(new InventoryIcon("saw wave", core));
            icons.add(new InventoryIcon("linear function", core));
            icons.add(new InventoryIcon("quadratic function", core));
            icons.add(new InventoryIcon("cubic function", core));
            icons.add(new InventoryIcon("polynomial function", core));
            icons.add(new InventoryIcon("composite function", core));
            icons.add(new InventoryIcon("charged particle", core));
            icons.add(new InventoryIcon("capacitor", core));
            icons.add(new InventoryIcon("spring", core));
            icons.add(new InventoryIcon("weight", core));
            
        }
        
        module = core.module[moduleNumber - 1];
        
    }
    
    
    public void toggle() {
        if (!showing) {
            deploy();
            
        }
        else {
            retract();
        }
        showing = !showing;
    }
    
    @Override
    public void deploy() {
        playSound("open");
        for (int i = 0; i < icons.size(); i++) {
            //set up
            InventoryIcon icon = icons.get(i);
            icon.display.setOpacity(0);
            icon.display.setLayoutX(40);
            icon.display.setLayoutY(200);
            icon.module.pane.getChildren().add(icons.get(i).display);
            icon.display.toFront();
            icon.primeExpandThread((i + 1) * 100);
        }
        
        new Thread(() -> {
            for (int i = 0; i < icons.size(); i++) {
                //show
                InventoryIcon icon = icons.get(i);
                try {
                    if (i > 0) {playSound("sort");}
                    icon.expandDown();
                    icon.transitionThread.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
        
    }
    
    @Override
    public void retract() {
        
        for (int i = 0; i < icons.size(); i++) {
            //hide
            InventoryIcon icon = icons.get(i);
            icon.primeRetractThread(i + 1);
        }
        
        new Thread(() -> {
            for (int i = 0; i < icons.size(); i++) {
                //show
                InventoryIcon icon = icons.get(i);
                try {
                    playSound("putaway");
                    icon.retractUp();
                    icon.transitionThread.join();
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            playSound("close");
        }).start();
        
    }
    
    void injectModuleRef(Module module) {
        this.module = module;
        for (int i = 0; i < icons.size(); i++) {
            //show
            icons.get(i).injectThisAndModuleRefs(this, module);
        }
    }
    
    @Override
    public void playSound(String mode) {
        javafx.scene.media.MediaPlayer sound;
        switch(mode) {
            case "open": sound = new javafx.scene.media.MediaPlayer(new Media(new File("src/Assets/asmr_open.mp3").toURI().toString())); break;
            case "close": sound = new javafx.scene.media.MediaPlayer(new Media(new File("src/Assets/asmr_close.mp3").toURI().toString())); break;
            case "sort": sound = new javafx.scene.media.MediaPlayer(new Media(new File("src/Assets/asmr_sort" + (int)(1 + Math.random()*3) + ".mp3").toURI().toString())); break;
            case "putaway": sound = new javafx.scene.media.MediaPlayer(new Media(new File("src/Assets/asmr_putaway" + (int)(1 + Math.random()*3) + ".mp3").toURI().toString())); break;
            default: sound = new javafx.scene.media.MediaPlayer(new Media(new File("src/Assets/click.mp3").toURI().toString())); break;
        }
        sound.setVolume(module.volume);
        new Thread(() -> {sound.play();}).start();
    }
}
