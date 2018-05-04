/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleChargeParticlePath;
import javafx.scene.image.Image;
import java.io.File;
import intpro.SpritedElement;
import javafx.animation.*;
import javafx.util.Duration;
import java.util.ArrayList;
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
import javafx.geometry.Pos;


/**
 *
 * @author Amine
 */
public class ChargeParticle extends SpritedElement{
    private double charge = 0;
    private double electricForceX = 0;
    private double electricForceY = 0;
    private double mass = 1;
    private Timeline animation;
    private ArrayList<ParallelPlateCapacitor> listOfCapacitors;
    
    
    public ChargeParticle() {
        super();
        animation = new Timeline(new KeyFrame(Duration.millis(1000/30),eh-> particleMotion()));
        animation.setCycleCount(Timeline.INDEFINITE);
        this.sprite.setFitWidth(15); this.sprite.setFitHeight(15);
        posX = this.sprite.getFitWidth()/2; posY = this.sprite.getFitHeight()/2;
        
    }
    
    public ChargeParticle(double charge, double mass) {
        this();
        
        this.charge = charge;
    }
    
    public ChargeParticle(double charge,double mass,double velX, double velY) {
        this();
        
        this.velX = velX;
        this.velY = velY;
        this.posX = 0;
        this.posY = 0;
        this.charge = charge;
    }
    
    public ChargeParticle(double charge,double mass,double velX, double velY, double posX, double posY) {
        this();
        
        this.velX = velX;
        this.velY = velY;
        this.posX = posX;
        this.posY = posY;
        this.charge = charge;
    }
    
    public double getCharge(){
        return charge;
    }
    public double getMass() {
        return mass;
    }
    
    public double getElectricForceX() {
        return electricForceX;
    }
    
    public double getElectricForceY() {
        return electricForceY;
    }
    
    public void setCharge(double charge){
        this.charge = charge;
    }
    public void setMass(double mass){
        this.mass = mass;
    }
    
    public void setElectricForce(double electricForceX, double electricForceY) {
        this.electricForceX = electricForceX;
        this.electricForceY = electricForceY;
    }
    
    public void setParticle(){
        
        if (this.charge > 0) {
           this.sprite.setImage(new Image(new File("src/Assets/PositiveCharge.png").toURI().toString()));
        }
        else if (this.charge < 0) {
           this.sprite.setImage(new Image(new File("src/Assets/NegativeCharge.png").toURI().toString()));
        }
        else {
           this.sprite.setImage(new Image(new File("src/Assets/NeutralCharge.png").toURI().toString()));
        }
        
        
        this.sprite.setX(posX - this.sprite.getFitWidth()/2); this.sprite.setY(posY - this.sprite.getFitHeight()/2);
        
        
        this.sprite.setOnMouseDragged(md -> {
            if (canMove){
            posX = md.getX(); posY = md.getY();
            
            this.sprite.setX(md.getX() - this.sprite.getImage().getWidth()/2);
            this.sprite.setY(md.getY() - this.sprite.getImage().getHeight()/2);
            }
            if(md.isShiftDown()) {
                Stage window = generateWindowCustomizeCapacitor();
                window.show();
            }
            });
        
      
    }
    public void setUp(ArrayList<ParallelPlateCapacitor> listOfCapacitors){
        this.listOfCapacitors = listOfCapacitors;
        
    }
    public void play(){
        
        animation.play();
    }
    public void stop() {
        animation.stop();
    }
    
