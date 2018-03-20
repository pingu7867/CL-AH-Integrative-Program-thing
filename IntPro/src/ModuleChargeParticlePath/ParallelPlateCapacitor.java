/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleChargeParticlePath;
import intpro.Element;
import intpro.SpritedElement;
import javafx.scene.image.ImageView;
/**
 *
 * @author Amine
 */
public class ParallelPlateCapacitor extends Element {
    private ElectricPlate botPlate = new ElectricPlate("-"); 
    private ElectricPlate topPlate = new ElectricPlate("+");
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
        this();
    }
    public ParallelPlateCapacitor(double distance, double sheetChargeDensity, double orientation) {
        this.distance = distance;
        this.sheetChargeDensity = sheetChargeDensity;
        this.orientation = orientation;
        this();
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
    public void setSheetChargeDensity(double sheetChargeDensity) {
        this.sheetChargeDensity = sheetChargeDensity;
    }
    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public void switchDirection() {
        botPlate.changeSign();
        topPlate.changeSign();
    }
        

    
}
