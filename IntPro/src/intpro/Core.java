/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;
import ModuleProjectileMotion.*;
/**
 *
 * @author Cédric
 */

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PathTransition;
import javafx.scene.control.Button;
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
    
    public boolean showHints = true;
    public boolean showContextHints = true;
    public boolean running = true;
    
    public boolean[] isHintShownOfModule = new boolean[modules];
    public Module[] module = new Module[modules];
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        intro = new IntroUI();
        MainMenuModuleButton[] moduleButtons;
        //Thread variableUpdateLooper = new Thread(new VariableUpdating());
        //variableUpdateLooper.start();
        MainMenuModuleButton testBut = new MainMenuModuleButton(1);
        testBut.setLayoutX(100);
        testBut.setLayoutY(300);
        testBut.injectCoreRef(this);
        
        intro.inject(testBut);
        intro.display();
        
        /*
        moduleButtons = new MainMenuModuleButton[modules];
        for (int n = 0; n < modules; n++) {
            moduleButtons[n] = new MainMenuModuleButton(n);
            moduleButtons[n].setLayoutX(70);
            moduleButtons[n].setLayoutY(100 + 100*n);
        }
        
        intro.inject(moduleButtons);
        */
        
    }
    
    public void pushModule(int moduleNumber) {
        if (module[moduleNumber] == null) {
            switch(moduleNumber) {
                case 1: module[moduleNumber] = (Module)(new ProjectileMotionModule(this));
                default: ;
            }
        }
        if(!module[moduleNumber].viewport.isShowing()) {module[moduleNumber].viewport.show();}
    }
    
    public static void waitSomeTime(long delay) {
        try {
            Thread.sleep(delay);
        }
        catch(InterruptedException ie) {
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
    
    class VariableUpdating implements Runnable {
        @Override
        public void run() {
            
        }
    }
    
}

