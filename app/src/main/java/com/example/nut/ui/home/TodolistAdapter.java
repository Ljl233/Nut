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

import java.util.ArrayList;
import java.util.List;

import static com.example.nut.ui.home.HomeFragment.*;

public class TodolistAdapter extends RecyclerView.Adapter {

    private List<TodoData> todayTodos;
    private List<TodoData> weekTodos;
    private List<TodoData> monthTodos;
    private List<TodoData> yearTodos;

    private List<List<TodoData>> todos;


    private final static int VIEW_TYPE_DELIVER = 0;
    private final static int VIEW_TYPE_TODO = 1;

    private int todayTodosSize;
    private int weekTodosSize;
    private int monthTodosSize;
    private int yearTodosSize;
    private int curLabel = LABEL_ALL;

    public TodolistAdapter(List<List<TodoData>> todos) {
        this.todos = todos;

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

        todos = new ArrayList<>();
        todos.add(todayTodos);
        todos.add(weekTodos);
        todos.add(monthTodos);
        todos.add(yearTodos);
    }

    @Override
    public int getItemViewType(int position) {
        if (curLabel == LABEL_ALL) {
            if (position == 0
                    || position == todayTodosSize + 1
                    || position == todayTodosSize + weekTodosSize + 2
                    || position == todayTodosSize + weekTodosSize + monthTodosSize + 3) {
                return VIEW_TYPE_DELIVER;
            }
            return VIEW_TYPE_TODO;
        }

        if (position == 0)
            return VIEW_TYPE_DELIVER;
        return VIEW_TYPE_TODO;
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
            if (curLabel == LABEL_ALL) {
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
                deliverVH.bindView(curLabel);
            }
        } else {
            VH vh = (VH) holder;
            switch (curLabel) {
                case LABEL_ALL:
                    if (position > 0 && position < todayTodosSize + 1) {
                        vh.bindView(todayTodos.get(position - 1));
                    } else if (position > todayTodosSize + 1 && position < todayTodosSize + weekTodosSize + 2) {
                        vh.bindView(weekTodos.get(position - todayTodosSize - 2));
                    } else if (position > todayTodosSize + weekTodosSize + 2 && position < todayTodosSize + weekTodosSize + monthTodos.size() + 3) {
                        vh.bindView(monthTodos.get(position - todayTodosSize - weekTodosSize - 3));
                    } else {
                        vh.bindView(yearTodos.get(position - todayTodosSize - weekTodosSize - monthTodosSize - 4));
                    }
                    break;
                case LABEL_DAY:
                    vh.bindView(todayTodos.get(position - 1));
                    break;
                case LABEL_WEEK:
                    vh.bindView(weekTodos.get(position - 1));
                    break;
                case LABEL_MONTH:
                    vh.bindView(monthTodos.get(position - 1));
                    break;
                case LABEL_YEAR:
                    vh.bindView(yearTodos.get(position - 1));
                    break;
            }
        }
    }


    @Override
    public int getItemCount() {

        switch (curLabel) {
            case LABEL_ALL:
                return todayTodosSize + weekTodosSize + monthTodosSize + yearTodosSize + 4;
            case LABEL_DAY:
                return todayTodosSize + 1;
            case LABEL_WEEK:
                return weekTodosSize + 1;
            case LABEL_MONTH:
                return monthTodosSize + 1;
            case LABEL_YEAR:
                return yearTodosSize + 1;
        }

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
            String predict= "预计时长:" + data.getPredictTime();
            tvPredict.setText(predict);
            String real = "实际时长:" + data.getRealTime();
            tvReal.setText(real);

            tvFeel.setOnClickListener(v -> {
                //todo: 跳转
            });

        }
    }

    class DeliverVH extends RecyclerView.ViewHolder {
        private TextView deliverTime;
        private int label;

        public DeliverVH(@NonNull View itemView) {
            super(itemView);
            deliverTime = itemView.findViewById(R.id.item_deliver_time);
        }

        void bindView() {
            switch (label) {
                case LABEL_DAY:
                    deliverTime.setText("日");
                    break;
                case LABEL_WEEK:
                    deliverTime.setText("周");
                    break;
                case LABEL_MONTH:
                    deliverTime.setText("月");
                    break;
                case LABEL_YEAR:
                    deliverTime.setText("年");
                    break;
            }
        }

        void bindView(int label) {
            switch (label) {
                case LABEL_DAY:
                    deliverTime.setText("日");
                    break;
                case LABEL_WEEK:
                    deliverTime.setText("周");
                    break;
                case LABEL_MONTH:
                    deliverTime.setText("月");
                    break;
                case LABEL_YEAR:
                    deliverTime.setText("年");
                    break;
            }
        }


    }

    public void changeLabel(int targetLabel) {
        curLabel = targetLabel;
/*
        todayTodos.clear();
        weekTodos.clear();
        monthTodos.clear();
        yearTodos.clear();
        List<TodoData> dullList = new ArrayList<>();

        switch (targetLabel) {
            case LABEL_ALL:
                todayTodos.addAll(todos.get(0));
                weekTodos.addAll(todos.get(1));
                monthTodos.addAll(todos.get(2));
                yearTodos.addAll(todos.get(3));
                break;
            case LABEL_DAY:
                todayTodos.addAll(todos.get(0));
                weekTodos.addAll(dullList);
                monthTodos.addAll(dullList);
                yearTodos.addAll(dullList);
                break;
            case LABEL_WEEK:
                todayTodos.addAll(dullList);
                weekTodos.addAll(todos.get(1));
                monthTodos.addAll(dullList);
                yearTodos.addAll(dullList);
                break;
            case LABEL_MONTH:
                todayTodos.addAll(dullList);
                weekTodos.addAll(dullList);
                monthTodos.addAll(todos.get(2));
                yearTodos.addAll(dullList);
                break;
            case LABEL_YEAR:
                todayTodos.addAll(dullList);
                weekTodos.addAll(dullList);
                monthTodos.addAll(dullList);
                yearTodos.addAll(todos.get(3));
                break;
        }
 */
        notifyDataSetChanged();
    }
}
