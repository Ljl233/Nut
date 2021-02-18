package com.example.nut.ui.home;

import com.example.nut.database.Task;

import java.util.List;

public interface HomeContract {
    interface View {

    }

    interface Presenter {
        void getTodoList(HomePresenter.Callback callback);

        void deleteTask(Task task);

        void addTask(Task task);

        void updateTask(Task task);

    }
}
