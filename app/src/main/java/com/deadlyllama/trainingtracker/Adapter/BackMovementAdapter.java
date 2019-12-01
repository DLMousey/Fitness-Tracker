package com.deadlyllama.trainingtracker.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deadlyllama.trainingtracker.Entity.Movement;
import com.deadlyllama.trainingtracker.R;

import java.util.List;

public class BackMovementAdapter extends RecyclerView.Adapter<BackMovementAdapter.BackMovementViewHolder> {

    private final LayoutInflater inflater;

    private BackMovementViewHolder viewHolder;
    private List<Movement> backMovements;

    public BackMovementAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BackMovementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_movement_item, parent, false);
        return new BackMovementViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BackMovementViewHolder holder, int position) {
        viewHolder = holder;

        if (backMovements != null) {
            Movement current = backMovements.get(position);
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

    public void setBackMovements(List<Movement> items) {
        backMovements = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (backMovements != null) {
            return backMovements.size();
        } else {
            return 0;
        }
    }

    public Movement getMovementAtPosition(int position) {
        return backMovements.get(position);
    }

    class BackMovementViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView descriptionTextView;
        private final TextView muscleGroupTextView;
        private final LinearLayout movementLinearLayout;
        private final ImageView imageView;

        private BackMovementViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_textView);
            descriptionTextView = itemView.findViewById(R.id.description_textView);
            muscleGroupTextView = itemView.findViewById(R.id.muscleGroup_textView);
            movementLinearLayout = itemView.findViewById(R.id.movement_linearLayout);
            imageView = itemView.findViewById(R.id.movement_imageview);
        }
    }
}
