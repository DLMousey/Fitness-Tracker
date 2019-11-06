package com.deadlyllama.trainingtracker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.deadlyllama.trainingtracker.Entity.Movement;
import com.deadlyllama.trainingtracker.R;
import com.deadlyllama.trainingtracker.Repository.MovementRepository;

public class MovementCreateActivity extends AppCompatActivity {

    private final MovementRepository movementRepository = new MovementRepository(getApplication());

    private EditText nameInput;
    private EditText descriptionInput;
    private EditText weightInput;
    private EditText repsInput;
    private EditText setsInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movements_create);

        nameInput = findViewById(R.id.input_movement_name);
        descriptionInput = findViewById(R.id.input_movement_description);
        weightInput = findViewById(R.id.input_weight);
        repsInput = findViewById(R.id.input_reps);
        setsInput = findViewById(R.id.input_sets);
    }

    public void saveMovement(View view) {
        String name = nameInput.getText().toString();
        String description = descriptionInput.getText().toString();
        Double weight = Double.parseDouble(weightInput.getText().toString());
        Integer reps = Integer.parseInt(repsInput.getText().toString(), 10);
        Integer sets = Integer.parseInt(setsInput.getText().toString(), 10);

        Movement movement = new Movement(name, description, reps, sets, weight, 1L);
        movementRepository.insert(movement);

        Intent intent = new Intent(this, MovementsActivity.class);
        startActivity(intent);
    }
}
