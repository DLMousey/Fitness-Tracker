package com.deadlyllama.trainingtracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deadlyllama.trainingtracker.Entity.Movement;
import com.deadlyllama.trainingtracker.R;

import java.util.List;

public class ShoulderMovementAdapter extends RecyclerView.Adapter<ShoulderMovementAdapter.ShoulderMovementViewHolder> {

    private final LayoutInflater inflater;

    private ShoulderMovementViewHolder viewHolder;
    private List<Movement> shoulderMovements;

    public ShoulderMovementAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ShoulderMovementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_movement_item, parent, false);
        return new ShoulderMovementViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoulderMovementViewHolder holder, int position) {
        viewHolder = holder;

        if (shoulderMovements != null) {
            Movement current = shoulderMovements.get(position);
            holder.nameTextView.setText(current.getName());
            holder.descriptionTextView.setText(current.getDescription());
            holder.muscleGroupTextView.setText(current.getMuscleGroup());
        } else {
            // handle no movements scenario
        }
    }

    public void setShoulderMovements(List<Movement> items) {
        shoulderMovements = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (shoulderMovements != null) {
            return shoulderMovements.size();
        } else {
            return 0;
        }
    }

    public Movement getMovementAtPosition(int position) {
        return shoulderMovements.get(position);
    }

    class ShoulderMovementViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView descriptionTextView;
        private final TextView muscleGroupTextView;

        private ShoulderMovementViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_textView);
            descriptionTextView = itemView.findViewById(R.id.description_textView);
            muscleGroupTextView = itemView.findViewById(R.id.muscleGroup_textView);
        }
    }
}
