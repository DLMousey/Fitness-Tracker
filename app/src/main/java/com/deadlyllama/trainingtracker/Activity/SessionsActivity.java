package com.deadlyllama.trainingtracker.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.deadlyllama.trainingtracker.R;
import com.deadlyllama.trainingtracker.Entity.Session;
import com.deadlyllama.trainingtracker.Adapter.SessionAdapter;
import com.deadlyllama.trainingtracker.ViewModel.SessionViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class SessionsActivity extends AppCompatActivity {

    private SessionViewModel sessionViewModel;
    private RecyclerView recyclerView;
    private SessionAdapter sessionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Sessions");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createSessionIntent = new Intent(view.getContext(), SessionCreateActivity.class);
                startActivity(createSessionIntent);
            }
        });

        sessionAdapter = new SessionAdapter(this);
        recyclerView = findViewById(R.id.sessions_recyclerview);
        recyclerView.setAdapter(sessionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sessionViewModel = ViewModelProviders.of(this).get(SessionViewModel.class);
        sessionViewModel.getAllSessions().observe(this, new Observer<List<Session>>() {
            @Override
            public void onChanged(List<Session> sessions) {
                sessionAdapter.setSessions(sessions);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_sessions:
                Intent sessionsIntent = new Intent(this, SessionsActivity.class);
                startActivity(sessionsIntent);
                break;
            case R.id.action_movements:
                Intent movementsIntent = new Intent(this, MovementsActivity.class);
                startActivity(movementsIntent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
