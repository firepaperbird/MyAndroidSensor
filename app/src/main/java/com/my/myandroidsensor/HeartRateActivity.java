package com.my.myandroidsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HeartRateActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager manager;
    private TextView txtHRM;
    private ImageView heartImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);

        heartImg = findViewById(R.id.imgHeart);
        txtHRM = findViewById(R.id.txtHRM);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_HEART_RATE) {
            txtHRM.setText((int)event.values[0]+" Bpm");
        }if (event.sensor.getType() == Sensor.TYPE_HEART_BEAT) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150*(int)event.values[0], 150*(int)event.values[0] );
//width and height of your Image ,if it is inside Relative change the LinearLayout to RelativeLayout.
            heartImg.setLayoutParams(layoutParams);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    protected void onResume() {
        super.onResume();
        Sensor sensor1 = manager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        Sensor sensor2 = manager.getDefaultSensor(Sensor.TYPE_HEART_BEAT);
        if(sensor1 == null){
            txtHRM.setText("Sensor.TYPE_HEART_RATE NOT Available");

        }
        manager.registerListener(this, sensor1,SensorManager.SENSOR_DELAY_FASTEST);
        manager.registerListener(this, sensor2,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }
}
