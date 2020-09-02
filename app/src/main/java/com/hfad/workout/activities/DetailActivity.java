package com.hfad.workout.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hfad.workout.R;
import com.hfad.workout.fragments.WorkoutDetailFragment;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        WorkoutDetailFragment frag = (WorkoutDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.detail_frag);  
        Long workoutId = (Long) getIntent().getExtras().get(EXTRA_WORKOUT_ID);
        assert frag != null;
        frag.setWorkout(workoutId);
    }
}