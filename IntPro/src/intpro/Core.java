/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;
import ModuleProjectileMotion.*;
import java.io.File;

/**
 *
 * @author Cédric
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.shape.Circle;

public class Core extends Application {
    //Splash screen
    IntroUI intro;
    
    public int modules = 8;
    public int res1x = 1200;
    public int res1y = 800;
    public int res2x;
    public int res2y;
    
    public int framerate = 60;
    public int volume = 100 / 400;
    
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
    public void start(Stage primaryStage) throws InterruptedException {
        intro = new IntroUI(this);
        MainMenuModuleButton[] moduleButtons;
        
        moduleButtons = new MainMenuModuleButton[modules];
        
        for (int n = 1; n < modules; n++) {
            moduleButtons[n] = new MainMenuModuleButton(n, this);
            moduleButtons[n].setPosX(30);
            moduleButtons[n].setPosY(240*n);
            moduleButtons[n].injectCoreRef(this);
            intro.addToPane(moduleButtons[n].display);
            
        }
        
        intro.viewport.setOnCloseRequest(e -> {
            for (int n = 0; n < modules; n++) {
                if ((module[n] != null) && (module[n].viewport != null)) {module[n].viewport.close();}
            }
            System.exit(0);
        });
        intro.display();
        intro.viewport.requestFocus();
        
        music.play();
        music.setVolume(0);
    }
    
    public void testMethod() {
        System.out.println("FUCK THAT SHIT");
    }
    
    public void flushModule(int moduleNumber) {
        module[moduleNumber].viewport.close();
        module[moduleNumber] = null;
    }
    
    public void pushModule(int moduleNumber) {
        if (module[moduleNumber] == null) {
            switch(moduleNumber) {
                case 1: module[moduleNumber] = new ProjectileMotionModule(this); module[moduleNumber].popOut(); System.out.println("poof1: projectile motion");
                default:
            }
        }
        if (!module[moduleNumber].viewport.isShowing()) {module[moduleNumber].viewport.show();}
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

