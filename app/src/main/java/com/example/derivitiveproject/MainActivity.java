package com.example.derivitiveproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series1;
    LineGraphSeries<DataPoint> series2;
    LineGraphSeries<DataPoint> series3;
    double time = -1;
    double position;
    double velocity;
    double acceleration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        series1 = new LineGraphSeries<DataPoint>();
        series2 = new LineGraphSeries<DataPoint>();
        series3 = new LineGraphSeries<DataPoint>();
        run();
    }

    public void reset(){
        time = -1;
        position = 0;
        velocity = 0;
        acceleration = 0;
    }

    public void update(){
        velocity = velocity+acceleration;
        position = position+velocity;

        Log.d("pos on update", String.valueOf(position));
        Log.d("vel on update", String.valueOf(velocity));
        Log.d("acc on update", String.valueOf(acceleration));

        GraphView graph1 = findViewById(R.id.Position);
        graph1.setTitle("Position");
        series1.appendData(new DataPoint(time,position), false, 1000000);
        graph1.addSeries(series1);


        GraphView graph2 = findViewById(R.id.Velocity);
        graph2.setTitle("Velocity");
        series2.appendData(new DataPoint(time,velocity), false, 1000000);
        graph2.addSeries(series2);

        GraphView graph3 = findViewById(R.id.Acceleration);
        graph3.setTitle("Acceleration");
        series3.appendData(new DataPoint(time,acceleration), false, 1000000);
        graph3.addSeries(series3);
    }

    public void accelerate(){
        acceleration += 0.5;
    }

    public void brake(){
        acceleration -= 0.5;
    }

    public void run() {
        reset();
        Button Accelerator = findViewById(R.id.Accelerate);
        Button Decelerator = findViewById(R.id.Deccelerate);
        Accelerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Log", "Accelerate");
                time += 1;
                accelerate();
                update();
                display();
            }
        });
        Decelerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Log", "Decelerate");
                time += 1;
                brake();
                update();
                display();
            }
        });
        }

    public void display(){
        TextView timeView = findViewById(R.id.time);
        timeView.setText("Time: " + time);

        TextView posView = findViewById(R.id.pos);
        posView.setText("Position: " + position);

        TextView velView = findViewById(R.id.vel);
        velView.setText("Velocity: " + velocity);

        TextView accView = findViewById(R.id.acc);
        accView.setText("Acceleration: " + acceleration);
    }
}