package com.example.nut.ui.home.model;

import android.content.Context;

import androidx.room.Room;

import com.example.nut.database.RoomKt;
import com.example.nut.database.Task;
import com.example.nut.database.TaskDAO;
import com.example.nut.database.TaskDatabase;

import java.util.List;

public class HomeRepository {
    private final TaskDatabase taskDatabase;
    private final TaskDAO taskDAO;

    public HomeRepository(Context context) {
        taskDatabase = RoomKt.getDatabase(context);
        taskDAO = taskDatabase.getTaskDao();
    }

    public List<Task> getTaskList() {
        return taskDAO.getAllTasks();
    }

    public void deleteTask(Task task) {
        taskDAO.delete(task);
    }

    public void addTask(Task task) {
        taskDAO.insert(task);
    }

    public void updateTask(Task newTask) {
        taskDAO.update(newTask);
    }
}
