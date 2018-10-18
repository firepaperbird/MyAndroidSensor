package com.my.myandroidsensor;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ListAllSensorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_sensor);

        TextView txt = (TextView) findViewById(R.id.txtSensors);

        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> list = manager.getSensorList(Sensor.TYPE_ALL);

        StringBuilder data = new StringBuilder();
        for (Sensor s : list){
            data.append("+ Name:"+ s.getName() +"\n");
            data.append("+ Vendor:"+ s.getVendor() +"\n");
            data.append("+ version:"+ s.getVersion() +"\n");
            data.append("----------------------------------\n");
        }

        txt.setText(data);
    }
}
