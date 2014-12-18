package com.company.pellet;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;


public class ResultActivity extends Activity {
    private SeekBar marginChange;
    private TextView zpValueView;
    private TextView ttlSaleView;
    private TextView tonaSaleView;
    private TextView ttlMrgView;
    private TextView tonaMrgView;
    private TextView tmpMarginValueView;
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
        woMarg = delivery + getIntent().getFloatExtra("weight", 0) * getIntent().getFloatExtra("buyPrice", 0) +
                getIntent().getFloatExtra("expenses", 0);


        TextView productView = (TextView) findViewById(R.id.prodTextView);
        TextView distanceView = (TextView) findViewById(R.id.distanceTextView);
        TextView woMarginView = (TextView) findViewById(R.id.woMarginTextView);
        TextView woMarginValueView = (TextView) findViewById(R.id.woMarginValueTextView);
        marginChange = (SeekBar) findViewById(R.id.seekBar);
        TextView ttlMarginView = (TextView) findViewById(R.id.wMarginTextView);
//        TextView tmpMarginView = (TextView) findViewById(R.id.tmpMarginTextView);
        TextView benefitView = (TextView) findViewById(R.id.benefitTextView);
        TextView zpView = (TextView) findViewById(R.id.zpTextView);
        zpValueView = (TextView) findViewById(R.id.zpValueTextView);
        ttlSaleView = (TextView) findViewById(R.id.ttlSaleTextView);
        tonaSaleView = (TextView) findViewById(R.id.tonaSaleTextView);
        ttlMrgView = (TextView) findViewById(R.id.ttlMrgTextView);
        tonaMrgView = (TextView) findViewById(R.id.tonaMrgTextView);
        tmpMarginValueView = (TextView) findViewById(R.id.tmpMarginValueTextView);

        productView.setText(getIntent().getStringExtra("product") + " (" + getIntent().getStringExtra("wrapping") + ")");
        distanceView.setText(getIntent().getStringExtra("fr") + " - " + getIntent().getStringExtra("destination") + " (" +
                getIntent().getFloatExtra("distance", 0) + "km) - доставка: " + delivery);
        woMarginView.setText("ИТОГО, без наценки: ");
        woMarginValueView.setText(String.valueOf(woMarg));
        ttlMarginView.setText("Продажная цена, общая / 1т: ");
        benefitView.setText("Прибыль, общая / 1т: ");
        zpView.setText("ЗП от прибыли: ");
//        tmpMarginView.setText("Наценка: ");

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

    // menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.result, menu);
        return true;
    }

    // обработка нажатий
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Операции для выбранного пункта меню
        switch (item.getItemId()) {
            case R.id.saveItem:
                DataBase db = new DataBase(getApplicationContext());

                String product = getIntent().getStringExtra("product");
                String wrapping = getIntent().getStringExtra("wrapping");
                String from = getIntent().getStringExtra("fr");
                String to = getIntent().getStringExtra("destination");
                String distance = String.valueOf(getIntent().getFloatExtra("distance", 0));
                String oneKmCost= String.valueOf(getIntent().getFloatExtra("oneKmCost", 0));
                String weight = String.valueOf(getIntent().getFloatExtra("weight", 0));
                String buyPrice = String.valueOf(getIntent().getFloatExtra("buyPrice", 0));
                String margin = String.valueOf(marginChange.getProgress());
                String expanses = String.valueOf(getIntent().getFloatExtra("expenses", 0));

                db.insertRow(product, wrapping, from, to, distance, oneKmCost, weight, buyPrice, margin, expanses);

                Toast toast = Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT);
                toast.show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void count () {
        marg = (woMarg * marginChange.getProgress()) / 100 + woMarg;
        tonaMarg = marg / getIntent().getFloatExtra("weight", 0);
        ttlBenefit = marg - woMarg;
        tonaBenefit = ttlBenefit / getIntent().getFloatExtra("weight", 0);
        zp = (float) (ttlBenefit * 0.3);
        ttlSaleView.setText(String.valueOf(BigDecimal.valueOf(marg).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue()));
        tonaSaleView.setText(String.valueOf(BigDecimal.valueOf(tonaMarg).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue()));
        tmpMarginValueView.setText(String.valueOf(marginChange.getProgress()) + "%");
        ttlMrgView.setText(String.valueOf(BigDecimal.valueOf(ttlBenefit).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue()));
        tonaMrgView.setText(String.valueOf(BigDecimal.valueOf(tonaBenefit).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue()));
        zpValueView.setText(String.valueOf(BigDecimal.valueOf(zp).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue()));
    }
}

