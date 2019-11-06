package com.deadlyllama.trainingtracker.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.deadlyllama.trainingtracker.Entity.Movement;
import com.deadlyllama.trainingtracker.Adapter.MovementAdapter;
import com.deadlyllama.trainingtracker.ViewModel.MovementViewModel;
import com.deadlyllama.trainingtracker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

public class MovementsActivity extends AppCompatActivity {

    private MovementViewModel movementViewModel;
    private RecyclerView recyclerView;
    private MovementAdapter movementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Movements");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movements);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createMovementIntent = new Intent(view.getContext(), MovementCreateActivity.class);
                startActivity(createMovementIntent);
            }
        });

        movementAdapter = new MovementAdapter(this);
        recyclerView = findViewById(R.id.movements_recyclerview);
        recyclerView.setAdapter(movementAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movementViewModel = ViewModelProviders.of(this).get(MovementViewModel.class);
        movementViewModel.getAllMovements().observe(this, new Observer<List<Movement>>() {
            @Override
            public void onChanged(List<Movement> movements) {
                movementAdapter.setMovements(movements);
            }
        });
    }

    /**
     * Add the options menu to the top right of the activity
     * @param menu - Android studio wouldn't stop whining until i added something here
     * @return - Android studio wouldn't stop whining until i added something here
     */
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
