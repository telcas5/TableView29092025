package es.telmocas;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PruebaController {
    @FXML
    private Rectangle rectangulo;
    @FXML
    private Slider sliderBl;
    @FXML
    private Slider sliderRed;
    @FXML
    private Slider sliderGr;

    @FXML
    public void initialize() {
        setSlider(sliderRed);
        setSlider(sliderGr);
        setSlider(sliderBl);

        sliderRed.setValue(0);
        sliderGr.setValue(0);
        sliderBl.setValue(255);

        sliderRed.valueProperty().addListener(this::cambiarSlider);
        sliderGr.valueProperty().addListener(this::cambiarSlider);
        sliderBl.valueProperty().addListener(this::cambiarSlider);

        cambiarColor();
    }

    @FXML
    private void setSlider(Slider slider) {
        slider.setMin(0);
        slider.setMax(255);
        slider.setValue(125);
        slider.setBlockIncrement(20);
        slider.setMajorTickUnit(85);
        slider.setMinorTickCount(10);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(true);
    }


    private void cambiarSlider(ObservableValue<? extends Number> propiedad, Number vOld, Number vNew){
        cambiarColor();
    }

    private void cambiarColor(){
        int r = (int) sliderRed.getValue();
        int g = (int) sliderGr.getValue();
        int b = (int) sliderBl.getValue();
        rectangulo.setFill(Color.rgb(r, g, b));
    }

}
