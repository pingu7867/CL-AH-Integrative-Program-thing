/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleChargeParticlePath;
import intpro.Element;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 *
 * @author Amine
 */
public class ParallelPlateCapacitor extends Element {
    private String arrowDirection = "down";; 
    private ElectricPlate botPlate = new ElectricPlate("+",arrowDirection); 
    private ElectricPlate topPlate = new ElectricPlate("-",arrowDirection);
    
    private Group capacitor = new Group();
    private double distance = 1; 
    private double sheetChargeDensity = 1;
    final private double VACUUM_PERMITTIVITY = 8.85418782*Math.pow(10, -12);
    private double ElectricFieldX;
    private double ElectricFieldY;
    private double orientation = 0;
    
    public ParallelPlateCapacitor() {
        double ElectricField = sheetChargeDensity/VACUUM_PERMITTIVITY;
        this.ElectricFieldX = ElectricField * Math.sin(orientation);
        this.ElectricFieldY = ElectricField * Math.cos(orientation);
        
        
    }
    public ParallelPlateCapacitor(double distance, double sheetChargeDensity) {
        this.distance = distance;
        this.sheetChargeDensity = sheetChargeDensity;
        
        double ElectricField = sheetChargeDensity/VACUUM_PERMITTIVITY;
        this.ElectricFieldX = ElectricField * Math.sin(orientation);
        this.ElectricFieldY = ElectricField * Math.cos(orientation);
    }
    public ParallelPlateCapacitor(double distance, double sheetChargeDensity, double orientation) {
        this.distance = distance;
        this.sheetChargeDensity = sheetChargeDensity;
        this.orientation = orientation;
        
        double ElectricField = sheetChargeDensity/VACUUM_PERMITTIVITY;
        this.ElectricFieldX = ElectricField * Math.sin(orientation);
        this.ElectricFieldY = ElectricField * Math.cos(orientation);
        posX = 0; posY = 0;
    }
    public double getDistance() {
        return distance;
    }
    public double getSheetChargeDensity() {
        return sheetChargeDensity;
    }
    public double getOrientation() {
        return orientation;
    }
    public double getElectricFieldX() {
        return ElectricFieldX;
    }
    public double getElectricFieldY() {
        return ElectricFieldY;
    }
    public ElectricPlate getBotPlate() {
        return botPlate;
    }
    public ElectricPlate getTopPlate() {
        return topPlate;
    }
    
    public void setSheetChargeDensity(double sheetChargeDensity) {
        this.sheetChargeDensity = sheetChargeDensity;
        double ElectricField = sheetChargeDensity/VACUUM_PERMITTIVITY;
        this.ElectricFieldX = ElectricField * Math.sin(orientation);
        this.ElectricFieldY = ElectricField * Math.cos(orientation);
    }
    public void setOrientation(double orientation) {
        this.orientation = orientation;
        double ElectricField = sheetChargeDensity/VACUUM_PERMITTIVITY;
        this.ElectricFieldX = ElectricField * Math.sin(orientation);
        this.ElectricFieldY = ElectricField * Math.cos(orientation);
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public void setElectricFieldX(double ElectricFieldX) {
        this.ElectricFieldX = ElectricFieldX;
        double ElectricField = Math.sqrt(this.ElectricFieldX * this.ElectricFieldX + this.ElectricFieldY * this.ElectricFieldY);
        sheetChargeDensity = VACUUM_PERMITTIVITY * ElectricField;
    }
    public void setElectricFieldY(double ElectricFieldY) {
        this.ElectricFieldY = ElectricFieldY;
        double ElectricField = Math.sqrt(this.ElectricFieldX * this.ElectricFieldX + this.ElectricFieldY * this.ElectricFieldY);
        sheetChargeDensity = VACUUM_PERMITTIVITY * ElectricField;
    }
    
    public void switchDirection() {
        botPlate.changeSign();
        topPlate.changeSign();
        ElectricFieldX *= -1;
        ElectricFieldY *= -1;
    }
    public void setCapacitor() {
        if (sheetChargeDensity >= 0) {
            arrowDirection = "down";
            topPlate = new ElectricPlate("+",arrowDirection);
            botPlate = new ElectricPlate("-",arrowDirection);
        }
        else if (sheetChargeDensity < 0) {
            arrowDirection = "up";
            topPlate = new ElectricPlate("-",arrowDirection);
            botPlate = new ElectricPlate("+",arrowDirection);
        }
        
        topPlate.sprite.setRotate(-1*orientation);
        botPlate.sprite.setRotate(-1*orientation);
        
        topPlate.sprite.setX(posX);
        topPlate.sprite.setY(posY);
        
        botPlate.sprite.setX(topPlate.sprite.getX() + (distance + topPlate.sprite.getImage().getHeight())*Math.sin(Math.toRadians(orientation)));
        botPlate.sprite.setY(topPlate.sprite.getY() + (distance + topPlate.sprite.getImage().getHeight())
                *Math.cos(Math.toRadians(orientation)));
        
        
        topPlate.sprite.setOnMouseDragged(eh-> {
            if (canMove) {
            topPlate.sprite.setX(eh.getSceneX());
            topPlate.sprite.setY(eh.getSceneY());
            posX = eh.getSceneX();
            posY = eh.getSceneY();
            
            botPlate.sprite.setX(topPlate.sprite.getX() + (distance + topPlate.sprite.getImage().getHeight())* Math.sin(Math.toRadians(orientation)));
            botPlate.sprite.setY(topPlate.sprite.getY() + (distance + topPlate.sprite.getImage().getHeight()) * Math.cos(Math.toRadians(orientation)));
            }
            else {
                
            }
        });
        botPlate.sprite.setOnMouseDragged(eh-> {
            if (canMove) {
            botPlate.sprite.setX(eh.getSceneX());
            botPlate.sprite.setY(eh.getSceneY());     
            
            topPlate.sprite.setX(botPlate.sprite.getX() - (distance + topPlate.sprite.getImage().getHeight())*Math.sin(Math.toRadians(orientation)));
            topPlate.sprite.setY(botPlate.sprite.getY() -(distance + topPlate.sprite.getImage().getHeight())*Math.cos(Math.toRadians(orientation)));
            posX = topPlate.sprite.getX();
            posY = topPlate.sprite.getY();
            }
            else {
                
            }
            });
        
        topPlate.sprite.setOnMouseClicked(eh-> {
            if(eh.isControlDown()) {
                Stage window = generateWindowCustomizeCapacitor();
                window.show();
            }
        });
        botPlate.sprite.setOnMouseClicked(eh-> {
            if(eh.isControlDown()) {
                Stage window = generateWindowCustomizeCapacitor();
                window.show();
            }
        });
    }
    
    public Stage generateWindowCustomizeCapacitor() {
        Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);
        
        Text warningMessage = new Text("WARNING: this field can only contain digits");
        warningMessage.setStroke(Color.RED);
        
        TextField FieldforSheetChargeDensity = new TextField("" + sheetChargeDensity);
        FieldforSheetChargeDensity.setPrefColumnCount(3);
        FieldforSheetChargeDensity.textProperty().addListener(ov-> {
            String text = FieldforSheetChargeDensity.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               FieldforSheetChargeDensity.setText(text);
            }
        });
        
