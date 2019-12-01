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

public class ArmMovementAdapter extends RecyclerView.Adapter<ArmMovementAdapter.ArmMovementViewHolder> {

    private final LayoutInflater inflater;

    private ArmMovementViewHolder viewHolder;
    private List<Movement> armMovements;

    public ArmMovementAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ArmMovementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_movement_item, parent, false);
        return new ArmMovementViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArmMovementViewHolder holder, int position) {
        viewHolder = holder;

        if (armMovements != null) {
            Movement current = armMovements.get(position);
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

    public void setArmMovements(List<Movement> items) {
        armMovements = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (armMovements != null) {
            return armMovements.size();
        } else {
            return 0;
        }
    }

    public Movement getMovementAtPosition(int position) {
        return armMovements.get(position);
    }

    class ArmMovementViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView descriptionTextView;
        private final TextView muscleGroupTextView;
        private final ImageView imageView;

        private ArmMovementViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_textView);
            descriptionTextView = itemView.findViewById(R.id.description_textView);
            muscleGroupTextView = itemView.findViewById(R.id.muscleGroup_textView);
            imageView = itemView.findViewById(R.id.movement_imageview);
        }
    }
}
