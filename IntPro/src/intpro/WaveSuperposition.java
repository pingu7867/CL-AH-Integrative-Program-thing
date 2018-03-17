import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import javafx.geometry.Orientation;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollBar;

/**
 *
 * @author 1630954
 */
public class WaveSuperposition extends Application {
    ArrayList<WavePane> Waves = new ArrayList<>();
    @Override
    public void start(Stage primaryStage){
        
        WavePane wpane1 = new WavePane(-1);
        BorderPane Bord1 = new BorderPane();
        HBox Sliders = new HBox(150);
        
        VBox SLt1 = new VBox(10); VBox SLt2 = new VBox(10); VBox SLt3 = new VBox(10); VBox SLt4 = new VBox();
        
        Slider slWL1 = new Slider(); slWL1.setOrientation(Orientation.HORIZONTAL); slWL1.setShowTickLabels(true); slWL1.setShowTickMarks(true);
        slWL1.setValue(wpane1.getWavelength()); slWL1.setMax(Math.abs(wpane1.getSpeed())); slWL1.setMin(1);
        
        TextField TWL1 = new TextField("" + wpane1.getWavelength());  TWL1.setPrefColumnCount(3); ; Label labWL1 = new Label("Wavelength (m)", TWL1); 
        labWL1.setContentDisplay(ContentDisplay.RIGHT);
        
        SLt1.getChildren().add(labWL1); SLt1.getChildren().add(slWL1);
        
        Slider slF1 = new Slider(); slF1.setOrientation(Orientation.HORIZONTAL); slF1.setShowTickLabels(true); slF1.setShowTickMarks(true);
        slF1.setValue(wpane1.getFrequency()); slF1.setMax(Math.abs(wpane1.getSpeed())); slF1.setMin(1);
        
        TextField TF1 = new TextField(""+wpane1.getFrequency());  TF1.setPrefColumnCount(3); ; Label labF1 = new Label("Frequency (Hz)", TF1);
        labF1.setContentDisplay(ContentDisplay.RIGHT);
        
        SLt2.getChildren().add(labF1); SLt2.getChildren().add(slF1);
        
        Slider slS1 = new Slider(); slS1.setOrientation(Orientation.HORIZONTAL); slS1.setShowTickLabels(true); slS1.setShowTickMarks(true);
        slS1.setValue(wpane1.getSpeed()); slS1.setMax(600); slS1.setMin(-600);
        
        TextField TS1 = new TextField(""+wpane1.getSpeed());  TS1.setPrefColumnCount(3); ; Label labS1 = new Label("Speed (m/s)", TS1);
        labS1.setContentDisplay(ContentDisplay.RIGHT);
        
        SLt3.getChildren().add(labS1); SLt3.getChildren().add(slS1);
        
        Slider slA1 = new Slider(); slA1.setOrientation(Orientation.HORIZONTAL); slA1.setShowTickLabels(true); slA1.setShowTickMarks(true);
        slA1.setValue(wpane1.getAmplitude()); slA1.setMax(100); slA1.setMin(0);
        
        TextField TA1 = new TextField("" + wpane1.getAmplitude());  TA1.setPrefColumnCount(3); ; Label labA1 = new Label("Amplitude (m)", TA1); 
        labA1.setContentDisplay(ContentDisplay.RIGHT);
        
        SLt4.getChildren().add(labA1); SLt4.getChildren().add(slA1);
        
        Sliders.getChildren().addAll(SLt1,SLt2,SLt3,SLt4);
        Sliders.setAlignment(Pos.CENTER);
        Bord1.setCenter(wpane1);
        Bord1.setBottom(Sliders);
        
        slWL1.valueProperty().addListener(ov ->{ 
            wpane1.setWavelength(slWL1.getValue()); 
            TWL1.setText("" + slWL1.getValue());
            slF1.setValue(wpane1.getFrequency());
            TF1.setText("" + wpane1.getFrequency());
            });
        TWL1.setOnAction(e-> {
            
            double Wl = Double.parseDouble(TWL1.getText());
            wpane1.setWavelength(Wl); slWL1.setValue(Wl);
            slF1.setValue(wpane1.getFrequency());
            TF1.setText("" + wpane1.getFrequency());
            });
        TWL1.setOnMouseClicked(e-> {
            TWL1.clear();
        });       
        
        slA1.valueProperty().addListener(ov-> {
           wpane1.setAmplitude(slA1.getValue());
           TA1.setText("" + slA1.getValue());
        });
        WavePane wpane2 = new WavePane(1);
        BorderPane Bord2 = new BorderPane();
        
       // WaveSuperpositionPane WSpane = new WaveSuperpositionPane(wpane1,wpane2);
        
        VBox WaveBox = new VBox(20);
        WaveBox.getChildren().addAll(Bord1,wpane2);
        
        primaryStage.setScene(new Scene(WaveBox));
        primaryStage.setTitle("Wave Superposition");
        primaryStage.show();
    }
    public static void main (String[] args) {
        Application.launch();
    }
    
    class WavePane extends Pane{
        private Polyline Curve = new Polyline();
        ObservableList<Double> points = Curve.getPoints();
        private double Amplitude = 50;
        private double Speed = 300;
        private double Frequency = 2;
        private double Wavelength = Speed/Frequency;
        private Timeline animation;
        private double offsetx; 
        private double ratioFonW = Frequency/Wavelength;
        
