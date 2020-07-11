package com.example.nut.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nut.R;
import com.example.nut.ui.home.data.TodoData;

import java.util.List;

public class TodolistAdapter extends RecyclerView.Adapter {

    private List<TodoData> todayTodos;
    private List<TodoData> weekTodos;
    private List<TodoData> monthTodos;
    private List<TodoData> yearTodos;

    private final static int VIEW_TYPE_DELIVER = 0;
    private final static int VIEW_TYPE_TODO = 1;

    private int todayTodosSize;
    private int weekTodosSize;
    private int monthTodosSize;
    private int yearTodosSize;

    public TodolistAdapter(List<List<TodoData>> todos) {
        todayTodos = todos.get(0);
        weekTodos = todos.get(1);
        monthTodos = todos.get(2);
        yearTodos = todos.get(3);

        todayTodosSize = todayTodos.size();
        weekTodosSize = weekTodos.size();
        monthTodosSize = monthTodos.size();
        yearTodosSize = yearTodos.size();
    }


    public TodolistAdapter(List<TodoData> todayTodos,
                           List<TodoData> weekTodos,
                           List<TodoData> monthTodos,
                           List<TodoData> yearTodos) {

        this.monthTodos = monthTodos;
        this.todayTodos = todayTodos;
        this.weekTodos = weekTodos;
        this.yearTodos = yearTodos;

        todayTodosSize = todayTodos.size();
        weekTodosSize = weekTodos.size();
        monthTodosSize = monthTodos.size();
        yearTodosSize = yearTodos.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_TODO) {
            return new VH(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_home_todo, parent, false));
        }

        return new DeliverVH(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_home_deliver, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof DeliverVH) {
            DeliverVH deliverVH = (DeliverVH) holder;
            if (position == 0) {
                deliverVH.deliverTime.setText("日");
            } else if (position == todayTodosSize + 1) {
                deliverVH.deliverTime.setText("周");
            } else if (position == todayTodosSize + weekTodosSize + 2) {
                deliverVH.deliverTime.setText("月");
            } else if (position == todayTodosSize + weekTodosSize + monthTodos.size() + 3) {
                deliverVH.deliverTime.setText("年");
            }
        } else {
            VH vh = (VH) holder;
            if (position > 0 && position < todayTodosSize + 1) {
                vh.bindView(todayTodos.get(position - 1));
            } else if (position > todayTodosSize + 1 && position < todayTodosSize + weekTodosSize + 2) {
                vh.bindView(weekTodos.get(position - todayTodosSize - 2));
            } else if (position > todayTodosSize + weekTodosSize + 2 && position < todayTodosSize + weekTodosSize + monthTodos.size() + 3) {
                vh.bindView(monthTodos.get(position - todayTodosSize - weekTodosSize - 3));
            } else {
                vh.bindView(yearTodos.get(position - todayTodosSize - weekTodosSize - monthTodosSize - 4));
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0
                || position == todayTodosSize + 1
                || position == todayTodosSize + weekTodosSize + 2
                || position == todayTodosSize + weekTodosSize + monthTodosSize + 3) {
            return VIEW_TYPE_DELIVER;
        }
        return VIEW_TYPE_TODO;
    }

    @Override
    public int getItemCount() {

        return todayTodosSize + weekTodosSize + monthTodosSize + yearTodosSize + 4;
    }

    class VH extends RecyclerView.ViewHolder {

        private TextView tvLabel, tvContent, tvPredict, tvReal, tvFeel;
        private CheckBox checkBox;

        public VH(@NonNull View itemView) {
            super(itemView);

            tvLabel = itemView.findViewById(R.id.item_todo_label);
            tvContent = itemView.findViewById(R.id.item_todo_content);
            tvPredict = itemView.findViewById(R.id.item_todo_predict);
            tvReal = itemView.findViewById(R.id.item_todo_realtime);
            tvFeel = itemView.findViewById(R.id.item_todo_feel);
            checkBox = itemView.findViewById(R.id.item_todo_check);

        }

        private void bindView(TodoData data) {
            tvLabel.setText(data.getLabel());
            tvContent.setText(data.getContent());
            tvPredict.setText(String.valueOf(data.getPredictTime()));
            tvReal.setText(String.valueOf(data.getRealTime()));

            tvFeel.setOnClickListener(v -> {
                //todo: 跳转
            });

        }
    }

    class DeliverVH extends RecyclerView.ViewHolder {
        private TextView deliverTime;

        public DeliverVH(@NonNull View itemView) {
            super(itemView);
            deliverTime = itemView.findViewById(R.id.item_deliver_time);
        }
    }
}
