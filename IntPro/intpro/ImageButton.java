
package intpro;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author 1631810
 */

public class ImageButton extends GeneralFunctionalitiesStackedInheritanceTower {
    
    public Image pressGraphic;
    public Image idleGraphic;
    public Image displayedImage;
    public ImageView display;
    public long lastClicked = 0;
    public int doubleClickDuration;
    public boolean clicked = false;
    public boolean blocker = false;
    
    Thread clickThread;
    ClickTimeHandler clicker;
    Thread displayThread;
    DisplayTimeHandler displayer;
    
    public ImageButton(Core core) {
        this.core = core;
        pressGraphic = new Image(new File("src/Assets/defaultButtonpress.png").toURI().toString());
        idleGraphic = new Image(new File("src/Assets/defaultButtonidle.png").toURI().toString());
        display = new ImageView(idleGraphic);
        doubleClickDuration = 2000;
        
        clicker = new ClickTimeHandler();
        displayer = new DisplayTimeHandler();
        
        display.setLayoutX(this.posX());
        display.setLayoutY(this.posY());
        
        refreshThreads();
        
        display.setOnMousePressed((MouseEvent e) -> {
            
            if (doubleClickDuration == 0) {
               lastClicked = System.currentTimeMillis();
               action();
            }
            else {
                if (!displayThread.isAlive()) {
                    blocker = true;
                    displayThread.start();
                }
                else {
                    clicker.additionalNanosRemaining = clicker.additionalNanosRemaining + (1000000 * doubleClickDuration);                    
                    if ((System.currentTimeMillis() - lastClicked) > doubleClickDuration) {
                        action();
                    }
                }
                lastClicked = System.currentTimeMillis();
            }
        });
    }
    
    public void refreshThreads() {
        clickThread = null;
        displayThread = null;
        
        clickThread = new Thread(clicker);
        displayThread = new Thread(displayer);
    }
    
    public void setGraphics(Image on, Image off) {
        pressGraphic = on;
        idleGraphic = off;
    }
    
    public void flipFlop() {
        if (display.getImage() == (pressGraphic)) {
            graphicOff();
        }
        else if (display.getImage() == (idleGraphic)) {
            graphicOn();
        }
    }
    
    public void graphicOn() {displayedImage = pressGraphic; display.setImage(displayedImage);}
    
    public void graphicOff() {displayedImage = idleGraphic; display.setImage(idleGraphic);}
    
    public void action() {}

    public class DisplayTimeHandler implements Runnable {

        public DisplayTimeHandler() {
            
        }
        
        @Override
        public synchronized void run() {
            Platform.runLater(() -> {
                graphicOn();
            });
            
            try {clickThread.start(); clickThread.join();
            }
            catch (InterruptedException ex) {}
            
            Platform.runLater(() -> {
                graphicOff();
            });
            
            clicked = false;
            
            Platform.runLater(() -> {
                refreshThreads();
            });
        }
    }

    public class ClickTimeHandler implements Runnable {
        public long additionalNanosRemaining = 0;
        public ClickTimeHandler() {
        }
        
        @Override
        public synchronized void run() {
            
            long startTimeN = System.nanoTime();
            while ((System.nanoTime() - startTimeN) <= (doubleClickDuration * 1000000) + additionalNanosRemaining) {
                
            }
            
            blocker = false;
        }
    }
    
    @Override
    public void setPosY(double posY) {
        this.display.setLayoutY(posY);
    }
    
    @Override
    public void setPosX(double posX) {
        this.display.setLayoutX(posX);
    }
    
    @Override
    public void changePosX(double posX) {
        this.display.setLayoutX(posX);
    }
    
    @Override
    public void changePosY(double posY) {
        this.display.setLayoutY(posY);
    }
    
}
