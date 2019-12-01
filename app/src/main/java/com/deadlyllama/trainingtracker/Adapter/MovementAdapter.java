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

public class MovementAdapter extends RecyclerView.Adapter<MovementAdapter.MovementViewHolder> {

    protected final LayoutInflater inflater;
    protected MovementViewHolder activeViewHolder;

    /** Movement Lists */
    private List<Movement> allMovements;
    private List<Movement> shoulderMovements;
    private List<Movement> armMovements;
    private List<Movement> backMovements;
    private List<Movement> coreMovements;
    private List<Movement> legMovements;

    public MovementAdapter(Context context) {
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

        if (allMovements != null) {
            Movement current = allMovements.get(position);
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
            // do stuff
        }
    }

    public void setAllMovements(List<Movement> items) {
        allMovements = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(allMovements != null) {
            return allMovements.size();
        } else {
            return 0;
        }
    }

    public Movement getMovementAtPosition(int position) {
        return allMovements.get(position);
    }

    class MovementViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView descriptionTextView;
        private final TextView muscleGroupTextView;
        private final ImageView imageView;

        private MovementViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_textView);
            descriptionTextView = itemView.findViewById(R.id.description_textView);
            muscleGroupTextView = itemView.findViewById(R.id.muscleGroup_textView);
            imageView = itemView.findViewById(R.id.movement_imageview);
        }
    }
}
