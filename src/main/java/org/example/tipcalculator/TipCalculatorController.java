package org.example.tipcalculator;

import javafx.fxml.FXML;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class TipCalculatorController {
    @FXML
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();
    private BigDecimal tipPercentage = new BigDecimal("0.15");

    @FXML
    private TextField amountTextField;
    @FXML
    private Label tipPercentageLabel;
    @FXML
    private Slider tipPercentageSlider;
    @FXML
    private TextField tipTextField;
    @FXML
    private TextField totalTextField;

    private void calculate() {
        try {
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);
            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        } catch (NumberFormatException ex) {
            tipTextField.setText("");
            totalTextField.setText("");

        }
    }

    public void initialize() {
        currency.setRoundingMode(RoundingMode.HALF_UP);

        tipPercentageSlider.valueProperty().addListener(
                (ov, oldValue, newValue) -> {
                    tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0);
                    tipPercentageLabel.setText(percent.format(tipPercentage));
                    calculate();
                }
        );


        amountTextField.textProperty().addListener(
                (observable, oldValue, newValue) -> calculate()
        );
    }
}
