package com.company.pellet;


import android.graphics.Color;
import android.widget.EditText;

public class Checker {

    public boolean error = false;

    public void checkFilling(EditText product, EditText  wrapping, EditText fr, EditText destination, EditText distance,
                             EditText oneKmCost, EditText weight, EditText buyPrice, EditText margin,
                             EditText expanses) {
        if (product.getText().toString().length() == 0) {
            product.setText("-");
        }
        if (wrapping.getText().toString().length() == 0) {
            wrapping.setText("-");
        }
        if (fr.getText().toString().length() == 0) {
            fr.setText("-");
        }
        if (destination.getText().toString().length() == 0) {
            destination.setText("-");
        }
        if (distance.getText().toString().length() == 0) {
            distance.setText("0");
        }
        if (oneKmCost.getText().toString().length() == 0) {
            oneKmCost.setText("0");
        }
        if (weight.getText().toString().length() == 0) {
            weight.setText("0");
        }
        if (weight.getText().toString().equals("0") || weight.getText().toString().equals("0.0")) {
            weight.setBackgroundColor(Color.RED);
            setError();
        } else {
            weight.setBackgroundColor(Color.parseColor("#E0E0E0"));
        }
        if (buyPrice.getText().toString().length() == 0) {
            buyPrice.setText("0");
        }
        if (margin.getText().toString().length() == 0) {
            margin.setText("0");
        }
        if (expanses.getText().toString().length() == 0) {
            expanses.setText("0");
        }
    }

    public boolean setError() {
        return (error = true);
    }
}
