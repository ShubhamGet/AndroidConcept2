package com.example.lightsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.os.strictmode.CredentialProtectedWhileLockedViolation;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SensorManager smr;
    TextView lightLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lightLevel = (TextView)findViewById(R.id.light_level);
        smr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor snr = smr.getDefaultSensor(Sensor.TYPE_LIGHT);
        smr.registerListener(listner,snr,smr.SENSOR_DELAY_NORMAL);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(smr != null) {
            smr.unregisterListener(listner);
        }
    }
    private SensorEventListener listner = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float value = sensorEvent.values[0];
            lightLevel.setText("Current Light Level is " + value + " lx");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
