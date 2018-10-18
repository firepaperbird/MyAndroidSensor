package com.my.myandroidsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BarometerActivity extends AppCompatActivity  implements SensorEventListener {
    private SensorManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barometer);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);

//        manager.registerListener(this,
//                manager.getDefaultSensor(Sensor.TYPE_PRESSURE),
//                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        TextView txtValue = (TextView)findViewById(R.id.txtValue);
        float[] value = event.values;
        txtValue.setText(value[0]+" hPa");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }
}
