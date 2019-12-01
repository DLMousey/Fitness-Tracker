package com.deadlyllama.trainingtracker.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.deadlyllama.trainingtracker.Adapter.ArmMovementAdapter;
import com.deadlyllama.trainingtracker.Adapter.BackMovementAdapter;
import com.deadlyllama.trainingtracker.Adapter.ChestMovementAdapter;
import com.deadlyllama.trainingtracker.Adapter.CoreMovementAdapter;
import com.deadlyllama.trainingtracker.Adapter.LegMovementAdapter;
import com.deadlyllama.trainingtracker.Adapter.ShoulderMovementAdapter;
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
    private MovementAdapter movementAdapter;

    /** Muscle group recyclerviews */
    private RecyclerView shoulderMovementsRecyclerView;
    private RecyclerView armMovementsRecyclerView;
    private RecyclerView chestMovementsRecyclerView;
    private RecyclerView backMovementsRecyclerView;
    private RecyclerView coreMovementsRecyclerView;
    private RecyclerView legMovementsRecyclerView;

    /** Muscle group viewmodels */
    private MovementViewModel shoulderMovementsViewModel;
    private MovementViewModel armMovementsViewModel;
    private MovementViewModel chestMovementsViewModel;
    private MovementViewModel backMovementsViewModel;
    private MovementViewModel coreMovementsViewModel;
    private MovementViewModel legMovementsViewModel;

    /** Muscle group adapters */
    private ShoulderMovementAdapter shoulderMovementsAdapter;
    private ArmMovementAdapter armMovementsAdapter;
    private ChestMovementAdapter chestMovementsAdapter;
    private BackMovementAdapter backMovementsAdapter;
    private CoreMovementAdapter coreMovementsAdapter;
    private LegMovementAdapter legMovementsAdapter;

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

        shoulderMovementsAdapter = new ShoulderMovementAdapter(this);
        armMovementsAdapter = new ArmMovementAdapter(this);
        chestMovementsAdapter = new ChestMovementAdapter(this);
        backMovementsAdapter = new BackMovementAdapter(this);
        coreMovementsAdapter = new CoreMovementAdapter(this);
        legMovementsAdapter = new LegMovementAdapter(this);

        shoulderMovementsRecyclerView = findViewById(R.id.movements_shoulders_recyclerview);
        armMovementsRecyclerView = findViewById(R.id.movements_arms_recyclerview);
        chestMovementsRecyclerView = findViewById(R.id.movements_chest_recyclerview);
        backMovementsRecyclerView = findViewById(R.id.movements_back_recyclerview);
        coreMovementsRecyclerView = findViewById(R.id.movements_core_recyclerview);
        legMovementsRecyclerView = findViewById(R.id.movements_legs_recyclerview);

        shoulderMovementsRecyclerView.setAdapter(shoulderMovementsAdapter);
        armMovementsRecyclerView.setAdapter(armMovementsAdapter);
        chestMovementsRecyclerView.setAdapter(chestMovementsAdapter);
        backMovementsRecyclerView.setAdapter(backMovementsAdapter);
        coreMovementsRecyclerView.setAdapter(coreMovementsAdapter);
        legMovementsRecyclerView.setAdapter(legMovementsAdapter);

        shoulderMovementsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        armMovementsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        chestMovementsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        backMovementsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        coreMovementsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        legMovementsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        shoulderMovementsViewModel = ViewModelProviders.of(this).get(MovementViewModel.class);
        armMovementsViewModel = ViewModelProviders.of(this).get(MovementViewModel.class);
        chestMovementsViewModel = ViewModelProviders.of(this).get(MovementViewModel.class);
        backMovementsViewModel = ViewModelProviders.of(this).get(MovementViewModel.class);
        coreMovementsViewModel = ViewModelProviders.of(this).get(MovementViewModel.class);
        legMovementsViewModel = ViewModelProviders.of(this).get(MovementViewModel.class);

        shoulderMovementsViewModel.getMovementsByMuscleGroup("shoulders").observe(this, new Observer<List<Movement>>() {
            @Override
            public void onChanged(List<Movement> movements) {
                shoulderMovementsAdapter.setShoulderMovements(movements);
            }
        });

        armMovementsViewModel.getMovementsByMuscleGroup("arms").observe(this, new Observer<List<Movement>>() {
            @Override
            public void onChanged(List<Movement> movements) {
                armMovementsAdapter.setArmMovements(movements);
            }
        });

        chestMovementsViewModel.getMovementsByMuscleGroup("chest").observe(this, new Observer<List<Movement>>() {
            @Override
            public void onChanged(List<Movement> movements) {
                chestMovementsAdapter.setChestMovements(movements);
            }
        });

        backMovementsViewModel.getMovementsByMuscleGroup("back").observe(this, new Observer<List<Movement>>() {
            @Override
            public void onChanged(List<Movement> movements) {
                backMovementsAdapter.setBackMovements(movements);
            }
        });

        coreMovementsViewModel.getMovementsByMuscleGroup("core").observe(this, new Observer<List<Movement>>() {
            @Override
            public void onChanged(List<Movement> movements) {
                coreMovementsAdapter.setCoreMovements(movements);
            }
        });

        legMovementsViewModel.getMovementsByMuscleGroup("legs").observe(this, new Observer<List<Movement>>() {
            @Override
            public void onChanged(List<Movement> movements) {
                legMovementsAdapter.setLegMovements(movements);
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
