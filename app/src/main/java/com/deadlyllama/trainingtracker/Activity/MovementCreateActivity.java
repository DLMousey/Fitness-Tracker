package com.deadlyllama.trainingtracker.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.deadlyllama.trainingtracker.Entity.Movement;
import com.deadlyllama.trainingtracker.Entity.Session;
import com.deadlyllama.trainingtracker.R;
import com.deadlyllama.trainingtracker.Repository.MovementRepository;
import com.deadlyllama.trainingtracker.Repository.SessionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MovementCreateActivity extends AppCompatActivity {

    private final SessionRepository sessionRepository = new SessionRepository(getApplication());
    private final MovementRepository movementRepository = new MovementRepository(getApplication());

    List<Session> sessions;

    private EditText nameInput;
    private EditText descriptionInput;
    private EditText weightInput;
    private EditText repsInput;
    private EditText setsInput;
    private Spinner sessionInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movements_create);

        nameInput = findViewById(R.id.input_movement_name);
        descriptionInput = findViewById(R.id.input_movement_description);
        weightInput = findViewById(R.id.input_weight);
        repsInput = findViewById(R.id.input_reps);
        setsInput = findViewById(R.id.input_sets);
        sessionInput = findViewById(R.id.input_session);

        try {
            sessions = sessionRepository.getAllSessionsSync();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String[] spinnerItems = new String[sessions.size()];
        HashMap<Long, String> spinnerMap = new HashMap<Long, String>();

        for (int i = 0; i < sessions.size(); i++) {
            Session currentSession = sessions.get(i);
            spinnerMap.put(
                    currentSession.getId(),
                    currentSession.getLocation() + " - " + currentSession.getCreatedAt()
            );
            spinnerItems[i] = currentSession.getLocation() + " - " + currentSession.getCreatedAt();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sessionInput.setAdapter(adapter);
    }

    public void saveMovement(View view) {
        String name = nameInput.getText().toString();
        String description = (!descriptionInput.getText().toString().equals("")) ?
                descriptionInput.getText().toString() :
                "No Description Provided";
        Double weight = Double.parseDouble(weightInput.getText().toString());
        Integer reps = Integer.parseInt(repsInput.getText().toString(), 10);
        Integer sets = Integer.parseInt(setsInput.getText().toString(), 10);
        Long sessionId = sessionInput.getSelectedItemId();

        Movement movement = new Movement(name, description, reps, sets, weight, sessionId);
        movementRepository.insert(movement);

        Intent intent = new Intent(this, MovementsActivity.class);
        startActivity(intent);
    }
}
