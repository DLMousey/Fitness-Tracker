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

public class LegMovementAdapter extends RecyclerView.Adapter<LegMovementAdapter.LegMovementViewHolder> {

    private final LayoutInflater inflater;

    private LegMovementViewHolder viewHolder;
    private List<Movement> legMovements;

    public LegMovementAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public LegMovementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_movement_item, parent, false);
        return new LegMovementViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LegMovementViewHolder holder, int position) {
        viewHolder = holder;

        if (legMovements != null) {
            Movement current = legMovements.get(position);
            holder.nameTextView.setText(current.getName());
            holder.descriptionTextView.setText(current.getDescription());
            holder.muscleGroupTextView.setText(current.getMuscleGroup());
        } else {
            // handle no movements scenario
        }
    }

    public void setLegMovements(List<Movement> items) {
        legMovements = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (legMovements != null) {
            return legMovements.size();
        } else {
            return 0;
        }
    }

    public Movement getMovementAtPosition(int position) {
        return legMovements.get(position);
    }

    class LegMovementViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView descriptionTextView;
        private final TextView muscleGroupTextView;

        private LegMovementViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_textView);
            descriptionTextView = itemView.findViewById(R.id.description_textView);
            muscleGroupTextView = itemView.findViewById(R.id.muscleGroup_textView);
        }
    }
}
