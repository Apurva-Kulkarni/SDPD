package com.example.rohit.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;

import java.util.Random;

public class accelerometer extends AppCompatActivity implements SensorEventListener {

    ConstraintLayout view;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private static final int SHAKE_THRESHOLD = 800;
    private float last_x = 0, last_y = 0, last_z = 0;
    long last_update = 0;
    Random rnd = new Random();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        view  = (ConstraintLayout) findViewById(R.id.view);
        view.setBackgroundColor(Color.GREEN);
        System.out.println(Color.GREEN);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        long curTime = System.currentTimeMillis();
        if ((curTime - last_update) > 100) {
            long diffTime = (curTime - last_update);
            last_update = curTime;

            float[] values = sensorEvent.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            float speed = Math.abs(x + y + z - last_x - last_y - last_z)/diffTime * 10000;

            if (speed > SHAKE_THRESHOLD) {

                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                view.setBackgroundColor(color);
            }

            last_x = x;
            last_y = y;
            last_z = z;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
