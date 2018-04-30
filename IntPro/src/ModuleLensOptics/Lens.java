/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleLensOptics;

import intpro.Element;


/**
 *
 * @author Cédric
 */
public class Lens extends SpritedElement {
    private double radiusLeftSideCurvature;
    private double radiusRightSideCurvature;
    private double height;
    private double focalLength;
    private double vergence;
    private String typeOfLen;
    private double refractionIndex = 1.52;
    
    public Lens() {
        
    }
    public Lens(double height, double radiusLeftSideCurvature,double radiusRightSideCurvature) {
        this.height = height;
        this.radiusLeftSideCurvature = radiusLeftSideCurvature;
        this.radiusRightSideCurvature = radiusRightSideCurvature;
        focalLength = (refractionIndex - 1) (1/this.radiusLeftSideCurvature - 1/this.radiusRightSideCurvature);
        vergence = 1/focalLength;
        
    }
    public double getRadiusLeftSideCurvature() {
        return RadiusLeftSideCurvature;
    }
    public double getRadiusRightSideCurvature() {
        return RadiusRightSideCurvature;
    }
    public double getHeight() {
        return Height;
    }
    public String getTypeOfLen() {
        return typeOfLen;
    }
    public double getFocalLength() {
        return FocalLength;
    }
    public double getVergence() {
        return Vergence;
    }
    public double getRefractionIndex() {
        return RefractionIndex;
    }
    public void setRadiusLeftSideCurvature(double RadiusLeftSideCurvature) {
        this.RadiusLeftSideCurvature = RadiusLeftSideCurvature;
    }
    public void setRadiusRightSideCurvature(double RadiusRightSideCurvature) {
        this.RadiusRightSideCurvature = RadiusRightSideCurvature;
    }
    public void setHeight(double Height) {
        this.Height = Height;
    }
    public void setTypeOfLen(String typeOfLen) {
        this.typeOfLen = typeOfLen;
    }
    public void setFocalLength(double FocalLength) {
        this.FocalLength = FocalLength;
    }
    public void setRefractionIndex(double RefractionIndex) {
        this.RefractionIndex = RefractionIndex;
    }
}
