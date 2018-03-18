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

import javafx.application.Application;
import javafx.stage.Stage;
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
        intro = new IntroUI(this);
        MainMenuModuleButton[] moduleButtons;
        
        ImageButton testBut = new ImageButton();
        testBut.display.setLayoutX(100);
        testBut.display.setLayoutY(300);
        intro.pane.getChildren().add(testBut.display);
        
        intro.display();
        
        ProjectileMotionModule proj = new ProjectileMotionModule(this);
        proj.popOut();
        
        
        /*
        moduleButtons = new MainMenuModuleButton[modules];
        for (int n = 0; n < modules; n++) {
            moduleButtons[n] = new MainMenuModuleButton(n);
            moduleButtons[n].setLayoutX(70);
            moduleButtons[n].setLayoutY(100 + 100*n);
        }
        
        intro.inject(moduleButtons);
        */
        
        System.out.println("start"); waitSomeTime(4000); System.out.println("end");
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
    
}

