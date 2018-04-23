/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleChargeParticlePath;
import intpro.Element;
import javafx.scene.Group;
/**
 *
 * @author Amine
 */
public class ParallelPlateCapacitor extends Element {
    private String arrowDirection = "down";
    private ElectricPlate botPlate = new ElectricPlate("-",arrowDirection); 
    private ElectricPlate topPlate = new ElectricPlate("+",arrowDirection);
    
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
        capacitor.getChildren().addAll(topPlate.sprite,botPlate.sprite);
        
        capacitor.setRotate(-1*orientation);
        topPlate.sprite.setX(0);
        topPlate.sprite.setY(0);
        
        botPlate.sprite.setX(topPlate.sprite.getX() + distance*Math.sin(Math.toRadians(orientation)));
        botPlate.sprite.setY(topPlate.sprite.getY() + (distance + topPlate.sprite.getImage().getHeight())
                *Math.cos(Math.toRadians(orientation)));
        
        
        topPlate.sprite.setOnMouseDragged(eh-> {
            if (canMove) {
            topPlate.sprite.setX(eh.getSceneX());
            topPlate.sprite.setY(eh.getSceneY());
            posX = eh.getSceneX();
            posY = eh.getSceneY();
            
            botPlate.sprite.setX(topPlate.sprite.getX() + distance * Math.sin(Math.toRadians(orientation)));
            botPlate.sprite.setY(topPlate.sprite.getY() + (distance + topPlate.sprite.getImage().getHeight()) * Math.cos(Math.toRadians(orientation)));
            }
            else {
                
            }
        });
        botPlate.sprite.setOnMouseDragged(eh-> {
            if (canMove) {
            botPlate.sprite.setX(eh.getSceneX());
            botPlate.sprite.setY(eh.getSceneY());
            
            topPlate.sprite.setX(botPlate.sprite.getX() - distance*Math.sin(Math.toRadians(orientation)));
            topPlate.sprite.setY(botPlate.sprite.getY() -(distance + topPlate.sprite.getImage().getHeight())*Math.cos(Math.toRadians(orientation)));
            }
            else {
                
            }
            });
        
    }    

    
}
