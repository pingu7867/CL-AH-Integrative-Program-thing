package ModuleChargeParticlePath;

/**
 *
 * @author 1630954
 */
import intpro.*;
import javafx.application.Application;
import javafx.stage.Stage;


public class TestModule extends Application{
    @Override
    public void start(Stage primaryStage) {
       ChargeParticlePathModule n = new ChargeParticlePathModule(new Core()); 
       n.viewport.show();
    }
    public static void main(String[] args) {
        Application.launch();
    }
}
