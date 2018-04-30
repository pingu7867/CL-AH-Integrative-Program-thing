/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleSpringSimpleHarmonicMotion;

import intpro.Module;
import intpro.*;

/**
 *
 * @author Cédric
 */
public class SpringSimpleHarmonicMotionModule extends Module {
    ArrayList<Spring> listOfSprings = new ArrayList<>();
    public SpringSimpleHarmonicMotionModule(Core core, int moduleNumber) {
        super(core, moduleNumber);
    }
    public generateSpringWindow() {
     Stage stage = new Stage();
        VBox WindowLayout = new VBox(20);

        TextField fieldforMass = new TextField("1");
        fieldforMass.setPrefColumnCount(3);
        fieldforMass.textProperty().addListener(ov-> {
            String text = fieldforMass.getText();
            if (!checkDecimal(text)) {
               text = text.substring(0, text.length() - 1);
               fieldforMass.setText(text);
            }
        });
        
        TextField fieldforLength = new TextField("1");
        fieldforLength.setPrefColumnCount(3);
        fieldforLength.textProperty().addListener(ov-> {
            String text = fieldforLength.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1);
               text.replace("-", "");
               fieldforLength .setText(text);
            }
        });
        
        TextField fieldforKConstant = new TextField("0");
        fieldforKConstant.setPrefColumnCount(3);
        fieldforKConstant.textProperty().addListener(ov-> {
            String text = fieldforKConstant.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text.replace("-", "");
               text = text.substring(0, text.length() - 1);
               fieldforKConstant.setText(text);
            }
        });

        Label labelforMass = new Label("Sheet Charge Density (C/m^2)", fieldforMass);
        labelforMass.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforLength = new Label("Separation Distance of the 2 plates (m)", fieldforLength);
        labelforVelX.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforkconstant = new Label("Orientation (degrees)", fieldforKConstant);
        labelforVelY.setContentDisplay(ContentDisplay.RIGHT);
        
        
        HBox buttons = new HBox(8);
        buttons.setAlignment(Pos.BOTTOM_RIGHT);
        Button Create = new Button("Create");        
        Button Cancel = new Button("Cancel");
        buttons.getChildren().addAll(Cancel, Create);
        
        Cancel.setOnAction(eh-> {
            stage.close();
        });
        Create.setOnAction(eh -> {
           double massValue = Double.parseDouble(fieldforMass.getText());
           double lengthValue = Double.parseDouble(fieldforLength.getText());
           double kconstantValue = Double.parseDouble(fieldforKConstant.getText());
           Spring spring = new Spring(lengthValue,kconstantValue, new Oscillator(), new Weigth(massValue));
           spring.setBody();
           listOfSpring.add(spring);
           stage.close();
        });
        
        WindowLayout.getChildren().addAll(labelforMass, labelforLength, labelforkconstant, buttons);
        if (!viewport.isShowing()) {
            stage.close();
        }
        stage.setScene(new Scene(WindowLayout));
        stage.requestFocus();
        return stage;
    }   
}
