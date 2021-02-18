package com.example.nut.ui.home.model;

import java.util.ArrayList;
import java.util.List;

public class TodoData {
    private int id;
    private String label;
    private String content;
    //单位 min
    // 预估时长，实际时长
    private int predictTime;
    private int realTime;
    private boolean isFinish;

    // 日，周，月，年
    private String type;

    public TodoData() {
    }

    public TodoData(int id, String label, String content, int predictTime, int realTime, boolean isFinish) {
        this.id = id;
        this.label = label;
        this.content = content;
        this.predictTime = predictTime;
        this.realTime = realTime;
        this.isFinish = isFinish;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPredictTime() {
        return predictTime;
    }

    public void setPredictTime(int predictTime) {
        this.predictTime = predictTime;
    }

    public int getRealTime() {
        return realTime;
    }

    public void setRealTime(int realTime) {
        this.realTime = realTime;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public static List<List<TodoData>> getFakeData() {
        List<TodoData> todoDataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TodoData todoData = new TodoData(i, "学习", "完成坚果todo界面", 30, 50, false);
            todoDataList.add(todoData);
        }
        List<List<TodoData>> res = new ArrayList<>();
        res.add(todoDataList);
        res.add(todoDataList);
        res.add(todoDataList);
        res.add(todoDataList);
        return res;
    }
}
