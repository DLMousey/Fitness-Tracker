package com.deadlyllama.trainingtracker.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.deadlyllama.trainingtracker.Entity.Movement;
import com.deadlyllama.trainingtracker.Repository.MovementRepository;

import java.util.List;

public class MovementViewModel extends AndroidViewModel {

    private MovementRepository repository;
    private LiveData<List<Movement>> allMovements;

    public MovementViewModel(Application application) {
        super(application);
        repository = new MovementRepository(application);
        allMovements = repository.getAllMovements();
    }

    public LiveData<List<Movement>> getAllMovements() {
        return allMovements;
    }

    public LiveData<List<Movement>> getMovementsByMuscleGroup(String muscleGroup) {
        return repository.getMovementsByMuscleGroup(muscleGroup);
    }

    public void insert(Movement movement) {
        repository.insert(movement);
    }

    public void delete(Movement movement) {
        repository.delete(movement);
    }
}
