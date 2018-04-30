/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleIdealGas;

/**
 *
 * @author Amine
 */
import intpro.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestIdealGasModule  extends Application{
    @Override
    public void start(Stage primaryStage) {
       IdealGasModule n = new IdealGasModule(new Core()); 
       n.viewport.show();
    }
    public static void main(String[] args) {
        Application.launch();
    }
    
}
