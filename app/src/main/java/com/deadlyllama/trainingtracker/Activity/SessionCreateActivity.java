package com.deadlyllama.trainingtracker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.deadlyllama.trainingtracker.Entity.Session;
import com.deadlyllama.trainingtracker.R;
import com.deadlyllama.trainingtracker.Repository.SessionRepository;

import java.util.Date;

public class SessionCreateActivity extends AppCompatActivity {

    private final SessionRepository sessionRepository = new SessionRepository(getApplication());

    private EditText locationInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_create);

        locationInput = findViewById(R.id.input_location);
    }

    public void saveSession(View view) {
        String location = locationInput.getText().toString();
        Session session = new Session(location);
        sessionRepository.insert(session);

        Intent intent = new Intent(this, SessionsActivity.class);
        startActivity(intent);
    }
}
