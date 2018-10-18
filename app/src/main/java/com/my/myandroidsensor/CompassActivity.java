package com.my.myandroidsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class CompassActivity extends AppCompatActivity implements SensorEventListener {
    private ImageView imgCompass;
    private float currentDegrees = 0f;
    private SensorManager manager;
    private TextView txtDegree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        imgCompass = (ImageView) findViewById(R.id.myImgCompass);
        txtDegree = (TextView) findViewById(R.id.txtDegree);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = Math.round(event.values[0]);

        txtDegree.setText("Heading: "+ Float.toString(degree) + " degrees");
        //Create a rotation animation
        RotateAnimation ra = new RotateAnimation(currentDegrees, -degree,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5F);
        //how long the animation will take place
        ra.setDuration(210);
        //set the animation after the end of reservation status
        ra.setFillAfter(true);
        //start anime
        imgCompass.startAnimation(ra);
        currentDegrees = - degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        manager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }
}