    public void particleMotion() {
        for(ParallelPlateCapacitor capa: listOfCapacitors){
            if (posX >= capa.getBotPlate().sprite.getX() 
                && posX <= capa.getBotPlate().sprite.getX() + capa.getBotPlate().sprite.getFitWidth()
                && posY >= capa.getTopPlate().sprite.getY() + capa.getTopPlate().sprite.getFitHeight()
                && posY <= capa.getBotPlate().sprite.getY()){
                this.electricForceX = charge * capa.getElectricFieldX();
                this.electricForceY = charge * capa.getElectricFieldY();
            }
            
            else if (this.sprite.intersects(capa.getBotPlate().sprite.getX(),capa.getBotPlate().sprite.getY(),capa.getBotPlate().sprite.getFitWidth(),capa.getBotPlate().sprite.getFitHeight()) 
                    || this.sprite.intersects(capa.getTopPlate().sprite.getX(),capa.getTopPlate().sprite.getY(),capa.getTopPlate().sprite.getFitWidth(),capa.getTopPlate().sprite.getFitHeight())) {
                velX = 0; velY=0; animation.stop();
            }
            else {
                this.electricForceX = 0; this.electricForceY = 0;
            }
        }
        velX += (this.electricForceX/mass)/30;
        velY -= (this.electricForceY/mass)/30;
        posX += velX/30; posY -= velY/30;
        this.sprite.setX(sprite.getX() + velX/30); this.sprite.setY(sprite.getY() - velY/30);
        System.out.println("VelX is" + velX + " VelY is " + velY);
        
    }
    public Stage generateWindowCustomizeCapacitor() {
        Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);
        
        Text warningMessage = new Text("WARNING: this field can only contain digits");
        warningMessage.setStroke(Color.RED);
        
        TextField fieldForVelocityX = new TextField("" + velX);
        fieldForVelocityX.setPrefColumnCount(3);
        fieldForVelocityX.textProperty().addListener(ov-> {
            String text = fieldForVelocityX.getText();
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForVelocityX.setText(text);
            }
        });
        
        TextField fieldForVelocityY = new TextField("" + velY);
        fieldForVelocityY.setPrefColumnCount(3);
        fieldForVelocityY.textProperty().addListener(ov-> {
            String text = fieldForVelocityY.getText();
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForVelocityY.setText(text);
            }
        });
        
        TextField fieldForCharge = new TextField("" + charge);
        fieldForCharge.setPrefColumnCount(3);
        fieldForCharge.textProperty().addListener(ov-> {
            String text = fieldForCharge.getText();
            if (!module.checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldForCharge.setText(text);
            }
        });
        
        TextField fieldForMass = new TextField("" + mass);
        fieldForMass.setPrefColumnCount(3);
        fieldForMass.textProperty().addListener(ov-> {
            String text = fieldForMass.getText();
            if (!module.checkDecimal(text) && text.contains("-") && text.charAt(0) == '0') {
               text = text.substring(0, text.length() - 1);
               fieldForMass.setText(text);
            }
        });
        
        
        Label labelForVelocityX = new Label("Velocity in X (m/s)", fieldForVelocityX);
        labelForVelocityX.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelForVelocityY = new Label("Velocity in Y (m/s)", fieldForVelocityY);
        labelForVelocityY.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelForCharge = new Label("Charge (C)", fieldForCharge);
        labelForCharge.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelForMass = new Label("Mass (kg)", fieldForMass);
        labelForMass.setContentDisplay(ContentDisplay.RIGHT);
        
        
        HBox buttons = new HBox(8);
        buttons.setAlignment(Pos.BOTTOM_RIGHT);
        Button Change = new Button("Change");        
        Button Cancel = new Button("Cancel");
        buttons.getChildren().addAll(Cancel, Change);
        
        
        Cancel.setOnAction(eh-> {
            stage.close();
        });
        Change.setOnAction(eh -> {
           
           double velXValue = Double.parseDouble(fieldForVelocityX.getText());
           double velYValue = Double.parseDouble(fieldForVelocityY.getText());
           double chargeValue = Double.parseDouble(fieldForCharge.getText());
           double massValue = Double.parseDouble(fieldForMass.getText());
           
           this.setVelX(velXValue); this.setVelY(velYValue);
           this.setCharge(chargeValue); this.setMass(massValue);
           setParticle();
           
           stage.close();
        });
        if (!this.sprite.getParent().getScene().getWindow().isShowing()) {
            stage.close();
        }
        WindowLayout.getChildren().addAll(labelForVelocityX,labelForVelocityY, labelForCharge, labelForMass, buttons);
        
        stage.setScene(new Scene(WindowLayout));
        stage.requestFocus();
        return stage;
    }
    
}
