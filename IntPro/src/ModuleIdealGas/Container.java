
package ModuleIdealGas;

/**
 *
 * @author Amine
 */
import intpro.SpritedElement;
import java.io.File;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.net.JarURLConnection;

public class Container extends SpritedElement{
    ArrayList<GasParticle> particles = new ArrayList<>();
    double amountOfMole = 0; //in moles
    double volume = 0; //in liters (L)
    double pressure = 0; //in kPa
    double temperature = 0; //in Kelvin (K)
    
    boolean unlockAOMVol = false;    //These are for keeping (Pre * Vol)/(AOM * Temp)
    boolean unlockAOMPre = false;
    
    boolean unlockVolPre = false;
    boolean unlockVolAOM = false;
    boolean unlockVolTemp = false;
    
    boolean unlockPreVol = false;
    boolean unlockPreAOM = false;
    boolean unlockPreTemp = false;
    
    boolean unlockTempVol = false;
    boolean unlockTempPre = false;
    
    double averageKineticEnergy = 0;
    final double GAS_CONSTANT = 8.3144598;
    final double BOLTZMANN_CONSTANT = 1.38064852 * Math.pow(10, -23); //in eV/K
    Rectangle bounds;
    ImageView tankFill;
    IdealGasModule module;
    
    public Container() {
        super(new ImageView());
        sprite.setImage(new Image(new File("src/Assets/GasTank.png").toURI().toString()));
        sprite.setX(0); sprite.setY(0);
        
        tankFill = new ImageView(); tankFill.setImage(new Image(new File("src/Assets/GasTankFill.png").toURI().toString())); //jarURLConnection()
        tankFill.setX(92); tankFill.setY(265);        
        
        bounds = new Rectangle(1, 1, 1, 1); // max X = 512 max Y = 1008
        bounds.setFill(Color.WHITE);
        bounds.setStroke(Color.BLACK);
        tankFill.setFitWidth(421); tankFill.setFitHeight(725);
        
        
        sprite.setOnMouseClicked(eh -> {
            Stage window = generateContainerWindow();
            window.show();
        });
        
    }
    public Container(double aom, double vol, double pre, double temp, IdealGasModule module) {
        this();
        amountOfMole = aom; volume = vol; pressure = pre; temperature = temp;
        
        averageKineticEnergy = (3/2) * BOLTZMANN_CONSTANT * temperature;
        this.module = module;
        this.setAvailableSpace();
        
        this.setGasParticle();  
       
    }
    public double getMoles() {
        return amountOfMole;
    }
    public double getVolume() {
        return volume;
    }
    public double getPressure() {
        return pressure;
    }
    public double getTemperature() {
        return temperature;
    }
    
    public void setMoles(double aom) {
        double ratioVN = volume/amountOfMole;
        double ratioPN = pressure/amountOfMole;
        if (aom > 50) {
            amountOfMole = 50;
        }
        else {
           amountOfMole = aom; 
        }
        
        
        if (unlockPreAOM) {
         pressure = ratioPN * aom;
        }
        else if (unlockVolAOM) {
         volume = ratioVN * aom;
         setAvailableSpace();
        }
        setGasParticle();
    }
    public void setVolume(double vol) {
        double kVP = volume * pressure;
        double ratioVT = volume/temperature;
        double ratioVN = volume/amountOfMole;
        if (vol >= 21) {
            volume = 21;
        }
        else {
        volume = vol;
        }
        if (unlockPreVol) {
            pressure = kVP/vol;
        }
        else if (unlockTempVol) {
            temperature = vol/ratioVT;
            averageKineticEnergy = (3/2) * BOLTZMANN_CONSTANT * temperature;
            setGasParticleAVke();
        }
        else if (unlockAOMVol) {
            amountOfMole = vol/ratioVN;
        }
        setAvailableSpace();
        
    }
    public void setPressure(double pre) {
        double kVP = volume * pressure;
        double ratioPN = pressure/amountOfMole;
        double ratioPT = pressure/temperature;
        if (pre >= 205) {
            pressure = 205;
        }
        else {
        pressure = pre;
        }
        if (unlockTempPre) {
            temperature = pre/ratioPT;
            averageKineticEnergy = (3/2) * BOLTZMANN_CONSTANT * temperature;
            setGasParticleAVke();
        }
        else if (unlockAOMPre) {
            amountOfMole = pre/ratioPN;
        }
        else if (unlockVolPre) {
            volume = kVP/pre;
            setAvailableSpace();
        }
    }
    public void setTemperature(double temp) {
        double ratioVT = volume/temperature;
        double ratioPT = pressure/temperature;
        if (temp >= 2000) {
            temp = 2000;
        }
        else {
        temperature = temp;
        }
        if (unlockVolTemp) {
            volume = ratioVT * temp;
            setAvailableSpace();
        }
        else if (unlockPreTemp) {
            pressure = ratioPT * temp;
        }
        averageKineticEnergy = (3/2) * BOLTZMANN_CONSTANT * temperature;
        setGasParticleAVke();
    }
    
