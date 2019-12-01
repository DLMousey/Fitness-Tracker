package com.deadlyllama.trainingtracker.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.deadlyllama.trainingtracker.Entity.Movement;
import com.deadlyllama.trainingtracker.Entity.Session;
import com.deadlyllama.trainingtracker.R;
import com.deadlyllama.trainingtracker.Repository.MovementRepository;
import com.deadlyllama.trainingtracker.Repository.SessionRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MovementCreateActivity extends AppCompatActivity {

    private final SessionRepository sessionRepository = new SessionRepository(getApplication());
    private final MovementRepository movementRepository = new MovementRepository(getApplication());

    List<Session> sessions;

    private EditText nameInput;
    private EditText descriptionInput;
    private Spinner muscleGroupInput;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movements_create);

        nameInput = findViewById(R.id.input_movement_name);
        descriptionInput = findViewById(R.id.input_movement_description);
        muscleGroupInput = findViewById(R.id.input_movement_muscle_group);
        imageView = findViewById(R.id.movement_imageview);

        try {
            sessions = sessionRepository.getAllSessionsSync();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.muscle_group_array,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        muscleGroupInput.setAdapter(adapter);
    }

    public void saveMovement(View view) {
        String name = nameInput.getText().toString();
        String description = (!descriptionInput.getText().toString().equals("")) ?
                descriptionInput.getText().toString() :
                "No Description Provided";
        String muscleGroup = muscleGroupInput.getSelectedItem().toString();
        String imagePath = "android.resource://com.deadlyllama.trainingtracker/" + R.drawable.deadlift;

        Movement movement = new Movement(name, description, muscleGroup, imagePath);
        movementRepository.insert(movement);

        Intent intent = new Intent(this, MovementsActivity.class);
        startActivity(intent);
    }
}