        TextField FieldforSeparationDistance = new TextField("" + distance);
        FieldforSeparationDistance.setPrefColumnCount(3);
        FieldforSeparationDistance.textProperty().addListener(ov-> {
            String text = FieldforSeparationDistance.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1);
               FieldforSeparationDistance.setText(text);
            }
        });
        
        TextField FieldforOrientation = new TextField("" + orientation);
        FieldforOrientation.setPrefColumnCount(3);
        FieldforOrientation.textProperty().addListener(ov-> {
            String text = FieldforOrientation.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1);
               FieldforOrientation.setText(text);
            }
        });

        Label labelforSheetChargeDensity = new Label("Sheet Charge Density (C/m^2)", FieldforSheetChargeDensity);
        labelforSheetChargeDensity.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforSeparationDistance = new Label("Separation Distance of the 2 plates (m)", FieldforSeparationDistance);
        labelforSeparationDistance.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforOrientation = new Label("Orientation (degrees)", FieldforOrientation);
        labelforOrientation.setContentDisplay(ContentDisplay.RIGHT);
        
        
        HBox buttons = new HBox(8);
        buttons.setAlignment(Pos.BOTTOM_RIGHT);
        Button Change = new Button("Change");        
        Button Cancel = new Button("Cancel");
        buttons.getChildren().addAll(Cancel, Change);
        
        
        Cancel.setOnAction(eh-> {
            stage.close();
        });
        Change.setOnAction(eh -> {
           
           double densityValue = Double.parseDouble(FieldforSheetChargeDensity.getText());
           double distanceValue = Double.parseDouble(FieldforSeparationDistance.getText());
           double orientationValue = Double.parseDouble(FieldforOrientation.getText());
           
           this.setSheetChargeDensity(densityValue);
           this.setDistance(distanceValue);
           this.setOrientation(orientationValue);
           this.remakeCapacitor();
           
           stage.close();
        });
        
        WindowLayout.getChildren().addAll(labelforSheetChargeDensity, labelforSeparationDistance, labelforOrientation, buttons);
        /*
        if (!this.topPlate.sprite.getParent().getScene().getWindow().isShowing()) {
            stage.close();
        } 
    */
        stage.setScene(new Scene(WindowLayout));
        stage.requestFocus();
        return stage;
    }

    public void remakeCapacitor() {
        if (sheetChargeDensity >= 0) {
            arrowDirection = "down";
            topPlate = new ElectricPlate("+",arrowDirection);
            botPlate = new ElectricPlate("-",arrowDirection);
        }
        else if (sheetChargeDensity < 0) {
            arrowDirection = "up";
            topPlate = new ElectricPlate("-",arrowDirection);
            botPlate = new ElectricPlate("+",arrowDirection);
        }
        
        topPlate.sprite.setRotate(-1*orientation);
        botPlate.sprite.setRotate(-1*orientation);
        
        topPlate.sprite.setX(posX);
        topPlate.sprite.setY(posY);
        
        botPlate.sprite.setX(topPlate.sprite.getX() + (distance + topPlate.sprite.getImage().getHeight())*Math.sin(Math.toRadians(orientation)));
        botPlate.sprite.setY(topPlate.sprite.getY() + (distance + topPlate.sprite.getImage().getHeight())
                *Math.cos(Math.toRadians(orientation)));
        
    }
}
