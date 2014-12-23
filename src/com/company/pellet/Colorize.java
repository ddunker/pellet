package com.company.pellet;

import android.content.res.Resources;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Colorize{

    public void colorMain(EditText product, EditText  wrapping, EditText fr, EditText destination, EditText distance,
                          EditText oneKmCost, EditText weight, EditText buyPrice, EditText margin,
                          EditText expanses, Resources res, Window w, Button btn) {
        product.setBackgroundResource(R.color.edit_text);
        wrapping.setBackgroundResource(R.color.edit_text);
        fr.setBackgroundResource(R.color.edit_text);
        destination.setBackgroundResource(R.color.edit_text);
        distance.setBackgroundResource(R.color.edit_text);
        oneKmCost.setBackgroundResource(R.color.edit_text);
        weight.setBackgroundResource(R.color.edit_text);
        buyPrice.setBackgroundResource(R.color.edit_text);
        margin.setBackgroundResource(R.color.edit_text);
        expanses.setBackgroundResource(R.color.edit_text);

        product.setTextColor(res.getColor(R.color.text_color));
        wrapping.setTextColor(res.getColor(R.color.text_color));
        fr.setTextColor(res.getColor(R.color.text_color));
        destination.setTextColor(res.getColor(R.color.text_color));
        distance.setTextColor(res.getColor(R.color.text_color));
        oneKmCost.setTextColor(res.getColor(R.color.text_color));
        weight.setTextColor(res.getColor(R.color.text_color));
        buyPrice.setTextColor(res.getColor(R.color.text_color));
        margin.setTextColor(res.getColor(R.color.text_color));
        expanses.setTextColor(res.getColor(R.color.text_color));

        w.getDecorView().setBackgroundResource(R.color.background);
        btn.setBackgroundResource(R.drawable.button);
    }

    public void colorResult(TextView zpValueView, TextView  ttlSaleView, TextView  tonaSaleView, TextView  ttlMrgView,
                            TextView tonaMrgView, TextView tmpMarginValueView, Resources res, Window w) {
        zpValueView.setTextColor(res.getColor(R.color.text_color));
        ttlSaleView.setTextColor(res.getColor(R.color.text_color));
        tonaSaleView.setTextColor(res.getColor(R.color.text_color));
        ttlMrgView.setTextColor(res.getColor(R.color.text_color));
        tonaMrgView.setTextColor(res.getColor(R.color.text_color));
        tmpMarginValueView.setTextColor(res.getColor(R.color.text_color));

        w.getDecorView().setBackgroundResource(R.color.background);
    }

    public void colorSearch(Window w) {
        w.getDecorView().setBackgroundResource(R.color.background);
    }

    public void colorPreferences(Window w) {
        w.getDecorView().setBackgroundResource(R.color.background);
    }
}
