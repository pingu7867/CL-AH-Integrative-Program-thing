/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleChargeParticlePath;
import intpro.SpritedElement;
import javafx.scene.image.ImageView;
/**
 *
 * @author Amine
 */
public class ParallelPlateCapacitor {
    private ElectricPlate botPlate = new ElectricPlate("-"); 
    private ElectricPlate topPlate = new ElectricPlate("+");
    private double distance = 1; 
    private double sheetChargeDensity = 1;
    final private double VACUUM_PERMITTIVITY = 8.85418782*Math.pow(10, -12);
    private double ElectricField;
    
    public ParallelPlateCapacitor() {
        ElectricField = sheetChargeDensity/VACUUM_PERMITTIVITY;
    }
    public ParallelPlateCapacitor(double distance, double sheetChargeDensity) {
        this.distance = distance;
        this.sheetChargeDensity = sheetChargeDensity;
    }
    public double getDistance() {
        return distance;
    }
    public double getSheetChargeDensity() {
        return sheetChargeDensity;
    }
    public double getElectricField() {
        return ElectricField;
    }
    
}
