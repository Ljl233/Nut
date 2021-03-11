package com.example.nut.ui.home.model;

import android.content.Context;

import com.example.nut.database.RoomKt;
import com.example.nut.database.Task;
import com.example.nut.database.TaskDao;
import com.example.nut.database.AppDatabase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class HomeRepository {
    private final AppDatabase appDatabase;
    private final TaskDao taskDAO;

    public HomeRepository(Context context) {
        appDatabase = RoomKt.getDatabase();
        taskDAO = appDatabase.getTaskDao();
    }

    public Single<List<Task>> getTaskList() {
        return taskDAO.getAllTasks();
    }

    public Single<List<Task>> getFinishedTasks() {
        return taskDAO.getFinishedTasks();
    }

    public Single<List<Task>> getUnfinishedTasks() {
        return taskDAO.getUnFinishedTasks();
    }

    public Single<Integer> deleteTask(Task task) {
        return taskDAO.delete(task);
    }

    public Completable addTask(Task task) {
        return taskDAO.insert(task);
    }

    public Completable updateTask(Task newTask) {
        return taskDAO.update(newTask);
    }
}
