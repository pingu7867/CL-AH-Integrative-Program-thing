/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intpro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Cédric
 */

public class Module implements GameTickActor {
    
    public Core dataSource;
    public int moduleNumber = 0;
    public RenderSet renderSet;
    public ArrayList<Element> elements;
    public Inventory inventory;
    public Element newestCreatedElement;
    public boolean deleteModeActivated = false;
    
    public Pane pane;
    public Scene scene;
    public Stage viewport;
    
    public double framerate = 60;
    public GameTickTimer ticker;
    public int volume = 100;
    
    public File save;
    public PrintWriter writer;
    
    public Module(Core creator, int moduleNumber) {
        this.moduleNumber = moduleNumber;
        this.dataSource = creator;
        renderSet = new RenderSet();
        elements = new ArrayList<Element>();
        this.ticker = new GameTickTimer(framerate);
        this.ticker.declareHost(this);
    }
    
    public int getResolutionX() {
        return dataSource.getRes2X();
    }
    
    public int getResolutionY() {
        return dataSource.getRes2Y();
    }
    
    public double getScaling() {
        return dataSource.res2x / dataSource.res1x;
    }
    
    public void saveState() throws IOException {
       
       String date = new java.util.Date().toString().replace(" ", "_");
       String path = ("src/saves/save_" + getModuleName() + "_" + date + ".txt").replace(" ", "_").replace(":", "_");
       
       System.out.println(path);
       System.out.println(getModuleName());
       try {
           save = new File(path);
           save.createNewFile();
           
       } catch (IOException ex) {
           Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, ex);
       }
       try {
           writer = new PrintWriter(save);
           writer.write("suh dud");
           writer.close();
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, ex);
       }
   
    }
    
    public String getModuleName() {
        switch (moduleNumber) {
            case 0: return "null";
            case 1: return "projectile motion";
            case 2: return "lens optics";
            case 3: return "charge particle path";
            case 4: return "spring simple harmonic motion";
            case 5: return "wave superposition";
            case 6: return "circular motion";
            case 7: return "momentum";
            case 8: return "ideal gas";
            default: return "not found";
        }
    }
    
    public Element[] getElementsArray() {
        return null; //o o f
    }
    
    @Override
    public void tickProcess() {
        //o o f
    }
    
    public void popOut() {
        //o o f
        //this.viewport.setOnCloseRequest(e -> {
        //    dataSource.flushModule(moduleNumber);
        //});
    }
    
    public void setVolume(int vol) {
        volume = vol;
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            Element element = (Element)it.next();
            element.setVolume(volume);
        }
    }
    
    public boolean checkDecimal(String value) {
        int minuscount = 0;
        int pointcount = 0;
        for (int i = 0; i < value.length();i++) {
            if((value.charAt(i) == '.') || value.charAt(i) == '-'|| ((value.charAt(i) <= '9') && (value.charAt(i) >= '0'))) {
                if (value.charAt(i) == '.') {pointcount++;}
                if (value.charAt(i) == '-') {minuscount++;}
            }
            else {
                return false;
            }
        }
        if (pointcount > 1) {
            return false;
        }
        if (minuscount > 1) {
            return false;
        }
        return true;
    }
    
    
}
