package com.my.myandroidsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LightSensorActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager manager;
    private TextView txtLight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);

        txtLight = findViewById(R.id.txtLight);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float lux = event.values[0];
        txtLight.setText(lux+" lux");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(sensor == null){
            txtLight.setText("Sensor.TYPE_LIGHT NOT Available");

        }
        manager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }


}
