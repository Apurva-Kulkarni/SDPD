package com.example.rohit.sensors;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.rohit.sensors.accelerometer;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void accelerometer(View view){
        Intent intent = new Intent(this, accelerometer.class);
        startActivity(intent);
    }

    public void proximity_sensor(View view){
        Intent intent = new Intent(this, proximity.class);
        startActivity(intent);
    }

    public void compass(View view){
        Intent intent = new Intent(this, compass.class);
        startActivity(intent);
    }
}
