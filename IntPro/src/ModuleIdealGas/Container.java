
package ModuleIdealGas;

/**
 *
 * @author 1630954
 */
import intpro.SpritedElement;
import java.util.ArrayList;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Container extends SpritedElement{
    ArrayList<GasParticle> particles = new ArrayList<>();
    double amountOfMole = 0; //in moles
    double volume = 0; //in liters (L)
    double pressure = 0; //in kPa
    double temperature = 0; //in Kelvin (K)
    
    boolean unlockAOM = false;
    boolean unlockVol = false;
    boolean unlockPre = false;
    boolean unlockTemp = false;
    
    double averageKineticEnergy = 0;
    final double GAS_CONSTANT = 8.3144598;
    final double BOLTZMANN_CONSTANT = 1.38064852 * Math.pow(10, -23); //in eV/K
    Rectangle hitbox;
    
    public Container() {
        super(new ImageView());
    }
    public Container(double aom, double vol, double pre, double temp) {
        this();
        amountOfMole = aom; volume = vol; pressure = pre; temperature = temp;
        
        averageKineticEnergy = (3/2) * BOLTZMANN_CONSTANT * temperature;
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
                
        amountOfMole = aom;
        
        if (!unlockPre) {
            volume = ratioVN * aom;
        }
        else if (!unlockVol) {
            pressure = ratioPN * aom;
        }
    }
    public void setVolume(double vol) {
        double kVP = volume * pressure;
        double ratioVT = volume/temperature;
        double ratioVN = volume/amountOfMole;
        
        volume = vol;
        if (!unlockAOM && !unlockTemp) {
            pressure = kVP/vol;
        }
        else if (!unlockAOM && !unlockPre) {
            temperature = vol/ratioVT;
        }
        else if (!unlockTemp && !unlockPre) {
            amountOfMole = vol/ratioVN;
        }
        
    }
    public void setPressure(double pre) {
        double kVP = volume * pressure;
        double ratioPN = pressure/amountOfMole;
        double ratioPT = pressure/temperature;
        
        pressure = pre;
        
        if (!unlockVol && !unlockAOM) {
            temperature = pre/ratioPT;
        }
        else if (!unlockVol && !unlockTemp) {
            amountOfMole = pre/ratioPN;
        }
        else if (!unlockAOM && !unlockTemp) {
            volume = kVP/pre;
        }
    }
    public void setTemperature(double temp) {
        double ratioVT = volume/temperature;
        double ratioPT = pressure/temperature;
        
        temperature = temp;
        
        if (!unlockVol) {
            
        }
    }
    
}
