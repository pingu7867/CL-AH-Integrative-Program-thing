
package intpro;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author 1631810
 */

public class ImageButton {
    
    public Image pressGraphic;
    public Image idleGraphic;
    public Image displayedImage;
    public ImageView display;
    public long lastClicked = 0;
    public int doubleClickDuration = 400;
    public boolean clicked = false;
    public boolean ClickShortTermRememberance = false;
    
    public ImageButton() {
        pressGraphic = new Image(new File("src/Assets/defaultButtonpress.png").toURI().toString());
        idleGraphic = new Image(new File("src/Assets/defaultButtonidle.png").toURI().toString());
        display = new ImageView(idleGraphic);
        
        /*display.setOnMouseClicked(e -> {
            ClickShortTermRememberance = true;
            long startTimeN = System.nanoTime();
            while ((startTimeN - System.nanoTime()) <= 2000) {}
            ClickShortTermRememberance = false;
        });
        
        display.setOnMouseClicked(e -> {
            if (doubleClickDuration != 0) {
                displayedImage = pressGraphic;
                display.setImage(displayedImage);
                if (lastClicked <= (System.currentTimeMillis() - doubleClickDuration)) {
                    clicked = true;
                    long startTimeM = System.currentTimeMillis();
                    while ((startTimeM - System.currentTimeMillis()) <= doubleClickDuration) {
                        if (ClickShortTermRememberance) {action();}
                    }
                    clicked = false;
                }
                lastClicked = System.currentTimeMillis();
                displayedImage = idleGraphic;
                display.setImage(displayedImage);
            }
            else {action();}
        });
        */
    }
    
    public void setGraphics(Image on, Image off) {
        pressGraphic = on;
        idleGraphic = off;
    }
    
    public void action() {}
}
