package com.deadlyllama.trainingtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovementAdapter extends RecyclerView.Adapter<MovementAdapter.MovementViewHolder> {

    private final LayoutInflater inflater;
    private List<Movement> movements;
    private MovementViewHolder activeViewHolder;

    MovementAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MovementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_movement_item, parent, false);
        return new MovementViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovementViewHolder holder, int position) {
        activeViewHolder = holder;

        if (movements != null) {
            Movement current = movements.get(position);
            holder.nameTextView.setText(current.getName());
            holder.descriptionTextView.setText(current.getDescription());
            holder.summaryTextView.setText(
                    current.getSets() + " x " +
                    current.getReps() + " @ " +
                    current.getWeight() + "kg");
        } else {
            // do stuff
        }
    }

    void setMovements(List<Movement> items) {
        movements = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(movements != null) {
            return movements.size();
        } else {
            return 0;
        }
    }

    public Movement getMovementAtPosition(int position) {
        return movements.get(position);
    }

    class MovementViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView descriptionTextView;
        private final TextView summaryTextView;

        private MovementViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_textView);
            descriptionTextView = itemView.findViewById(R.id.description_textView);
            summaryTextView = itemView.findViewById(R.id.summary_textView);
        }
    }
}
