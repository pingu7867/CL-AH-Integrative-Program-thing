
package Assets;

/**
 *
 * @author 1631810
 */

import javafx.animation.RotateTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class test extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Pane child = new Pane();
        Scene scene = new Scene(pane, 200, 200);
        Stage viewport = new Stage();
        viewport.setScene(scene);
        viewport.show();
        pane.getChildren().add(child);
        pane.setStyle("-fx-background-color: black;");
        child.setRotate(30);
        RotateTransition rt = new RotateTransition();
        rt.setNode(child);
        rt.setCycleCount(666);
        rt.setRate(2);
        child.setStyle("-fx-background-color: white;");
    }
    
}