    public void setGasParticle() {
        for (int i = 0; i < particles.size(); i++) {
        module.pane.getChildren().remove(particles.get(i).sprite);
        }
        
        particles.clear();
        
        double aom = 0;
        while (aom < this.amountOfMole) {
            particles.add(new GasParticle(bounds,averageKineticEnergy));
            aom++;
        }    
        
        for (int i = 0; i < particles.size(); i++) {
            module.pane.getChildren().add(particles.get(i).sprite);
        }

        setAnimation();
        
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).animation.play();
        }
    }
    public void setGasParticleAVke() {
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).setVelocityKineticEnergy(averageKineticEnergy);
        }
    }
    public void setAvailableSpace() {
        double boundX = 302; double boundY = 627;  // Max Vol: X = 92 Y: 265 boundWidth: 420 boundHeight: 723
        double boundWidth = 0; double boundHeight = 0; // Min Vol: X = 302 Y:597 boundWidth: 0 boundHeight: 0
        
        if (volume*10 >= 210) {
            boundX = 92;
            boundWidth = 420;
        }
        else {
            boundX -= volume*10;
            boundWidth = volume * 20;
        }
        if (volume * 17.26 >= 361) {
            boundY = 265;
            boundHeight = 723;
        }
        else {
            boundY -= volume*17.26;
            boundHeight = volume*34.52;
        }
        
        bounds.setX(boundX); bounds.setY(boundY);
        bounds.setWidth(boundWidth); bounds.setHeight(boundHeight);
        
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).setBounds(bounds);
        }
        
    }
    
    public void setAnimation() {
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).setAnimation(particles,i);
        }
    }
    public Stage generateContainerWindow() {
        Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);
        VBox radioButtonLayout = new VBox(60);
        HBox sceneLayout = new HBox(20);
        sceneLayout.getChildren().addAll(WindowLayout, radioButtonLayout);
           
        TextField fieldForMoles = new TextField("" + this.getMoles());
        fieldForMoles.setPrefColumnCount(3);
        fieldForMoles.textProperty().addListener(ov-> {
            String text = fieldForMoles.getText();
            if (!module.checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1);
               fieldForMoles.setText(text);
            }
            if (text.contains("-")) {
                text = text.replace("-", "");
                fieldForMoles.setText(text);
            }
        });
        
        
        TextField fieldForVolume = new TextField("" + this.getVolume());
        fieldForVolume.setPrefColumnCount(3);
        fieldForVolume.textProperty().addListener(ov-> {
            String text = fieldForVolume.getText();
            if (!module.checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1);
               fieldForVolume.setText(text);
            }
            if (text.contains("-")) {
                text = text.replace("-", "");
                fieldForVolume.setText(text);
            }
        });
        
        TextField fieldForPressure = new TextField("" + this.getPressure());
        fieldForPressure.setPrefColumnCount(3);
        fieldForPressure.textProperty().addListener(ov-> {
            String text = fieldForPressure.getText();
            if (!module.checkDecimal(text) && text.contains("-")) {
               
               text = text.substring(0, text.length() - 1);
               fieldForPressure.setText(text);
            }
            if (text.contains("-")) {
                text = text.replace("-", "");
                fieldForPressure.setText(text);
            }
        });
        TextField fieldForTemperature = new TextField("" + this.getTemperature());
        fieldForTemperature.setPrefColumnCount(3);
        fieldForTemperature.textProperty().addListener(ov-> {
            String text = fieldForTemperature.getText();
            if (!module.checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1);
               fieldForTemperature.setText(text);
            }
            if (text.contains("-")) {
                text = text.replace("-", "");
                fieldForTemperature.setText(text);
            }
        });

        Label labelforMoles = new Label("Amount of moles (mol) \nMax: 50 Only positive decimals \nany value higher than the max will be converted to 50", fieldForMoles);
        labelforMoles.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforVolume = new Label("Volume (L) \nMax: 21 Only positive decimals \nany value higher than the max will be converted to 21", fieldForVolume);
        labelforVolume.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforPressure = new Label("Pressure (kPa) \nMax: 205 Only positive decimals \nany value higher than the max will be converted to 205", fieldForPressure);
        labelforPressure.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforTemperature = new Label("Temperature (Kelvin) \nMax: 2000 Only positive decimals \nany value higher than the max will be converted to 2000", fieldForTemperature);
        labelforTemperature.setContentDisplay(ContentDisplay.RIGHT);
        
        HBox buttons = new HBox(8);
        buttons.setAlignment(Pos.BOTTOM_RIGHT);
        Button Change = new Button("Change");        
        Button Cancel = new Button("Cancel");
        buttons.getChildren().addAll(Cancel, Change);
        
        
        HBox radioButtonAom = new HBox(10);
        
        RadioButton rbUnlockVolAOM = new RadioButton("Unlock volume for amount of moles");
        RadioButton rbUnlockPreAOM = new RadioButton("Unlock pressure for amount of moles");
        RadioButton rbUnlockNoneAOM = new RadioButton("Unlock none");
        radioButtonAom.getChildren().addAll(rbUnlockVolAOM, rbUnlockPreAOM, rbUnlockNoneAOM);
        
        ToggleGroup groupAOM = new ToggleGroup();
        rbUnlockVolAOM.setToggleGroup(groupAOM);
        rbUnlockPreAOM.setToggleGroup(groupAOM);
        rbUnlockNoneAOM.setToggleGroup(groupAOM);
        
        rbUnlockVolAOM.setOnAction(eh-> {
            if (rbUnlockVolAOM.isSelected()) {
                this.unlockAOMVol = true;
                this.unlockAOMPre = false;
            }
            
        });
        rbUnlockPreAOM.setOnAction(eh-> {
            if (rbUnlockPreAOM.isSelected()) {
                this.unlockAOMPre = true;
                this.unlockAOMVol = false;
            }
            
        });
        rbUnlockNoneAOM.setOnAction(eh-> {
            if (rbUnlockNoneAOM.isSelected()) {
                this.unlockAOMPre = false;
                this.unlockAOMVol = false;
            }
        });
    
        HBox radioButtonVol = new HBox(10);
        
        RadioButton rbUnlockPreVol = new RadioButton("Unlock pressure for volume");
        RadioButton rbUnlockAOMVol = new RadioButton("Unlock amount of moles for volume");
        RadioButton rbUnlockTempVol = new RadioButton("Unlock temperature for volume");
        RadioButton rbUnlockNoneVol = new RadioButton("Unlock none");
        
        radioButtonVol.getChildren().addAll(rbUnlockPreVol, rbUnlockAOMVol,rbUnlockTempVol,rbUnlockNoneVol);
        ToggleGroup groupVol = new ToggleGroup();
        rbUnlockPreVol.setToggleGroup(groupVol);
        rbUnlockAOMVol.setToggleGroup(groupVol);
        rbUnlockTempVol.setToggleGroup(groupVol);
        rbUnlockNoneVol.setToggleGroup(groupVol);
        
        rbUnlockPreVol.setOnAction(eh-> {
            if (rbUnlockPreVol.isSelected()) {
                this.unlockVolPre = true;
                this.unlockVolAOM = false;
                this.unlockVolTemp = false;
            }
            
        });
        rbUnlockAOMVol.setOnAction(eh-> {
            if (rbUnlockAOMVol.isSelected()) {
                this.unlockVolPre = false;
                this.unlockVolAOM = true;
                this.unlockVolTemp = false;
            }
            
        });
        rbUnlockTempVol.setOnAction(eh-> {
            if (rbUnlockTempVol.isSelected()) {
                this.unlockVolPre = false;
                this.unlockVolAOM = false;
                this.unlockVolTemp = true;
            }
            
        });
        rbUnlockNoneVol.setOnAction(eh->{
            if (rbUnlockNoneVol.isSelected()) {
                this.unlockVolPre = false;
                this.unlockVolAOM = false;
                this.unlockVolTemp = false;
            }
        });
        
        HBox radioButtonPre = new HBox(10);
        
        RadioButton rbUnlockVolPre = new RadioButton("Unlock volume for pressure");
        RadioButton rbUnlockAOMPre = new RadioButton("Unlock amount of moles for pressure");
        RadioButton rbUnlockTempPre = new RadioButton("Unlock temperature for pressure");
        RadioButton rbUnlockNonePre = new RadioButton("Unlock none");
        
        radioButtonPre.getChildren().addAll(rbUnlockVolPre, rbUnlockAOMPre,rbUnlockTempPre, rbUnlockNonePre);
        ToggleGroup groupPre = new ToggleGroup();
        rbUnlockVolPre.setToggleGroup(groupPre);
        rbUnlockAOMPre.setToggleGroup(groupPre);
        rbUnlockTempPre.setToggleGroup(groupPre);
        rbUnlockNonePre.setToggleGroup(groupPre);
        
        rbUnlockVolPre.setOnAction(eh-> {
            if (rbUnlockVolPre.isSelected()) {
                this.unlockPreVol = true;
                this.unlockPreAOM = false;
                this.unlockPreTemp = false;
            }
            
        });
        rbUnlockAOMPre.setOnAction(eh-> {
            if (rbUnlockAOMPre.isSelected()) {
                this.unlockPreVol = false;
                this.unlockPreAOM = true;
                this.unlockPreTemp = false;
            }
            
        });
        rbUnlockTempPre.setOnAction(eh-> {
            if (rbUnlockTempPre.isSelected()) {
                this.unlockPreVol = false;
                this.unlockPreAOM = false;
                this.unlockPreTemp = true;
            }
            
        });
        rbUnlockNonePre.setOnAction(eh->{
            if (rbUnlockNonePre.isSelected()) {
                this.unlockPreVol = false;
                this.unlockPreAOM = false;
                this.unlockPreTemp = false;
            }
        });
        HBox radioButtonTemp = new HBox(10);
        
        RadioButton rbUnlockVolTemp = new RadioButton("Unlock volume for temperature");
        RadioButton rbUnlockPreTemp = new RadioButton("Unlock pressure for temperature");
        RadioButton rbUnlockNoneTemp = new RadioButton("Unlock none");
        
        radioButtonTemp.getChildren().addAll(rbUnlockVolTemp, rbUnlockPreTemp, rbUnlockNoneTemp);
                
        ToggleGroup groupTemp = new ToggleGroup();
        rbUnlockVolTemp.setToggleGroup(groupTemp);
        rbUnlockPreTemp.setToggleGroup(groupTemp);
        rbUnlockNoneTemp.setToggleGroup(groupTemp);
        
        rbUnlockVolTemp.setOnAction(eh-> {
            if (rbUnlockVolTemp.isSelected()) {
                this.unlockTempVol = true;
                this.unlockTempPre = false;
            }
            
        });
        rbUnlockPreTemp.setOnAction(eh-> {
            if (rbUnlockPreTemp.isSelected()) {
                this.unlockTempVol = false;
                this.unlockTempPre = true;
            }
            
        });
        rbUnlockNoneTemp.setOnAction(eh-> {
            if (rbUnlockNoneTemp.isSelected()) {
                this.unlockTempVol = false;
                this.unlockTempPre = false;
            }
        });
        
        Cancel.setOnAction(eh-> {
            stage.close();
        });
        Change.setOnAction(eh -> {
           
           double molesValue = Double.parseDouble(fieldForMoles.getText());
           double volumeValue = Double.parseDouble(fieldForVolume.getText());
           double pressureValue = Double.parseDouble(fieldForPressure.getText());
           double temperatureValue = Double.parseDouble(fieldForTemperature.getText());
           
           setMoles(molesValue); setVolume(volumeValue); setPressure(pressureValue); setTemperature(temperatureValue);
           setGasParticle();
           setAnimation();
           
           
           stage.close();
        });
        radioButtonLayout.getChildren().addAll(radioButtonAom,radioButtonVol,radioButtonPre,radioButtonTemp);
        WindowLayout.getChildren().addAll(labelforMoles, labelforVolume, labelforPressure, labelforTemperature, buttons);
        stage.setScene(new Scene(sceneLayout));
        stage.requestFocus();
        return stage;
    
    }
}
