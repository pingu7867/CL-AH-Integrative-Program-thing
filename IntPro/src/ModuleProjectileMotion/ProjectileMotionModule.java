
package ModuleProjectileMotion;

import intpro.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author 1631810
 */

public class ProjectileMotionModule extends Module {
    public ImageView image = new ImageView(new Image("https://www.dropbox.com/s/ync9w4sx70b0q4e/TestImage1152x648.png?dl=1"));
    
    public ProjectileMotionModule(Core core) {
        super(core);
        pane = new Pane();
        
    }
    
    public void popOut() {
        scene = new Scene(pane);
        //pane.setPrefSize(dataSource.res2x, dataSource.res2y);
        image.setLayoutX(500);
        image.setLayoutY(500);
        image.setOnMouseClicked(e -> {
            image.setVisible(!image.isVisible());
        });
        
        pane.getChildren().add(image);
        
        viewport = new Stage();
        viewport.setScene(scene);
        viewport.show();
    }
}
