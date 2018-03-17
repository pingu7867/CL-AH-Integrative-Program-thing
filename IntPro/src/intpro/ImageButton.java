
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
    public ImageView display = new ImageView(idleGraphic);
    public long lastClicked = 0;
    public int doubleClickDuration = 400;
    
    public ImageButton() {
        pressGraphic = new Image(new File("src/Assets/defaultButtonpress.png").toURI().toString());
        idleGraphic = new Image(new File("src/Assets/defaultButtonidle.png").toURI().toString());
        
        display.setOnMouseClicked(e -> {
            if (doubleClickDuration != 0) {
                if (lastClicked <= (System.currentTimeMillis() - doubleClickDuration)) {
                    action();
                    lastClicked = System.currentTimeMillis();
                }
                else {action();}
            }
            
        });
        
        display.setOnMouseClicked(e -> {
            if (doubleClickDuration != 0) {
                displayedImage = pressGraphic;
                display.setImage(displayedImage);

                try {Thread.sleep(doubleClickDuration);}
                catch(InterruptedException ie) {}

                displayedImage = idleGraphic;
                display.setImage(displayedImage);
            }
        });
        
    }
    
    public void setGraphics(Image on, Image off) {
        pressGraphic = on;
        idleGraphic = off;
    }
    
    public void action() {}
}
