
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
    
    public ImageButton testButtipiton;
    
    public ProjectileMotionModule(Core core) {
        super(core);
        pane = new Pane();
        testButtipiton = new ImageButton(core);
        testButtipiton.display.setLayoutX(500); testButtipiton.display.setLayoutY(300);
        pane.getChildren().add(testButtipiton.display);
        
        GameTickTimer timer = new GameTickTimer(44); timer.declareHost(this);
        timer.setFPS(60);
        timer.startThread();
    }
    
    @Override
    public void popOut() {
        scene = new Scene(pane);
        //pane.setPrefSize(dataSource.res2x, dataSource.res2y);
        image.setLayoutX(500);
        image.setLayoutY(500);
        image.setScaleX(0.3); image.setScaleY(0.3);
        image.setOnMouseClicked(e -> {
            image.setVisible(!image.isVisible());
        });
        
        pane.getChildren().add(image);
        
        viewport = new Stage();
        viewport.setScene(scene);
        viewport.show();
        
        this.viewport.setOnCloseRequest(e -> {
            dataSource.flushModule(moduleNumber);
        });
        
    }
    
    @Override
    public void tickProcess() {
    }
    
    public generateCannonWindow() {
     Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);
        CheckBox checkToPutMountedCannon= new CheckBox();
        
        TextField fieldforVelX = new TextField("1");
        fieldforVelX .setPrefColumnCount(3);
        fieldforVelX .textProperty().addListener(ov-> {
            String text = fieldforVelX.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1);
               text.replace("-", "");
               fieldforVelX .setText(text);
            }
        });
        
        TextField fieldforVelY = new TextField("0");
        fieldforVelY.setPrefColumnCount(3);
        fieldforVelY.textProperty().addListener(ov-> {
            String text = fieldforVelY.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text.replace("-", "");
               text = text.substring(0, text.length() - 1);
               fieldforVelY.setText(text);
            }
        });
        
        Label labelforVelX = new Label("Separation Distance of the 2 plates (m)", fieldforVelX);
        labelforVelX.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforVelY = new Label("Orientation (degrees)", fieldforVelY);
        labelforVelY.setContentDisplay(ContentDisplay.RIGHT);
        
        HBox buttons = new HBox(8);
        buttons.setAlignment(Pos.BOTTOM_RIGHT);
        Button Create = new Button("Create");        
        Button Cancel = new Button("Cancel");
        buttons.getChildren().addAll(Cancel, Create);
        
        Cancel.setOnAction(eh-> {
            stage.close();
        });
        Create.setOnAction(eh -> {
           
           double velXValue = Double.parseDouble(fieldforVelX.getText());
           double velYValue = Double.parseDouble(fieldforVelY.getText());
           Cannon cannon = new Cannon(velXValue,velYValue);
           body.setBody();
           listOfPhysicalBodies.add(body);
           stage.close();
        });
        
        WindowLayout.getChildren().addAll(labelforHeight, labelforRadiusLeft, labelforRadiusRight, buttons);
        if (!viewport.isShowing()) {
            stage.close();
        }
        stage.setScene(new Scene(WindowLayout));
        stage.requestFocus();
        return stage;
    }   
}
