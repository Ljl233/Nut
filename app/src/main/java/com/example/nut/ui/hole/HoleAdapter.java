package com.example.nut.ui.hole;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nut.R;
import com.example.nut.database.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HoleAdapter extends RecyclerView.Adapter<HoleAdapter.VH> {

    private List<Task> tasks;

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hole, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        private TextView date, content, feeling, emotion;

        public VH(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.item_hole_date);
            content = itemView.findViewById(R.id.item_hole_content);
            feeling = itemView.findViewById(R.id.item_hole_feeling);
            emotion = itemView.findViewById(R.id.item_hole_emotion);
        }

        private void bind(Task task) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
            date.setText(simpleDateFormat.format(new Date(System.currentTimeMillis())));

            content.setText(task.getContent());
            feeling.setText(task.getFeeling());
            emotion.setText(task.getEmotion());
        }
    }
}
