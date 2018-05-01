/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleCircularMotion;
import intpro.*;
/**
 *
 * @author Amine
 */
public class PauseButtonCircularMotion extends PauseButton{
    CircularMotionModule module;
    public PauseButtonCircularMotion(Core creator, CircularMotionModule module) {
        super(creator);
        this.doubleClickDuration = 0;
        this.module = module;
    }
    @Override
    public void action() {
    }
}
