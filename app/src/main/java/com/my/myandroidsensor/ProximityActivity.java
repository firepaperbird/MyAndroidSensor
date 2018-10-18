package com.my.myandroidsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ProximityActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager manager;
    private Sensor proximity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);

        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        proximity = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);


    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        ImageView img = findViewById(R.id.imgPro);
        if(sensorEvent.values[0] == 0){
            img.setImageResource(R.drawable.horizontal);
        }else {
            img.setImageResource(R.drawable.vertical);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
