/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleMomentum;

import intpro.Module;
import intpro.*;

/**
 *
 * @author Cédric
 */
public class MomentumModule extends Module {
    ArrayList<PhysicalBody> listOfPhysicalBodies = new ArrayList<>();
    public MomentumModule(Core creator) {
        super(creator);
    }
    
    public generatePhysicalBodyWindow() {
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
        
        TextField fieldforVelX = new TextField("1");
        fieldforVelX .setPrefColumnCount(3);
        fieldforVelX .textProperty().addListener(ov-> {
            String text = fieldforVelX.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text = text.substring(0, text.length() - 1);
               text.replace("-", "");
               fieldforVelX .setText(text);
            }
        });
        
        TextField fieldforVelY = new TextField("0");
        fieldforVelY.setPrefColumnCount(3);
        fieldforVelY.textProperty().addListener(ov-> {
            String text = fieldforVelY.getText();
            if (!checkDecimal(text) && text.contains("-")) {
               text.replace("-", "");
               text = text.substring(0, text.length() - 1);
               fieldforVelY.setText(text);
            }
        });

        Label labelforMass = new Label("Sheet Charge Density (C/m^2)", fieldforMass);
        labelforMass.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforVelX = new Label("Separation Distance of the 2 plates (m)", fieldforVelX);
        labelforVelX.setContentDisplay(ContentDisplay.RIGHT);
        
        Label labelforVelY = new Label("Orientation (degrees)", fieldforVelY);
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
           double velXValue = Double.parseDouble(fieldforVelX.getText());
           double velYValue = Double.parseDouble(fieldforVelY.getText());
           PhysicalBody body = new PhysicalBody(massValue,velXValue,velYValue);
           body.setBody();
           listOfPhysicalBodies.add(body);
           stage.close();
        });
        
        WindowLayout.getChildren().addAll(labelforHeight, labelforRadiusLeft, labelforRadiusRight, buttons);
        if (!viewport.isShowing()) {
            stage.close();
        }
        stage.setScene(new Scene(WindowLayout));
        stage.requestFocus();
        return stage;
    }   
 }

