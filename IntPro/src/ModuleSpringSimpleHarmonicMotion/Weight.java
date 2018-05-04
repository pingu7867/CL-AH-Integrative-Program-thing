/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleSpringSimpleHarmonicMotion;

import intpro.SpritedElement;

/**
 *
 * @author Cédric
 */
public class Weight extends SpritedElement {
    double mass = 1;
    double force;
    
    final double INTENSITY_GRAVITATIONAL_PULL = 9.8;
    
    public Weight() {
        
    }
    
    public Weight(double mass) {
        this.mass = mass;
        force = this.mass * INTENSITY_GRAVITATIONAL_PULL;
    }
    
}
