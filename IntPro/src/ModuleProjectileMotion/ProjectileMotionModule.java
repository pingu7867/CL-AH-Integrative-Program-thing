
package ModuleProjectileMotion;

import intpro.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author 1631810
 */

public class ProjectileMotionModule extends Module {
    public ImageView image = new ImageView(new Image("https://www.dropbox.com/s/ync9w4sx70b0q4e/TestImage1152x648.png?dl=1"));
    
    public ImageButton testButtipiton;
    
    public ProjectileMotionModule(Core core, int moduleNumber) {
        super(core, moduleNumber);
        pane = new Pane();
        
        inventory = new Inventory("projectile motion", dataSource);
        pane.getChildren().add(inventory.deployIcon.display);
        
        GameTickTimer timer = new GameTickTimer(44); timer.declareHost(this);
        timer.setFPS(60);
        
        
        pane.setPrefSize(dataSource.res2x, dataSource.res2y);
        
        image.setScaleX(0.3); image.setScaleY(0.3);
        image.setLayoutX(400);
        image.setLayoutY(400);
        
        pane.getChildren().add(image);
        
    }
    
    @Override
    public void popOut() {
        scene = new Scene(pane);
        
        viewport = new Stage();
        viewport.setScene(scene);
        viewport.show();
        
        this.viewport.setOnCloseRequest(e -> {
            try {
                dataSource.flushModule(moduleNumber);
            } catch (IOException ex) {
                Logger.getLogger(ProjectileMotionModule.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    
    @Override
    public void tickProcess() {
    }
    
    public Stage generateCannonWindow() {
        Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);
        CheckBox checkToPutMountedCannon= new CheckBox();
        
        TextField fieldforVelX = new TextField("0");
        TextField fieldforVelY = new TextField("0");
        TextField fieldforPosX = new TextField("100");
        TextField fieldforPosY = new TextField("100");
        
        
        
        fieldforVelX .setPrefColumnCount(3);
        fieldforVelX .textProperty().addListener(ov-> {
            String text = fieldforVelX.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1);
               text.replace("-", "");
               fieldforVelX .setText(text);
            }
        });
        
        fieldforVelY.setPrefColumnCount(3);
        fieldforVelY.textProperty().addListener(ov-> {
            String text = fieldforVelY.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text.replace("-", "");
               text = text.substring(0, text.length() - 1);
               fieldforVelY.setText(text);
            }
        });
        
        
        
        fieldforPosX .setPrefColumnCount(3);
        fieldforPosX .textProperty().addListener(ov-> {
            String text = fieldforPosX.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1);
               text.replace("-", "");
               fieldforVelX .setText(text);
            }
        });
        
        fieldforPosY.setPrefColumnCount(3);
        fieldforPosY.textProperty().addListener(ov-> {
            String text = fieldforPosY.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text.replace("-", "");
               text = text.substring(0, text.length() - 1);
               fieldforVelY.setText(text);
            }
        });
        
        
        
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
           double posXValue = Double.parseDouble(fieldforPosX.getText());
           double posYValue = Double.parseDouble(fieldforPosY.getText());
           
           Cannon cannon = (Cannon)newestCreatedElement;
           cannon.setAngle(cannon.getAngleRad(velXValue, velYValue));
           
           cannon.setVelX(velXValue);
           cannon.setVelY(velYValue);
           cannon.setPosX(posXValue);
           cannon.setPosY(posYValue);
           
           //body.setBody();
           //listOfPhysicalBodies.add(body);
           
           stage.close();
        });
        
        WindowLayout.getChildren().addAll(fieldforVelX, fieldforVelY, fieldforPosX, fieldforPosY, buttons);
        if (!viewport.isShowing()) {
            stage.close();
        }
        stage.setScene(new Scene(WindowLayout));
        stage.requestFocus();
        return stage;
    }
}
