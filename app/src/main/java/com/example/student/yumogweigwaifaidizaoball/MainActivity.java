package com.example.student.yumogweigwaifaidizaoball;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    private TextView answerText;
    private SensorManager sensorManager;
    private MediaPlayer mediaPlayer;
    private Sensor accelerometer;
    private double acceleration;
    private double currentAcceleration;
    private double previousAcceleration;
    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            double x = event.values[0];
            double y = event.values[1];
            double z = event.values[2];
            previousAcceleration = currentAcceleration;
            currentAcceleration = Math.sqrt(x * x + y * y + z * z);
            double delta = currentAcceleration - previousAcceleration;
            acceleration = acceleration * 0.9f + delta;
            if(acceleration > 15) {
                Toast toast = Toast.makeText(getApplication(), "Device has shaken", Toast.LENGTH_SHORT);
                toast.show();
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.yu_mo_gwai_gui_xd);
                mediaPlayer.start();
                String ans = Predictions.get().getPrediction();
                answerText.setText(ans);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        acceleration = 0.0f;
        currentAcceleration = SensorManager.GRAVITY_EARTH;
        previousAcceleration = SensorManager.GRAVITY_EARTH;
        answerText = (TextView) findViewById(R.id.answerText);
        answerText.setText(Predictions.get().getPrediction());
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorListener);
    }
}
