/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleLensOptics;
import intpro.*
import java.util.ArrayList;
/**
 *
 * @author Amine
 */
public class SystemOfLens extends Element{
    ArrayList<Lens> listOfLens = new ArrayList<>();
    double vergence = 0;
    double focalLength;
    
    public SystemOfLens() {
        
    }
    public void addLen(Lens len) {
        listOfLens.add(len);
    }
    public void addLens(Lens[] lens) {
        listOfLens.addAll(lens);
    }
    public void addLens(List<Lens> lens) {
        listOfLens.addAll(lens);
    }
    public void setVergenceAdded() {
        for (int i = 0; i < listOfLens.size(); i++) {
           vergence += listOfLens.get(i).getVergence();   
        }
        focalLength = 1/vergence;
    }
    public double getVergence() {
        return vergence;
    }
    public double getFocalLength() {
        return focalLength;   
    }
}