        public WavePane() {
            CreateCurve();
            Curve.setStroke(Color.BLACK);
            getChildren().add(Curve);
            
            if (Speed > 0) {
                offsetx = -1;
            }
            else if (Speed < 0) {
                offsetx = points.get(points.size() - 2) + 1;
            }
            
            if (Speed !=0) {
                animation = new Timeline(60,new KeyFrame(Duration.millis(1000/30)  , eh -> AnimateWaveMotion()));
                animation.setCycleCount(Timeline.INDEFINITE);
                
                animation.play();
            }
        }
        public WavePane(double dir) {
            Speed*=dir;
            CreateCurve();
            Curve.setStroke(Color.BLACK);
            getChildren().add(Curve);
            
            if (Speed > 0) {
                offsetx = -1;
            }
            else if (Speed < 0) {
                offsetx = points.get(points.size() - 2) + 1;
            }
            
            if (Speed !=0) {
                animation = new Timeline(new KeyFrame(Duration.millis(1000/30)  , eh -> AnimateWaveMotion()));
                animation.setCycleCount(Timeline.INDEFINITE);
                animation.play();
            }
        }
       
        public void setSpeed(double Speed) {
            if (this.Speed == 0 && Speed > 0) {
                animation = new Timeline(new KeyFrame(Duration.millis(2000/Math.abs(Speed) * 8)  , eh -> AnimateWaveMotion()));
                animation.setCycleCount(Timeline.INDEFINITE);
                animation.play();
                
            }
            else if (Speed == 0) {
                animation.stop();
            }
            else if (this.Speed > 0 && Speed < 0) {
                
            }
            else if (this.Speed < 0 && Speed > 0) {
                
            }
            
            this.Speed = Speed;
            
        }

        
        public void setFrequency(double Frequency) {
            this.Frequency = Frequency;
            this.Wavelength = Math.abs(Speed)/Frequency;
        }
        public void setWavelength(double Wavelength) {
            
            getChildren().remove(Curve); points.clear();
            
            this.Wavelength = Wavelength;
            this.Frequency = Math.abs(Speed)/Wavelength;
            CreateCurve();
            getChildren().add(Curve);
            offsetx = (Speed > 0) ? -1 + offsetx: (Speed < 0) ? points.get(points.size() - 2) + 1: 0;
            
        }
        public void setAmplitude(double Amplitude) {
            
            ChangeCurve(Wavelength,Amplitude);
            this.Amplitude = Amplitude;
            offsetx = (Speed > 0) ? -1 + offsetx: (Speed < 0) ? points.get(points.size() - 2) + 1: 0;
            
        }
        
        public double getWavelength() {
            return this.Wavelength;
        }
        public double getFrequency() {
            return this.Frequency;
        }
        public double getSpeed() {
            return this.Speed;
        }
        public double getAmplitude() {
            return this.Amplitude;
        }
        public ObservableList<Double> getPoints() {
            return points;
        }
        
        private void CreateCurve() {
            
            double x = 0; double y = 150;
            points.add(x); points.add(y);
            
            while (x <= 1200) {
            x +=  1; y = 150 - Amplitude*(Math.sin(x/Wavelength * 2*Math.PI));
            
            points.add(x); points.add(y);
            }
            
        }
        private void AnimateWaveMotion() {         
            for (int i = 0; i < points.size(); i+=2) {
                if (Speed > 0) {
                    points.set(i,points.get(i) + Speed/100);
                    
                    if (i == points.size() - 2) {                        
                        
                        double y = 150 - Amplitude*(Math.sin(offsetx/Wavelength * 2*Math.PI));
                        points.add(0,0.); points.add(1,y); offsetx-=Speed/100;
                        points.remove(points.size()-1); points.remove(points.size()-1);
                        break;
                    }
                    
                }
                else if (Speed < 0) {
                    points.set(i,points.get(i) + Speed/100);
                    
                    if (i == points.size() - 2) {
                        
                        double y = 150 - Amplitude * Math.sin(offsetx/Wavelength * 2*Math.PI);
                        points.add(1200.); points.add(y); offsetx -= Speed/100;
                        
                        points.remove(0); points.remove(0);
                        break;
                    }
                }
                else {
                    
                }
            }

                
        }
        private void ChangeCurve(double newWavelength, double newAmplitude) {
            for(int i = 1; i < points.size(); i+=2) {
                points.set(i,150 - newAmplitude*(Math.sin((points.get(i-1) + offsetx)/newWavelength * 2*Math.PI)));
            }
        }
                
        
        
    
    }
    class WaveSuperpositionPane extends Pane {
        private Polyline Curve = new Polyline();
        ObservableList<Double> points = Curve.getPoints();
        ArrayList<WavePane> waves = new ArrayList<>();
        private Timeline animation;
        private double x;
        
        
        public WaveSuperpositionPane(WavePane wave1, WavePane wave2) {
            waves.add(wave1); waves.add(wave2);
            CreateCurve();
            getChildren().add(Curve);
            animation = new Timeline(new KeyFrame(Duration.millis(2000/30), eh-> ChangeCurve()));
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();
        }
        
        public void CreateCurve() {
            double x = 0; double y = 150;
            points.add(x); points.add(y);
            int IndexForWaves = 1;
            for (int i = 0; i < 600; i++) {
                y = 150; double Sin = 0;
                x+=1;
                for(int j = 0; j < waves.size(); j++ ) {
                   Sin -= waves.get(j).getPoints().get(IndexForWaves);
                                      
                }
                y += Sin;
                this.points.add(x); this.points.add(y);
                IndexForWaves += 2;
            }
            }
        public void ChangeCurve(){
            for(int i = 1; i < points.size(); i+=2){
                double Sin = 0;
                
                for (int j = 0; j < waves.size(); j++) {
                    Sin -= waves.get(j).getPoints().get(i);
                }
                double y = 150 - Sin;
                points.set(i, y);
            }
        }
        public void AddCurve(WavePane wave) {
            this.waves.add(wave);
        }
        }
        
    }


