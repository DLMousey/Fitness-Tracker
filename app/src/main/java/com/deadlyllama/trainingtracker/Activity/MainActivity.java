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

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SessionViewModel sessionViewModel;
    private RecyclerView recyclerView;
    private SessionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Welcome");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        adapter = new SessionAdapter(this);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sessionViewModel = ViewModelProviders.of(this).get(SessionViewModel.class);
        sessionViewModel.getAllSessions().observe(this, new Observer<List<Session>>() {
            @Override
            public void onChanged(List<Session> sessions) {
                adapter.setSessions(sessions);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
