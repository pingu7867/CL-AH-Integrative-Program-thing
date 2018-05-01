/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWaveSuperposition;
import intpro.*;
/**
 *
 * @author Amine
 */
public class RunSimulationOneWave extends RunSimulationButton{
    Curve curve;
    public RunSimulationOneWave(Core creator, Curve curve) {
        super(creator);
        this.doubleClickDuration = 0;
        this.curve = curve;
    }
    @Override
    public void action() {
        curve.play();
    }
}
