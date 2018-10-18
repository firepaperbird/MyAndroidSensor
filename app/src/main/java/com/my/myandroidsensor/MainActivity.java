package com.my.myandroidsensor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickToListAll(View view) {
        Intent intent = new Intent(this,ListAllSensorActivity.class);
        startActivity(intent);
    }

    public void clickToBaro(View view) {
        Intent intent = new Intent(this,BarometerActivity.class);
        startActivity(intent);
    }

    public void clickToCompass(View view) {
        Intent intent = new Intent(this,CompassActivity.class);
        startActivity(intent);
    }

    public void clickToLight(View view) {
        Intent intent = new Intent(this,LightSensorActivity.class);
        startActivity(intent);
    public void clickToAccele(View view) {
        Intent intent = new Intent(this,AccelerometerActivity.class);
        startActivity(intent);
    }

    public void clickToHearRate(View view) {
        Intent intent = new Intent(this,HeartRateActivity.class);
        startActivity(intent);
    public void clickToProximity(View view) {
        Intent intent = new Intent(this,ProximityActivity.class);
        startActivity(intent);
    }
}
