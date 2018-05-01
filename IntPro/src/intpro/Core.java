/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;
import ModuleProjectileMotion.*;
import ModuleWaveSuperposition.*;
import ModuleMomentum.*;
import ModuleSpringSimpleHarmonicMotion.*;
import ModuleLensOptics.*;
import ModuleCircularMotion.*;
import ModuleIdealGas.*;
import ModuleChargeParticlePath.*;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Cédric
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Core extends Application {
    //Splash screen
    IntroUI intro;
    
    public int modules = 8;
    public int res1x = 1200;
    public int res1y = 800;
    public int res2x = 1200;
    public int res2y = 800;
    
    public int framerate = 60;
    public double volume = 100 / 400;
    
    public boolean showHints = true;
    public boolean showContextHints = true;
    public boolean running = true;
    
    public boolean[] isHintShownOfModule = new boolean[modules];
    public Module[] module = new Module[modules];
    
    javafx.scene.media.MediaPlayer music = new javafx.scene.media.MediaPlayer(new Media(new File("src/Assets/RelaxDaily.mp3").toURI().toString()));
    
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws InterruptedException, IOException {
        intro = new IntroUI(this);
        MainMenuModuleButton[] moduleButtons;
        
        moduleButtons = new MainMenuModuleButton[modules];
        
        intro.viewport.setOnCloseRequest(e -> {
            for (int n = 0; n < modules; n++) {
                if ((module[n] != null) && (module[n].viewport != null)) {module[n].viewport.close();}
            }
            System.exit(0);
        });
        
        intro.viewport.requestFocus();
        intro.display();
        
        for (int n = 1; n <= modules; n++) {
            moduleButtons[n - 1] = new MainMenuModuleButton(n, this);
            moduleButtons[n - 1].setPosX(30);
            moduleButtons[n - 1].setPosY(240*n);
            moduleButtons[n - 1].injectCoreRef(this);
            intro.addToPane(moduleButtons[n - 1].display);
            
        }
        
        music.setVolume(volume);
        music.play();
        
        
    }
    
    public void testMethod() {
        
    }
    
    public void injectModuleRef(int num, Module mod) {
        module[num - 1].inventory.injectModuleRef(mod);
    }
    
    public void flushModule(int moduleNumber) throws IOException {
        System.out.println("closing module " + moduleNumber);
        module[moduleNumber - 1].viewport.close();
        module[moduleNumber - 1] = null;
    }
    
    public void pushModule(int moduleNumber) {
        if (module[moduleNumber - 1] == null) {
            switch(moduleNumber) {
                case 1: module[moduleNumber - 1] = new ProjectileMotionModule(this, 1); module[moduleNumber - 1].popOut(); System.out.println("poof1: projectile motion"); break;
                case 2: module[moduleNumber - 1] = new LensOpticsModule(this, 2); module[moduleNumber - 1].popOut(); System.out.println("poof2: lens optics"); break;
                case 3: module[moduleNumber - 1] = new ChargeParticlePathModule(this, 3); module[moduleNumber - 1].popOut(); System.out.println("poof3: charge particle path"); break;
                case 4: module[moduleNumber - 1] = new SpringSimpleHarmonicMotionModule(this, 4); module[moduleNumber - 1].popOut(); System.out.println("poof4: spring SHM"); break;
                case 5: module[moduleNumber - 1] = new WaveSuperpositionModule(this, 5); module[moduleNumber - 1].popOut(); System.out.println("poof5: wave superposition"); break;
                case 6: module[moduleNumber - 1] = new CircularMotionModule(this, 6); module[moduleNumber - 1].popOut(); System.out.println("poof6: circular motion"); break;
                case 7: module[moduleNumber - 1] = new MomentumModule(this, 7); module[moduleNumber - 1].popOut(); System.out.println("poof7: momentum"); break;
                case 8: module[moduleNumber - 1] = new IdealGasModule(this, 8); module[moduleNumber - 1].popOut(); System.out.println("poof8: ideal gas"); break;
                default:
            }
            injectModuleRef(moduleNumber, module[moduleNumber - 1]);
        }
        
        if (!module[moduleNumber - 1].viewport.isShowing()) {module[moduleNumber - 1].viewport.show();}
        
    }
    
    public static void waitSomeTime(long delay) {
        long startTimeNano = System.nanoTime();
        while ((System.nanoTime() - startTimeNano) <= delay * 1000000) {
        }
    }
    
    public int getRes2X() {
        return this.res2x;
    }
    public int getRes2Y() {
        return this.res2y;
    }
    public int getRes1X() {
        return this.res1x;
    }
    public int getRes1Y() {
        return this.res1y;
    }
    
    public void setVolume(int vol) {
        volume = vol;
        music.setVolume((double)vol / 400);
    }
    
}

