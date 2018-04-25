
package ModuleIdealGas;

/**
 *
 * @author 1630954
 */
import intpro.SpritedElement;
import java.util.ArrayList;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Container extends SpritedElement{
    ArrayList<GasParticle> particles = new ArrayList<>();
    double amountOfMole = 0; //in moles
    double volume = 0; //in liters (L)
    double pressure = 0; //in kPa
    double temperature = 0; //in Kelvin (K)
    double averageKineticEnergy = 0;
    final double GAS_CONSTANT = 8.3144598;
    final double BOLTZMANN_CONSTANT = 1.38064852 * Math.pow(10, -23); //in eV/K
    
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
        amountOfMole = aom;
    }
    public void setVolume(double vol) {
        volume = vol;
    }
    public void setPressure(double pre) {
        pressure = pre;
    }
    public void setTemperature(double temp) {
        temperature = temp;
    }
    
}
