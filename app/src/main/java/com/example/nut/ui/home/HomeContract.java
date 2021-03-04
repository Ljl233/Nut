package com.example.nut.ui.home;

import com.example.nut.database.Task;

import java.util.List;

public interface HomeContract {
    interface View {

    }

    interface Presenter {
        void getTodoList(HomePresenter.Callback<List<Task>> callback);

        void deleteTask(Task task, HomePresenter.Callback<Integer> callback);

        void addTask(Task task, HomePresenter.Callback<Void> callback);

        void updateTask(Task task, HomePresenter.Callback<Void> callback);

    }
}
