package com.deadlyllama.trainingtracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deadlyllama.trainingtracker.Entity.Movement;
import com.deadlyllama.trainingtracker.R;

import java.util.List;

public class ChestMovementAdapter extends RecyclerView.Adapter<ChestMovementAdapter.ChestMovementViewHolder> {

    private final LayoutInflater inflater;

    private ChestMovementViewHolder viewHolder;
    private List<Movement> chestMovements;

    public ChestMovementAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ChestMovementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_movement_item, parent, false);
        return new ChestMovementViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChestMovementViewHolder holder, int position) {
        viewHolder = holder;

        if (chestMovements != null) {
            Movement current = chestMovements.get(position);
            holder.nameTextView.setText(current.getName());
            holder.descriptionTextView.setText(current.getDescription());
            holder.muscleGroupTextView.setText(current.getMuscleGroup());
            int drawableRes = holder.itemView.getResources().getIdentifier(
                    current.getImagePath(),
                    "drawable",
                    holder.itemView.getContext().getPackageName()
            );

            holder.imageView.setImageResource(drawableRes);
        } else {
            // handle no movements scenario
        }
    }

    public void setChestMovements(List<Movement> items) {
        chestMovements = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (chestMovements != null) {
            return chestMovements.size();
        } else {
            return 0;
        }
    }

    public Movement getMovementAtPosition(int position) {
        return chestMovements.get(position);
    }

    class ChestMovementViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView descriptionTextView;
        private final TextView muscleGroupTextView;
        private final ImageView imageView;

        private ChestMovementViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_textView);
            descriptionTextView = itemView.findViewById(R.id.description_textView);
            muscleGroupTextView = itemView.findViewById(R.id.muscleGroup_textView);
            imageView = itemView.findViewById(R.id.movement_imageview);
        }
    }
}
