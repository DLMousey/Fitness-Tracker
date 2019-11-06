package com.deadlyllama.trainingtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.SessionViewHolder> {

    private final LayoutInflater inflater;
    private List<Session> sessions;
    private SessionViewHolder activeViewHolder;

    SessionAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SessionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new SessionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionViewHolder holder, int position) {
        activeViewHolder = holder;

        if (sessions != null) {
            Session current = sessions.get(position);
        } else {
            // do stuff
        }
    }

    void setSessions(List<Session> items) {
        sessions = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(sessions != null) {
            return sessions.size();
        } else {
            return 0;
        }
    }

    public Session getSessionAtPosition(int position) {
        return sessions.get(position);
    }

    class SessionViewHolder extends RecyclerView.ViewHolder {
        private final TextView createdAtTextView;

        private SessionViewHolder(View itemView) {
            super(itemView);
            createdAtTextView = itemView.findViewById(R.id.created_at_textView);
        }
    }
}
