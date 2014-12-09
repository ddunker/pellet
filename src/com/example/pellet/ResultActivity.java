package com.example.pellet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import java.math.BigDecimal;

/**
 * Created by binkovskiy on 05.12.14.
 */
public class ResultActivity extends Activity {
    private TextView productView;
    private TextView distanceView;
    private TextView woMarginView;
    private SeekBar marginChange;
    private TextView ttlMarginView;
    private TextView tmpMarginView;
    private TextView benefitView;
    private TextView zpView;
    float woMarg;
    float marg;
    float tonaMarg;
    float ttlBenefit;
    float tonaBenefit;
    float zp;


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setContentView(R.layout.result);

        float delivery = getIntent().getFloatExtra("distance", 0) * getIntent().getFloatExtra("oneKmCost", 0);
        woMarg = delivery + getIntent().getFloatExtra("weight", 0) * getIntent().getFloatExtra("buy", 0) +
                getIntent().getFloatExtra("exp", 0);


        productView = (TextView)findViewById(R.id.prodTextView);
        distanceView = (TextView)findViewById(R.id.distanceTextView);
        woMarginView = (TextView)findViewById(R.id.woMargeTextView);
        marginChange = (SeekBar)findViewById(R.id.seekBar);
        ttlMarginView = (TextView)findViewById(R.id.wMarginTextView);
        tmpMarginView = (TextView)findViewById(R.id.tmpMarginTextView);
        benefitView = (TextView)findViewById(R.id.benefitTextView);
        zpView = (TextView)findViewById(R.id.zpTextView);

        productView.setText(getIntent().getStringExtra("product") + " (" + getIntent().getStringExtra("wrapping") + ")");

        distanceView.setText(getIntent().getStringExtra("from") + " - " + getIntent().getStringExtra("to") + " (" +
                getIntent().getFloatExtra("distance", 0) + "km) - доставка: " + delivery);

        woMarginView.setText("ИТОГО, без наценки: " + woMarg);

        marginChange.setProgress((getIntent().getIntExtra("margin", 0)));
        count();

        marginChange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar bar) {
            }
            public void onStartTrackingTouch(SeekBar bar) {
            }
            public void onProgressChanged(SeekBar bar, int paramInt, boolean paramBoolean) {
                count();
            }
        });
    }

    public void count () {
        marg = (woMarg * marginChange.getProgress()) / 100 + woMarg;
        tonaMarg = marg / getIntent().getFloatExtra("weight", 0);
        ttlBenefit = marg - woMarg;
        tonaBenefit = ttlBenefit / getIntent().getFloatExtra("weight", 0);
        zp = (float) (ttlBenefit * 0.3);
        ttlMarginView.setText("Продажная цена, общая / 1т: " +
                String.valueOf(BigDecimal.valueOf(marg).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue()) +
                " / " + String.valueOf(BigDecimal.valueOf(tonaMarg).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue()));
        tmpMarginView.setText("Наценка: " + String.valueOf(marginChange.getProgress()));
        benefitView.setText("Прибыль, общая / 1т: " +
                String.valueOf(BigDecimal.valueOf(ttlBenefit).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue()) +
                " / " + String.valueOf(BigDecimal.valueOf(tonaBenefit).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue()));
        zpView.setText("ЗП от прибыли: " +
                String.valueOf(BigDecimal.valueOf(zp).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue()));
    }
}

