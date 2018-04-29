/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWaveSuperposition;
import intpro.*;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 *
 * @author Amine
 */
public class TestWavePane extends Application {
    @Override
    public void start(Stage primaryStage) {
       WaveSuperpositionModule n = new WaveSuperpositionModule(new Core()); 
       n.viewport.show();
    }
    public static void main(String[] args) {
        Application.launch();
    }{
    
}
}
