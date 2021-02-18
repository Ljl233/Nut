package com.example.nut.ui.home.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nut.R;
import com.example.nut.database.Task;
import com.example.nut.ui.home.HomeContract;
import com.example.nut.ui.home.HomePresenter;
import com.example.nut.ui.home.create.CreateFragment;
import com.example.nut.ui.home.model.TodoData;

import java.util.Calendar;
import java.util.List;

import javax.security.auth.callback.Callback;

public class HomeFragment extends Fragment implements HomeContract.View {

    private Toolbar toolbar;
    private RecyclerView todoListView;
    private TextView labelAll, labelDay, labelWeek, labelMonth, labelYear, labelCustom, barTitle;

    private TodolistAdapter mAdapter;

    public static final int LABEL_ALL = 0;
    public static final int LABEL_DAY = 1;
    public static final int LABEL_WEEK = 2;
    public static final int LABEL_MONTH = 3;
    public static final int LABEL_YEAR = 4;
    public static final int LABEL_CUSTOM = 5;
    private int focusedLabel = LABEL_ALL;

    private HomeContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        todoListView = root.findViewById(R.id.home_list);
        toolbar = root.findViewById(R.id.home_toolbar);
        barTitle = root.findViewById(R.id.home_actionbar_title);

        mPresenter = new HomePresenter(this);

        initToolbar();
        initLabel(root);
        initAdapter();

        return root;
    }

    private void initToolbar() {
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.calendar:
                    NavHostFragment.findNavController(this).navigate(R.id.calendar_dest);
                    break;
                case R.id.love:
                    NavHostFragment.findNavController(this).navigate(R.id.emotion_status_dest);
                    break;
                case R.id.add:
                    showDialog();
                    break;
            }
            return true;
        });

        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int data = calendar.get(Calendar.DATE);
        String title = "今天" + "（" + month + "月" + data + "日" + "）";
        barTitle.setText(title);
    }

    private void showDialog() {
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        Fragment prev = getParentFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        CreateFragment fragment = new CreateFragment();
        fragment.show(ft, "dialog");

    }

    private void initLabel(View root) {

        labelAll = root.findViewById(R.id.home_label_all);
        labelDay = root.findViewById(R.id.home_label_day);
        labelWeek = root.findViewById(R.id.home_label_week);
        labelMonth = root.findViewById(R.id.home_label_month);
        labelYear = root.findViewById(R.id.home_label_year);
        labelCustom = root.findViewById(R.id.home_label_custom);

        labelAll.setTextColor(getResources().getColor(R.color.colorTheme));

        labelAll.setOnClickListener(v -> {
            changeFocusedLabel(focusedLabel, LABEL_ALL);
        });

        labelDay.setOnClickListener(v -> {
            changeFocusedLabel(focusedLabel, LABEL_DAY);
        });

        labelWeek.setOnClickListener(v -> {
            changeFocusedLabel(focusedLabel, LABEL_WEEK);
        });

        labelMonth.setOnClickListener(v -> {
            changeFocusedLabel(focusedLabel, LABEL_MONTH);
        });

        labelYear.setOnClickListener(v -> {
            changeFocusedLabel(focusedLabel, LABEL_YEAR);
        });

        labelCustom.setOnClickListener(v -> {
            changeFocusedLabel(focusedLabel, LABEL_CUSTOM);
        });
    }

    private void changeFocusedLabel(int curLabel, int targetLabel) {
        switch (curLabel) {
            case LABEL_ALL:
                labelAll.setTextColor(Color.GRAY);
                break;
            case LABEL_DAY:
                labelDay.setTextColor(Color.GRAY);
                break;
            case LABEL_WEEK:
                labelWeek.setTextColor(Color.GRAY);
                break;
            case LABEL_MONTH:
                labelMonth.setTextColor(Color.GRAY);
                break;
            case LABEL_YEAR:
                labelYear.setTextColor(Color.GRAY);
                break;
            case LABEL_CUSTOM:
                labelCustom.setTextColor(Color.GRAY);
                break;
        }

        switch (targetLabel) {
            case LABEL_ALL:
                labelAll.setTextColor(getResources().getColor(R.color.colorTheme));
                break;
            case LABEL_DAY:
                labelDay.setTextColor(getResources().getColor(R.color.colorTheme));
                break;
            case LABEL_WEEK:
                labelWeek.setTextColor(getResources().getColor(R.color.colorTheme));
                break;
            case LABEL_MONTH:
                labelMonth.setTextColor(getResources().getColor(R.color.colorTheme));
                break;
            case LABEL_YEAR:
                labelYear.setTextColor(getResources().getColor(R.color.colorTheme));
                break;
            case LABEL_CUSTOM:
                labelCustom.setTextColor(getResources().getColor(R.color.colorTheme));
                break;
        }

        focusedLabel = targetLabel;
        mAdapter.changeLabel(targetLabel);
    }


    private void initAdapter() {
        mAdapter = new TodolistAdapter(null);
        todoListView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        todoListView.setAdapter(mAdapter);
        mPresenter.getTodoList(tasks -> mAdapter.addTodos(tasks));
    }

    void statusOnclick(View v) {
        //todo: 跳转到任务详情界面

    }

    public void commitTask(Task task) {
        mPresenter.addTask(task);
        mAdapter.addTask(task);
        Toast.makeText(this.getContext(), "commit task", Toast.LENGTH_SHORT).show();
    }

}
