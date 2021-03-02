package com.example.nut.ui.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nut.R;
import com.example.nut.database.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.example.nut.ui.home.view.HomeFragment.*;

public class TodolistAdapter extends RecyclerView.Adapter {

    private List<Task> todayTodos = new ArrayList<>();
    private List<Task> weekTodos = new ArrayList<>();
    private List<Task> monthTodos = new ArrayList<>();
    private List<Task> yearTodos = new ArrayList<>();
    private List<Task> customTodos = new ArrayList<>();

    private LinkedList<Task> todos = new LinkedList<>();

    private final static int VIEW_TYPE_DELIVER = 0;
    private final static int VIEW_TYPE_TODO = 1;

    private int todayTodosSize;
    private int weekTodosSize;
    private int monthTodosSize;
    private int yearTodosSize;
    private int curLabel = LABEL_ALL;

    //每个Item checkbox的监听器
    private OnCheckedChangeListener mListener;

    public TodolistAdapter(List<Task> todos) {
        if (todos != null) {
            this.todos.addAll(todos);
            deliverTodos(todos);
        }
    }

    private void deliverTodos(List<Task> todos) {
        if (todos == null) return;
        for (int i = 0; i < todos.size(); i++) {
            Task e = todos.get(i);
            switch (e.getType()) {
                case "day":
                    todayTodos.add(e);
                    break;
                case "week":
                    weekTodos.add(e);
                    break;
                case "month":
                    monthTodos.add(e);
                    break;
                case "year":
                    yearTodos.add(e);
                    break;
                default:
                    customTodos.add(e);
                    break;
            }
        }
        todayTodosSize = todayTodos.size();
        weekTodosSize = weekTodos.size();
        monthTodosSize = monthTodos.size();
        yearTodosSize = yearTodos.size();
    }


    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_TODO;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_home_todo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        if (todos.isEmpty()) return;
        switch (curLabel) {
            case LABEL_ALL:
                vh.bindView(todos.get(position));
                break;
            case LABEL_DAY:
                vh.bindView(todayTodos.get(position));
                break;
            case LABEL_WEEK:
                vh.bindView(weekTodos.get(position));
                break;
            case LABEL_MONTH:
                vh.bindView(monthTodos.get(position));
                break;
            case LABEL_YEAR:
                vh.bindView(yearTodos.get(position));
                break;
            case LABEL_CUSTOM:
                vh.bindView(customTodos.get(position));
                break;
        }
    }


    @Override
    public int getItemCount() {

        switch (curLabel) {
            case LABEL_ALL:
                return todos.size();
            case LABEL_DAY:
                return todayTodosSize;
            case LABEL_WEEK:
                return weekTodosSize;
            case LABEL_MONTH:
                return monthTodosSize;
            case LABEL_YEAR:
                return yearTodosSize;
            case LABEL_CUSTOM:
                return customTodos.size();
        }

        return todos.size();
    }

    public void addTodos(List<Task> tasks) {
        todos.addAll(tasks);
        deliverTodos(todos);
        notifyDataSetChanged();
    }

    public void addTask(Task task) {
        todos.addFirst(task);
        notifyItemChanged(0);
    }

    public void setOnCheckedChangedListener(OnCheckedChangeListener listener) {
        mListener = listener;
    }

    class VH extends RecyclerView.ViewHolder {

        private TextView tvType, tvTag, tvContent, tvPredict, tvReal, tvFeel;
        private CheckBox checkBox;

        public VH(@NonNull View itemView) {
            super(itemView);

            tvType = itemView.findViewById(R.id.item_todo_type);
            tvTag = itemView.findViewById(R.id.item_todo_tag);
            tvContent = itemView.findViewById(R.id.item_todo_content);
            tvPredict = itemView.findViewById(R.id.item_todo_predict);
            tvReal = itemView.findViewById(R.id.item_todo_realtime);
            tvFeel = itemView.findViewById(R.id.item_todo_feel);
            checkBox = itemView.findViewById(R.id.item_todo_check);


        }

        private void bindView(Task data) {
            switch (data.getType()) {
                case "day":
                    tvType.setText("日");
                    break;
                case "week":
                    tvType.setText("周");
                    break;
                case "month":
                    tvType.setText("月");
                    break;
                case "year":
                    tvType.setText("年");
                    break;
                case "custom":
                    tvType.setText("自定义");
                    break;
            }
            tvTag.setText(data.getTag());
            tvContent.setText(data.getContent());
            String predict = "预计时长:" + data.getSchedule();
            tvPredict.setText(predict);
            String real = "实际时长:" + data.getActualTime();
            tvReal.setText(real);

            tvFeel.setOnClickListener(v -> {
                //todo: 跳转
            });

            if (mListener != null) {
                checkBox.setOnCheckedChangeListener((buttonView, isChecked) ->
                        mListener.onCheckedChanged(data, isChecked));
            }
        }
    }

    public void changeLabel(int targetLabel) {
        curLabel = targetLabel;
        notifyDataSetChanged();
    }
}

interface OnCheckedChangeListener {
    void onCheckedChanged(Task task, boolean isChecked);
}
