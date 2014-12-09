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
    private SeekBar marginChange;
    private TextView ttlMarginView;
    private TextView tmpMarginView;
    private TextView benefitView;
    private TextView zpValueView;
    private TextView ttlSaleView;
    private TextView tonaSaleView;
    private TextView ttlMrgView;
    private TextView tonaMrgView;
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


        TextView productView = (TextView) findViewById(R.id.prodTextView);
        TextView distanceView = (TextView) findViewById(R.id.distanceTextView);
        TextView woMarginView = (TextView) findViewById(R.id.woMarginTextView);
        TextView woMarginValueView = (TextView) findViewById(R.id.woMarginValueTextView);
        marginChange = (SeekBar)findViewById(R.id.seekBar);
        ttlMarginView = (TextView)findViewById(R.id.wMarginTextView);
        tmpMarginView = (TextView)findViewById(R.id.tmpMarginTextView);
        benefitView = (TextView)findViewById(R.id.benefitTextView);
        TextView zpView = (TextView) findViewById(R.id.zpTextView);
        zpValueView = (TextView)findViewById(R.id.zpValueTextView);
        ttlSaleView = (TextView) findViewById(R.id.ttlSaleTextView);
        tonaSaleView = (TextView) findViewById(R.id.tonaSaleTextView);
        ttlMrgView = (TextView) findViewById(R.id.ttlMrgTextView);
        tonaMrgView = (TextView) findViewById(R.id.tonaMrgTextView);

        productView.setText(getIntent().getStringExtra("product") + " (" + getIntent().getStringExtra("wrapping") + ")");
        distanceView.setText(getIntent().getStringExtra("from") + " - " + getIntent().getStringExtra("to") + " (" +
                getIntent().getFloatExtra("distance", 0) + "km) - доставка: " + delivery);
        woMarginView.setText("ИТОГО, без наценки: ");
        woMarginValueView.setText(String.valueOf(woMarg));
        ttlMarginView.setText("Продажная цена, общая / 1т: ");
        benefitView.setText("Прибыль, общая / 1т: ");
        zpView.setText("ЗП от прибыли: ");

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
        ttlSaleView.setText(String.valueOf(BigDecimal.valueOf(marg).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue()));
        tonaSaleView.setText(String.valueOf(BigDecimal.valueOf(tonaMarg).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue()));
        tmpMarginView.setText("Наценка: " + String.valueOf(marginChange.getProgress()) + "%");
        ttlMrgView.setText(String.valueOf(BigDecimal.valueOf(ttlBenefit).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue()));
        tonaMrgView.setText(String.valueOf(BigDecimal.valueOf(tonaBenefit).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue()));
        zpValueView.setText(String.valueOf(BigDecimal.valueOf(zp).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue()));
    }
}

