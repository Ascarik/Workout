package com.hfad.workout.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.hfad.workout.R;

import java.util.Locale;


public class StopwatchFragment extends Fragment implements View.OnClickListener {

    public static final String RUNNING = "running";
    public static final String WAS_RUNNING = "wasrunning";
    public static final String SECONDS = "seconds";
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        runTimer(layout);
        Button startButton = layout.findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        Button stopButton = layout.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);
        Button resetButton = layout.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);
        return layout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt(SECONDS);
            running = savedInstanceState.getBoolean(RUNNING);
            wasRunning = savedInstanceState.getBoolean(WAS_RUNNING);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
    }

    private void onClickStart() {
        running = true;
    }

    private void onClickStop() {
        running = false;
    }

    private void onClickReset() {
        running = false;
        seconds = 0;
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    private void runTimer(View layout) {
        final TextView textView = layout.findViewById(R.id.time_view);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                textView.setText(time);

                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000L);
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.start_button:
                onClickStart();
                break;
            case R.id.reset_button:
                onClickReset();
                break;
            case R.id.stop_button:
                onClickStop();
                break;
        }

    }
}