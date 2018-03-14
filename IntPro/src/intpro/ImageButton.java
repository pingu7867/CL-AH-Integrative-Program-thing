
package intpro;

/**
 *
 * @author 1631810
 */


public class ImageButton extends javafx.scene.control.Button {
    public javafx.scene.image.Image pressGraphic;
    public javafx.scene.image.Image idleGraphic;
    public javafx.scene.image.ImageView display;
    public long lastClicked = 0;
    public int doubleClickDuration = 400;
    
    public ImageButton() {
        this.setGraphic(display);
        this.setOnMouseClicked(e -> {
            if (lastClicked <= (System.currentTimeMillis() - 800)) {
                action();
            }
            lastClicked = System.currentTimeMillis();
        });
        this.setOnMouseClicked(e -> {
            display.setImage(pressGraphic);
            try {Thread.sleep(doubleClickDuration);}
            catch(InterruptedException ie) {}
            display.setImage(idleGraphic);
        });
    }
    
    public void setGraphics(javafx.scene.image.Image on, javafx.scene.image.Image off) {
        pressGraphic = on;
        idleGraphic = off;
    }
    
    public void action() {}
}
