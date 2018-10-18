package com.my.myandroidsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener{
    private float mLastX;
    private float mLastY;
    private float mLastZ;
    private boolean bInit;
    private SensorManager manager;
    private Sensor accelerometer;
    private final float NOISE = 0.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        bInit = false;
        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        TextView txtX = (TextView)findViewById(R.id.txtXAxis);
        TextView txtY = (TextView)findViewById(R.id.txtYAxis);
        TextView txtZ = (TextView)findViewById(R.id.txtZAxis);
        ImageView imageView = (ImageView)findViewById(R.id.imgDirection);

        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        if(!bInit){
            mLastX = x;
            mLastY = y;
            mLastZ = z;
            txtX.setText("0.0");
            txtY.setText("0.0");
            txtZ.setText("0.0");
            bInit = true;
        }else {
            float deltaX = mLastX - x;
            float deltaY = mLastY - y;
            float deltaZ = mLastZ - z;

            deltaX = (deltaX < NOISE) ? 0.0f : deltaX;
            deltaY = (deltaY < NOISE) ? 0.0f : deltaY;
            deltaZ = (deltaZ < NOISE) ? 0.0f : deltaZ;

            mLastX = x;
            mLastY = y;
            mLastZ = z;

            txtX.setText(deltaX + "");
            txtY.setText(deltaY + "");
            txtZ.setText(deltaZ + "");
            imageView.setVisibility(View.VISIBLE);

            if(deltaX > deltaY){
                imageView.setImageResource(R.drawable.horizontal);
            }else if (deltaY > deltaX){
                imageView.setImageResource(R.drawable.vertical);
            } else {
                imageView.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
