package com.example.nut.ui.home;

import androidx.fragment.app.Fragment;

import com.example.nut.database.Task;
import com.example.nut.ui.home.model.HomeRepository;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    private HomeRepository mRepository;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
        mRepository = new HomeRepository(((Fragment) view).getContext());
    }

    @Override
    public void getTodoList(Callback callback) {
        Thread thread = new Thread(() ->
                callback.onReceiveData(mRepository.getTaskList()));
        thread.start();
    }

    @Override
    public void deleteTask(Task task) {
        mRepository.deleteTask(task);
    }

    @Override
    public void addTask(Task task) {
        mRepository.addTask(task);
    }

    @Override
    public void updateTask(Task task) {
        mRepository.updateTask(task);
    }

    public interface Callback {
        void onReceiveData(List<Task> tasks);
    }
}
