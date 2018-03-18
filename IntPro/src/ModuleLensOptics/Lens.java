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
public class Lens extends Element {
    double RadiusLeftSideCurvature;
    double RadiusRightSideCurvature;
    double Height;
    double FocalLength;
    double Vergence;
    String typeOfLen;
    double RefractionIndex = 1.52;
    
    public Lens() {
        
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
