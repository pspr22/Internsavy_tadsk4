package com.pspr.task5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView time;
    Button start,hold,stop;
    private int seconds = 0;
    private boolean running;
    private boolean was_ruuning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            was_ruuning = savedInstanceState.getBoolean("wasRunning");
        }
        running_timer();


        time = findViewById(R.id.time);
        start = findViewById(R.id.start);
        hold = findViewById(R.id.hold);
        stop = findViewById(R.id.stop);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = true;
            }
        });

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;
                seconds = 0;
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", was_ruuning);
    }

    @Override
    protected void onPause() {
        super.onPause();
        was_ruuning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(was_ruuning){
            running = true;
        }
    }




    private void running_timer(){

        final Handler handle = new Handler();

        handle.post(new Runnable() {
            @Override
            public void run() {

                int hrs = seconds/3600;
                int mins = (seconds%3600)/60;
                int secs = seconds % 60;

                String time_t = String.format(Locale.getDefault(),
                        "%d:%02d:%02d ",hrs,mins,secs);
                time.setText(time_t);
                if(running){
                    seconds++;
                }
                handle.postDelayed(this,1000);

            }
        });

    }


}